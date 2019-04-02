package com.abin.park_system.service;

import com.abin.park_system.entity.Image;
import com.abin.park_system.mapper.ImageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageService {
    @Resource
    private ImageMapper imageMapper;


    public void insert(Image image) {
        imageMapper.insert(image);

    }

    public List<Image> selectById(String id) {
       return imageMapper.selectById(id);

    }

}
