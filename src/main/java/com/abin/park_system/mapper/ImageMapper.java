package com.abin.park_system.mapper;


import com.abin.park_system.entity.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper {

    @Insert("insert into image(park_id,img_path) values(#{park_id},#{img_path})")
    public void insert(Image image);

    @Select("select * from image where park_id = #{park_id}")
    public List<Image> selectById(String id);


}
