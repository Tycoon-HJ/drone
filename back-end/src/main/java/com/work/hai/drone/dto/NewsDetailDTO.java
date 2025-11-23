package com.work.hai.drone.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class NewsDetailDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Integer views;
    private LocalDate publishDate;
    
    // 上一篇 info
    private Long prevId;
    private String prevTitle;
    
    // 下一篇 info
    private Long nextId;
    private String nextTitle;
}