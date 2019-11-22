package com.github.binarywang.demo.wx.mp.service.impl;

import com.github.binarywang.demo.wx.mp.dao.CheckInDao;
import com.github.binarywang.demo.wx.mp.dao.CheckInListDao;
import com.github.binarywang.demo.wx.mp.pojo.CheckIn;
import com.github.binarywang.demo.wx.mp.pojo.CheckList;
import com.github.binarywang.demo.wx.mp.service.CheckListService;
import com.github.binarywang.demo.wx.mp.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.security.cert.CollectionCertStoreParameters;
import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/18 12:28
 * @Description: 沈泽鹏写点注释吧
 */
@Service
public class CheckInListServiceImpl implements CheckListService {
    @Autowired
    private CheckInListDao checkInListDao;
    @Autowired
    private CheckInDao checkInDao;
    @Override
    public Integer insertCheckList(CheckList checkList) {
        if (checkList==null){
            throw  new RuntimeException("没有传参");
        }
        //签到时间是否过期
        Long checkInId = checkList.getCheckInId();
        CheckIn checkInById = checkInDao.findCheckInById(checkInId);
        if (checkInById.getExpiredTime().after(DateUtils.getLocalDate())){
            throw new RuntimeException("签到时间已过期，无法在签到");
        }
        if (!StringUtils.isEmpty(checkList.getStudentId())&&
            !StringUtils.isEmpty(checkList.getCheckInId())){
            List<CheckList> checkLists = checkInListDao.hasCheckIn(checkList.getStudentId(), checkList.getCheckInId());
            if (CollectionUtils.isEmpty(checkLists)){
                return checkInListDao.addCheckInList(checkList);
            }else {
                throw new RuntimeException("已经签到了，不用重复签到");
            }
        }
        return null;
    }
}
