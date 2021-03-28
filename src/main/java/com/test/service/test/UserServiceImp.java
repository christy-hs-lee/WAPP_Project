package com.test.service.test;

import com.test.dao.UserDao;
import com.test.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public ArrayList<UserDto> getItemList() {
        return userDao.getItemList();
    }

    @Override
    public void addItem(UserDto userDto) {
        System.out.println("insertUser Service do");
        userDao.addItem(userDto);
        System.out.println("insertUser Service end");
    }
    @Override
    public UserDto selectItem(int userNo) {
        return userDao.selectItem(userNo);
    }

    @Override
    public void deleteItem(int userNo) {
        userDao.deleteItem(userNo);
    }

    @Override
    public void editItem(UserDto userDto){
        userDao.editItem(userDto);
    }

}
