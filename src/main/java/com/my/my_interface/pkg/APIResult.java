package com.my.my_interface.pkg;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class APIResult {
  private String result;
  private JSONObject data;

  /*
   * 返回正确
   */
  @Override
  public String toString() {
    try {
      JSONObject jo = new JSONObject();
      jo.put("result", result);
      jo.put("data", data);
      return jo.toString();
    } catch (Exception e) {
      return "";
    }
  }

  /*
   * 返回OK
   */
  public String getSuccess() {
    try {
      JSONObject jo = new JSONObject();
      jo.put("result", "success");
      JSONObject error = new JSONObject();
      error.put("msg", "ok");
      jo.put("data", error);
      return jo.toString();
    } catch (Exception e) {
      return "";
    }
  }

  /*
   * 返回错误信息
   */
  public String getError(String msg) {
    try {
      JSONObject jo = new JSONObject();
      jo.put("result", "error");
      JSONObject error = new JSONObject();
      error.put("msg", msg);
      jo.put("data", error);
      return jo.toString();
    } catch (Exception e) {
      return "";
    }
  }

}
