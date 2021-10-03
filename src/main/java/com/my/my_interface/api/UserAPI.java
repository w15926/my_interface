package com.my.my_interface.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.my_interface.pkg.APIResult;
import com.my.my_interface.service.UserService;
import com.my.my_interface.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", method = {RequestMethod.POST})
public class UserAPI {
  @Autowired
  private UserService service;

  // 登录
  @RequestMapping(value = "/login")
  public String login(@RequestBody String json) {
    try {
      JSONObject jo = JSON.parseObject(json);
      String uname = jo.getJSONObject("data").getString("uname");
      String password = jo.getJSONObject("data").getString("password");
      return service.login(uname, password);
    } catch (Exception e) {
      APIResult pkg = new APIResult();
      return pkg.getError("操作失败");
    }
  }

  // 验证token（测试）
  @RequestMapping(value = "/tokenVal")
  public String tokenValidator(@RequestBody String json) {
    JSONObject jo = JSON.parseObject(json);
    String token = jo.getJSONObject("data").getString("token");
    try {
      JWTUtils.verifyToken(token);
      return "success";
    } catch (Exception e) {
      return "error";
    }
  }

  // 注册
  @RequestMapping(value = "/register")
  public String register(@RequestBody String json) {
    try {
      JSONObject jo = JSON.parseObject(json);
      String uname = jo.getJSONObject("data").getString("uname");
      String pwd = jo.getJSONObject("data").getString("password");
      String secretKey = jo.getJSONObject("data").getString("secretKey");
      return service.register(uname, pwd, secretKey);
    } catch (Exception e) {
      APIResult pkg = new APIResult();
      return pkg.getError("操作失败");
    }
  }

  // 修改密码 - 密码
  @RequestMapping("/modifyPwdByPwd")
  public String modifyPwdByPwd(@RequestBody String json) {
    try {
      JSONObject jo = JSON.parseObject(json);
      String uname = jo.getJSONObject("data").getString("uname");
      String pwd = jo.getJSONObject("data").getString("password");
      String newPwd = jo.getJSONObject("data").getString("newPassword");
      return service.modifyPwdByPwd(uname, pwd, newPwd);
    } catch (Exception e) {
      APIResult pkg = new APIResult();
      return pkg.getError("操作失败");
    }
  }

  // 修改密码 - 密钥
  @RequestMapping("/modifyPwdBySecretKey")
  public String modifyPwdBySecretKey(@RequestBody String json) {
    try {
      JSONObject jo = JSON.parseObject(json);
      String uname = jo.getJSONObject("data").getString("uname");
      String newPwd = jo.getJSONObject("data").getString("newPassword");
      String secretKey = jo.getJSONObject("data").getString("secretKey");
      return service.modifyPwdBySecretKey(uname, newPwd, secretKey);
    } catch (Exception e) {
      APIResult pkg = new APIResult();
      return pkg.getError("操作失败");
    }
  }

  @RequestMapping(value = "/queryUser")
  public String getUser(@RequestBody String json) {
    try {
      JSONObject jo = JSON.parseObject(json);
      String uid = jo.getJSONObject("data").getString("uid");
      String result = service.queryUser(uid);
      return result;
    } catch (Exception e) {
      APIResult pkg = new APIResult();
      return pkg.getError("操作失败");
    }
  }
}
