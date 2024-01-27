package com.example.shardingbugdemo.config;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 *
 */
public class DefaultDataBaseAlgorithm implements HintShardingAlgorithm<String> {

    /**
     *
     *
     * @param collection 库或者表集合
     * @param hintShardingValue 路由key
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<String> hintShardingValue) {
        return hintShardingValue.getValues();
    }
}