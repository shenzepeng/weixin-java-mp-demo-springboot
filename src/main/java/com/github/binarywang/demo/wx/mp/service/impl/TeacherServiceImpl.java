package com.github.binarywang.demo.wx.mp.service.impl;

import com.github.binarywang.demo.wx.mp.dao.TeacherDao;
import com.github.binarywang.demo.wx.mp.pojo.Teacher;
import com.github.binarywang.demo.wx.mp.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/18 11:32
 * @Description: 沈泽鹏写点注释吧
 */
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Override
    public Integer insert(Teacher teacher) {
        if (teacher==null|| StringUtils.isEmpty(teacher.getUsername())){
            return 0;
        }
        List<Teacher> teacherByUsername = teacherDao.findTeacherByUsername(teacher.getUsername());
        if (CollectionUtils.isEmpty(teacherByUsername)){
            return teacherDao.insertTeacher(teacher);
        }else {
            throw new RuntimeException("该用户已经添加过了");
        }

    }

    @Override
    public Teacher login(String username, String password) {
        List<Teacher> usernameAndPassword = teacherDao.findUsernameAndPassword(username, password);
        if (CollectionUtils.isEmpty(usernameAndPassword)){
            return null;
        }
        return usernameAndPassword.get(0);
    }

    @Override
    public Teacher findTeacherById(Long id) {
        return  teacherDao.findTeacherById(id);
    }
}
