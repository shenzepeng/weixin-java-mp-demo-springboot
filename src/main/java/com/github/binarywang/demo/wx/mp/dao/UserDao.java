package com.github.binarywang.demo.wx.mp.dao;

import com.github.binarywang.demo.wx.mp.mapper.UserMapper;
import com.github.binarywang.demo.wx.mp.pojo.User;
import com.github.binarywang.demo.wx.mp.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/18 10:24
 * @Description: 沈泽鹏写点注释吧
 */
@Repository
public class UserDao {
    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户
     * @param user
     * @return
     */
    public Integer insertUser(User user){
        return userMapper.insert(user);
    }

    /**
     * 通过openId拿到用户的信息
     * @param openId
     * @return
     */
    public List<User> findUserByOpenId(String openId){
        UserExample userExample=new UserExample();
        userExample.createCriteria()
            .andOpenIdEqualTo(openId);
        return userMapper.selectByExample(userExample);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public Integer updateUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 通过主键查找
     * @param id
     * @return
     */
    public User findUserByPrimaryKey(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过主键list查
     * @param ids
     * @return
     */
    public List<User> findUserInIdList(List<Long> ids){
        UserExample userExample=new UserExample();
        userExample.createCriteria()
            .andIdIn(ids);
        return userMapper.selectByExample(userExample);
    }
}
