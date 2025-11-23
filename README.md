# JTUAV 智能无人机企业官网系统

这是一个基于 **Vue 3 + Spring Boot 3** 全栈开发的企业级官网系统。项目旨在复刻无人机行业标杆网站（如 jtuav.cn）的核心功能与视觉体验，采用现代化的前后端分离架构，支持响应式布局与国际化。

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Vue](https://img.shields.io/badge/Vue-3.x-green.svg)
![SpringBoot](https://img.shields.io/badge/SpringBoot-3.x-green.svg)

## 📚 目录

- [项目介绍](#-项目介绍)
- [技术栈](#-技术栈)
- [核心架构与特性](#-核心架构与特性)
- [数据库设计](#-数据库设计)
- [本地开发指南](#-本地开发指南)
- [生产环境部署](#-生产环境部署)

---

## 📖 项目介绍

本项目是一个标准的 B2B 企业官网解决方案，包含产品展示、新闻发布、品牌介绍等模块。

**主要功能：**
* **首页 (Home)**: 沉浸式轮播图、核心优势栅格布局、推荐产品展示。
* **产品中心 (Product)**: 支持多级分类筛选、产品列表分页加载。
* **产品详情 (Detail)**: 支持图集轮播、富文本详情展示、参数规格表（基于 JSON 动态渲染）。
* **新闻动态 (News)**: 时间轴风格的新闻列表、侧边栏推荐阅读、上下篇导航。
* **国际化 (I18n)**: 支持中/英双语无缝切换（静态文案+动态数据）。

---

## 🛠 技术栈

### 前端 (Frontend)
* **核心框架**: Vue 3 (Composition API)
* **构建工具**: Vite 4+
* **UI 组件库**: Element Plus
* **路由管理**: Vue Router 4
* **状态/逻辑**: Vue I18n (国际化), Axios (网络请求封装)
* **CSS**: SCSS / CSS3

### 后端 (Backend)
* **核心框架**: Spring Boot 3.x (JDK 17+)
* **ORM 框架**: MyBatis Plus 3.5.3.2+
* **数据库**: MySQL 8.0
* **工具库**: Lombok, Hutool
* **JSON 处理**: Jackson (自动映射 MySQL JSON 类型)

---

## 🧩 核心架构与特性

### 1. 垂直分表设计
为了提升列表页的查询性能，我们将产品表拆分为**主表**和**详情表**：
* **列表页**: 仅查询 `sys_product` (轻量级，无大字段)。
* **详情页**: 通过 DTO 聚合查询 `sys_product` + `sys_product_detail` (包含富文本、图集)。

### 2. 灵活的数据存储
利用 MySQL 的 `JSON` 类型存储非结构化数据，无需建立繁琐的关联表：
* **轮播图集**: `["url1", "url2"]`
* **规格参数**: `[{"name":"续航", "value":"60min"}, ...]`

### 3. 目录结构
```text
├── frontend/                 # 前端项目
│   ├── src/
│   │   ├── locales/          # i18n 语言包 (zh.js, en.js)
│   │   ├── utils/            # 工具类 (request.js 封装)
│   │   ├── views/            # 页面组件
│   │   └── .env              # 环境变量
│
├── backend/                  # 后端项目
│   ├── controller/           # Web 接口层
│   ├── dto/                  # 数据传输对象 (ProductCompleteDTO)
│   ├── entity/               # 数据库实体
│   └── mapper/               # DAO 层
```

## 💾 数据库设计

请在 MySQL 8.0+ 中执行以下 SQL 脚本：

SQL

```
CREATE DATABASE IF NOT EXISTS uav_website DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
USE uav_website;

-- 1. 产品主表 (列表页用)
CREATE TABLE `sys_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '产品名称',
  `name_en` varchar(255) COMMENT '英文名称',
  `category` varchar(50) NOT NULL COMMENT '分类: rotor, wing, vtol',
  `image_url` varchar(500) DEFAULT NULL COMMENT '列表封面图',
  `description` varchar(500) DEFAULT NULL COMMENT '中文描述',
  `description_en` varchar(500) DEFAULT NULL COMMENT '英文描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. 产品详情表 (详情页用 - 垂直分表)
CREATE TABLE `sys_product_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '关联主表ID',
  `images` json DEFAULT NULL COMMENT '轮播图集 (JSON数组)',
  `detail_content` longtext COMMENT '富文本详情(中文)',
  `detail_content_en` longtext COMMENT '富文本详情(英文)',
  `specs` json DEFAULT NULL COMMENT '规格参数 (JSON对象数组)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. 新闻表
CREATE TABLE `sys_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `summary` varchar(500) COMMENT '摘要',
  `content` longtext COMMENT '富文本内容',
  `image_url` varchar(500) COMMENT '封面图',
  `author` varchar(100) DEFAULT '官方',
  `views` int(11) DEFAULT 0,
  `publish_date` date DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

## 🚀 本地开发指南

### 后端 (Spring Boot)

1. 修改 `backend/src/main/resources/application.yml` 配置数据库连接。
2. 确保 Maven 依赖下载完成。
3. 运行启动类 `DroneApplication.java`。
4. 服务默认端口：`5555` (http://localhost:5555)。

### 前端 (Vue 3)

1. 进入前端目录：

   Bash

   ```
   cd frontend
   npm install
   ```

2. 检查 `.env` 文件配置：

   Properties

   ```
   VITE_API_BASE_URL=http://localhost:5555/api
   ```

3. 启动开发服务器：

   Bash

   ```
   npm run dev
   ```

4. 访问 `http://localhost:5173`。

## 🌏 生产环境部署

推荐使用 **Nginx 反向代理 + 后端 Jar 包** 的方式。

### 1. 后端打包

Bash

```
cd backend
mvn clean package -DskipTests
# 生成 target/app.jar
```

**运行：**

Bash

```
nohup java -jar app.jar --server.port=5555 > log.out 2>&1 &
```

### 2. 前端打包

Bash

```
cd frontend
# 1. 确保 .env.production 或构建配置正确
npm run build
# 生成 dist/ 目录
```

### 3. Nginx 配置示例

此配置解决了 Vue Router 的 History 模式刷新 404 问题，以及生产环境跨域问题。

Nginx

```
server {
    listen       80;
    server_name  [www.yourdomain.com](https://www.yourdomain.com);

    # 前端静态资源
    location / {
        root   /usr/share/nginx/html/dist; # 指向 npm run build 生成的 dist 目录
        index  index.html index.htm;
        try_files $uri $uri/ /index.html; # 关键：解决路由刷新404
    }

    # 后端接口反向代理
    location /api/ {
        proxy_pass [http://127.0.0.1:5555/api/](http://127.0.0.1:5555/api/); # 转发给后端端口
        proxy_set_header Host $host;
    }
}
```



### 