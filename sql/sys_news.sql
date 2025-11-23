create table sys_news
(
    title        varchar(255)                           not null,
    id           bigint auto_increment
        primary key,
    summary      varchar(500)                           null comment '摘要',
    content      longtext                               null comment '富文本内容',
    image_url    varchar(500)                           null comment '封面图',
    author       varchar(100) default '官方'            null,
    views        int          default 0                 null,
    publish_date date                                   null,
    create_time  datetime     default CURRENT_TIMESTAMP null
);

INSERT INTO uav_website.sys_news (title, id, summary, content, image_url, author, views, publish_date, create_time) VALUES ('1', 6, '展示最新技术成果', '<p>展会现场报道...</p>', 'https://aosspic10001.websiteonline.cn/pro58a77549/image/%E5%9B%BE%E7%89%87.png', '官方', 1205, '2023-11-15', '2025-11-22 02:56:07');
INSERT INTO uav_website.sys_news (title, id, summary, content, image_url, author, views, publish_date, create_time) VALUES ('2', 7, '助力智慧城市建设', '<p>交付详细内容...</p>', 'https://aosspic10001.websiteonline.cn/pro58a77549/image/%E5%9B%BE%E7%89%87.png', '官方', 891, '2023-11-02', '2025-11-22 02:56:07');
INSERT INTO uav_website.sys_news (title, id, summary, content, image_url, author, views, publish_date, create_time) VALUES ('3', 8, '公司以技术创新、服务至上的理念大宗旨，以无人机整机制造，机载任务设备、指挥控制系统以及系统集成为核心技术，致力于为用户提供多用途，高性能、一体化的工业级无人机系统解决方案。
', '<p>公司以技术创新、服务至上的理念大宗旨，以无人机整机制造，机载任务设备、指挥控制系统以及系统集成为核心技术，致力于为用户提供多用途，高性能、一体化的工业级无人机系统解决方案。

</p>', 'https://aosspic10001.websiteonline.cn/pro58a77549/image/%E5%9B%BE%E7%89%87.png', '官方', 108, '2023-11-20', '2025-11-22 11:29:02');
INSERT INTO uav_website.sys_news (title, id, summary, content, image_url, author, views, publish_date, create_time) VALUES ('4', 9, '1公司以技术创新、服务至上的理念大宗旨，以无人机整机制造，机载任务设备、指挥控制系统以及系统集成为核心技术，致力于为用户提供多用途，高性能、一体化的工业级无人机系统解决方案。
', '<p>公司以技术创新、服务至上的理念大宗旨，以无人机整机制造，机载任务设备、指挥控制系统以及系统集成为核心技术，致力于为用户提供多用途，高性能、一体化的工业级无人机系统解决方案。

</p>', 'https://aosspic10001.websiteonline.cn/pro58a77549/image/%E5%9B%BE%E7%89%87.png', '官方', 200, '2023-11-19', '2025-11-22 11:29:02');
INSERT INTO uav_website.sys_news (title, id, summary, content, image_url, author, views, publish_date, create_time) VALUES ('5', 10, '2公司以技术创新、服务至上的理念大宗旨，以无人机整机制造，机载任务设备、指挥控制系统以及系统集成为核心技术，致力于为用户提供多用途，高性能、一体化的工业级无人机系统解决方案。
', '<p>公司以技术创新、服务至上的理念大宗旨，以无人机整机制造，机载任务设备、指挥控制系统以及系统集成为核心技术，致力于为用户提供多用途，高性能、一体化的工业级无人机系统解决方案。

</p>', 'https://aosspic10001.websiteonline.cn/pro58a77549/image/%E5%9B%BE%E7%89%87.png', '官方', 51, '2023-11-18', '2025-11-22 11:29:02');
