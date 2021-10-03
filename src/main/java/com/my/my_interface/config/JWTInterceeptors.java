package com.my.my_interface.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.my.my_interface.pkg.APIResult;
import com.my.my_interface.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceeptors implements HandlerInterceptor {
  /*
   * token验证拦截器
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // 获取前端请求头中token
    String token = request.getHeader("token");
    APIResult pkg = new APIResult();
    response.setContentType("application/json;charset=UTF-8");

    if (token == null) {
      response.getWriter().println(pkg.getError("header中token不存在"));
      return false;
    }

    try {
      JWTUtils.verifyToken(token); // 验证token
      return true; // 放行
    } catch (SignatureVerificationException e) {
      response.getWriter().println(pkg.getError("无效签名"));
      return false;
    } catch (TokenExpiredException e) {
      response.getWriter().println(pkg.getError("token已过期"));
      return false;
    } catch (AlgorithmMismatchException e) {
      response.getWriter().println(pkg.getError("token算法不一致"));
      return false;
    } catch (Exception e) {
      response.getWriter().println(pkg.getError("token无效"));
      return false;
    }

  }
}
