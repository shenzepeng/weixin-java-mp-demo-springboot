package com.github.binarywang.demo.wx.mp.service;

import com.github.binarywang.demo.wx.mp.pojo.CheckList;

/**
 * @Auther: szp
 * @Date: 2019/11/18 12:26
 * @Description: 学生根据签到id进行签到
 */
public interface CheckListService {
    Integer insertCheckList(CheckList checkList);
}
