package com.github.binarywang.demo.wx.mp.dao;

import com.github.binarywang.demo.wx.mp.mapper.StudentTeacherShipMapper;
import com.github.binarywang.demo.wx.mp.mapper.TeacherMapper;
import com.github.binarywang.demo.wx.mp.pojo.StudentTeacherShip;
import com.github.binarywang.demo.wx.mp.pojo.StudentTeacherShipExample;
import com.github.binarywang.demo.wx.mp.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/17 20:39
 * @Description: 沈泽鹏写点注释吧
 */
@Repository
public class TeacherStudentShipDao {
    @Autowired
    private StudentTeacherShipMapper studentTeacherShipMapper;

    /**
     * 添加关系
     * @param studentTeacherShip
     * @return
     */
    public Integer insertTeacherStudentShip(StudentTeacherShip studentTeacherShip){
        return studentTeacherShipMapper.insert(studentTeacherShip);
    }

    /**
     * 通过老师id找到所有学生
     * @param teacherId
     * @return
     */
    public List<StudentTeacherShip>  findShips(Long teacherId){
        StudentTeacherShipExample studentTeacherShipExample=new StudentTeacherShipExample();
        studentTeacherShipExample.createCriteria()
            .andTeacherIdEqualTo(teacherId)
            .andStatusEqualTo((short)0);
        return studentTeacherShipMapper.selectByExample(studentTeacherShipExample);
    }

    /**
     * 找到相关的老师
     * @param studentId
     * @return
     */
    public List<StudentTeacherShip> findShipByStudentId(Long studentId){
        StudentTeacherShipExample studentTeacherShipExample=new StudentTeacherShipExample();
        studentTeacherShipExample.createCriteria()
            .andStudentIdEqualTo(studentId)
            .andStatusEqualTo((short)0);
        return studentTeacherShipMapper.selectByExample(studentTeacherShipExample);
    }

    /**
     * 更新关系
     * 0 是当前学生
     * 1 不是当前学生
     * @param studentTeacherShip
     * @return
     */
    public Integer updateTeacherStudentShip(StudentTeacherShip studentTeacherShip){
        return studentTeacherShipMapper.updateByPrimaryKeySelective(studentTeacherShip);
    }

    /**
     * 通过学生id和老师id查看该信息是否已经添加了
     * @param studentId
     * @param teacherId
     * @return
     */
    public List<StudentTeacherShip> findTeacherAndStudent(Long studentId,Long teacherId){
        StudentTeacherShipExample studentTeacherShipExample=new StudentTeacherShipExample();
        studentTeacherShipExample.createCriteria()
            .andStudentIdEqualTo(studentId)
            .andTeacherIdEqualTo(teacherId);
        return studentTeacherShipMapper.selectByExample(studentTeacherShipExample);
    }

    /**
     * 通过主键查找
     * @param id
     * @return
     */
    public StudentTeacherShip findTeacherAndStudent(Long id){
        return studentTeacherShipMapper.selectByPrimaryKey(id);
    }
}
