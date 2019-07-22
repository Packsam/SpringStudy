package com.example.demo.mapper;

import com.example.demo.model.Commodity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityMapper {
    @Insert("insert into commodity (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
     void create(Commodity commodity);

@Select("select * from commodity")
    List<Commodity> list();
}
