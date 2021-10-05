package com.my.my_interface.mapper;

import com.my.my_interface.model.CategoryItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryItemsMapper {
  @Select("select * from categoryItems")
  List<CategoryItems> categoryItems();
}
