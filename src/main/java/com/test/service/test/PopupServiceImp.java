package com.test.service.test;

import com.test.dao.PopupDao;
import com.test.dto.PopupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class PopupServiceImp implements PopupService {
    @Autowired
    PopupDao popupDao;

    @Override
    public ArrayList<PopupDto> getItemList() {
        return popupDao.getItemList();
    }

    @Override
    public void addItem(PopupDto popupDto) {
        System.out.println("insertPopup Service do");
        popupDao.addItem(popupDto);
        System.out.println("insertPopup Service end");
    }
    @Override
    public PopupDto selectItem(int popNo) {
        return popupDao.selectItem(popNo);
    }

    @Override
    public void deleteItem(int popNo) {
        popupDao.deleteItem(popNo);
    }

    @Override
    public void editItem(PopupDto popupDto){
        popupDao.editItem(popupDto);
    }

}
