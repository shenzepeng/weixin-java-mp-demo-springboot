package com.github.binarywang.demo.wx.mp.mapper;

import com.github.binarywang.demo.wx.mp.pojo.CheckIn;
import com.github.binarywang.demo.wx.mp.pojo.CheckInExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CheckInMapper {
    int countByExample(CheckInExample example);

    int deleteByExample(CheckInExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CheckIn record);

    int insertSelective(CheckIn record);

    List<CheckIn> selectByExample(CheckInExample example);

    CheckIn selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CheckIn record, @Param("example") CheckInExample example);

    int updateByExample(@Param("record") CheckIn record, @Param("example") CheckInExample example);

    int updateByPrimaryKeySelective(CheckIn record);

    int updateByPrimaryKey(CheckIn record);
}
