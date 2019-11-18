package com.github.binarywang.demo.wx.mp.mapper;

import com.github.binarywang.demo.wx.mp.pojo.MsgHistory;
import com.github.binarywang.demo.wx.mp.pojo.MsgHistoryExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MsgHistoryMapper {
    int countByExample(MsgHistoryExample example);

    int deleteByExample(MsgHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MsgHistory record);

    int insertSelective(MsgHistory record);

    List<MsgHistory> selectByExample(MsgHistoryExample example);

    MsgHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MsgHistory record, @Param("example") MsgHistoryExample example);

    int updateByExample(@Param("record") MsgHistory record, @Param("example") MsgHistoryExample example);

    int updateByPrimaryKeySelective(MsgHistory record);

    int updateByPrimaryKey(MsgHistory record);
}
