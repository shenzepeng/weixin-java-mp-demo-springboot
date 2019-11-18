package com.github.binarywang.demo.wx.mp.service;

import com.github.binarywang.demo.wx.mp.dao.CheckInDao;
import com.github.binarywang.demo.wx.mp.dto.CheckInListDto;
import com.github.binarywang.demo.wx.mp.pojo.CheckIn;

import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/18 11:41
 * @Description: 沈泽鹏写点注释吧
 */
public interface CheckInService {
    /**
     * 发起签到
     * @param checkIn
     * @return
     */
     CheckIn addCheck(CheckIn checkIn);

    /**
     * 通过id找到签到本次签到所有的
     * @param id
     * @return
     */
     CheckIn findCheckInById(Long id);

    /**
     * 通过老师id找到所有签到
     * @param teacherId
     * @return
     */
     List<CheckIn> findCheckInByTeacherId(Long teacherId);
    /**
     * 查看本次签到详情
     */
    CheckInListDto findCheckInListDto(Long checkInId);
}
