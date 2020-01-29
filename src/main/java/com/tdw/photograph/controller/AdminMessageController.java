package com.tdw.photograph.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tdw.photograph.bean.Message;
import com.tdw.photograph.service.AdminMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author 谭锭伟
 * @date 2020/1/3-12:42
 */
@Controller
public class AdminMessageController {

    @Autowired
    private AdminMessageService adminMessageService;

    //管理员推送信息
    @RequestMapping("toPushMessage")
    public String toPushMessage(
            @RequestParam("message_writer") String message_writer,
            @RequestParam("message_title") String message_title,
            @RequestParam("message_content") String message_content) {
        //写信息
        adminMessageService.pushMessage(message_writer, message_title, message_content, new Date());
        return "redirect:/adminPushMessage";
    }

    //页面加载完成后，显示前10条信息
    @RequestMapping("/firstTenMessage")
    @ResponseBody
    public JSONArray firstTenMessage() {
        List<Message> messages = adminMessageService.firstTenMessage();
        JSONArray array = JSON.parseArray(JSON.toJSONString(messages));
        return array;
    }

    //往下滚动，加载下一个10条信息
    @RequestMapping("/moreTenMessage")
    @ResponseBody
    public JSONArray moreTenMessage(int page) {
        int pageA = page * 10;
        int pageB = 10;
        List<Message> messages = adminMessageService.moreTenMessage(pageA, pageB);
        JSONObject json = new JSONObject();
        if (messages.size() < 10) {
            json.put("pageAll", true);
        } else {
            json.put("pageAll", false);
        }
        JSONArray array = JSON.parseArray(JSON.toJSONString(messages));
        array.set(10, json);
        return array;
    }
}