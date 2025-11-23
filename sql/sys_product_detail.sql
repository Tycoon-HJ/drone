create table sys_product_detail
(
    id             bigint auto_increment
        primary key,
    product_id     bigint   not null comment '关联主表ID',
    images         json     null comment '轮播图集 (JSON)',
    detail_content longtext null comment '富文本详情',
    specs          json     null comment '规格参数 (JSON)',
    constraint uk_product_id
        unique (product_id)
)
    charset = utf8mb4;

INSERT INTO uav_website.sys_product_detail (id, product_id, images, detail_content, specs) VALUES (1, 1, '["https://aosspic10001.websiteonline.cn/pro58a77549/image/JT450.jpg", "https://aosspic10001.websiteonline.cn/pro58a77549/image/300N%E6%8A%A0%E5%9B%BE.png"]', '<h3>详细介绍</h3><p>这是HTML内容...</p>', '[{"name": "续航", "value": "60min"}, {"name": "载重", "value": "5kg"}, {"name": "续航2", "value": "60min"}, {"name": "载重1", "value": "5kg"}]');
