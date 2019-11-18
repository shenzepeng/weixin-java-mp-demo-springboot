package com.github.binarywang.demo.wx.mp.dao;

import com.github.binarywang.demo.wx.mp.mapper.TeacherMapper;
import com.github.binarywang.demo.wx.mp.pojo.Teacher;
import com.github.binarywang.demo.wx.mp.pojo.TeacherExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/17 20:05
 * @Description: 沈泽鹏写点注释吧
 */
@Repository
public class TeacherDao {
    @Autowired
    private TeacherMapper teacherMapper;

    public List<Teacher> findUsernameAndPassword(String username,String password){
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria()
            .andUsernameEqualTo(username)
            .andPasswordEqualTo(password);
        return teacherMapper.selectByExample(teacherExample);
    }

    public Teacher findTeacherById(Long id){
        return teacherMapper.selectByPrimaryKey(id);
    }

    public Integer insertTeacher(Teacher teacher){
        return teacherMapper.insert(teacher);
    }

    public List<Teacher> findTeacherByUsername(String username){
        TeacherExample teacherExample=new TeacherExample();
        teacherExample.createCriteria()
            .andUsernameEqualTo(username);
        return teacherMapper.selectByExample(teacherExample);
    }
}
