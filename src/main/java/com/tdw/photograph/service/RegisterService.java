package com.tdw.photograph.service;

import com.tdw.photograph.bean.User;
import com.tdw.photograph.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 谭锭伟
 * @date 2020/1/6-16:21
 */
@Service
public class RegisterService {
    @Autowired
    private UserMapper userMapper;

    public int register(String username, String password,
                        String nickname, Date date) {
        return userMapper.register(username, password, nickname, date);
    }

    public User isRegistered(String username) {
        return userMapper.isRegistered(username);
    }

}
