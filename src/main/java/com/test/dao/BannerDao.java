package com.test.dao;

import com.test.dto.BannerDto;
import com.test.mapper.BannerMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public class BannerDao {
    @Autowired
    SqlSession sqlSession;

    public ArrayList<BannerDto> getItemList(){
        try{
            BannerMapper bannerMapper = sqlSession.getMapper(BannerMapper.class);
            return bannerMapper.getItemList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void addItem(BannerDto bannerDto){
        try{
            BannerMapper bannerMapper = sqlSession.getMapper(BannerMapper.class);
            bannerMapper.addItem(bannerDto);
            System.out.println("dao: " + bannerDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteItem(int banNo){
        try{
            BannerMapper bannerMapper = sqlSession.getMapper(BannerMapper.class);
            bannerMapper.deleteItem(banNo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BannerDto selectItem(int banNo){
        try{
            BannerMapper bannerMapper = sqlSession.getMapper(BannerMapper.class);
            System.out.println("select: " + banNo);
            return bannerMapper.selectItem(banNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void editItem(BannerDto bannerDto) {
        try{
            BannerMapper bannerMapper = sqlSession.getMapper(BannerMapper.class);
            bannerMapper.editItem(bannerDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
