package com.my.my_interface.api;

import com.my.my_interface.pkg.APIResult;
import com.my.my_interface.service.CategoryItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryItemsAPI {
  @Autowired
  private CategoryItemsService service;

  @PostMapping("/categoryItems")
  public String categoryItems() {
    try {
      System.out.println("21321312312312");
      return service.categoryItems();
    } catch (Exception e) {
      return new APIResult().getError("操作失败");
    }
  }
}
