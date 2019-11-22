package com.github.binarywang.demo.wx.mp.controller;

import com.github.binarywang.demo.wx.mp.common.SzpJsonResult;
import com.github.binarywang.demo.wx.mp.dto.CheckInListDto;
import com.github.binarywang.demo.wx.mp.pojo.CheckIn;
import com.github.binarywang.demo.wx.mp.pojo.CheckList;
import com.github.binarywang.demo.wx.mp.service.CheckInService;
import com.github.binarywang.demo.wx.mp.service.CheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: szp
 * @Date: 2019/11/18 16:08
 * @Description: 沈泽鹏写点注释吧
 */
@RestController
@RequestMapping("checkin")
public class CheckInController {
    @Autowired
    private CheckListService checkListService;
    @Autowired
    private CheckInService checkInService;
    /**
     * 发起签到
     * @return
     */
    @PostMapping
    public SzpJsonResult<Integer> addCheckIn(@RequestBody CheckIn checkIn){
        return SzpJsonResult.ok(checkInService.addCheck(checkIn));
    }

    /**
     * 拿到签到数据
     * @param checkId
     * @return
     */
    @GetMapping
    public SzpJsonResult<CheckInListDto> getSignSatuation(Long checkId){
        return SzpJsonResult.ok(checkInService.findCheckInListDto(checkId));
    }
}
