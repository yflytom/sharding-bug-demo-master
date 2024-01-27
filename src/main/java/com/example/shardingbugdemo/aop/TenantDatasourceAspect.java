package com.example.shardingbugdemo.aop;

import org.apache.shardingsphere.infra.hint.HintManager;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 通过aop方式给所有租户的操作指定数据源
 *
 * @author yhc
 * @Date 2024/1/26
 */
@Aspect
@Order(1)
@Component
public class TenantDatasourceAspect {
    /**
     * point cut.
     */
    @Pointcut("execution(* com.example.shardingbugdemo.controller.*.*(..))")
    public void useTenantDSPointCut() {
        // no impl
    }

    @Before("useTenantDSPointCut()")
    public void doDs0Before() {
        HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
        hintManager.setDatabaseShardingValue("ds_1");

    }

    @After("useTenantDSPointCut()")
    public void doDs0after() {
        HintManager.clear();
    }

}
