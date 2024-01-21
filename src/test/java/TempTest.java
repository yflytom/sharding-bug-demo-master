import com.alibaba.fastjson.JSON;
import com.example.shardingbugdemo.ShardingBugDemoApplication;
import com.example.shardingbugdemo.entity.Books;
import com.example.shardingbugdemo.entity.User;
import com.example.shardingbugdemo.service.BooksService;
import com.example.shardingbugdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest(classes = {ShardingBugDemoApplication.class})
@RunWith(SpringRunner.class)
public class TempTest {

    @Autowired
    private BooksService booksService;

    @Autowired
    private UserService userService;


    @Test
    public void test(){


        System.out.println("=======================================================");
//        List<Books> booksList = booksService.lambdaQuery().eq(Books::getPrice, "").list();
//        System.out.println(JSON.toJSONString(booksList));
        List<User> list = userService.lambdaQuery().list();
        System.out.println(JSON.toJSONString(list));

    }

}
