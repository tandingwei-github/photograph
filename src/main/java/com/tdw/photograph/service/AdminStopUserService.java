package com.tdw.photograph.service;

import com.tdw.photograph.bean.User;
import com.tdw.photograph.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谭锭伟
 * @date 2020/1/8-20:06
 */
@Service
public class AdminStopUserService {

    @Autowired
    private AdminMapper adminMapper;

    //管理员搜索用户
    public List<User> toSearchUser(String username) {
        return adminMapper.toSearchUser(username);
    }

    //管理员封禁账号
    public int toStopUser(String username) {
        return adminMapper.toStopUser(username);
    }

    //管理员解禁账号
    public int toBeginUser(String username) {
        return adminMapper.toBeginUser(username);
    }

}
