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


    public void setPagination(Integer totalcount, Integer page, Integer size) {
        Integer totalPage = totalcount%size==0?totalcount/size:totalcount/size+1;
        pages.add(page);
        for(int i = 1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-1);
            }
            if(page+i<=totalcount){
                pages.add(page+i);
            }
        }





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
        if(pages.contains(totalcount)){
            showEndpage = false;
        }else{
            showEndpage = true;
        }
    }
}
