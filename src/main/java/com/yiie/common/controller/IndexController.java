package com.yiie.common.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Time：2019-12-30 23:41
 * Email： yiie315@163.com
 * Desc：视图索引控制器 ，返回视图
 *
 * @author： yiie
 * @version：1.0.0
 */
@Api(tags = "视图",description = "负责返回视图")
@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/login")
    public String login(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request){
        return "home";
    }

    @GetMapping("/users/password")
    public String updatePassword(){
        return "users/update_password";
    }

    @GetMapping("/users/info")
    public String userDetail(Model model){
        model.addAttribute("flagType","edit");
        return "users/user_edit";
    }

    @GetMapping("/menus")
    public String menusList(){
        return "menus/menu_list";
    }

    @GetMapping("/roles")
    public String roleList(){
        return "roles/role_list";
    }

    @GetMapping("/users")
    public String userList(){
        return "users/user_list";
    }

    @GetMapping("/logs")
    public String logList(){
        return "logs/log_list";
    }

    @GetMapping("/loginlogs")
    public String loginlogList(){
        return "logs/loginlog_list";
    }

    @GetMapping("/depts")
    public String deptList(){
        return "depts/dept_list";
    }

    @GetMapping("/403")
    public String error403(){
        return "error/403";
    }
    @GetMapping("/404")
    public String error404(){
        return "error/404";
    }

    @GetMapping("/500")
    public String error405(){
        return "error/500";
    }

    @GetMapping("/main")
    public String indexHome(){
        return "main";
    }

    @GetMapping("/vpnusers")
    public String vpnUsers(){
        return "vpn/vpnuser_list";
    }

    @GetMapping("/vpnnodes")
    public String vpnNodes(){
        return "vpn/vpnnode_list";
    }

    @GetMapping("/vpnuserflow")
    public String vpnUserFlow(){
        return "vpn/vpnuserflow_list";
    }

    @GetMapping("/server")
    public String server(){
        return "logs/server";
    }

    @GetMapping("/realshow")
    public String realShow(){
        return "show/real";
    }

    @GetMapping("/hotshow")
    public String hotShow(){
        return "show/hot";
    }
}
