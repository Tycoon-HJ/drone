<template>
  <div class="news-detail-page">
    <div class="breadcrumb-bar">
      <div class="container">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/news' }">新闻动态</el-breadcrumb-item>
          <el-breadcrumb-item>正文</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>

    <div class="container main-content-area">
      <el-row :gutter="40">

        <el-col :md="17" :sm="24">
          <div class="article-wrapper">
            <h1 class="article-title">{{ newsData.title }}</h1>

            <div class="article-meta">
              <span class="meta-item"><el-icon><Calendar /></el-icon> {{ newsData.publishDate }}</span>
              <span class="meta-item"><el-icon><User /></el-icon> 发布者：{{ newsData.author }}</span>
              <span class="meta-item"><el-icon><View /></el-icon> {{ newsData.views }} 阅读</span>
            </div>

            <el-divider border-style="dashed" />

            <div class="article-content" v-html="newsData.content"></div>

            <el-divider />

            <div class="article-navigation">
              <div class="nav-item prev" v-if="newsData.prevId" @click="goToNews(newsData.prevId)">
                <span>上一篇：</span>
                <a href="javascript:;">{{ newsData.prevTitle }}</a>
              </div>
              <div class="nav-item next" v-if="newsData.nextId" @click="goToNews(newsData.nextId)">
                <span>下一篇：</span>
                <a href="javascript:;">{{ newsData.nextTitle }}</a>
              </div>
            </div>
          </div>
        </el-col>

        <el-col :md="7" :sm="24" class="sidebar-col">
          <div class="sidebar-card">
            <div class="card-header">
              <h3>最新资讯</h3>
              <span class="line"></span>
            </div>
            <ul class="recent-news-list">
              <li v-for="item in recentNews" :key="item.id" @click="goToNews(item.id)">
                <span class="dot"></span>
                <span class="title">{{ item.title }}</span>
              </li>
            </ul>
          </div>

          <div class="sidebar-card contact-card">
            <h3>需要无人机解决方案？</h3>
            <p>我们要为您提供专业的行业应用方案</p>
            <el-button type="primary" class="contact-btn" @click="router.push('/about')">联系我们</el-button>
          </div>
        </el-col>

      </el-row>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { Calendar, User, View } from '@element-plus/icons-vue'
import axios from 'axios'
const route = useRoute()
const router = useRouter()

const newsData = ref({})
const recentNews = ref([])

// // 模拟获取新闻详情数据
// const fetchNewsDetail = (id) => {
//   // 实际开发中：const res = await axios.get(`/api/news/${id}`)
//
//   // Mock Data
//   newsData.value = {
//     id: id,
//     title: 'JTUAV 亮相第十届国际无人机系统博览会，展现硬核科技实力',
//     publishDate: '2023-11-15',
//     author: '品牌部',
//     views: 1205,
//     // 模拟富文本内容 HTML
//     content: `
//       <p>2023年11月15日，第十届国际无人机系统博览会在深圳会展中心盛大开幕。作为国内领先的工业级无人机解决方案提供商，JTUAV 携旗下全系产品精彩亮相。</p>
//       <p><br></p>
//       <img src="https://via.placeholder.com/800x450?text=Exhibition+Scene" style="max-width: 100%; border-radius: 4px;">
//       <p style="text-align: center; color: #888; font-size: 14px;">展会现场人头攒动</p>
//       <p><br></p>
//       <h3>JT-600 新品首发</h3>
//       <p>本次展会上，最引人注目的莫过于 JTUAV 全新发布的 <strong>JT-600 氢燃料长航时无人机</strong>。该机型采用了先进的氢燃料电池系统，续航时间突破了传统锂电池无人机的瓶颈，达到了惊人的4.5小时。</p>
//       <p>这一技术突破将极大地拓展无人机在长距离电力巡检、边境巡逻、大面积测绘等场景下的应用潜力。</p>
//       <h3>行业交流与合作</h3>
//       <p>展会期间，JTUAV 展台接待了来自全球20多个国家的专业客户和采购团。公司技术总监李博士在“无人机应用高峰论坛”上发表了题为《AI驱动下的无人机集群作业》的主题演讲，获得了现场专家的高度评价。</p>
//     `,
//     prevId: 100,
//     prevTitle: '关于2023年中秋国庆放假安排的通知',
//     nextId: 102,
//     nextTitle: '助力智慧城市：警用无人机交付仪式顺利举行'
//   }
// }
//
// // 模拟获取侧边栏最新新闻
// const fetchRecentNews = () => {
//   recentNews.value = [
//     { id: 102, title: '助力智慧城市：警用无人机交付仪式顺利举行' },
//     { id: 103, title: '技术突破：新型氢燃料电池无人机试飞成功' },
//     { id: 104, title: 'JTUAV荣获“年度科技创新企业”称号' },
//     { id: 105, title: '关于开展冬季安全生产大检查的通知' },
//     { id: 106, title: '无人机飞控系统V3.0版本更新说明' }
//   ]
// }

