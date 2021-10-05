package com.my.my_interface.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 设置请求拦截器
 * @Param
 * @Return
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
  // 添加拦截器
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new JWTInterceeptors())
            .addPathPatterns("/**") // 此路径进行拦截器验证
            .excludePathPatterns( // 不包括某些路径
                    // "/user/login",
                    // "/user/register",
                    // "/user/modifyPwdByPwd",
                    // "/user/modifyPwdBySecretKey",
                    "/user/**",
                    "/carousel",
                    "/categoryItems"
            );
  }
}
