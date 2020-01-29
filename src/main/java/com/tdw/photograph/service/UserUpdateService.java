package com.tdw.photograph.service;

import com.tdw.photograph.bean.User;
import com.tdw.photograph.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谭锭伟
 * @date 2019/12/30-19:37
 */
@Service
public class UserUpdateService {

    @Autowired
    UserMapper userMapper;

    //用户修改昵称
    public int updateNickname(String username, String newNickname) {
        return userMapper.updateNickname(username, newNickname);
    }

    //用户修改密码
    public int updatePassword(String username, String newPassword) {
        return userMapper.updatePassword(username, newPassword);
    }

    //用户修改头像
    public int updateLogo(String username, String logoLocation) {
        return userMapper.updateLogo(username, logoLocation);
    }

    //用户绑定手机号
    public int updatePhone(String username, String userPhone) {
        return userMapper.updatePhone(username, userPhone);
    }

    //查询该号码是否已被绑定
    public User getPhone(String phone) {
        return userMapper.getPhone(phone);
    }

}
