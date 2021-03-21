package com.test.service.test;

import com.test.dto.LectureDto;

import java.util.ArrayList;

public interface LectureService {
    ArrayList<LectureDto> getItemList();
    void deleteItem(int lecNo);
    void addItem(LectureDto lectureDto);
    LectureDto selectItem(int lecNo);
    void editItem(LectureDto lectureDto);
}
