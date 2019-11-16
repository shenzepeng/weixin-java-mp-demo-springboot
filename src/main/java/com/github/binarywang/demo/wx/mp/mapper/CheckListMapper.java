package com.github.binarywang.demo.wx.mp.mapper;

import com.github.binarywang.demo.wx.mp.pojo.CheckList;
import com.github.binarywang.demo.wx.mp.pojo.CheckListExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CheckListMapper {
    int countByExample(CheckListExample example);

    int deleteByExample(CheckListExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CheckList record);

    int insertSelective(CheckList record);

    List<CheckList> selectByExample(CheckListExample example);

    CheckList selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CheckList record, @Param("example") CheckListExample example);

    int updateByExample(@Param("record") CheckList record, @Param("example") CheckListExample example);

    int updateByPrimaryKeySelective(CheckList record);

    int updateByPrimaryKey(CheckList record);
}
