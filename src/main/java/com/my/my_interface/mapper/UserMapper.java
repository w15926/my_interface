package com.my.my_interface.mapper;

import com.my.my_interface.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
  // 登录
  @Select("select * from users where uname = #{uname} and password = #{password}")
  String login(String uname, String password);

  // 注册
  @Insert({
          "insert into users (uname, password, secretKey)",
          "values (#{uname}, #{password}, #{secretKey})"
  })
  int register(String uname, String password, String secretKey);

  // 检查当前用户名是否存在
  @Select("select uname from users where uname = #{uname}")
  List<User> isExistUname(String uname);

  // 检查当前密钥是否一致
  @Select("select secretKey from users where uname = #{uname}")
  List<User> isExistSecretKey(String uname);

  // 检查当前密码是否一致
  @Select("select password from users where uname = #{uname}")
  List<User> isExistPassword(String uname);

  // 修改密码
  @Update("update users set password = #{password} where uname = #{uname}")
  int modifyPwd(String uname, String password);


  @Delete("delete from users where uid = #{uid}")
  int deleteByPrimaryKey(Integer uid);

  @Options(useGeneratedKeys = true, keyProperty = "uid", keyColumn = "uid")
  @Insert({
          "insert into users (uid, uname, password, secretKey)",
          "values (#{uid}, #{uname}, #{password}, #{secretKey})"
  })
  int insert(User user);

  @Select({
          "select",
          "uid, uname, password, secretKey",
          "from users",
          "where uid = #{uid}"
  })
  User selectByPrimaryKey(Integer uid);

  @Select("select uname from users where uid = #{uid}")
  String getByUserUname(int uid);

  @Update({
          "update users",
          "set",
          "uname = #{uname}, password = #{password}, secretKey = #{secretKey}",
          "where uid = #{uid}"
  })
  int updateByPrimaryKey(User user);
}
