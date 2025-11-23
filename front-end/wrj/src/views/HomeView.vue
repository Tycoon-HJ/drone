<template>
  <div class="home-page">
    <el-carousel :interval="4000" type="card" height="500px">
      <el-carousel-item v-for="(banner, index) in banners" :key="index">
        <img :src="banner.src" :alt="banner.alt" class="carousel-image" />
        <div class="carousel-text">{{ banner.alt }}</div>
      </el-carousel-item>
    </el-carousel>

    <section class="section company-intro">
      <h2 class="section-title"><span>公司简介</span> / ABOUT US</h2>
      <p class="intro-text">
        JTUAV 智能无人机技术有限公司，是国内领先的无人机系统及解决方案提供商。我们专注于工业级无人机的研发、生产、销售与服务，致力于为电力巡检、地理测绘、农业植保、安防监控等领域提供高效、智能、可靠的空中作业平台和定制化解决方案。
      </p>
      <div class="advantage-grid">
        <div class="advantage-item">
          <el-icon :size="40" class="advantage-icon"><TrophyBase /></el-icon>
          <h3>行业领先</h3>
          <p>多项核心技术专利，引领行业发展。</p>
        </div>
        <div class="advantage-item">
          <el-icon :size="40" class="advantage-icon"><Setting /></el-icon>
          <h3>智能高效</h3>
          <p>AI赋能，提升作业效率与数据精度。</p>
        </div>
        <div class="advantage-item">
          <el-icon :size="40" class="advantage-icon"><Setting /></el-icon>
          <h3>安全可靠</h3>
          <p>严苛测试标准，保障飞行作业安全。</p>
        </div>
        <div class="advantage-item">
          <el-icon :size="40" class="advantage-icon"><Service /></el-icon>
          <h3>卓越服务</h3>
          <p>专业团队，提供全方位技术支持。</p>
        </div>
      </div>
    </section>

    <section class="section product-recommendation">
      <h2 class="section-title"><span>核心产品</span> / PRODUCTS</h2>
      <el-row :gutter="20">
        <el-col :span="6" v-for="product in products" :key="product.id">
          <el-card class="product-card" shadow="hover" @click="goToProductDetail(product.id)">
            <img :src="product.imageUrl" class="product-image" alt="Product Image" />
            <div class="product-info">
              <h3>{{ product.name }}</h3>
              <p>{{ product.description.substring(0, 50) }}...</p>
              <el-button type="primary" link>查看详情 <el-icon><Right /></el-icon></el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <div class="more-button-container">
        <el-button type="primary" round size="large" @click="router.push('/products')">查看更多产品 <el-icon><ArrowRight /></el-icon></el-button>
      </div>
    </section>

    <section class="section news-updates">
      <h2 class="section-title"><span>新闻动态</span> / NEWS</h2>
      <el-row :gutter="20">
        <el-col :span="12" class="news-main-col">
          <el-card class="main-news-card" shadow="hover" @click="goToNewsDetail(mainNews.id)">
<!--            <img :src="mainNews.imageUrl" class="main-news-image" alt="Main News Image" />-->
            <span style="color: #0055a4;font-size: 16px" >新闻标题</span>
            <div class="main-news-content">
              <h4>{{ mainNews.title }}</h4>
              <p class="main-news-date">{{ mainNews.date }}</p>
              <p>{{ mainNews.summary }}</p>
              <el-button type="primary" link>阅读全文 <el-icon><Right /></el-icon></el-button>
            </div>
          </el-card>
        </el-col>
        <el-col :span="12" class="news-list-col">
          <el-list class="news-list">
            <el-list-item v-for="news in newsList" :key="news.id" class="news-list-item" @click="goToNewsDetail(news.id)">
              <div class="news-item-content">
                <span class="news-list-title">{{ news.title }}</span>
                <span class="news-list-date">{{ news.date }}</span>
              </div>
            </el-list-item>
          </el-list>
          <div class="more-button-container-news">
            <el-button type="info" round @click="router.push('/news')">更多新闻 <el-icon><ArrowRight /></el-icon></el-button>
          </div>
        </el-col>
      </el-row>
    </section>

    <section class="section partners">
      <h2 class="section-title"><span>合作伙伴</span> / PARTNERS</h2>
      <div class="partner-logos">
        <img src="http://www.socoologo.com/uploads/images/products/2019/20190906_5d71c50f75b42.jpg" alt="Partner 1" />
        <img src="http://www.socoologo.com/uploads/images/products/2019/20190906_5d71d70866219.jpg" alt="Partner 2" />
        <img src="http://www.socoologo.com/uploads/images/products/2019/20190906_5d71d5682c303.jpg" alt="Partner 3" />
        <img src="http://www.socoologo.com/uploads/images/products/2019/20190906_5d720ef19baae.jpg" alt="Partner 4" />
        <img src="http://www.socoologo.com/uploads/images/products/2019/20190911_5d78af94a67b6.jpg" alt="Partner 5" />
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import {
  TrophyBase, Setting, Service, Right, ArrowRight // 引入 Element Plus 图标
} from '@element-plus/icons-vue'

