package com.my.my_interface.api;

import com.my.my_interface.pkg.APIResult;
import com.my.my_interface.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarouselAPI {
  @Autowired
  private CarouselService service;

  @PostMapping("/carousel")
  public String carouselData() {
    try {
      return service.carousel();
    } catch (Exception e) {
      APIResult pkg = new APIResult();
      return new APIResult().getError("操作失败");
    }
  }
}
