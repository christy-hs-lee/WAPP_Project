package com.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PopupDto {
    private int popNo;
    private String popType;
    private String popImg;
    private Date popRegDate;

    public String getPopImg() {
        return popImg;
    }

    public void setPopImg(String popImg) {
        this.popImg = popImg;
    }
}
