package com.tdw.photograph.controller;

import com.tdw.photograph.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author 谭锭伟
 * @date 2019/12/26-9:56
 */
@Controller
public class LogoutController {
    @Autowired
    private LoginService loginService;

    //管理员退出
    @RequestMapping("/logoutAdmin")
    public String logoutAdmin(HttpSession session) {
        loginService.updateAdminLogoutTime(
                session.getAttribute("userName").toString(), new Date());
        session.invalidate();
        return "redirect:/loginAdmin";
    }

    //普通用户退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
