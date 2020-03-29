package com.yiie.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiie.common.mapper.DeptMapper;
import com.yiie.common.mapper.LoginLogMapper;
import com.yiie.common.mapper.UserMapper;
import com.yiie.common.service.*;
import com.yiie.constant.Constant;
import com.yiie.entity.*;
import com.yiie.enums.BaseResponseCode;
import com.yiie.exceptions.BusinessException;
import com.yiie.utils.*;
import com.yiie.vo.request.*;
import com.yiie.vo.response.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Time：2020-1-1 17:51
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private int LOGIN_FAIL_CNT = 10;
    private int LOGIN_FAIL_TIME = 60;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private TokenSettings tokenSettings;

    @Override
    public LoginRespVO login(LoginReqVO vo) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        LoginLog loginLog = new LoginLog();
        String ip = IPUtil.getIpAddr(request);
        // 判断该用户是否被锁
        Map<String, Object> retMap = new HashMap<String, Object>();
        boolean isLock = redisService.isLoginLock(ip);
        System.out.println("======================================用户被锁：" + isLock);
        if(isLock) {
            loginLog.setStatus(0);
            loginLog.setMsg("IP被锁定");
            loginLog.setCode(BaseResponseCode.IP_LOGIN_FAILCOUNT_BIG.getCode());
            saveloginLog(loginLog);
            throw new BusinessException(BaseResponseCode.IP_LOGIN_FAILCOUNT_BIG);
        }
        if(ip != null){
            // 某时间间隔内ip错误次数
            Long retriesLockNum = redisService.getLoginRetriesLockNum(ip);
            System.out.println("==================================" + ip + " 失败次数 " + retriesLockNum);
//            if(retriesLockNum != null && retriesLockNum < LOGIN_FAIL_CNT && retriesLockNum > 0) {
//                System.out.println("==================================" + ip + " 可以正常登录 ，删除了redis记录");
//                //登录成功后删除redis记录
//                redisService.delete(ip);
//            } else
                if(retriesLockNum >= LOGIN_FAIL_CNT) {// 处理并发
                isLock = redisService.isLoginLock(ip);
                if (!isLock) {
                    redisService.loginLock(ip, 60 * LOGIN_FAIL_TIME);
                }
                loginLog.setStatus(0);
                loginLog.setMsg("IP被锁定");
                loginLog.setCode(BaseResponseCode.IP_LOGIN_FAILCOUNT_BIG.getCode());
                saveloginLog(loginLog);
                throw new BusinessException(BaseResponseCode.IP_LOGIN_FAILCOUNT_BIG);
            }
        }
        Long retriesLockNum = redisService.setloginRetriesLockNum(ip, 60);
        if(retriesLockNum != null && retriesLockNum >= LOGIN_FAIL_CNT) {
            isLock = redisService.loginLock(ip, 60*LOGIN_FAIL_TIME);
            loginLog.setStatus(0);
            loginLog.setMsg("IP被锁定");
            loginLog.setCode(BaseResponseCode.IP_LOGIN_FAILCOUNT_BIG.getCode());
            saveloginLog(loginLog);
            throw new BusinessException(BaseResponseCode.IP_LOGIN_FAILCOUNT_BIG);
        }
        loginLog.setUsername(vo.getUsername());
        loginLog.setLoginTime(new Date());
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(IPUtil.getIpAddr(request)));
        loginLog.setSystemBrowserInfo();
        loginLog.setStatus(1);
        User user = userMapper.getUserInfoByName(vo.getUsername());
        if(null == user){
            loginLog.setStatus(0);
            loginLog.setMsg("用户不存在");
            loginLog.setCode(BaseResponseCode.NOT_ACCOUNT.getCode());
            saveloginLog(loginLog);
            throw new BusinessException(BaseResponseCode.NOT_ACCOUNT);
        }
        if(2 == user.getStatus()){
            loginLog.setStatus(0);
            loginLog.setMsg("用户锁定");
            loginLog.setCode(BaseResponseCode.USER_LOCK.getCode());
            saveloginLog(loginLog);
            throw new BusinessException(BaseResponseCode.USER_LOCK);
        }
        if(!PasswordUtil.matches(user.getSalt(),vo.getPassword(),user.getPassword())){
            loginLog.setStatus(0);
            loginLog.setMsg("密码错误");
            redisService.incrby(ip,0L);
            loginLog.setCode(BaseResponseCode.PASSWORD_ERROR.getCode());
            saveloginLog(loginLog);
            throw new BusinessException(BaseResponseCode.PASSWORD_ERROR);
        }
        LoginRespVO respVO=new LoginRespVO();
        BeanUtils.copyProperties(user,respVO);
        Map<String,Object> claims=new HashMap<>();
        claims.put(Constant.JWT_PERMISSIONS_KEY,getPermissionsByUserId(user.getId()));
        claims.put(Constant.JWT_ROLES_KEY,getRolesByUserId(user.getId()));
        claims.put(Constant.JWT_USER_NAME,user.getUsername());
        String access_token= JwtTokenUtil.getAccessToken(user.getId(),claims);
        String refresh_token;
        if(vo.getType().equals("1")){
            refresh_token=JwtTokenUtil.getRefreshToken(user.getId(),claims);
        }else {
            refresh_token=JwtTokenUtil.getRefreshAppToken(user.getId(),claims);
        }
        respVO.setAccessToken(access_token);
        respVO.setRefreshToken(refresh_token);
        loginLog.setCode(0);
        loginLog.setMsg("登录成功");
        loginLog.setStatus(1);
        redisService.delete(ip);
        saveloginLog(loginLog);
        return respVO;
    }

    public void saveloginLog(LoginLog log){
        loginLogMapper.saveLoginLog(log);
    }
    @Override
    public void logout(String accessToken, String refreshToken) {
        if(StringUtils.isEmpty(accessToken)||StringUtils.isEmpty(refreshToken)){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        log.info("subject.getPrincipals()={}",subject.getPrincipals());
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        String userId=JwtTokenUtil.getUserId(accessToken);

        redisService.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+accessToken,userId,JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);

        redisService.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+refreshToken,userId,JwtTokenUtil.getRemainingTime(refreshToken),TimeUnit.MILLISECONDS);


        redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
    }

    @Override
    public User detailInfo(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public HomeRespVO getHomeInfo(String userId) {
        User user=detailInfo(userId);
        UserInfoRespVO vo=new UserInfoRespVO();

        if(user!=null){
            BeanUtils.copyProperties(user, vo);
            Dept dept = deptService.detailInfo(user.getDeptId());
            if(dept!=null){
                vo.setDeptId(dept.getId());
                vo.setDeptName(dept.getName());
            }
        }
        List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);

        HomeRespVO respVO=new HomeRespVO();
        respVO.setMenus(menus);
        respVO.setUserInfo(vo);

        return respVO;
    }

    @Override
    public String refreshToken(String refreshToken, String accessToken) {
        if(redisService.hasKey(Constant.JWT_ACCESS_TOKEN_BLACKLIST+refreshToken)||!JwtTokenUtil.validateToken(refreshToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        String userId=JwtTokenUtil.getUserId(refreshToken);
        log.info("userId={}",userId);
        User sysUser=userMapper.selectByPrimaryKey(userId);
        if (null==sysUser){
            throw new BusinessException(BaseResponseCode.TOKEN_PARSE_ERROR);
        }
        Map<String,Object> claims=null;

        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)){
            claims=new HashMap<>();
            claims.put(Constant.JWT_ROLES_KEY,getRolesByUserId(userId));
            claims.put(Constant.JWT_PERMISSIONS_KEY,getPermissionsByUserId(userId));
        }
        String newAccessToken=JwtTokenUtil.refreshToken(refreshToken,claims);

        redisService.setifAbsen(Constant.JWT_REFRESH_STATUS+accessToken,userId,1,TimeUnit.MINUTES);

        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)){
            redisService.set(Constant.JWT_REFRESH_IDENTIFICATION+newAccessToken,userId,redisService.getExpire(Constant.JWT_REFRESH_KEY+userId,TimeUnit.MILLISECONDS),TimeUnit.MILLISECONDS);
        }
        return newAccessToken;
    }

    @Override
    public PageVO<User> pageInfo(UserPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<User> sysUsers = userMapper.selectAll(vo);
        if(!sysUsers.isEmpty()){
            for (User sysUser:sysUsers){
                Dept sysDept = deptMapper.selectByPrimaryKey(sysUser.getDeptId());
                if (sysDept!=null){
                    sysUser.setDeptName(sysDept.getName());
                }
            }
        }
        return PageUtils.getPageVO(sysUsers);
    }

    @Override
    public void updatePwd(UpdatePasswordReqVO vo,String userId,String accessToken, String refreshToken) {

        User sysUser=userMapper.selectByPrimaryKey(userId);
        if(sysUser==null){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        if(!PasswordUtil.matches(sysUser.getSalt(),vo.getOldPwd(),sysUser.getPassword())){
            throw new BusinessException(BaseResponseCode.OLD_PASSWORD_ERROR);
        }
        sysUser.setUpdateTime(new Date());
        sysUser.setPassword(PasswordUtil.encode(vo.getNewPwd(),sysUser.getSalt()));
        int i = userMapper.updateByPrimaryKeySelective(sysUser);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }

        redisService.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+accessToken,userId,JwtTokenUtil.getRemainingTime(accessToken),TimeUnit.MILLISECONDS);

        redisService.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+refreshToken,userId,JwtTokenUtil.getRemainingTime(refreshToken),TimeUnit.MILLISECONDS);


        redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);

    }

    @Override
    public void addUser(UserAddReqVO vo) {
        User sysUser=new User();
        BeanUtils.copyProperties(vo,sysUser);
        sysUser.setSalt(PasswordUtil.getSalt());
        String encode = PasswordUtil.encode(vo.getPassword(), sysUser.getSalt());
        sysUser.setPassword(encode);
        sysUser.setEmail(vo.getEmail());
        sysUser.setRealName(vo.getRealName());
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setCreateTime(new Date());
        sysUser.setSex(vo.getSex());
        int i = userMapper.insertSelective(sysUser);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        if(null!=vo.getRoleIds()&&!vo.getRoleIds().isEmpty()){
            UserRoleOperationReqVO reqVO=new UserRoleOperationReqVO();
            reqVO.setUserId(sysUser.getId());
            reqVO.setRoleIds(vo.getRoleIds());
            userRoleService.addUserRoleInfo(reqVO);
        }
    }

    @Override
    public void updateUserInfo(UserUpdateReqVO vo, String operationId) {

        User sysUser=userMapper.selectByPrimaryKey(vo.getId());
        if (null==sysUser){
            log.error("传入 的 id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        if(operationId.equals(vo.getId()) && !operationId.equals("fcf34b56-a7a2-4719-9236-867495e74c31")){
            throw new BusinessException(BaseResponseCode.OPERATION_MYSELF);
        }
        if(!operationId.equals("fcf34b56-a7a2-4719-9236-867495e74c31") && vo.getId().equals("fcf34b56-a7a2-4719-9236-867495e74c31")){
            throw new BusinessException(BaseResponseCode.OPERATION_ADMIN);
        }
        BeanUtils.copyProperties(vo,sysUser);
        sysUser.setUpdateTime(new Date());
        if(!StringUtils.isEmpty(vo.getPassword())){
            String newPassword=PasswordUtil.encode(vo.getPassword(),sysUser.getSalt());
            sysUser.setPassword(newPassword);
        }else {
            sysUser.setPassword(null);
        }
        if(vo.getSex() == null || vo.getSex().equals("")){
            sysUser.setSex(userMapper.getUserInfoByName(vo.getUsername()).getSex());
        }else {
            sysUser.setSex(vo.getSex());
        }
        sysUser.setUpdateId(operationId);
        int count= userMapper.updateByPrimaryKeySelective(sysUser);
        if (count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        if(vo.getRoleIds() == null){
            vo.setRoleIds(userRoleService.getRoleIdsByUserId(vo.getId()));
        }
        setUserOwnRole(sysUser.getId(),vo.getRoleIds(),null);
        if(vo.getStatus()==2){
            redisService.set(Constant.ACCOUNT_LOCK_KEY+sysUser.getId(),sysUser.getId());
        }else {
            redisService.delete(Constant.ACCOUNT_LOCK_KEY+sysUser.getId());
        }
    }

    @Override
    public void setUserOwnRole(String userId, List<String> roleIds,HttpServletRequest request) {
        if(request != null){
            String operationId=JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
            if(operationId.equals(userId) && !operationId.equals("fcf34b56-a7a2-4719-9236-867495e74c31")){
                throw new BusinessException(BaseResponseCode.OPERATION_MYSELF);
            }
            if(!operationId.equals("fcf34b56-a7a2-4719-9236-867495e74c31") && userId.equals("fcf34b56-a7a2-4719-9236-867495e74c31")){
                throw new BusinessException(BaseResponseCode.OPERATION_ADMIN);
            }
        }

        userRoleService.removeByUserId(userId);

        if(roleIds!=null && !roleIds.isEmpty()){
            UserRoleOperationReqVO reqVO=new UserRoleOperationReqVO();
            reqVO.setUserId(userId);
            reqVO.setRoleIds(roleIds);
            userRoleService.addUserRoleInfo(reqVO);
        }
        redisService.set(Constant.JWT_REFRESH_KEY+userId,userId,tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
        //清空权鉴缓存
        redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletedUsers(List<String> userIds, String operationId) {
        if(userIds.contains(operationId)){
            throw new BusinessException(BaseResponseCode.DELETE_CONTAINS_MYSELF);
        }
        if(userIds.contains("fcf34b56-a7a2-4719-9236-867495e74c31")){
            throw new BusinessException(BaseResponseCode.OPERATION_ADMIN);
        }
        User sysUser=new User();
        sysUser.setUpdateId(operationId);
        sysUser.setUpdateTime(new Date());
        sysUser.setDeleted(0);
        int i = userMapper.deletedUsers(sysUser,userIds);
        if (i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        for (String userId:userIds){
            redisService.set(Constant.DELETED_USER_KEY+userId,userId,tokenSettings.getRefreshTokenExpireAppTime().toMillis(),TimeUnit.MILLISECONDS);
            //清空权鉴缓存
            redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
        }
    }

    @Override
    public UserOwnRoleRespVO getUserOwnRole(String userId,HttpServletRequest request) {
        String Id= JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        if(Id.equals(userId) && !Id.equals("fcf34b56-a7a2-4719-9236-867495e74c31")){
            throw new BusinessException(BaseResponseCode.OPERATION_MYSELF);
        }
        List<String> roleIdsByUserId = userRoleService.getRoleIdsByUserId(userId);
        List<Role> list = roleService.selectAllRoles();
        UserOwnRoleRespVO vo=new UserOwnRoleRespVO();
        vo.setAllRole(list);
        vo.setOwnRoles(roleIdsByUserId);
        return vo;
    }

    @Override
    public List<User> getUserListByDeptIds(List<String> deptIds) {
        return userMapper.selectUserInfoByDeptIds(deptIds);
    }

    @Override
    public PageVO<User> selectUserInfoByDeptIds(int pageNum, int pageSize, List<String> deptIds) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list=userMapper.selectUserInfoByDeptIds(deptIds);
        return PageUtils.getPageVO(list);
    }

    private List<String> getRolesByUserId(String userId){
        return  roleService.getRoleNames(userId);
    }

    private Set<String> getPermissionsByUserId(String userId){
        return  permissionService.getPermissionsByUserId(userId);
    }

}
