package com.test.dao;

import com.test.dto.UserDto;
import com.test.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserDao {
    @Autowired
    SqlSession sqlSession;

    public ArrayList<UserDto> getItemList(){
        try{
            System.out.println("user.do Dao do");
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            System.out.println("user.do Dao end");
            return userMapper.getItemList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void deleteItem(int userNo){
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteItem(userNo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addItem(UserDto userDto){
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.addItem(userDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public UserDto selectItem(int userNo){
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            System.out.println("select: " + userNo);
            return userMapper.selectItem(userNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void editItem(UserDto userDto){
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.editItem(userDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
