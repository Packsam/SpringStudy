package com.example.demo.service;

import com.example.demo.dto.CommodityDTO;
import com.example.demo.mapper.CommodityMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Commodity;
import com.example.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private UserMapper userMapper;

    public List<CommodityDTO> list() {
        List<Commodity> commoditys = commodityMapper.list();
        List<CommodityDTO> commodityDTOs = new ArrayList<>();
        for(Commodity commodity:commoditys){
            User user=userMapper.findById(commodity.getCreator());
            CommodityDTO commodityDTO = new CommodityDTO();
            BeanUtils.copyProperties(commodity,commodityDTO);
            commodityDTO.setUser(user);
            commodityDTOs.add(commodityDTO);
        }
        return commodityDTOs;
    }
}
