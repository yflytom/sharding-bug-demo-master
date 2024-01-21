package com.example.shardingbugdemo.service;


import com.example.shardingbugdemo.entity.Books;
import com.example.shardingbugdemo.mapper.BooksMapper;
import org.springframework.stereotype.Service;


@Service
public class BooksService extends BaseService<BooksMapper, Books> {

}
