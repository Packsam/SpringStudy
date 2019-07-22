package com.example.demo.model;

import lombok.Data;

@Data
public class Commodity {
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
}
