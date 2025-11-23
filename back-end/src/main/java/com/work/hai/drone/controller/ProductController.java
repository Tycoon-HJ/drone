package com.work.hai.drone.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.hai.drone.common.Result;
import com.work.hai.drone.dto.ProductCompleteDTO;
import com.work.hai.drone.entity.Product;
import com.work.hai.drone.entity.ProductDetail;
import com.work.hai.drone.mapper.ProductDetailMapper;
import com.work.hai.drone.mapper.ProductMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private ProductDetailMapper productDetailMapper;

    // 1. 获取分页列表 (只查询 sys_product 表，性能高)
    @GetMapping("/page")
    public Result<Page<Product>> getPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(required = false) String category
    ) {
        Page<Product> pageParam = new Page<>(page, size);
        QueryWrapper<Product> query = new QueryWrapper<>();
        
        if (StringUtils.hasText(category) && !"all".equals(category)) {
            query.eq("category", category);
        }
        query.orderByDesc("create_time");
        
        return Result.success(productMapper.selectPage(pageParam, query));
    }

    // 2. 获取详情 (同时查询 sys_product 和 sys_product_detail)
    @GetMapping("/{id}")
    public Result<ProductCompleteDTO> getDetail(@PathVariable Long id) {
        // Step 1: 查主表
        Product product = productMapper.selectById(id);
        if (product == null) {
            return Result.error("产品不存在");
        }

        // Step 2: 查从表
        QueryWrapper<ProductDetail> detailQuery = new QueryWrapper<>();
        detailQuery.eq("product_id", id);
        ProductDetail productDetail = productDetailMapper.selectOne(detailQuery);

        // Step 3: 合并数据到 DTO
        ProductCompleteDTO dto = new ProductCompleteDTO();
        
        // 复制主表数据
        BeanUtils.copyProperties(product, dto);
        
        // 复制从表数据 (如果从表有数据)
        if (productDetail != null) {
            dto.setImages(productDetail.getImages());
            dto.setSpecs(productDetail.getSpecs());
            dto.setDetailContent(productDetail.getDetailContent());
        }

        return Result.success(dto);
    }
}