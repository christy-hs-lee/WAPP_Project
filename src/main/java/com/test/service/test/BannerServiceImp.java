package com.test.service.test;

import com.test.dao.BannerDao;
import com.test.dto.BannerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class BannerServiceImp implements BannerService {
    @Autowired
    BannerDao bannerDao;

    @Override
    public ArrayList<BannerDto> getItemList() {
        return bannerDao.getItemList();
    }

    @Override
    public void addItem(BannerDto bannerDto) {
        System.out.println("insertBanner Service do");
        bannerDao.addItem(bannerDto);
        System.out.println("insertBanner Service end");
    }
    @Override
    public BannerDto selectItem(int banNo) {
        return bannerDao.selectItem(banNo);
    }

    @Override
    public void deleteItem(int banNo) {
        bannerDao.deleteItem(banNo);
    }

    @Override
    public void editItem(BannerDto bannerDto){
        bannerDao.editItem(bannerDto);
    }

}