const router = useRouter()

// 模拟数据，实际应从后端获取
const banners = ref([
  { src: 'https://aosspic10001.websiteonline.cn/pro58a77549/image/大图2.jpg', alt: '工业无人机解决方案' },
  { src: 'https://aosspic10001.websiteonline.cn/pro58a77549/image/300照片1.png', alt: '智能无人机系统' },
  { src: 'https://aosspic10001.websiteonline.cn/pro58a77549/image/大图1.jpg', alt: '探索空中科技未来' }
])

const products = ref([
  { id: 1, name: 'JT-500 智能巡检无人机', imageUrl: 'https://aosspic10001.websiteonline.cn/pro58a77549/image/245_GO6NHh.jpg', description: '专为电力线巡检设计，具备长航时、高精度、智能化避障等特点。' },
  { id: 2, name: 'JT-X 测绘无人机', imageUrl: 'https://aosspic10001.websiteonline.cn/pro58a77549/image/380.jpg', description: '适用于高精度地形测绘、三维建模，提高作业效率。' },
  { id: 3, name: 'JT-AG 农业植保无人机', imageUrl: 'https://aosspic10001.websiteonline.cn/pro58a77549/image/450_hh2HvT.jpg', description: '高效精准植保作业，大幅提升农业生产效率。' },
  { id: 4, name: 'JT-PRO 警用安防无人机', imageUrl: 'https://aosspic10001.websiteonline.cn/pro58a77549/image/500.jpg', description: '用于公安、消防等应急响应，提供空中视角支持。' }
])

const mainNews = ref({
  id: 101,
  title: 'JTUAV荣获“年度科技创新企业”称号',
  // imageUrl: 'https://via.placeholder.com/600x400?text=News+Highlight',
  date: '2023-10-26',
  summary: '近日，JTUAV凭借其在无人机领域的多项创新技术和卓越市场表现，荣获了由XXX协会颁发的“年度科技创新企业”称号。这标志着业界对JTUAV技术实力和市场贡献的充分肯定。'
})

const newsList = ref([
  { id: 102, title: 'JT-500无人机在电力巡检中再创佳绩', date: '2023-10-20' },
  { id: 103, title: 'JTUAV携手XXX大学共建无人机联合实验室', date: '2023-10-15' },
  { id: 104, title: '新型复合材料无人机成功首飞', date: '2023-10-10' },
  { id: 105, title: 'JTUAV发布2023年Q3财报，营收同比增长30%', date: '2023-09-30' }
])

const goToProductDetail = (id) => {
  router.push(`/products/${id}`)
}

const goToNewsDetail = (id) => {
  router.push(`/news/${id}`)
}

// 实际项目中，这里会调用后端接口获取数据
onMounted(async () => {
  // try {
  //   const productsRes = await axios.get('http://localhost:8080/api/product/list')
  //   products.value = productsRes.data.slice(0, 4) // 只显示前4个产品
  //
  //   const newsRes = await axios.get('http://localhost:8080/api/news/list')
  //   mainNews.value = newsRes.data[0]
  //   newsList.value = newsRes.data.slice(1, 5)
  // } catch (error) {
  //   console.error("Failed to fetch data:", error)
  // }
})
</script>

