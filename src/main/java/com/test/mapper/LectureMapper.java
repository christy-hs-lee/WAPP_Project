package com.test.mapper;

import com.test.dto.LectureDto;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface LectureMapper {
    ArrayList<LectureDto> getItemList();
    void addItem(@Param("lectureDto") LectureDto lectureDto);
    void deleteItem(@Param("lecNo") int lecNo);
    LectureDto selectItem(@Param("lecNo") int lecNo);
    void editItem(@Param("lectureDto") LectureDto lectureDto);
}
