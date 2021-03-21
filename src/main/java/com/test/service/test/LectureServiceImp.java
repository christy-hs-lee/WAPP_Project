package com.test.service.test;

import com.test.dao.LectureDao;
import com.test.dto.LectureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LectureServiceImp implements LectureService {
    @Autowired
    LectureDao lectureDao;

    @Override
    public ArrayList<LectureDto> getItemList() {
        return lectureDao.getItemList();
    }

    @Override
    public void deleteItem(int lecNo) {
        lectureDao.deleteItem(lecNo);
    }

    @Override
    public void addItem(LectureDto lectureDto) {
        lectureDao.addItem(lectureDto);
    }

    @Override
    public LectureDto selectItem(int lecNo) {
        LectureDto lecture = lectureDao.selectItem(lecNo);
        return lecture;
    }

    @Override
    public void editItem(LectureDto lectureDto) {
        lectureDao.editItem(lectureDto);
    }
}
