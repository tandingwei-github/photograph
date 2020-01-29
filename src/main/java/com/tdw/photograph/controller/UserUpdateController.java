package com.tdw.photograph.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.tdw.photograph.bean.User;
import com.tdw.photograph.service.LoginService;
import com.tdw.photograph.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;

/**
 * @author 谭锭伟
 * @date 2019/12/30-19:33
 */
@Controller
public class UserUpdateController {

    @Autowired
    private UserUpdateService userUpdateService;
    @Autowired
    private LoginService loginService;

    //用户修改昵称
    @RequestMapping("/toUpdateNickname")
    public String toUpdateNickname(@RequestParam("newNickname") String newNickname,
                                   HttpSession session, Map<String, Object> map) {

        String forbiddenWords[] = {"黄", "赌", "毒"};
        boolean wordOK = true;
        for (String x : forbiddenWords) {
            if (newNickname.contains(x)) {
                wordOK = false;
            }
        }

        if (newNickname.equals("")) {
            userUpdateService.updateNickname(
                    session.getAttribute("userName").toString(), "不留名用户");
            session.setAttribute("userNick", "不留名用户");
            return "redirect:/updateYourNickname";
        } else if (!wordOK) {
            map.put("msg", "含有敏感词汇！！禁止更新操作");
            return "users/userChangeNickname";
        } else {
            if (newNickname.contains("不留名")) {
                map.put("msg", "干啥呢，浪费系统资源");
                return "users/userChangeNickname";
            } else {
                userUpdateService.updateNickname(
                        session.getAttribute("userName").toString(), newNickname);
                session.setAttribute("userNick", newNickname);
                return "redirect:/updateYourNickname";
            }
        }
    }

    //用户修改密码
    @RequestMapping("/toUpdatePassword")
    public String toUpdatePassword(@RequestParam("oldPassword") String oldPassword,
                                   @RequestParam("newPassword") String newPassword,
                                   HttpSession session, Map<String, Object> map) {

        User user = loginService.loginForUser(
                session.getAttribute("userName").toString(), oldPassword);
        if (user == null) {
            map.put("msg", "密码错误，请重新输入");
            return "users/userChangePassword";
        } else {
            userUpdateService.updatePassword(
                    session.getAttribute("userName").toString(), newPassword);
            session.setAttribute("passwordChanged", "yes");
            return "redirect:/updateYourPassword";
        }
    }

    //用户修改头像
    @ResponseBody
    @RequestMapping("/toLogoUpload")
    public JSONObject toLogoUpload(@RequestParam("file") MultipartFile file, HttpSession httpSession) {
        JSONObject json = new JSONObject();
        try {
            String filepath = ResourceUtils.getURL("classpath:").getPath() + "static/logo/";

            //系统未创建LOGO文件夹，则创建
            File newFile = new File(filepath);
            if (!newFile.exists()) {
                newFile.mkdirs();
            }

            //保存文件到服务器
            file.transferTo(new File(filepath + httpSession.getAttribute("userName").toString() + ".png"));
            //写入数据库
            userUpdateService.updateLogo(
                    httpSession.getAttribute("userName").toString(),
                    "/static/logo/" + httpSession.getAttribute("userName").toString() + ".png");
            //写入session。实现刷新
            httpSession.setAttribute("userLogo",
                    "/static/logo/" + httpSession.getAttribute("userName").toString() + ".png");
            json.put("code", 1);
            json.put("text", "成功了");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 0);
            json.put("text", "失败了");
        }
        return json;
    }

    //删除目录下的所有文件
    boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }

    //全局变量作为验证码的后台存储；
    String getPhoneCode = "000000";

    //用户绑定手机号-获取验证码
    @ResponseBody
    @RequestMapping("/toGetPhoneCode")
    public JSONArray toGetPhoneCode(@RequestParam("phone") String phone) {
        JSONObject jsonObject = new JSONObject();
        //随机的六位数
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";

        //查询该号码是否已被绑定
        if (userUpdateService.getPhone(phone) != null) {
            jsonObject.put("phoneUsed", 1);
        } else {
            //生成验证码并发送短信
            getPhoneCode = send(phone, code);
        }
        JSONArray array = new JSONArray();
        array.set(1, jsonObject);
        return array;
    }

    String send(String phone, String key) {
        //第二个参数为自己独有的accessKeyid，第三个参数为自己独有的accessKeySecret
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shenzhen",
                "LTAI4Fi3yKCrZCdno7hAYAwD",
                "de41Z3pVCiPv9X0CP8x6h13gjIeeB2");

        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();//组装请求对象

        //设置post提交
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        //短信API产品域名（接口地址固定，无需修改）
        request.setDomain("dysmsapi.aliyuncs.com");

        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-shenzhen");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "图片网");//短信签名
        request.putQueryParameter("TemplateCode", "SMS_181555899");
        request.putQueryParameter("TemplateParam", "{code:" + key + "}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            //System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println(key);
        return key;
    }

    //用户绑定手机号-比对验证码
    @RequestMapping("/toUpdatePhone")
    public String toComparePhoneCode(
            @RequestParam("phoneCode") String phoneCode,
            @RequestParam("phone") String phone,
            HttpSession session, Map<String, Object> map) {
        if (phoneCode.equals(getPhoneCode)) {
            userUpdateService.updatePhone(
                    session.getAttribute("userName").toString(), phone);
            session.setAttribute("userPhone", phone);
            session.setAttribute("phoneCodeRight", "yes");
            return "redirect:/updateYourPhone";
        } else {
            map.put("msg", "验证码错误，请重新输入");
            session.setAttribute("Phone", phone);
            return "users/userChangePhone";
        }
    }
}