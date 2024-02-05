package com.example.shardingbugdemo.config;

import com.example.shardingbugdemo.entity.ShardingDataSourceConfig;
import com.example.shardingbugdemo.service.ShardingDataSourceConfigService;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.api.yaml.YamlJDBCConfiguration;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.infra.util.yaml.YamlEngine;
import org.apache.shardingsphere.infra.yaml.config.swapper.rule.YamlRuleConfigurationSwapperEngine;
import org.apache.shardingsphere.mode.repository.standalone.StandalonePersistRepositoryConfiguration;
import org.apache.shardingsphere.sharding.yaml.config.YamlShardingRuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.ResourceUtils.getFile;

/**
 * shardingjdbc 配置
 *
 * @author yhc
 * @Date 2024/1/18
 */
@Configuration
@Slf4j
public class ShardingConfig {


    @Resource
    public ShardingDataSourceConfigService shardingDataSourceConfigService;

    /**
     * 初始化数据源
     */
    public void shardingDataSource() throws IOException, SQLException {
        // 动态读取数据库配置源，创建ShardingSphereDataSource
        Map<String, DataSource> dataSourceMap = createDataSource();
        Set<String> dataSourceName = dataSourceMap.keySet();
        // 读取分片配置信息
        YamlJDBCConfiguration rootConfig = YamlEngine.unmarshal(getFile("classpath:sharding.yml"), YamlJDBCConfiguration.class);

        // 更新数据源到分表规则中，配置文件中只需要将需要分表的信息配置完成，自动填充数据源
        rootConfig.getRules().forEach(item -> {
            if (item instanceof YamlShardingRuleConfiguration) {
                ((YamlShardingRuleConfiguration) item).getTables()
                        .values().forEach(value -> value.setActualDataNodes(dataSourceName.stream()
                                .map(name -> name + "." + value.getActualDataNodes())
                                .collect(Collectors.joining(","))));
            }
        });

        YamlRuleConfigurationSwapperEngine swapperEngine = new YamlRuleConfigurationSwapperEngine();
        Collection<RuleConfiguration> ruleConfigs = swapperEngine.swapToRuleConfigurations(rootConfig.getRules());


        // 模式配置：Standalone为单机模式
        ModeConfiguration modeConfiguration = new ModeConfiguration("Standalone", new StandalonePersistRepositoryConfiguration("JDBC", new Properties()));
        ShardingSphereDataSourceFactory.createDataSource(modeConfiguration, dataSourceMap, ruleConfigs, rootConfig.getProps());
    }


    /**
     * 读取用户库获取数据源信息
     *
     * @return
     */
    private Map<String, DataSource> createDataSource() {
        List<ShardingDataSourceConfig> sourceConfigList = shardingDataSourceConfigService.lambdaQuery().list();
        if (CollectionUtils.isEmpty(sourceConfigList)) {
        }
        Map<String, DataSource> dataSourceMap = new HashMap<>(sourceConfigList.size());
        sourceConfigList.forEach(item -> {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(item.getDriverClassName());
            dataSource.setJdbcUrl(item.getJdbcUrl());
            dataSource.setUsername(item.getUser());
            dataSource.setPassword(item.getPwd());
            log.info("sharding数据源：{}", item.getName());
            dataSourceMap.put(item.getName(), dataSource);
        });
        return dataSourceMap;
    }


    /**
     * 配置分片数据源
     */
    @Bean
    public DataSource getShardingDataSource() throws IOException, SQLException {
        return YamlShardingSphereDataSourceFactory.createDataSource(getFile("classpath:sharding.yml"));
    }


    /**
     * 配置分片算法
     * 公众号：程序员小富
     */
    private Map<String, AlgorithmConfiguration> getShardingAlgorithms() {
        Map<String, AlgorithmConfiguration> shardingAlgorithms = new LinkedHashMap<>();

        // 自定义分库算法
        Properties databaseAlgorithms = new Properties();
        databaseAlgorithms.setProperty("algorithm-expression", "CS01_$->{id % 2}");
        shardingAlgorithms.put("database-inline", new AlgorithmConfiguration("INLINE", databaseAlgorithms));

        return shardingAlgorithms;
    }
}
