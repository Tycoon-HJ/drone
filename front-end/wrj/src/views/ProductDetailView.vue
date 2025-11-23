<template>
  <div class="product-detail-page" v-loading="loading">
    <div class="breadcrumb-area">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/products' }">产品中心</el-breadcrumb-item>
        <el-breadcrumb-item>{{ product.name }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div v-if="product.id" class="product-main-info container">
      <el-row :gutter="40">
        <el-col :md="12">
          <div class="main-image">
            <img :src="activeImage" alt="Main" />
          </div>
          <div class="thumbnail-list" v-if="product.images && product.images.length > 0">
            <div
                v-for="(img, index) in product.images"
                :key="index"
                class="thumb-item"
                :class="{ active: activeImage === img }"
                @click="activeImage = img"
            >
              <img :src="img" />
            </div>
          </div>
        </el-col>

        <el-col :md="12">
          <div class="info-content">
            <h1 class="product-title">{{ product.name }}</h1>
            <div class="product-meta">
              <span class="label">分类：</span>
              <el-tag>{{ getCategoryName(product.category) }}</el-tag>
            </div>
            <p class="product-intro">{{ product.description }}</p>

            <div class="specs-preview" v-if="product.specs && product.specs.length > 0">
              <div class="spec-item" v-for="(spec, idx) in product.specs.slice(0, 3)" :key="idx">
                <strong>{{ spec.name }}</strong>
                <p>{{ spec.value }}</p>
              </div>
            </div>

            <div class="action-buttons">
              <el-button type="primary" size="large" icon="Phone">联系咨询</el-button>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>

    <div v-if="product.id" class="product-detail-tabs container">
      <el-tabs type="border-card">
        <el-tab-pane label="产品详情">
          <div class="rich-text-content" v-html="product.detailContent"></div>
        </el-tab-pane>
        <el-tab-pane label="技术参数">
          <el-table :data="product.specs" style="width: 100%" stripe border>
            <el-table-column prop="name" label="参数名称" width="200" />
            <el-table-column prop="value" label="详细数值" />
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import { Phone } from '@element-plus/icons-vue'

const route = useRoute()
const loading = ref(false)
const product = ref({})
const activeImage = ref('')
const apiBaseUrl = 'http://116.198.201.161:5555/api'

const fetchProductDetail = async (id) => {
  loading.value = true
  try {
    const res = await axios.get(`${apiBaseUrl}/product/${id}`)
    if (res.data.code === 200) {
      const data = res.data.data
      product.value = data

      // 图片处理逻辑
      if (data.images && data.images.length > 0) {
        activeImage.value = data.images[0]
      } else {
        activeImage.value = data.imageUrl || 'https://via.placeholder.com/600x400'
      }
    }
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const getCategoryName = (code) => {
  const map = { 'rotor': '多旋翼', 'wing': '固定翼', 'vtol': '垂直起降', 'payload': '任务载荷' }
  return map[code] || code
}

watch(() => route.params.id, (newId) => { if(newId) fetchProductDetail(newId) })

onMounted(() => { fetchProductDetail(route.params.id) })
</script>

<style scoped>
/* 样式同前一次回答，此处省略以节省空间 */
.container { max-width: 1200px; margin: 0 auto; padding: 20px; }
.product-main-info { margin-bottom: 50px; }
.main-image { width: 100%; height: 400px; display: flex; align-items: center; justify-content: center; background: #f5f5f5; border: 1px solid #eee; border-radius: 4px; }
.main-image img { max-width: 100%; max-height: 100%; }
.thumbnail-list { display: flex; gap: 10px; margin-top: 15px; }
.thumb-item { width: 80px; height: 60px; cursor: pointer; border: 2px solid #eee; opacity: 0.7; }
.thumb-item.active { border-color: #409EFF; opacity: 1; }
.thumb-item img { width: 100%; height: 100%; object-fit: cover; }
.product-title { font-size: 32px; margin-bottom: 15px; }
.specs-preview { display: flex; gap: 40px; margin-bottom: 30px; background: #f8f8f8; padding: 20px; border-radius: 8px; }
.rich-text-content { padding: 20px; line-height: 1.8; }
:deep(.rich-text-content img) { max-width: 100%; height: auto; display: block; margin: 15px auto; }
</style>