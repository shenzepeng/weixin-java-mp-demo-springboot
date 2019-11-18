package com.github.binarywang.demo.wx.mp.controller;

import com.github.binarywang.demo.wx.mp.common.SzpJsonResult;
import com.github.binarywang.demo.wx.mp.pojo.Teacher;
import com.github.binarywang.demo.wx.mp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: szp
 * @Date: 2019/11/18 16:08
 * @Description: 沈泽鹏写点注释吧
 */
@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("login")
    public SzpJsonResult<Teacher> login(@RequestParam(value = "username",required = true) String username,
                                        @RequestParam(value = "password",required = true) String password){
        return SzpJsonResult.ok(teacherService.login(username,password));
    }
}
