package com.tdw.photograph.service;

import com.tdw.photograph.bean.Message;
import com.tdw.photograph.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 谭锭伟
 * @date 2020/1/3-13:05
 */
@Service
public class AdminMessageService {
    @Autowired
    private AdminMapper adminMapper;

    //管理员推送信息
    public int pushMessage(String message_writer, String message_title,
                           String message_content, Date message_time) {
        return adminMapper.pushMessage(message_writer, message_title,
                message_content, message_time);
    }

    //页面加载完成后，显示前10条信息
    public List<Message> firstTenMessage() {
        return adminMapper.firstTenMessage();
    }

    //往下滚动，加载下一个10条信息
    public List<Message> moreTenMessage(int pageA, int pageB) {
        return adminMapper.moreTenMessage(pageA, pageB);
    }

}
