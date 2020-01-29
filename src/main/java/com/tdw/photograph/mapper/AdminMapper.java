package com.tdw.photograph.mapper;

import com.tdw.photograph.bean.Admin;
import com.tdw.photograph.bean.Message;
import com.tdw.photograph.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AdminMapper {
    //管理员登录
    Admin login(@Param("username") String username,
                @Param("password") String password);

    //管理员最后下线时间
    int updateAdminLogoutTime(@Param("username") String username,
                              @Param("LastLogout") Date LastLogout);

    //管理员推送信息
    int pushMessage(@Param("message_writer") String message_writer,
                    @Param("message_title") String message_title,
                    @Param("message_content") String message_content,
                    @Param("message_time") Date message_time);

    //页面加载完成后，显示前20条信息
    List<Message> firstTenMessage();

    //往下滚动，加载下一个10条信息
    List<Message> moreTenMessage(@Param("pageA") Integer pageA,
                                 @Param("pageB") Integer pageB);

    //管理员搜索用户
    List<User> toSearchUser(@Param("username") String username);

    //管理员封禁账号
    int toStopUser(@Param("username") String username);

    //管理员解封账号
    int toBeginUser(@Param("username") String username);

}