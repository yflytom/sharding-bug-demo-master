package com.example.shardingbugdemo.service;


import com.example.shardingbugdemo.entity.User;
import com.example.shardingbugdemo.mapper.UserMapper;
import org.springframework.stereotype.Service;


@Service
public class UserService extends BaseService<UserMapper, User> {

}
