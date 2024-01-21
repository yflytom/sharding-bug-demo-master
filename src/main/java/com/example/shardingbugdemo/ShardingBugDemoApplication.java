package com.example.shardingbugdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class ShardingBugDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingBugDemoApplication.class, args);
    }

}
