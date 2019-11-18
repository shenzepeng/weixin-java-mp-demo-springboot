package com.github.binarywang.demo.wx.mp.service;

import com.github.binarywang.demo.wx.mp.mapper.UserMapper;
import com.github.binarywang.demo.wx.mp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: szp
 * @Date: 2019/11/18 11:12
 * @Description: 沈泽鹏写点注释吧
 */

public interface UserService {
    /**
     * 添加用户
     */
    Integer insertUser(User user);

    /**
     * 更新用户
     * @return
     */
    Integer updateUser(User user);

    /**
     * 通过openid找到user
     * @return
     */
    User findUserByOpenId(String openId);

    /**
     * 通过主键id找到user
     * @return
     */
    User findUserById(Long id);
}
