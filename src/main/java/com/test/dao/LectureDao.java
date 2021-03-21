package com.test.dao;

import com.test.dto.LectureDto;
import com.test.mapper.LectureMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class LectureDao {
    @Autowired
    SqlSession sqlSession;

    public ArrayList<LectureDto> getItemList(){
        try{
            System.out.println("lec.do Dao do");
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            System.out.println("lec.do Dao end");
            return lectureMapper.getItemList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void deleteItem(int lecNo){
        try{
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            lectureMapper.deleteItem(lecNo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addItem(LectureDto lectureDto){
        try{
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            lectureMapper.addItem(lectureDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public LectureDto selectItem(int lecNo){
        try{
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            System.out.println("select: " + lecNo);
            return lectureMapper.selectItem(lecNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void editItem(LectureDto lectureDto){
        try{
            LectureMapper lectureMapper = sqlSession.getMapper(LectureMapper.class);
            lectureMapper.editItem(lectureDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
