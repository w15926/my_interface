package com.my.my_interface.mapper;

import com.my.my_interface.model.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarouselMapper {
  @Select("select * from carousel")
  List<Carousel> carouselData();
}
