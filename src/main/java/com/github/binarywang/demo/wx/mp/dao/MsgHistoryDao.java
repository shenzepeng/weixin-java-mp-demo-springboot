package com.github.binarywang.demo.wx.mp.dao;

import com.github.binarywang.demo.wx.mp.mapper.MsgHistoryMapper;
import com.github.binarywang.demo.wx.mp.pojo.MsgHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Auther: szp
 * @Date: 2019/11/17 11:24
 * @Description: 沈泽鹏写点注释吧
 */
@Repository
@Slf4j
public class MsgHistoryDao {
    @Autowired
    private MsgHistoryMapper msgHistoryMapper;

    public Integer insertMsgHistory(MsgHistory msgHistory){
        log.info("保留本地的消息是-{}",msgHistory);
        return msgHistoryMapper.insert(msgHistory);
    }
}
