package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

@Data
public class CommodityDTO {
    private  Integer id;
    private  String title;
    private  String description;
    private  Integer price;
    private  Long gmtCreate;
    private  Long gmtModified;
    private  Integer creator;
    private  String status;
    private  Integer commentCount;
    private  Integer viewCount;
    private  String tag;
    private User user;
}
