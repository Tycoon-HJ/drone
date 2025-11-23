<template>
  <div class="about-page">
    <div class="about-banner">
      <h1>关于我们</h1>
    </div>

    <div class="content-section container">
      <div class="intro-text">
        <h2>公司简介</h2>
        <p>
          JTUAV 智能无人机技术有限公司成立于2015年，是一家集无人机研发、生产、销售、服务于一体的高新技术企业。
          公司总部位于科技之都，拥有超过2000平米的研发中心和生产基地。
        </p>
        <p>
          我们始终坚持“技术驱动，创新未来”的发展理念，汇聚了一批来自航空航天院校的顶尖技术人才。
          目前，公司拥有自主知识产权专利50余项，产品涵盖多旋翼、固定翼、垂直起降固定翼等多个系列，
          广泛应用于电力巡检、应急救援、农林植保、测绘勘探等领域。
        </p>
      </div>

      <div class="intro-image">
        <img src="https://aosspic10001.websiteonline.cn/pro58a77549/image/%E8%B5%B0%E8%BF%9B%E6%8D%B7%E7%89%B9%E8%88%AA%E7%A9%BA%E5%8F%B3%E8%BE%B9%E9%85%8D%E5%9B%BE.jpg" alt="Office">
      </div>
    </div>

    <div class="timeline-section">
      <div class="container">
        <h2>发展历程</h2>
        <el-timeline>
          <el-timeline-item timestamp="2023" placement="top">
            <el-card>
              <h4>国际化战略启动</h4>
              <p>产品出口欧美、东南亚等20多个国家和地区。</p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="2020" placement="top">
            <el-card>
              <h4>高新技术企业认证</h4>
              <p>获得国家高新技术企业认定，发布JT-X系列测绘机。</p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="2015" placement="top">
            <el-card>
              <h4>公司成立</h4>
              <p>JTUAV 正式注册成立，确立工业级无人机研发方向。</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
    </div>

    <div class="contact-section container">
      <h2>联系方式</h2>
      <el-descriptions border :column="1">
        <el-descriptions-item label="公司地址">XX省XX市高新技术开发区科技路88号</el-descriptions-item>
        <el-descriptions-item label="联系电话">400-123-4567 / 010-88888888</el-descriptions-item>
        <el-descriptions-item label="电子邮箱">service@jtuav.com</el-descriptions-item>
        <el-descriptions-item label="工作时间">周一至周五 09:00 - 18:00</el-descriptions-item>
      </el-descriptions>
    </div>
  </div>
</template>

<style scoped>
.about-banner {
  height: 300px;
  background-color: #003366;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
}
.about-banner h1 { font-size: 48px; }

.container { max-width: 1000px; margin: 0 auto; padding: 60px 20px; }

.content-section { display: flex; gap: 40px; align-items: center; }
.intro-text { flex: 1; }
.intro-text h2 { color: #003366; margin-bottom: 20px; }
.intro-text p { line-height: 1.8; color: #555; margin-bottom: 15px; text-align: justify; }
.intro-image { flex: 1; }
.intro-image img { width: 100%; border-radius: 8px; box-shadow: 0 10px 20px rgba(0,0,0,0.1); }

.timeline-section { background-color: #f5f7fa; padding: 60px 0; }
.timeline-section h2 { text-align: center; margin-bottom: 40px; color: #333; }

.contact-section h2 { text-align: center; margin-bottom: 30px; color: #333; }
.map-placeholder {
  margin-top: 30px;
  height: 300px;
  background-color: #e0e0e0;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #888;
  font-size: 20px;
}

@media (max-width: 768px) {
  .content-section { flex-direction: column; }
}
</style>