package com.tdw.photograph.mapper;

import com.tdw.photograph.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserMapper {
    //用户登录
    User login(@Param("username") String username,
               @Param("password") String password);

    //用户最后登录时间
    int updateLoginTime(@Param("username") String username,
                        @Param("userLastLogin") Date userLastLogin);

    //用户修改昵称
    int updateNickname(@Param("username") String username,
                       @Param("newNickname") String newNickname);

    //用户修改密码
    int updatePassword(@Param("username") String username,
                       @Param("newPassword") String newPassword);

    //用户修改头像
    int updateLogo(@Param("username") String username,
                   @Param("logoLocation") String logoLocation);

    //用户注册
    int register(@Param("username") String username,
                 @Param("password") String password,
                 @Param("nickname") String nickname,
                 @Param("date") Date date);

    //查询账号是否已被注册
    User isRegistered(@Param("username") String username);

    //用户绑定手机号
    int updatePhone(@Param("username") String username,
                    @Param("userPhone") String userPhone);

    //查询该号码是否已被绑定
    User getPhone(@Param("phone") String phone);

}