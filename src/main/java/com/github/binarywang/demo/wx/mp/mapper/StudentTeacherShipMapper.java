package com.github.binarywang.demo.wx.mp.mapper;

import com.github.binarywang.demo.wx.mp.pojo.StudentTeacherShip;
import com.github.binarywang.demo.wx.mp.pojo.StudentTeacherShipExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface StudentTeacherShipMapper {
    int countByExample(StudentTeacherShipExample example);

    int deleteByExample(StudentTeacherShipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StudentTeacherShip record);

    int insertSelective(StudentTeacherShip record);

    List<StudentTeacherShip> selectByExample(StudentTeacherShipExample example);

    StudentTeacherShip selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StudentTeacherShip record, @Param("example") StudentTeacherShipExample example);

    int updateByExample(@Param("record") StudentTeacherShip record, @Param("example") StudentTeacherShipExample example);

    int updateByPrimaryKeySelective(StudentTeacherShip record);

    int updateByPrimaryKey(StudentTeacherShip record);
}
