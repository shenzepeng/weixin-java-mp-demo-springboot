package com.github.binarywang.demo.wx.mp.service;

import com.github.binarywang.demo.wx.mp.pojo.Teacher;

/**
 * @Auther: szp
 * @Date: 2019/11/18 11:30
 * @Description: 沈泽鹏写点注释吧
 */
public interface TeacherService {
    Integer insert(Teacher teacher);
    Teacher login(String username,String password);
    Teacher findTeacherById(Long id);
}
