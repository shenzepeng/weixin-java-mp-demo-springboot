package com.github.binarywang.demo.wx.mp.reqeust;

import lombok.Data;

/**
 * @Auther: szp
 * @Date: 2019/11/22 13:19
 * @Description: 学生和老师关系的request
 */
@Data
public class AddShipRequest {
    /**
     * userId
     */
    private Long studentId;

    private Long teacherId;
}
