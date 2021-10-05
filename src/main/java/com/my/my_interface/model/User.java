package com.my.my_interface.model;

import lombok.Data;

import java.time.LocalDateTime;

/*
 * 用户
 */
@Data
public class User {
  private Integer uid;
  private String uname;
  private String password;
  private String secretKey;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
