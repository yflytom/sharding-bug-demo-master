package com.example.shardingbugdemo.controller;

import com.example.shardingbugdemo.entity.Books;
import com.example.shardingbugdemo.entity.User;
import com.example.shardingbugdemo.service.BooksService;
import com.example.shardingbugdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yhc
 * @Date 2024/1/26
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private BooksService booksService;


    @GetMapping(value = "/tran")
    public List<User> userList2() {
        userService.testTran();
        return null;
    }


    @GetMapping(value = "/tran2")
    public List<User> tran2() {
        userService.tran2();
        return null;
    }

    @GetMapping(value = "/user")
    public List<User> userList() {
        List<User> list = userService.lambdaQuery().list();
        return list;
    }

    @GetMapping(value = "/book")
    public List<Books> booksList() {
        List<Books> list = booksService.lambdaQuery().list();
        return list;
    }
}
