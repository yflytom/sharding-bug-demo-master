package com.example.shardingbugdemo.service;


import com.example.shardingbugdemo.entity.Books;
import com.example.shardingbugdemo.entity.User;
import com.example.shardingbugdemo.mapper.UserMapper;
import org.locationtech.jts.index.hprtree.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService extends BaseService<UserMapper, User> {

    @Autowired
    private BooksService booksService;

    @Transactional(rollbackFor = Exception.class)
    public void testTran() {
       baseMapper.testSel();
    }
    @Transactional(rollbackFor = Exception.class)
    public void tran2() {

        List<User> list = this.lambdaQuery().list();
        list.forEach(item -> {
            item.setUsername("tomm2");
            this.updateById(item);
        });

        List<Books> list1 = booksService.lambdaQuery().list();
        list1.forEach(item -> {
            item.setTitle("tom 33");
            booksService.lambdaUpdate().set(Books::getTitle, "tom 3 ==" + item.getId()).eq(Books::getId, item.getId()).update();
        });
        throw new NullPointerException("测试事务回滚");

    }
}
