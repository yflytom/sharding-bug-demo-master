import com.alibaba.fastjson.JSON;
import com.example.shardingbugdemo.ShardingBugDemoApplication;
import com.example.shardingbugdemo.entity.Books;
import com.example.shardingbugdemo.entity.User;
import com.example.shardingbugdemo.service.BooksService;
import com.example.shardingbugdemo.service.UserService;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.broadcast.api.config.BroadcastRuleConfiguration;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableReferenceRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.audit.ShardingAuditStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


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

        HintManager hintManager = HintManager.getInstance();
        hintManager.setDatabaseShardingValue("ds_1");
        List<User> list = userService.lambdaQuery().list();
        System.out.println(JSON.toJSONString(list));
        hintManager.close();
    }



}
