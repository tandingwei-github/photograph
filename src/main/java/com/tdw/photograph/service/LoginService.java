package com.tdw.photograph.service;

import com.tdw.photograph.bean.Admin;
import com.tdw.photograph.bean.User;
import com.tdw.photograph.mapper.AdminMapper;
import com.tdw.photograph.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 谭锭伟
 * @date 2019/12/26-15:53
 */
@Service
public class LoginService {
    @Autowired
    AdminMapper adminMapper;

    //管理员登录
    public Admin loginForAdmin(String username, String password) {
        return adminMapper.login(username, password);
    }

    //管理员最后下线时间
    public int updateAdminLogoutTime(String username, Date LastLogout) {
        return adminMapper.updateAdminLogoutTime(username, LastLogout);
    }

    @Autowired
    UserMapper userMapper;

    //用户登录
    public User loginForUser(String username, String password) {
        return userMapper.login(username, password);
    }

    //用户最后登录时间
    public int updateUserLoginTime(String username, Date userLastLogin) {
        return userMapper.updateLoginTime(username, userLastLogin);
    }

}