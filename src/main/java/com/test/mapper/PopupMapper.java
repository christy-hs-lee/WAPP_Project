package com.test.mapper;

import com.test.dto.PopupDto;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface PopupMapper {
    ArrayList<PopupDto> getItemList();
    void addItem(@Param("popupDto") PopupDto popupDto);
    void deleteItem(@Param("popNo") int popNo);
    void editItem(@Param("popupDto") PopupDto popupDto);
    PopupDto selectItem(@Param("popNo") int popNo);
}
