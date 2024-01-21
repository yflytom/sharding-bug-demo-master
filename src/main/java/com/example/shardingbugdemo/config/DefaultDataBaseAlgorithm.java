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

    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<String> hintShardingValue) {
        Collection<String> hintShardingValues = hintShardingValue.getValues();
        Collection<String> result = new ArrayList<>();
        for (String key : hintShardingValues) {
            result.add(hintShardingValue.getLogicTableName() + "_" + key);
        }
        return result;
    }
}