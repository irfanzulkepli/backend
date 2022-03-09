package com.imocha.lms.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.lms.users.model.UsersListResponse;
import com.imocha.lms.users.service.UsersService;

@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("list")
    public List<UsersListResponse> list() {
        return usersService.list();
    }

    @GetMapping("list-v2")
    public List<UsersListResponse> listv1(@RequestBody String name) {
        return usersService.list();
    }
}
