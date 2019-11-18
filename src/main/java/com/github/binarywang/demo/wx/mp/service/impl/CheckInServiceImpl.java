package com.github.binarywang.demo.wx.mp.service.impl;

import com.github.binarywang.demo.wx.mp.dao.CheckInDao;
import com.github.binarywang.demo.wx.mp.dao.CheckInListDao;
import com.github.binarywang.demo.wx.mp.dao.TeacherStudentShipDao;
import com.github.binarywang.demo.wx.mp.dao.UserDao;
import com.github.binarywang.demo.wx.mp.dto.CheckInListDto;
import com.github.binarywang.demo.wx.mp.pojo.CheckIn;
import com.github.binarywang.demo.wx.mp.pojo.CheckList;
import com.github.binarywang.demo.wx.mp.pojo.StudentTeacherShip;
import com.github.binarywang.demo.wx.mp.pojo.User;
import com.github.binarywang.demo.wx.mp.service.CheckInService;
import com.github.binarywang.demo.wx.mp.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/18 12:29
 * @Description: 沈泽鹏写点注释吧
 */
@Slf4j
@Service
public class CheckInServiceImpl implements CheckInService {
    @Autowired
    private CheckInDao checkInDao;
    @Autowired
    private CheckInListDao checkInListDao;
    @Autowired
    private TeacherStudentShipDao teacherStudentShipDao;
    @Autowired
    private UserDao userDao;
    @Override
    public CheckIn addCheck(CheckIn checkIn) {
        if (checkIn==null||StringUtils.isEmpty(checkIn.getTeacherId())){
            throw new RuntimeException("传参不正确");
        }
        List<CheckIn> checkIns = checkInDao.findCheckInId(checkIn.getTeacherId());
        CheckIn lastCheckIn = checkIns.get(checkIns.size() - 1);
        if (lastCheckIn.getExpiredTime().after(DateUtils.getLocalDate())){
            throw new RuntimeException("签到已有签到,不能再次发起签到,待过期时间过了，才可以再次发起签到");
        }
        checkIn.setCreateTime(DateUtils.getLocalDate());
        checkIn.setUpdateTime(DateUtils.getLocalDate());
        Date day = Date.from(LocalDateTime.now()
            .plusMinutes(5)
            .atZone(ZoneId.systemDefault())
            .toInstant());
        checkIn.setExpiredTime(day);
        return null;
    }

    @Override
    public CheckIn findCheckInById(Long id) {
        return checkInDao.findCheckInById(id);
    }

    @Override
    public List<CheckIn> findCheckInByTeacherId(Long teacherId) {
        return checkInDao.findCheckInId(teacherId);
    }

    @Override
    public CheckInListDto findCheckInListDto(Long checkInId) {
        CheckInListDto checkInListDto=new CheckInListDto();
        //1.拿到本次签到的信息
        CheckIn checkInById = checkInDao.findCheckInById(checkInId);
        if (checkInById==null){
            return null;
        }
        //当前老师的学生
        List<StudentTeacherShip> ships = teacherStudentShipDao.findShips(checkInById.getTeacherId());
        List<Long> studentIds=new ArrayList<>();
        ships.forEach((t)->studentIds.add(t.getStudentId()));
        //2.拿到已签到的人数
        List<CheckList> checkLists = checkInListDao.hasCheckInList(studentIds, checkInId);
        List<Long> checkUserIds=new ArrayList<>();
        checkLists.forEach((t)->checkUserIds.add(t.getStudentId()));
        List<User> checkedUser = userDao.findUserInIdList(checkUserIds);
        //3.拿到没有签到的人数
        studentIds.removeAll(checkUserIds);
        List<User> userInIdList = userDao.findUserInIdList(studentIds);
        //4.set值进入checkInListDto
        checkInListDto.setCheckIn(checkInById);
        checkInListDto.setExpiredTime(checkInById.getExpiredTime());
        checkInListDto.setSignedUserList(checkedUser);
        checkInListDto.setUnSignedUserList(userInIdList);
        checkInListDto.setSignedNumber(checkedUser.size());
        checkInListDto.setTotal(studentIds.size());
        return checkInListDto;
    }
}
