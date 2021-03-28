package com.test.mapper;

import com.test.dto.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface UserMapper {
    ArrayList<UserDto> getItemList();
    void addItem(@Param("userDto") UserDto userDto);
    void deleteItem(@Param("userNo") int userNo);
    void editItem(@Param("userDto") UserDto userDto);
    UserDto selectItem(@Param("userNo") int userNo);
}
