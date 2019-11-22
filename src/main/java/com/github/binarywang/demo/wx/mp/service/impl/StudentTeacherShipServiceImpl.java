package com.github.binarywang.demo.wx.mp.service.impl;

import com.github.binarywang.demo.wx.mp.dao.TeacherStudentShipDao;
import com.github.binarywang.demo.wx.mp.pojo.StudentTeacherShip;
import com.github.binarywang.demo.wx.mp.reqeust.AddShipRequest;
import com.github.binarywang.demo.wx.mp.reqeust.DeleteShipRequest;
import com.github.binarywang.demo.wx.mp.service.StudentTeacherShipService;
import com.github.binarywang.demo.wx.mp.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/22 13:35
 * @Description: 沈泽鹏写点注释吧
 */
@Service
public class StudentTeacherShipServiceImpl implements StudentTeacherShipService {
    @Autowired
    private TeacherStudentShipDao shipDao;
    @Override
    public Integer addShip(AddShipRequest addShipRequest) {
        List<StudentTeacherShip> teacherAndStudent = shipDao.findTeacherAndStudent(addShipRequest.getStudentId(), addShipRequest.getTeacherId());
        StudentTeacherShip studentTeacherShip=new StudentTeacherShip();
        BeanUtils.copyProperties(addShipRequest,studentTeacherShip);
        studentTeacherShip.setStatus((short)0);
        studentTeacherShip.setCreateTime(DateUtils.getLocalDate());
        studentTeacherShip.setUpdateTime(DateUtils.getLocalDate());
        //第一次添加
        if (CollectionUtils.isEmpty(teacherAndStudent)){
            return shipDao.insertTeacherStudentShip(studentTeacherShip);
        }
        //将之前删除的再修改状态
        StudentTeacherShip ship = teacherAndStudent.get(0);
        ship.getUpdateTime(DateUtils.getLocalDate());
        ship.setStatus((short)0);
        return shipDao.updateTeacherStudentShip(ship);
    }

    @Override
    public Integer deleteShip(DeleteShipRequest deleteShipRequest) {
        if (deleteShipRequest==null||deleteShipRequest.getId()==null){
            throw new RuntimeException("没有传入id");
        }
        Long id = deleteShipRequest.getId();
        StudentTeacherShip teacherAndStudent = shipDao.findTeacherAndStudent(id);
        teacherAndStudent.setStatus((short)1);
        teacherAndStudent.setUpdateTime(DateUtils.getLocalDate());
        return shipDao.updateTeacherStudentShip(teacherAndStudent);
    }
}
