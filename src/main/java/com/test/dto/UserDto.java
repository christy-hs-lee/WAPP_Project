package com.test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class UserDto {
    private int userNo;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Date userRegDate;

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserNo() {
        return userNo;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
}
