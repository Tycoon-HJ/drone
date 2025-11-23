<template>
  <div class="product-list-page page-container">
    <div class="page-header-banner">
      <div class="banner-content">
        <h2>产品中心</h2>
        <p>INNOVATION & QUALITY / 创新技术，卓越品质</p>
      </div>
    </div>

    <div class="category-filter">
      <div class="filter-wrapper">
        <span class="filter-label">产品分类：</span>
        <el-radio-group v-model="currentCategory" size="large" @change="handleCategoryChange">
          <el-radio-button label="all">全部产品</el-radio-button>
          <el-radio-button label="wing">固定翼无人机</el-radio-button>
          <el-radio-button label="rotor">多旋翼无人机</el-radio-button>
          <el-radio-button label="vtol">垂直起降(VTOL)</el-radio-button>
          <el-radio-button label="payload">任务载荷</el-radio-button>
        </el-radio-group>
      </div>
    </div>

    <div class="product-grid-container" v-loading="loading">
      <el-row :gutter="30">
        <el-col
            :xs="24" :sm="12" :md="8" :lg="6"
            v-for="product in productList"
            :key="product.id"
            class="product-col"
        >
          <div class="product-item" @click="goToDetail(product.id)">
            <div class="img-box">
              <img :src="product.imageUrl || 'https://via.placeholder.com/400x300'" :alt="product.name">
              <div class="overlay">
                <el-button type="primary" circle><el-icon><View /></el-icon></el-button>
              </div>
            </div>
            <div class="info-box">
              <h3>{{ product.name }}</h3>
              <p class="category-tag">{{ getCategoryName(product.category) }}</p>
              <p class="desc">{{ product.description }}</p>
            </div>
          </div>
        </el-col>
      </el-row>

      <el-empty v-if="productList.length === 0 && !loading" description="暂无相关产品" />

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
import axios from 'axios' // 引入 axios
import { View } from '@element-plus/icons-vue'

const router = useRouter()

// --- 状态定义 ---
const loading = ref(false)
const currentCategory = ref('all')
const currentPage = ref(1)
const pageSize = ref(8)
const total = ref(0)
const productList = ref([]) // 存储后端返回的真实数据

// --- 配置 API 请求 (建议封装到单独文件，这里为了方便直接写) ---
// 假设你的后端运行在 8080，且配置了跨域
const apiBaseUrl = 'http://116.198.201.161:5555/api'

// --- 核心方法：获取产品列表 ---
const fetchProducts = async () => {
  loading.value = true
  try {
    const res = await axios.get(`${apiBaseUrl}/product/page`, {
      params: {
        page: currentPage.value,
        size: pageSize.value,
        category: currentCategory.value
      }
    })

    if (res.data.code === 200) {
      // MyBatis Plus 分页返回的数据结构通常是 data.records
      productList.value = res.data.data.records
      total.value = res.data.data.total
    } else {
      console.error('获取数据失败:', res.data.msg)
    }
  } catch (error) {
    console.error('网络错误:', error)
  } finally {
    loading.value = false
  }
}

// --- 事件处理 ---

// 切换分类
const handleCategoryChange = () => {
  currentPage.value = 1 // 切换分类时重置为第一页
  fetchProducts()
}

// 切换页码
const handlePageChange = (page) => {
  currentPage.value = page
  fetchProducts()
  window.scrollTo({ top: 0, behavior: 'smooth' })
}

// 跳转详情
const goToDetail = (id) => {
  router.push(`/products/${id}`)
}

// 分类名称映射辅助函数
const getCategoryName = (code) => {
  const map = {
    'rotor': '多旋翼',
    'wing': '固定翼',
    'vtol': '垂直起降',
    'payload': '任务载荷'
  }
  return map[code] || '通用产品'
}

// --- 初始化 ---
onMounted(() => {
  fetchProducts()
})
</script>

<style scoped>
/* 样式保持不变，复用之前的 CSS */
.page-header-banner {
  height: 300px;
  background: linear-gradient(rgba(0,51,102,0.8), rgba(0,51,102,0.8)), url('https://via.placeholder.com/1920x400?text=Products+Banner') center/cover;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  color: white;
}
.banner-content h2 { font-size: 40px; margin-bottom: 10px; letter-spacing: 2px; }
.banner-content p { font-size: 16px; opacity: 0.8; }

.category-filter {
  background-color: #fff;
  padding: 20px 0;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
  display: flex;
  justify-content: center;
}
.filter-wrapper { display: flex; align-items: center; }
.filter-label { margin-right: 15px; font-weight: bold; color: #555; }

.product-grid-container {
  max-width: 1200px;
  margin: 40px auto;
  padding: 0 20px;
  min-height: 400px;
}
.product-col { margin-bottom: 30px; }
.product-item {
  background: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0,0,0,0.05);
  transition: all 0.3s;
  cursor: pointer;
  height: 100%;
  border: 1px solid #eee;
}
.product-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0,0,0,0.1);
  border-color: #409EFF;
}
.img-box {
  height: 220px;
  position: relative;
  overflow: hidden;
}
.img-box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.5s;
}
.product-item:hover .img-box img { transform: scale(1.05); }
.overlay {
  position: absolute;
  top: 0; left: 0; width: 100%; height: 100%;
  background: rgba(0,0,0,0.3);
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transition: opacity 0.3s;
}
.product-item:hover .overlay { opacity: 1; }

.info-box { padding: 20px; }
.info-box h3 { margin: 0 0 10px; color: #333; font-size: 18px; }
.category-tag {
  display: inline-block;
  background: #ecf5ff;
  color: #409EFF;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  margin-bottom: 10px;
}
.desc { font-size: 14px; color: #888; line-height: 1.5; margin: 0; }

.pagination-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 40px;
}

/* 移动端适配：单选按钮组横向滚动 */
@media (max-width: 768px) {
  .filter-wrapper {
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
    padding: 0 20px;
  }
  .filter-label { margin-bottom: 10px; }
  .el-radio-group {
    display: flex;
    flex-wrap: nowrap;
    overflow-x: auto;
    width: 100%;
  }
}
</style>