package com.test.service.test;

import com.test.dto.UserDto;

import java.util.ArrayList;

public interface UserService {
    void addItem(UserDto userDto);
    ArrayList<UserDto> getItemList();
    UserDto selectItem(int userNo);
    void deleteItem(int userNo);
    void editItem(UserDto userDto);
}
