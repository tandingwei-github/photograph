package com.tdw.photograph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 谭锭伟
 * @date 2019/12/22-18:20
 */
//页面转发类
@Controller
public class PageController {
    //注册页面
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    //登录页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //管理员登录页面
    @RequestMapping("/loginAdmin")
    public String loginForAdmin() {
        return "loginAdmin";
    }


    //下面是用户页面
    //用户首页,显示个人信息
    @RequestMapping("/yourInfo")
    public String yourInfo(HttpSession session) {
        clearSessionUsers(session);
        return "users/userInfo";
    }

    //用户已评论的
    @RequestMapping("/yourCommented")
    public String yourCommented(HttpSession session) {
        clearSessionUsers(session);
        return "users/userCommented";
    }

    //用户已分享的
    @RequestMapping("/yourShared")
    public String yourShared(HttpSession session) {
        clearSessionUsers(session);
        return "users/userShared";
    }

    //用户已下载的
    @RequestMapping("/yourDownloaded")
    public String yourDownloaded(HttpSession session) {
        clearSessionUsers(session);
        return "users/userDownloaded";
    }

    //用户修改昵称
    @RequestMapping("/updateYourNickname")
    public String updateYourNickname(HttpSession session) {
        clearSessionUsers(session);
        return "users/userChangeNickname";
    }

    //用户修改密码
    @RequestMapping("/updateYourPassword")
    public String updateYourPassword(HttpSession session) {
        clearSessionUsers(session);
        return "users/userChangePassword";
    }

    //用户修改头像
    @RequestMapping("/updateYourLogo")
    public String updateYourLogo(HttpSession session) {
        clearSessionUsers(session);
        return "users/userChangeLogo";
    }

    //用户接收系统信息
    @RequestMapping("/yourMessage")
    public String yourMessage(HttpSession session) {
        clearSessionUsers(session);
        return "users/userGetMessage";
    }

    //用户绑定/改绑手机
    @RequestMapping("/updateYourPhone")
    public String updateYourPhone(HttpSession session) {
        clearSessionUsers(session);
        return "users/userChangePhone";
    }


    //下面是管理员页面
    //管理员首页
    @RequestMapping("/adminStopUser")
    public String adminStopUser(HttpSession session) {
        return "admin/adminStopUser";
    }

    //审核图片
    @RequestMapping("/adminCheckPhoto")
    public String adminCheckPhoto(HttpSession session) {
        return "admin/adminCheckPhoto";
    }

    //下架图片
    @RequestMapping("/adminStopPhoto")
    public String adminStopPhoto(HttpSession session) {
        return "admin/adminStopPhoto";
    }

    //删除评论
    @RequestMapping("/adminDeleteComment")
    public String adminDeleteComment(HttpSession session) {
        return "admin/adminDeleteComment";
    }

    //标注版权
    @RequestMapping("/adminTagCopyright")
    public String adminTagCopyright(HttpSession session) {
        return "admin/adminTagCopyright";
    }

    //推送信息
    @RequestMapping("/adminPushMessage")
    public String adminPushMessage(HttpSession session) {
        return "admin/adminPushMessage";
    }

    //管理员跳转页面，清除标记用的session
    void clearSessionAdmin(HttpSession session) {
    }

    //用户跳转页面，清除标记用的session
    void clearSessionUsers(HttpSession session) {
        session.setAttribute("phoneCodeRight", "no");
        session.setAttribute("passwordChanged", "no");
        session.setAttribute("Phone", "");

    }
}
