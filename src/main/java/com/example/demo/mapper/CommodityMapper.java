package com.example.demo.mapper;

import com.example.demo.model.Commodity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityMapper {
    @Insert("insert into commodity (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
     void create(Commodity commodity);

@Select("select * from commodity limit #{offset},#{size}")
    List<Commodity> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);//因为传入的参数并不是对象，则需要写注解。

@Select("select count(1) from commodity")
Integer count();
}
