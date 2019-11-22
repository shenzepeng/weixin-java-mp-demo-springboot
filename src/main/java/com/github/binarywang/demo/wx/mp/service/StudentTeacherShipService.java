package com.github.binarywang.demo.wx.mp.service;

import com.github.binarywang.demo.wx.mp.reqeust.AddShipRequest;
import com.github.binarywang.demo.wx.mp.reqeust.DeleteShipRequest;
import io.swagger.models.auth.In;

/**
 * @Auther: szp
 * @Date: 2019/11/22 13:23
 * @Description: 沈泽鹏写点注释吧
 */

public interface StudentTeacherShipService {
    Integer addShip(AddShipRequest addShipRequest);
    Integer deleteShip(DeleteShipRequest deleteShipRequest);
}
