package com.example.shardingbugdemo.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class GenericTool {
    /**
     * 通过时间戳获取时间
     * @param timestamp
     * @return
     */
    public static LocalDateTime getLocalDate(String timestamp){
        Instant startInstant = Instant.ofEpochMilli(Long.valueOf(timestamp));
        LocalDateTime localDate = LocalDateTime.ofInstant(startInstant, ZoneId.systemDefault());
        return localDate;
    }


}