// 配置 axios 基础路径 (或者在 main.js 全局配置)
const request = axios.create({
  baseURL: 'http://116.198.201.161:5555/api'
})

const fetchNewsDetail = async (id) => {
  try {
    const res = await request.get(`/news/${id}`)
    if (res.data.code === 200) {
      newsData.value = res.data.data
    }
  } catch (e) {
    console.error(e)
  }
}

const fetchRecentNews = async () => {
  try {
    const res = await request.get('/news/recent')
    if (res.data.code === 200) {
      recentNews.value = res.data.data
    }
  } catch (e) {
    console.error(e)
  }
}

const goToNews = (id) => {
  router.push(`/news/${id}`)
  // 滚动到顶部
  window.scrollTo({ top: 0, behavior: 'smooth' })
}

// 监听路由ID变化，重新加载数据（处理侧边栏点击或上下篇跳转）
watch(
    () => route.params.id,
    (newId) => {
      if (newId) {
        fetchNewsDetail(newId)
      }
    }
)

onMounted(() => {
  fetchNewsDetail(route.params.id)
  fetchRecentNews()
})
</script>

<style scoped>
.news-detail-page {
  background-color: #f8f9fa;
  min-height: 80vh;
  padding-bottom: 60px;
}

/* 顶部面包屑条 */
.breadcrumb-bar {
  background-color: #fff;
  padding: 15px 0;
  border-bottom: 1px solid #eee;
  margin-bottom: 30px;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

/* 左侧文章主体 */
.article-wrapper {
  background-color: #fff;
  padding: 40px;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.article-title {
  font-size: 28px;
  color: #333;
  margin-bottom: 20px;
  line-height: 1.4;
}

.article-meta {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  color: #999;
  font-size: 14px;
  margin-bottom: 20px;
}

.meta-item {
  display: flex;
  align-items: center;
}

.meta-item .el-icon {
  margin-right: 5px;
}

/* 正文样式 - 注意 v-html 内容的样式需要穿透或全局定义，
   但在 scoped 中，我们通常针对通用标签做简单样式 */
.article-content {
  line-height: 1.8;
  font-size: 16px;
  color: #444;
  min-height: 300px;
}

/* 深度选择器：控制 v-html 内部的图片自适应 */
:deep(.article-content img) {
  max-width: 100%;
  height: auto;
  display: block;
  margin: 20px auto;
}

:deep(.article-content h3) {
  color: #003366;
  margin-top: 30px;
  margin-bottom: 15px;
  border-left: 4px solid #003366;
  padding-left: 10px;
}

/* 上下篇导航 */
.article-navigation {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.nav-item {
  font-size: 14px;
  color: #666;
  cursor: pointer;
}

.nav-item:hover a {
  color: #409EFF;
  text-decoration: underline;
}

.nav-item a {
  color: #333;
  margin-left: 5px;
}

/* 右侧侧边栏 */
.sidebar-col {
  margin-top: 0;
}

@media (max-width: 992px) {
  .sidebar-col {
    margin-top: 30px;
  }
}

.sidebar-card {
  background-color: #fff;
  padding: 25px;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
  margin-bottom: 20px;
}

.card-header {
  margin-bottom: 20px;
  position: relative;
}

.card-header h3 {
  margin: 0;
  font-size: 18px;
  color: #333;
  padding-left: 10px;
  border-left: 4px solid #003366;
}

.recent-news-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.recent-news-list li {
  padding: 12px 0;
  border-bottom: 1px dashed #eee;
  display: flex;
  align-items: center;
  cursor: pointer;
  transition: all 0.2s;
}

.recent-news-list li:hover {
  color: #409EFF;
  transform: translateX(5px);
}

.recent-news-list li:last-child {
  border-bottom: none;
}

.recent-news-list .dot {
  width: 6px;
  height: 6px;
  background-color: #ccc;
  border-radius: 50%;
  margin-right: 10px;
  flex-shrink: 0;
}

.recent-news-list li:hover .dot {
  background-color: #409EFF;
}

.recent-news-list .title {
  font-size: 14px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

/* 联系卡片 */
.contact-card {
  background: linear-gradient(135deg, #003366 0%, #0055a4 100%);
  color: white;
  text-align: center;
}

.contact-card h3 {
  color: white;
  margin-bottom: 10px;
}

.contact-card p {
  opacity: 0.8;
  font-size: 14px;
  margin-bottom: 20px;
}

.contact-btn {
  background-color: white;
  color: #003366;
  border: none;
  font-weight: bold;
  padding: 10px 30px;
}

.contact-btn:hover {
  background-color: #f0f0f0;
  color: #003366;
}
</style>