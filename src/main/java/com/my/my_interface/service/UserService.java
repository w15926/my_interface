package com.my.my_interface.service;

import com.alibaba.fastjson.JSONObject;
import com.my.my_interface.mapper.UserMapper;
import com.my.my_interface.model.User;
import com.my.my_interface.pkg.APIResult;
import com.my.my_interface.utils.JWTUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
  @Autowired
  private UserMapper mapper;

  @Test
  public void t(){
    System.out.println(mapper);
  }

  // 登录
  public String login(String uname, String password) {
    String res = mapper.login(uname, password);

    if (res != null) {
      HashMap<String, String> map = new HashMap<>();
      map.put("uname", uname);
      map.put("password", password);
      String token = JWTUtils.getToken(map);

      JSONObject jo = new JSONObject();
      jo.put("token", token);
      APIResult pkg = new APIResult();
      pkg.setResult("success");
      pkg.setData(jo);
      return pkg.toString();
    }
    APIResult pkg = new APIResult();
    return pkg.getError("请检查账号密码");
  }

  // 注册
  public String register(String uname, String password, String secretKey) {
    List<User> existUname = mapper.isExistUname(uname);
    if (existUname.size() > 0) {
      APIResult pkg = new APIResult();
      return pkg.getError("该用户已存在");
    } else {
      mapper.register(uname, password, secretKey);
      JSONObject jo = new JSONObject();
      jo.put("msg", "注册成功");
      APIResult pkg = new APIResult();
      pkg.setResult("success");
      pkg.setData(jo);
      return pkg.toString();
    }
  }

  // 修改密码 - 密码
  public String modifyPwdByPwd(String uname, String password, String newPwd) {
    List<User> existUname = mapper.isExistUname(uname);
    List<User> existPassword = mapper.isExistPassword(uname);
    if (existUname.size() < 1) {
      APIResult pkg = new APIResult();
      return pkg.getError("该用户不存在");
    } else if (!existPassword.get(0).getPassword().contains(password)) {
      APIResult pkg = new APIResult();
      return pkg.getError("该用户密码错误");
    } else {
      mapper.modifyPwd(uname, newPwd);
      JSONObject jo = new JSONObject();
      jo.put("msg", "修改成功");
      APIResult pkg = new APIResult();
      pkg.setResult("success");
      pkg.setData(jo);
      return pkg.toString();
    }
  }

  // 修改密码 - 密钥
  public String modifyPwdBySecretKey(String uname,String newPwd,String secrectKey){
    List<User> existUname = mapper.isExistUname(uname);
    List<User> existSecretKey = mapper.isExistSecretKey(uname);
    if (existUname.size() < 1) {
      APIResult pkg = new APIResult();
      return pkg.getError("该用户不存在");
    } else if (!existSecretKey.get(0).getSecretKey().contains(secrectKey)) {
      APIResult pkg = new APIResult();
      return pkg.getError("该用户密钥错误");
    } else {
      mapper.modifyPwd(uname, newPwd);
      JSONObject jo = new JSONObject();
      jo.put("msg", "修改成功");
      APIResult pkg = new APIResult();
      pkg.setResult("success");
      pkg.setData(jo);
      return pkg.toString();
    }
  }

  public String queryUser(String uid) {
    String result = mapper.getByUserUname(Integer.parseInt(uid));
    if (result != null) {
      JSONObject jo = new JSONObject();
      jo.put("data", result);
      APIResult pkg = new APIResult();
      pkg.setResult("success");
      pkg.setData(jo);
      return pkg.toString();
    } else {
      APIResult pkg = new APIResult();
      return pkg.getError("该用户不存在");
    }
  }
}
