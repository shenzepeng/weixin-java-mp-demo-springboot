package com.github.binarywang.demo.wx.mp.service.impl;

import com.github.binarywang.demo.wx.mp.dao.UserDao;
import com.github.binarywang.demo.wx.mp.pojo.User;
import com.github.binarywang.demo.wx.mp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/18 11:19
 * @Description: 沈泽鹏写点注释吧
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Integer insertUser(User user) {
        if (user==null){
            return 0;
        }
        String openId = user.getOpenId();
        if (StringUtils.isEmpty(openId)){
            throw new RuntimeException("openId不能为null");
        }
        List<User> userByOpenId = userDao.findUserByOpenId(openId);
        if (!CollectionUtils.isEmpty(userByOpenId)){
            log.info("该用户已经添加过了，用户是-{}",userByOpenId.get(0));
            return 0;
        }
        return userDao.insertUser(user);
    }

    @Override
    public Integer updateUser(User user) {
        if (user==null||user.getId()==null){
            throw new RuntimeException("接收参数不正常");
        }
        return userDao.updateUser(user);
    }

    @Override
    public User findUserByOpenId(String openId) {
        List<User> userByOpenId = userDao.findUserByOpenId(openId);
        if (CollectionUtils.isEmpty(userByOpenId)){
            return null;
        }
        return userByOpenId.get(0);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserByPrimaryKey(id);
    }
}
