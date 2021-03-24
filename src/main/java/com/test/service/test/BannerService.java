package com.test.service.test;

import com.test.dto.BannerDto;

import java.util.ArrayList;

public interface BannerService {
    void addItem(BannerDto bannerDto);
    ArrayList<BannerDto> getItemList();
    BannerDto selectItem(int banNo);
    void deleteItem(int banNo);
    void editItem(BannerDto bannerDto);
}
