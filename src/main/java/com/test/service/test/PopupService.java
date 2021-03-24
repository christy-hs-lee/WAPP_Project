package com.test.service.test;

import com.test.dto.PopupDto;

import java.util.ArrayList;

public interface PopupService {
    void addItem(PopupDto popupDto);
    ArrayList<PopupDto> getItemList();
    PopupDto selectItem(int popNo);
    void deleteItem(int popNo);
    void editItem(PopupDto popupDto);
}
