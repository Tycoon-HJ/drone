package com.work.hai.drone.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@TableName(value = "sys_product", autoResultMap = true) // 开启自动映射，用于JSON处理
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String category; // rotor, wing, vtol
    private String imageUrl;
    private String description;
    private String detailContent;
    private LocalDateTime createTime;

    // MyBatis Plus 可以自动将数据库的 JSON 字符串转为 List/Map
    // 需要在 pom 中引入 jackson-databind (SpringBoot自带)
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Map<String, String>> specs; 
}