package com.test.dao;

import com.test.dto.PopupDto;
import com.test.mapper.PopupMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public class PopupDao {
    @Autowired
    SqlSession sqlSession;

    public ArrayList<PopupDto> getItemList(){
        try{
            PopupMapper popupMapper = sqlSession.getMapper(PopupMapper.class);
            return popupMapper.getItemList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void addItem(PopupDto popupDto){
        try{
            PopupMapper popupMapper = sqlSession.getMapper(PopupMapper.class);
            popupMapper.addItem(popupDto);
            System.out.println("dao: " + popupDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteItem(int popNo){
        try{
            PopupMapper popupMapper = sqlSession.getMapper(PopupMapper.class);
            popupMapper.deleteItem(popNo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public PopupDto selectItem(int popNo){
        try{
            PopupMapper popupMapper = sqlSession.getMapper(PopupMapper.class);
            System.out.println("select: " + popNo);
            return popupMapper.selectItem(popNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void editItem(PopupDto popupDto) {
        try{
            PopupMapper popupMapper = sqlSession.getMapper(PopupMapper.class);
            popupMapper.editItem(popupDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
