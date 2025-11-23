package com.work.hai.drone.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@TableName(value = "sys_product_detail", autoResultMap = true) // 开启JSON映射
public class ProductDetail {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private Long productId; // 关联ID

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> images;

    private String detailContent;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Map<String, String>> specs;
}