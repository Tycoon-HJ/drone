<template>
  <div class="news-list-page page-container">
    <div class="page-header-banner news-banner">
      <div class="banner-content">
        <h2>新闻动态</h2>
        <p>LATEST NEWS & EVENTS / 关注我们，了解最新行业资讯</p>
      </div>
    </div>

    <div class="news-container" v-loading="loading">

      <el-timeline v-if="newsList.length > 0">
        <el-timeline-item
            v-for="news in newsList"
            :key="news.id"
            :timestamp="formatDate(news.publishDate)"
            placement="top"
            color="#003366"
        >
          <el-card class="news-card" shadow="hover" @click="goToDetail(news.id)">
            <div class="news-content-wrapper">
              <div class="news-thumb">
                <img :src="news.imageUrl || 'https://via.placeholder.com/200x140?text=News'" alt="news thumb">
              </div>

              <div class="news-text">
                <h3>{{ news.title }}</h3>
                <p class="news-summary">{{ news.summary || news.title }}</p>

                <div class="news-meta-row">
                  <span class="views"><el-icon><View /></el-icon> {{ news.views || 0 }} 阅读</span>
                </div>

                <div class="news-footer">
                  <span class="read-more">阅读全文 <el-icon><ArrowRight /></el-icon></span>
                </div>
              </div>
            </div>
          </el-card>
        </el-timeline-item>
      </el-timeline>

      <el-empty v-else-if="!loading" description="暂无新闻动态" />

      <div class="pagination-wrapper" v-if="total > 0">
        <el-pagination
            background
            layout="prev, pager, next"
            :total="total"
            :page-size="pageSize"
            v-model:current-page="currentPage"
            @current-change="handlePageChange"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ArrowRight, View } from '@element-plus/icons-vue'

const router = useRouter()
const apiBaseUrl = 'http://116.198.201.161:5555/api'

// --- 状态变量 ---
const loading = ref(false)
const newsList = ref([])
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10) // 每页显示10条

// --- API 请求 ---
const fetchNewsList = async () => {
  loading.value = true
  try {
    const res = await axios.get(`${apiBaseUrl}/news/page`, {
      params: {
        page: currentPage.value,
        size: pageSize.value
      }
    })

    if (res.data.code === 200) {
      newsList.value = res.data.data.records
      total.value = res.data.data.total
    }
  } catch (error) {
    console.error("获取新闻列表失败", error)
  } finally {
    loading.value = false
  }
}

// --- 辅助方法 ---

// 简单的日期格式化 (截取 yyyy-MM-dd)
const formatDate = (dateStr) => {
  if (!dateStr) return ''
  return dateStr.split('T')[0] // 处理 '2023-11-15T10:00:00' 这种格式
}

const goToDetail = (id) => {
  router.push(`/news/${id}`)
}

const handlePageChange = (page) => {
  currentPage.value = page
  fetchNewsList()
  window.scrollTo({ top: 0, behavior: 'smooth' })
}

// --- 初始化 ---
onMounted(() => {
  fetchNewsList()
})
</script>

<style scoped>
.page-header-banner.news-banner {
  height: 250px;
  background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('https://via.placeholder.com/1920x300?text=News+Center') center/cover;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  text-align: center;
}
.banner-content h2 { font-size: 36px; margin-bottom: 10px; }

.news-container {
  max-width: 1000px;
  margin: 50px auto;
  padding: 0 20px;
  min-height: 400px;
}
.news-card { cursor: pointer; margin-bottom: 10px; border: none; transition: transform 0.2s; }
.news-card:hover { transform: translateY(-3px); }

.news-content-wrapper { display: flex; gap: 20px; }
.news-thumb { flex-shrink: 0; width: 240px; height: 160px; overflow: hidden; border-radius: 4px; }
.news-thumb img { width: 100%; height: 100%; object-fit: cover; transition: transform 0.5s; }
.news-card:hover .news-thumb img { transform: scale(1.1); }

.news-text { flex-grow: 1; display: flex; flex-direction: column; }
.news-text h3 { margin: 0 0 10px; color: #333; font-size: 20px; font-weight: bold; }
.news-card:hover h3 { color: #409EFF; }

.news-summary { color: #666; font-size: 14px; line-height: 1.6; flex-grow: 1; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden; }

.news-meta-row { margin-top: 10px; color: #999; font-size: 12px; display: flex; align-items: center; }
.news-meta-row .el-icon { margin-right: 4px; vertical-align: middle; }

.news-footer { text-align: right; margin-top: 5px; }
.read-more { color: #409EFF; font-size: 14px; display: flex; align-items: center; justify-content: flex-end; font-weight: 500; }

.pagination-wrapper { text-align: center; margin-top: 40px; }

/* 移动端适配 */
@media (max-width: 768px) {
  .news-content-wrapper { flex-direction: column; }
  .news-thumb { width: 100%; height: 180px; }
  .news-text h3 { font-size: 18px; margin-top: 10px; }
}
</style>