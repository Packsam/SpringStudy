package com.example.demo.service;

import com.example.demo.dto.CommodityDTO;
import com.example.demo.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalcount = commodityMapper.count();//获得所有商品总数
        Integer totalPage;
        totalPage = totalcount % size == 0 ? totalcount / size : totalcount / size + 1;

        if (page < 1) {//页码边界控制
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);


        Integer offset = size * (page - 1);
        List<Commodity> commoditys = commodityMapper.list(offset, size);
        List<CommodityDTO> commodityDTOs = new ArrayList<>();
        for (Commodity commodity : commoditys) {
            User user = userMapper.findById(commodity.getCreator());
            CommodityDTO commodityDTO = new CommodityDTO();
            BeanUtils.copyProperties(commodity, commodityDTO);
            commodityDTO.setUser(user);
            commodityDTOs.add(commodityDTO);
        }
        paginationDTO.setCommoditys(commodityDTOs);

        return paginationDTO;
    }

    public PaginationDTO list(Integer userid, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalcount = commodityMapper.countByUserId(userid);//获得所有商品总数
        Integer totalPage;
        totalPage = totalcount % size == 0 ? totalcount / size : totalcount / size + 1;
        if(totalPage==0){
            totalPage =1;
        }
        if (page > totalPage) {
            page = totalPage;
        }


        if (page < 1) {//页码边界控制
            page = 1;
        }



        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Commodity> commoditys = commodityMapper.listByUserId(userid, offset, size);
        List<CommodityDTO> commodityDTOs = new ArrayList<>();
        for (Commodity commodity : commoditys) {
            User user = userMapper.findById(commodity.getCreator());
            CommodityDTO commodityDTO = new CommodityDTO();
            BeanUtils.copyProperties(commodity, commodityDTO);
            commodityDTO.setUser(user);
            commodityDTOs.add(commodityDTO);
        }
        paginationDTO.setCommoditys(commodityDTOs);

        return paginationDTO;
    }
}
