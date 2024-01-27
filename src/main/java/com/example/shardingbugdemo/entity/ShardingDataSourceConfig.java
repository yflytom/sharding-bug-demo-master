package com.example.shardingbugdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * sharding 数据源配置
 * </p>
 *
 * @author yhc
 * @since 2024-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShardingDataSourceConfig extends Model<ShardingDataSourceConfig> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 指定数据源名称，区分不同数据源
     */
    private String name;

    /**
     * 驱动
     */
    private String driverClassName;

    /**
     * 连接url
     */
    private String jdbcUrl;

    /**
     * 用户
     */
    private String user;

    /**
     * 密码
     */
    private String pwd;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
