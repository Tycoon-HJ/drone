package com.work.hai.drone.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class ProductCompleteDTO {
    // 来自主表
    private Long id;
    private String name;
    private String category;
    private String imageUrl;
    private String description;
    private LocalDateTime createTime;

    // 来自详情表
    private List<String> images;
    private String detailContent;
    private List<Map<String, String>> specs;
}