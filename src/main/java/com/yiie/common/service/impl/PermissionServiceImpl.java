package com.yiie.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiie.common.mapper.PermissionMapper;
import com.yiie.common.service.PermissionService;
import com.yiie.common.service.RedisService;
import com.yiie.common.service.RolePermissionService;
import com.yiie.common.service.UserRoleService;
import com.yiie.constant.Constant;
import com.yiie.entity.Permission;
import com.yiie.enums.BaseResponseCode;
import com.yiie.exceptions.BusinessException;
import com.yiie.utils.PageUtils;
import com.yiie.utils.TokenSettings;
import com.yiie.vo.request.PermissionAddReqVO;
import com.yiie.vo.request.PermissionPageReqVO;
import com.yiie.vo.request.PermissionUpdateReqVO;
import com.yiie.vo.response.PageVO;
import com.yiie.vo.response.PermissionRespNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Time：2020-1-2 9:34
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private TokenSettings tokenSettings;

    @Override
    public List<Permission> getPermission(String userId) {
        List<String> roleIds = userRoleService.getRoleIdsByUserId(userId);
        if(roleIds.isEmpty()){
            return null;
        }
        List<String> permissionIds= rolePermissionService.getPermissionIdsByRoles(roleIds);
        if (permissionIds.isEmpty()){
            return null;
        }
        List<Permission> result=permissionMapper.selectInfoByIds(permissionIds);
        return result;
    }

    /**
     * 获取权限标识
     * @param userId
     * @return
     */
    @Override
    public Set<String> getPermissionsByUserId(String userId) {
        List<Permission> list=getPermission(userId);
        Set<String> permissions=new HashSet<>();
        if (null==list||list.isEmpty()){
            return null;
        }
        for (Permission permission:list){
            if(!StringUtils.isEmpty(permission.getPerms())){
                permissions.add(permission.getPerms());
            }
        }
        return permissions;
    }

    @Override
    public List<PermissionRespNode> permissionTreeList(String userId) {
        List<Permission> list=getPermission(userId);
        return getTree(list,true);
    }

    private List<PermissionRespNode> getTree(List<Permission> all,boolean type){

        List<PermissionRespNode> list=new ArrayList<>();
        if (all==null||all.isEmpty()){
            return list;
        }
        for(Permission permission:all){
            if(permission.getPid().equals("0")){
                PermissionRespNode permissionRespNode=new PermissionRespNode();
                BeanUtils.copyProperties(permission,permissionRespNode);
                permissionRespNode.setTitle(permission.getName());
                if(type){
                    permissionRespNode.setChildren(getChildExcBtn(permission.getId(),all));
                }else {
                    permissionRespNode.setChildren(getChildAll(permission.getId(),all));
                }
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    private List<PermissionRespNode>getChildExcBtn(String id,List<Permission> all){

        List<PermissionRespNode> list=new ArrayList<>();
        for(Permission Permission:all){
            if(Permission.getPid().equals(id)&&Permission.getType()!=3){
                PermissionRespNode permissionRespNode=new PermissionRespNode();
                BeanUtils.copyProperties(Permission,permissionRespNode);
                permissionRespNode.setTitle(Permission.getName());
                permissionRespNode.setChildren(getChildExcBtn(Permission.getId(),all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    private List<PermissionRespNode>getChildAll(String id,List<Permission> all){

        List<PermissionRespNode> list=new ArrayList<>();
        for(Permission Permission:all){
            if(Permission.getPid().equals(id)){
                PermissionRespNode permissionRespNode=new PermissionRespNode();
                BeanUtils.copyProperties(Permission,permissionRespNode);
                permissionRespNode.setTitle(Permission.getName());
                permissionRespNode.setChildren(getChildAll(Permission.getId(),all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    @Override
    public Permission addPermission(PermissionAddReqVO vo) {
        Permission Permission=new Permission();
        BeanUtils.copyProperties(vo,Permission);
        verifyForm(Permission);
        Permission.setId(UUID.randomUUID().toString());
        Permission.setCreateTime(new Date());
        int count=permissionMapper.insertSelective(Permission);
        if (count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        return Permission;
    }
    /**
     * 操作后的菜单类型是目录的时候 父级必须为目录
     * 操作后的菜单类型是菜单的时候，父类必须为目录类型
     * 操作后的菜单类型是按钮的时候 父类必须为菜单类型
     */
    private void verifyFormPid(Permission Permission){
        Permission parent=permissionMapper.selectByPrimaryKey(Permission.getPid());
        switch (Permission.getType()){
            case 1:
                if(parent!=null){
                    if(parent.getType()!=1){
                        throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                    }
                }else if(!Permission.getPid().equals("0")){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                }
                break;
            case 2:
                if(parent==null||parent.getType()!=1){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_MENU_ERROR);
                }
                if(StringUtils.isEmpty(Permission.getUrl())){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }

                break;
            case 3:
                if(parent==null||parent.getType()!=2){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_BTN_ERROR);
                }
                if(StringUtils.isEmpty(Permission.getPerms())){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_PERMS_NULL);
                }
                if(StringUtils.isEmpty(Permission.getUrl())){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                if(StringUtils.isEmpty(Permission.getMethod())){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }
                break;
        }
    }
    /**
     * 编辑或者新增的时候检验
     * @Author:      小霍
     * @CreateDate:  2019/10/31 21:22
     * @UpdateUser:
     * @UpdateDate:  2019/10/31 21:22
     * @Version:     0.0.1
     * @param Permission
     * @return       void
     * @throws
     */
    private void verifyForm(Permission Permission){

        verifyFormPid(Permission);
        /**
         * id 不为空说明是编辑
         */
        if(!StringUtils.isEmpty(Permission.getId())) {
            List<Permission> list = permissionMapper.selectChild(Permission.getId());
            if (!list.isEmpty()) {
                throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_UPDATE);
            }
        }

    }
    /**
     * 查询菜单权限详情
     * @Author:      小霍
     * @CreateDate:  2019/9/20 14:05
     * @UpdateUser:
     * @UpdateDate:  2019/9/20 14:05
     * @Version:     0.0.1
     * @param permissionId
     * @return       com.xh.lesson.entity.Permission
     * @throws
     */
    @Override
    public Permission detailInfo(String permissionId) {

        return permissionMapper.selectByPrimaryKey(permissionId);
    }
    /**
     * 更新菜单权限
     * @Author:      小霍
     * @CreateDate:  2019/9/20 14:04
     * @UpdateUser:
     * @UpdateDate:  2019/9/20 14:04
     * @Version:     0.0.1
     * @param vo
     * @return       void
     * @throws
     */
    @Override
    public void updatePermission(PermissionUpdateReqVO vo) {

        Permission Permission=permissionMapper.selectByPrimaryKey(vo.getId());
        if (null==Permission){
            log.error("传入 的 id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        Permission update=new Permission();
        BeanUtils.copyProperties(vo,update);
        /**
         * 只有类型变更
         * 或者所属菜单变更
         */
        if(Permission.getType()!=vo.getType()||!Permission.getPid().equals(vo.getPid())){
            verifyForm(update);
        }
        update.setUpdateTime(new Date());
        int count=permissionMapper.updateByPrimaryKeySelective(update);
        if(count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        /**
         * 说明这个菜单权限 权鉴标识发生变化
         * 所有管理这个菜单权限用户将重新刷新token
         */
        if(StringUtils.isEmpty(vo.getPerms())&&!Permission.getPerms().equals(vo.getPerms())){
            List<String> roleIds = rolePermissionService.getRoleIds(vo.getId());
            if(!roleIds.isEmpty()){
                List<String> userIds = userRoleService.getUserIdsByRoleIds(roleIds);
                if(!userIds.isEmpty()){
                    for (String userId:userIds){
                        redisService.set(Constant.JWT_REFRESH_KEY +userId,userId,tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                        //清空权鉴缓存
                        redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
                    }

                }
            }
        }

    }
    /**
     * 删除菜单权限
     * 判断是否 有角色关联
     * 判断是否有子集
     * @Author:      小霍
     * @CreateDate:  2019/9/20 14:04
     * @UpdateUser:
     * @UpdateDate:  2019/9/20 14:04
     * @Version:     0.0.1
     * @param permissionId
     * @return       void
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleted(String permissionId) {
        Permission Permission = permissionMapper.selectByPrimaryKey(permissionId);
        if(null==Permission){
            log.error("传入 的 id:{}不合法",permissionId);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        List<Permission> childs = permissionMapper.selectChild(permissionId);
        if(!childs.isEmpty()){
            throw new BusinessException(BaseResponseCode.ROLE_PERMISSION_RELATION);
        }
        Permission.setDeleted(0);
        Permission.setUpdateTime(new Date());
        int count=permissionMapper.updateByPrimaryKeySelective(Permission);
        if(count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        /**
         * 删除和角色关联
         */
        rolePermissionService.removeByPermissionId(permissionId);
        List<String> roleIds = rolePermissionService.getRoleIds(permissionId);
        if(!roleIds.isEmpty()){
            List<String> userIds = userRoleService.getUserIdsByRoleIds(roleIds);
            if(!userIds.isEmpty()){
                for (String userId:userIds){
                    redisService.set(Constant.JWT_REFRESH_KEY +userId,userId,tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                    //清空权鉴缓存
                    redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
                }

            }
        }
    }
    /**
     * 分页获取所有菜单权限
     * @Author:      小霍
     * @CreateDate:  2019/9/20 14:03
     * @UpdateUser:
     * @UpdateDate:  2019/9/20 14:03
     * @Version:     0.0.1
     * @param vo
     * @return       com.xh.lesson.vo.resp.PageVO<com.xh.lesson.entity.Permission>
     * @throws
     */
    @Override
    public PageVO<Permission> pageInfo(PermissionPageReqVO vo) {

        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Permission> list=selectAll();
        return PageUtils.getPageVO(list);
    }
    /**
     * 获取所有菜单权限
     * @Author:      小霍
     * @CreateDate:  2019/9/20 14:03
     * @UpdateUser:
     * @UpdateDate:  2019/9/20 14:03
     * @Version:     0.0.1
     * @param
     * @return       java.util.List<com.xh.lesson.entity.Permission>
     * @throws
     */
    @Override
    public List<Permission> selectAll() {
        List<Permission> result =permissionMapper.selectAll();
        if(!result.isEmpty()){
            for (Permission Permission:result){
                Permission parent=permissionMapper.selectByPrimaryKey(Permission.getPid());
                if (parent!=null){
                    Permission.setPidName(parent.getName());
                }
            }
        }
        return result;
    }
    /**
     * 获取权限标识
     * @Author:      小霍
     * @CreateDate:  2019/9/20 23:13
     * @UpdateUser:
     * @UpdateDate:  2019/9/20 23:13
     * @Version:     0.0.1
     * @param userId
     * @return       java.util.List<java.lang.String>
     * @throws
     */


    /**
     * 获取所有菜单权限按钮
     * @Author:      小霍
     * @CreateDate:  2019/10/27 18:29
     * @UpdateUser:
     * @UpdateDate:  2019/10/27 18:29
     * @Version:     0.0.1
     * @param
     * @return       java.util.List<com.xh.lesson.vo.resp.PermissionRespNode>
     * @throws
     */
    @Override
    public List<PermissionRespNode> selectAllByTree() {

        List<Permission> list=selectAll();
        return getTree(list,false);
    }
    /**
     * 获取所有的目录菜单树排除按钮
     * 因为不管是新增或者修改
     * 选择所属菜单目录的时候
     * 都不可能选择到按钮
     * 而且编辑的时候 所属目录不能
     * 选择自己和它的子类
     * @Author:      小霍
     * @CreateDate:  2019/10/31 13:08
     * @UpdateUser:
     * @UpdateDate:  2019/10/31 13:08
     * @Version:     0.0.1
     * @param
     * @return       java.util.List<com.xh.lesson.vo.resp.PermissionRespNode>
     * @throws
     */
    @Override
    public List<PermissionRespNode> selectAllMenuByTree(String permissionId) {

        List<Permission> list=selectAll();
        if(!list.isEmpty()&&!StringUtils.isEmpty(permissionId)){
            for (Permission Permission:list){
                if (Permission.getId().equals(permissionId)){
                    list.remove(Permission);
                    break;
                }
            }
        }
        List<PermissionRespNode> result=new ArrayList<>();
        //新增顶级目录是为了方便添加一级目录
        PermissionRespNode respNode=new PermissionRespNode();
        respNode.setId("0");
        respNode.setTitle("默认顶级菜单");
        respNode.setSpread(true);
        respNode.setChildren(getTree(list,true));
        result.add(respNode);
        return result;
    }
}
