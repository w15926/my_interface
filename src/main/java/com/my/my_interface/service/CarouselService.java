package com.my.my_interface.service;

import com.alibaba.fastjson.JSONObject;
import com.my.my_interface.mapper.CarouselMapper;
import com.my.my_interface.model.Carousel;
import com.my.my_interface.pkg.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselService {
  @Autowired
  private CarouselMapper mapper;

  public String carouselData() {
    List<Carousel> data = mapper.carouselData();
    if (data.size() == 0) {
      APIResult pkg = new APIResult();
      return pkg.getError("暂无轮播图数据");
    }
    JSONObject jo = new JSONObject();
    jo.put("carouselData", data);
    APIResult pkg = new APIResult();
    pkg.setResult("success");
    pkg.setData(jo);
    return pkg.toString();
  }
}
