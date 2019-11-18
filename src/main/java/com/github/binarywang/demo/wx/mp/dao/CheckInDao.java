package com.github.binarywang.demo.wx.mp.dao;

import com.github.binarywang.demo.wx.mp.mapper.CheckInMapper;
import com.github.binarywang.demo.wx.mp.pojo.CheckIn;
import com.github.binarywang.demo.wx.mp.pojo.CheckInExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/17 18:38
 * @Description: 沈泽鹏写点注释吧
 */
@Repository
public class CheckInDao {
    @Autowired
    private CheckInMapper checkInMapper;

    /**
     * 签到
     * @param checkIn
     * @return
     */
    public Integer addCheckIn(CheckIn checkIn){
        return checkInMapper.insert(checkIn);
    }

    /**
     * 通过teacher_id找到
     * @param teacherId
     * @return
     */
    public List<CheckIn> findCheckInId(Long teacherId){
        CheckInExample checkInExample=new CheckInExample();
        checkInExample.createCriteria()
            .andTeacherIdEqualTo(teacherId);
        return checkInMapper.selectByExample(checkInExample);
    }

    /**
     * 通过主键查找
     * @param id
     * @return
     */
    public CheckIn findCheckInById(Long id){
        return checkInMapper.selectByPrimaryKey(id);
    }


}
