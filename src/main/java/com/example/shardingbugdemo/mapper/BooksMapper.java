package com.example.shardingbugdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shardingbugdemo.entity.Books;
import org.springframework.stereotype.Component;


@Component
public interface BooksMapper extends BaseMapper<Books> {
}
