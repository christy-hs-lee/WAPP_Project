package com.test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class BannerDto {
    private int banNo;
    private String banImg;
    private int banCount;
    private Date banRegDate; // date로 바꿀까?
    private String banTitle;
    private String banContent;


    public void setBanImg(String banImg) {
        this.banImg =  banImg;
    }
    public String getBanImg() { return banImg; }

}
