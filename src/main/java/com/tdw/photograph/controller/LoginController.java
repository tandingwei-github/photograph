package com.tdw.photograph.controller;

import com.tdw.photograph.bean.Admin;
import com.tdw.photograph.bean.User;
import com.tdw.photograph.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author 谭锭伟
 * @date 2019/12/25-15:37
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    //管理员登录
    @PostMapping("/toLoginAdmin")
    public String toLoginAdmin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Map<String, Object> map,
                               HttpSession session) {

        Admin admin = loginService.loginForAdmin(username, password);
        if (admin == null) {
            map.put("msg", "登录失败！账号不存在或密码错误");
            return "loginAdmin";
        } else {
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            session.setAttribute("userName", admin.getUsername());
            session.setAttribute("userNick", admin.getNickname());
            session.setAttribute("loginTime", df.format(new Date()));
            return "redirect:/adminStopUser";
        }
    }

    //普通用户登录
    @PostMapping("/toLogin")
    public String toLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Map<String, Object> map,
                          HttpSession session) {
        User user = loginService.loginForUser(username, password);

        if (user == null) {
            map.put("msg", "登录失败！账号不存在或密码错误");
            return "login";
        } else {
            String Phone = null;
            if (user.getUserPhone() == null) {
                Phone = "未绑定";
            } else {
                Phone = user.getUserPhone();
            }

            if (user.getUserIsStopped().equals("yes")) {
                map.put("msg", "此账号已被封禁，请更换账号登录");
                return "login";
            } else {
                session.setAttribute("userName", user.getUsername());
                session.setAttribute("userPhone", Phone);
                session.setAttribute("userNick", user.getUserNick());
                session.setAttribute("userShared", user.getUserShared());
                session.setAttribute("userPurchased", user.getUserPurchased());
                session.setAttribute("userCommented", user.getUserCommented());
                session.setAttribute("userLogo", user.getUserLogo());

                loginService.updateUserLoginTime(user.getUsername(), new Date());

                return "redirect:/yourInfo";
            }
        }
    }
}