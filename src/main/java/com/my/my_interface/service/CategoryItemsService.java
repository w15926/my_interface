package com.my.my_interface.service;

import com.alibaba.fastjson.JSONObject;
import com.my.my_interface.mapper.CarouselMapper;
import com.my.my_interface.mapper.CategoryItemsMapper;
import com.my.my_interface.model.Carousel;
import com.my.my_interface.model.CategoryItems;
import com.my.my_interface.pkg.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryItemsService {
  @Autowired
  private CategoryItemsMapper mapper;

  public String categoryItems() {
    List<CategoryItems> data = mapper.categoryItems();
    if (data.size() == 0) {
      APIResult pkg = new APIResult();
      return pkg.getError("暂无分类数据");
    }
    JSONObject jo = new JSONObject();
    jo.put("list", data);
    APIResult pkg = new APIResult();
    pkg.setResult("success");
    pkg.setData(jo);
    return pkg.toString();
  }

}
