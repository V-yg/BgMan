package com.yiie.shiro;

import com.alibaba.fastjson.JSON;
import com.yiie.constant.Constant;
import com.yiie.enums.BaseResponseCode;
import com.yiie.exceptions.BusinessException;
import com.yiie.utils.DataResult;
import com.yiie.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Time：2020-1-2 17:29
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        try {
            Subject subject=getSubject(servletRequest,servletResponse);
            System.out.println(subject.isAuthenticated()+"");

            System.out.println(HttpContextUtils.isAjaxRequest(request));
            log.info(request.getMethod());
            log.info(request.getRequestURL().toString());
            String token=request.getHeader(Constant.ACCESS_TOKEN);
            if(StringUtils.isEmpty(token)){
                throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
            }
            CustomPasswordToken customPasswordToken=new CustomPasswordToken(token);
            getSubject(servletRequest, servletResponse).login(customPasswordToken);
        }catch (BusinessException exception){
            if(HttpContextUtils.isAjaxRequest(request)){
                customRsponse(exception.getMessageCode(),exception.getDetailMessage(),servletResponse);
            }else if(exception.getMessageCode()==BaseResponseCode.TOKEN_ERROR.getCode()){
                servletRequest.getRequestDispatcher("/index/login").forward(servletRequest,servletResponse);
            }else if(exception.getMessageCode()==BaseResponseCode.UNAUTHORIZED_ERROR.getCode()){
                servletRequest.getRequestDispatcher("/index/403").forward(servletRequest,servletResponse);
            }else {
                servletRequest.getRequestDispatcher("/index/500").forward(servletRequest,servletResponse);
            }
            return false;
        } catch (AuthenticationException e){
            if(HttpContextUtils.isAjaxRequest(request)){
                if(e.getCause() instanceof BusinessException){
                    BusinessException exception= (BusinessException) e.getCause();
                    customRsponse(exception.getMessageCode(),exception.getDetailMessage(),servletResponse);
                }else {
                    customRsponse(BaseResponseCode.SYSTEM_BUSY.getCode(),BaseResponseCode.SYSTEM_BUSY.getMsg(),servletResponse);
                }
            }else {
                servletRequest.getRequestDispatcher("/index/403").forward(servletRequest,servletResponse);
            }
            return false;
        }catch (Exception e) {
            if(HttpContextUtils.isAjaxRequest(request)){
                if(e.getCause() instanceof BusinessException){
                    BusinessException exception= (BusinessException) e.getCause();
                    customRsponse(exception.getMessageCode(),exception.getDetailMessage(),servletResponse);
                }else {
                    customRsponse(BaseResponseCode.SYSTEM_BUSY.getCode(),BaseResponseCode.SYSTEM_BUSY.getMsg(),servletResponse);
                }
            }else {
                servletRequest.getRequestDispatcher("/index/500").forward(servletRequest,servletResponse);
            }
            return false;
        }
        return true;
    }

    private void customRsponse(int code,String msg,ServletResponse response){
        try {
            DataResult result = DataResult.getResult(code,msg);

            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            String userJson = JSON.toJSONString(result);
            OutputStream out = response.getOutputStream();
            out.write(userJson.getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            log.error("eror={}",e);
        }
    }
}
