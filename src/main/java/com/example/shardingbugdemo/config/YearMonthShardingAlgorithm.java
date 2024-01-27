package com.example.shardingbugdemo.config;

import cn.hutool.core.date.DateUtil;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Date;

/**
 * 按照date类型年月进行分表
 *
 */
public class YearMonthShardingAlgorithm implements StandardShardingAlgorithm<Date> {

    @Override
    public String getType() {
        return null;
    }

    /**
     * 精确分片
     *
     * @param collection
     * @param preciseShardingValue
     * @return
     */
    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        //获取字段值

        return "books_202401";
    }

    /**
     * 处理范围分片
     *
     * @param collection
     * @param rangeShardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection collection, RangeShardingValue rangeShardingValue) {

        return null;
    }
}