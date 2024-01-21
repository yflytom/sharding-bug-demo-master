package com.example.shardingbugdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("books")
public class Books extends Model<Books> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     **/
    @TableField("title")
    private String title;

    /**
     * 标题
     **/
    @TableField("price")
    private BigDecimal price;


    /**
     * 关系人证件号
     **/
    @TableField("publishDate")
    private LocalDateTime publishDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
