package com.work.hai.drone.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.hai.drone.common.Result;
import com.work.hai.drone.dto.NewsDetailDTO;
import com.work.hai.drone.entity.News;
import com.work.hai.drone.mapper.NewsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsMapper newsMapper;

    // 新闻分页列表
    @GetMapping("/page")
    public Result<Page<News>> getPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<News> pageParam = new Page<>(page, size);
        QueryWrapper<News> query = new QueryWrapper<>();
        query.orderByDesc("publish_date"); // 按发布日期倒序
        return Result.success(newsMapper.selectPage(pageParam, query));
    }

    // 获取“最新资讯”（侧边栏用，取前5条）
    @GetMapping("/recent")
    public Result<List<News>> getRecent() {
        Page<News> page = new Page<>(1, 5);
        QueryWrapper<News> query = new QueryWrapper<>();
        query.select("id", "title"); // 只查ID和标题，优化性能
        query.orderByDesc("create_time");
        return Result.success(newsMapper.selectPage(page, query).getRecords());
    }

    // 新闻详情（包含上一篇/下一篇逻辑）
    @GetMapping("/{id}")
    public Result<NewsDetailDTO> getDetail(@PathVariable Long id) {
        News current = newsMapper.selectById(id);
        if (current == null) {
            return Result.error("新闻不存在");
        }

        // 增加浏览量 (简单实现，生产环境应使用Redis)
        current.setViews(current.getViews() + 1);
        newsMapper.updateById(current);

        NewsDetailDTO dto = new NewsDetailDTO();
        BeanUtils.copyProperties(current, dto);

        // 查上一篇 (ID小于当前ID的最大值)
        QueryWrapper<News> prevQuery = new QueryWrapper<>();
        prevQuery.lt("id", id).orderByDesc("id").last("LIMIT 1");
        News prev = newsMapper.selectOne(prevQuery);
        if (prev != null) {
            dto.setPrevId(prev.getId());
            dto.setPrevTitle(prev.getTitle());
        }

        // 查下一篇 (ID大于当前ID的最小值)
        QueryWrapper<News> nextQuery = new QueryWrapper<>();
        nextQuery.gt("id", id).orderByAsc("id").last("LIMIT 1");
        News next = newsMapper.selectOne(nextQuery);
        if (next != null) {
            dto.setNextId(next.getId());
            dto.setNextTitle(next.getTitle());
        }

        return Result.success(dto);
    }
}