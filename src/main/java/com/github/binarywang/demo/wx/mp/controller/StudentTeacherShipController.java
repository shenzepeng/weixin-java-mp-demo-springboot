package com.github.binarywang.demo.wx.mp.controller;

import com.github.binarywang.demo.wx.mp.common.SzpJsonResult;
import com.github.binarywang.demo.wx.mp.pojo.StudentTeacherShip;
import com.github.binarywang.demo.wx.mp.reqeust.AddShipRequest;
import com.github.binarywang.demo.wx.mp.reqeust.DeleteShipRequest;
import com.github.binarywang.demo.wx.mp.service.StudentTeacherShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: szp
 * @Date: 2019/11/22 13:16
 * @Description: 沈泽鹏写点注释吧
 */
@RestController
@RequestMapping("ship")
public class StudentTeacherShipController {
    @Autowired
    private StudentTeacherShipService shipService;
    @PostMapping
    public SzpJsonResult addStudent(@RequestBody AddShipRequest addShipRequest){
        return SzpJsonResult.ok(shipService.addShip(addShipRequest));
    }
    @DeleteMapping
    public SzpJsonResult deleteStudent(@RequestBody DeleteShipRequest deleteShipRequest){
        return SzpJsonResult.ok(shipService.deleteShip(deleteShipRequest));
    }
}