<style scoped>
.home-page {
  max-width: 1200px;
  margin: 0 auto;
}

/* 轮播图样式 */
.el-carousel {
  margin-bottom: 40px;
}
.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.el-carousel__item .carousel-text {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  padding: 15px 20px;
  font-size: 24px;
  text-align: center;
}

/* 通用 section 样式 */
.section {
  padding: 60px 20px;
  background-color: #fff;
  margin-bottom: 30px;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.section-title {
  text-align: center;
  font-size: 32px;
  margin-bottom: 50px;
  color: #333;
  position: relative;
}
.section-title span {
  color: #003366; /* 主题色 */
  font-weight: bold;
}
.section-title::after {
  content: '';
  display: block;
  width: 80px;
  height: 4px;
  background-color: #409EFF; /* 亮蓝色强调 */
  margin: 15px auto 0;
}

/* 公司简介 */
.company-intro .intro-text {
  text-align: center;
  font-size: 18px;
  color: #666;
  line-height: 1.8;
  max-width: 900px;
  margin: 0 auto 50px;
}
.advantage-grid {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 30px;
  margin-top: 40px;
}
.advantage-item {
  text-align: center;
  width: 220px;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  transition: all 0.3s ease;
}
.advantage-item:hover {
  background-color: #e6f7ff;
  transform: translateY(-5px);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}
.advantage-icon {
  color: #409EFF;
  margin-bottom: 15px;
}
.advantage-item h3 {
  font-size: 20px;
  color: #003366;
  margin-bottom: 10px;
}
.advantage-item p {
  font-size: 14px;
  color: #777;
  line-height: 1.6;
}

/* 产品推荐 */
.product-recommendation .el-row {
  justify-content: center;
}
.product-card {
  margin-bottom: 20px;
  cursor: pointer;
}
.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-bottom: 1px solid #eee;
}
.product-info {
  padding: 15px;
}
.product-info h3 {
  font-size: 18px;
  color: #003366;
  margin-top: 0;
  margin-bottom: 10px;
}
.product-info p {
  font-size: 14px;
  color: #666;
  line-height: 1.6;
  height: 60px; /* 限制高度，避免内容过多撑开 */
  overflow: hidden;
  margin-bottom: 10px;
}
.more-button-container {
  text-align: center;
  margin-top: 40px;
}

/* 新闻动态 */
.news-updates .el-row {
  align-items: flex-start;
}
.main-news-card {
  height: 100%;
  cursor: pointer;
}
.main-news-image {
  width: 100%;
  height: 250px;
  object-fit: cover;
  border-bottom: 1px solid #eee;
  margin-bottom: 15px;
}
.main-news-content h4 {
  font-size: 22px;
  color: #003366;
  margin-top: 0;
  margin-bottom: 10px;
}
.main-news-date {
  font-size: 13px;
  color: #999;
  margin-bottom: 10px;
}
.main-news-content p {
  font-size: 15px;
  color: #666;
  line-height: 1.7;
  height: 90px;
  overflow: hidden;
  margin-bottom: 15px;
}

.news-list {
  list-style: none;
  padding: 0;
  margin: 0;
}
.news-list-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 0;
  border-bottom: 1px dashed #eee;
  cursor: pointer;
  transition: all 0.2s ease;
}
.news-list-item:last-child {
  border-bottom: none;
}
.news-list-item:hover {
  background-color: #f5f5f5;
  padding-left: 10px;
}
.news-item-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}
.news-list-title {
  font-size: 16px;
  color: #333;
  flex: 1;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  margin-right: 15px;
}
.news-list-date {
  font-size: 13px;
  color: #999;
  flex-shrink: 0;
}
.more-button-container-news {
  text-align: right;
  margin-top: 20px;
}

/* 合作伙伴 */
.partners .partner-logos {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 30px;
}
.partners .partner-logos img {
  width: 150px;
  height: 80px;
  object-fit: contain;
  filter: grayscale(100%);
  opacity: 0.7;
  transition: all 0.3s ease;
}
.partners .partner-logos img:hover {
  filter: grayscale(0%);
  opacity: 1;
}
</style>