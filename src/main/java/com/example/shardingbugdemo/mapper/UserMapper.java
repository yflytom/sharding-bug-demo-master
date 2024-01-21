package com.example.shardingbugdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shardingbugdemo.entity.User;
import org.springframework.stereotype.Component;


@Component
public interface UserMapper extends BaseMapper<User> {
}
