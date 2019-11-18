package com.github.binarywang.demo.wx.mp.dao;

import com.github.binarywang.demo.wx.mp.mapper.CheckInMapper;
import com.github.binarywang.demo.wx.mp.mapper.CheckListMapper;
import com.github.binarywang.demo.wx.mp.pojo.CheckIn;
import com.github.binarywang.demo.wx.mp.pojo.CheckInExample;
import com.github.binarywang.demo.wx.mp.pojo.CheckList;
import com.github.binarywang.demo.wx.mp.pojo.CheckListExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/17 19:03
 * @Description: 沈泽鹏写点注释吧
 */
@Repository
public class CheckInListDao {
    @Autowired
    private CheckListMapper checkListMapper;

    /**
     * 添加签到
     * @param checkList
     * @return
     */
    public Integer addCheckInList(CheckList checkList){
        return checkListMapper.insert(checkList);
    }

    /**
     * 查看是否已经签到
     * @param
     * @param checkInId
     * @return
     */
    public List<CheckList> hasCheckInList(List<Long> studentIds,Long checkInId){
        CheckListExample checkListExample=new CheckListExample();
        checkListExample.createCriteria()
            .andStudentIdIn(studentIds)
            .andCheckInIdEqualTo(checkInId);
        return checkListMapper.selectByExample(checkListExample);
    }
    public List<CheckList> hasCheckIn(Long studentId,Long checkInId){
        CheckListExample checkListExample=new CheckListExample();
        checkListExample.createCriteria()
            .andStudentIdEqualTo(studentId)
            .andCheckInIdEqualTo(checkInId);
        return checkListMapper.selectByExample(checkListExample);
    }
    /**
     * 查看没有签到的人
     */
    public List<CheckList> doNotCheckIn(List<Long> studentIds,Long checkInId){
        CheckListExample checkListExample=new CheckListExample();
        checkListExample.createCriteria()
            .andStudentIdNotIn(studentIds)
            .andCheckInIdEqualTo(checkInId);
        return checkListMapper.selectByExample(checkListExample);
    }

}
