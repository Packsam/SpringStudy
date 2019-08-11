package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<CommodityDTO> commoditys;
    private boolean showPrevious;
    private boolean showFirstpage;
    private boolean showNext;
    private boolean showEndpage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        if(page<=totalPage) {
            pages.add(page);
        }

        this.totalPage = totalPage;
        for(int i = 1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        this.page = page;

        if(page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        if(page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }

        if(pages.contains(1)){
            showFirstpage = false;
        }else{
            showFirstpage = true;
        }
        if(pages.contains(totalPage)){
            showEndpage = false;
        }else{
            showEndpage = true;
        }
    }
}
