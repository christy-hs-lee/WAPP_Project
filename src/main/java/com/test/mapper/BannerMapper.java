package com.test.mapper;

import com.test.dto.BannerDto;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface BannerMapper {
    ArrayList<BannerDto> getItemList();
    void insertBanner(@Param("bannerDto") BannerDto bannerDto);
    void deleteItem(@Param("banNo") int banNo);
    void editItem(@Param("bannerDto") BannerDto bannerDto);
    BannerDto selectItem(@Param("banNo") int banNo);
}
