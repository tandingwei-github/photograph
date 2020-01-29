package com.tdw.photograph.controller;

import com.tdw.photograph.bean.User;
import com.tdw.photograph.service.LoginService;
import com.tdw.photograph.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author 谭锭伟
 * @date 2020/1/6-15:02
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/toRegister")
    public String toRegister(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("password2") String password2,
                             Map<String, Object> map,
                             HttpSession session) {
        if (username.equals("") || password.equals("") || password2.equals("")) {
            map.put("msg", "注册失败，表单不能有空值");
            return "register";
        } else if (username.length() > 10 || password.length() > 10) {
            map.put("msg", "注册失败，账号或密码长度过长");
            return "register";
        } else if (!password.equals(password2)) {
            map.put("msg", "注册失败，两次密码不相同");
            return "register";
        } else if (registerService.isRegistered(username) != null) {
            map.put("msg", "注册失败，账号" + username + "已被注册");
            return "register";
        } else {
            String nickname = "图片网某用户";
            registerService.register(username, password, nickname, new Date());

            User user = loginService.loginForUser(username, password);
            String Phone = null;
            if (user.getUserPhone() == null) {
                Phone = "未绑定";
            } else {
                Phone = user.getUserPhone();
            }
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