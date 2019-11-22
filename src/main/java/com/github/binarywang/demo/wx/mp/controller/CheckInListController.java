package com.github.binarywang.demo.wx.mp.controller;

import com.github.binarywang.demo.wx.mp.common.SzpJsonResult;
import com.github.binarywang.demo.wx.mp.pojo.CheckList;
import com.github.binarywang.demo.wx.mp.service.CheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: szp
 * @Date: 2019/11/18 16:09
 * @Description: 添加签到
 */
@RestController
public class CheckInListController {
    @Autowired
    private CheckListService checkListService;
    @PostMapping
    public SzpJsonResult<Integer> addCheckIn(@RequestBody CheckList checkList){
        return SzpJsonResult.ok(checkListService.insertCheckList(checkList));
    }

}
