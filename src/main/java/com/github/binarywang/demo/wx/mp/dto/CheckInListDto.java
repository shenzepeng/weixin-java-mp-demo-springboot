package com.github.binarywang.demo.wx.mp.dto;

import com.github.binarywang.demo.wx.mp.pojo.CheckIn;
import com.github.binarywang.demo.wx.mp.pojo.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/18 13:55
 * @Description: 本次签到签到情况
 */
@Data
public class CheckInListDto {
    private CheckIn checkIn;
    private List<User> signedUserList;
    private List<User> unSignedUserList;
    private Integer total;
    private Integer signedNumber;
    private Date expiredTime;
}
