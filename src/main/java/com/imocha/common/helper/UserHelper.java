package com.imocha.common.helper;

import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

    @Autowired
    UsersService usersService;

    public Users getCurrentLoginUser() {
        return usersService.get(1);
    }
}
