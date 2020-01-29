package com.tdw.photograph.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tdw.photograph.bean.User;
import com.tdw.photograph.service.AdminStopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 谭锭伟
 * @date 2020/1/8-20:03
 */
@Controller
public class AdminStopUserController {

    @Autowired
    private AdminStopUserService adminStopUserService;

    //管理员搜索用户
    @ResponseBody
    @RequestMapping("/toSearchUser")
    public JSONArray toSearchUser(@RequestParam("username") String username) {
        List<User> searched = adminStopUserService.toSearchUser(username);
        JSONArray array = JSON.parseArray(JSON.toJSONString(searched));
        return array;
    }

    //管理员封禁账号
    @RequestMapping("/toStopUser")
    @ResponseBody
    public JSONObject toStopUser(@RequestParam("username") String username) {
        JSONObject jsonObject = new JSONObject();
        adminStopUserService.toStopUser(username);
        jsonObject.put("0", "账号" + username + "被成功封禁了");
        return jsonObject;
    }

    //管理员解禁账号
    @RequestMapping("/toBeginUser")
    @ResponseBody
    public JSONObject toBeginUser(@RequestParam("username") String username) {
        JSONObject jsonObject = new JSONObject();
        adminStopUserService.toBeginUser(username);
        jsonObject.put("0", "账号" + username + "被成功解禁了");
        return jsonObject;
    }
}
