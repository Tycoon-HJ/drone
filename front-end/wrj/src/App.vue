<template>
  <div class="common-layout">
    <el-container>
      <el-header class="app-header">
        <div class="header-content">
          <div class="logo">
            <img src="https://aosspic10001.websiteonline.cn/pro58a77549/image/logo1.png" alt="JTUAV Logo"
                 class="logo-img"/>
          </div>
          <el-menu
              :default-active="activePath"
              class="el-menu-demo"
              mode="horizontal"
              :ellipsis="false"
              router
              @select="handleMenuSelect"
          >
            <el-menu-item index="/">{{ $t('menu.home') }}</el-menu-item>
            <el-menu-item index="/products">{{ $t('menu.products') }}</el-menu-item>
            <el-menu-item index="/news">{{ $t('menu.news') }}</el-menu-item>
            <el-menu-item index="/about">{{ $t('menu.about') }}</el-menu-item>
          </el-menu>
          <div class="header-right">
            <el-dropdown @command="handleLangCommand">
              <span class="lang-dropdown-link">
                <el-icon><Operation/></el-icon> {{ currentLang === 'zh' ? '中文' : 'English' }}
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item command="zh">中文</el-dropdown-item>
                  <el-dropdown-item command="en">English</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </el-header>

      <el-main class="app-main">
        <router-view/>
      </el-main>

      <el-footer class="app-footer footer-section">
        <div class="footer-content">
          <div class="footer-section contact-info">
            <h3>联系我们</h3>
            <p>
              <el-icon>
                <Location/>
              </el-icon>
              地址：XXX省XXX市XXX区XXX号
            </p>
            <p>
              <el-icon>
                <Phone/>
              </el-icon>
              电话：400-123-4567
            </p>
            <p>
              <el-icon>
                <Message/>
              </el-icon>
              邮箱：info@jtuav.com
            </p>
          </div>
          <div class="footer-section nav-links">
            <h3>快速链接</h3>
            <ul>
              <li>
                <router-link to="/">首页</router-link>
              </li>
              <li>
                <router-link to="/products">产品中心</router-link>
              </li>
              <li>
                <router-link to="/news">新闻动态</router-link>
              </li>
              <li>
                <router-link to="/about">关于我们</router-link>
              </li>
            </ul>
          </div>
          <div class="footer-section social-media">
            <h3>关注我们</h3>
            <!--            <div class="social-icons">-->
            <!--              <el-button type="primary" link><el-icon :size="20"><component :is="'Wechat'"></component></el-icon></el-button>-->
            <!--              <el-button type="primary" link><el-icon :size="20"><component :is="'Qq'"></component></el-icon></el-button>-->
            <!--              <el-button type="primary" link><el-icon :size="20"><component :is="'Weibo'"></component></el-icon></el-button>-->
            <!--            </div>-->
            <img
                src="https://aosspic10001.websiteonline.cn/pro58a77549/image/%E5%AE%98%E6%96%B9%E4%BA%8C%E7%BB%B4%E7%A0%81.png"
                alt="QR Code" class="qr-code">
          </div>
        </div>
        <div class="copyright footer-section social-media">
          <el-text :cell-style="{'text-align':'center'}" >© 2025 JTUAV Technology Co., Ltd. All Rights Reserved. ICP备 XXXXXXXX号</el-text>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
import {ref, watch} from 'vue'
import {useRoute} from 'vue-router'
import {useI18n} from 'vue-i18n'
import {Phone, Location, Message, Operation} from '@element-plus/icons-vue' // 引入Element Plus图标

const route = useRoute()
const activePath = ref('/')
const {locale, t} = useI18n()
const currentLang = ref(locale.value)

// 切换语言方法
const handleLangCommand = (command) => {
  locale.value = command
  currentLang.value = command
  localStorage.setItem('lang', command) // 持久化存储
  // 可选：刷新页面以确保所有组件特别是请求的数据更新
  // window.location.reload()
}

watch(route, (newRoute) => {
  activePath.value = newRoute.path
}, {immediate: true}) // 立即执行一次

const handleMenuSelect = (index) => {
  // console.log(index) // 可以用于调试
}
</script>

<style>
/* 全局样式 */
body {
  margin: 0;
  font-family: 'PingFang SC', 'Helvetica Neue', Helvetica, 'Microsoft YaHei', Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #333;
  background-color: #f5f5f5;
}

a {
  text-decoration: none;
  color: inherit;
}

.common-layout {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.app-header {
  height: 80px;
  background-color: #003366; /* 深蓝色 */
  color: white;
  display: flex;
  align-items: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 0; /* 移除默认 padding */
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.logo {
  display: flex;
  align-items: center;
  font-size: 24px;
  font-weight: bold;
  color: white;
}

.logo-img {
  height: 40px;
  margin-right: 10px;
}

.el-menu-demo {
  background-color: transparent !important;
  border-bottom: none !important;
  flex-grow: 1;
  justify-content: center; /* 菜单项居中 */
}

.el-menu-demo .el-menu-item,
.el-menu-demo .el-sub-menu__title {
  color: white !important;
  font-size: 16px;
  padding: 0 25px;
}

.el-menu-demo .el-menu-item:hover,
.el-menu-demo .el-sub-menu__title:hover {
  background-color: rgba(255, 255, 255, 0.1) !important;
  color: #409EFF !important; /* 鼠标悬停时的亮蓝色 */
}

.el-menu-demo .el-menu-item.is-active {
  color: #409EFF !important;
  border-bottom: 2px solid #409EFF !important;
  background-color: transparent !important;
}

.header-right {
  display: flex;
  align-items: center;
}

.header-right .el-button {
  color: white;
  margin-left: 15px;
}

.header-right .el-icon {
  margin-right: 5px;
}

.app-main {
  flex-grow: 1;
  padding: 0; /* 移除默认 padding */
}

.app-footer {
  background-color: #222; /* 深灰色 */
  color: #ccc;
  padding: 40px 0 20px 0;
  font-size: 14px;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  padding: 0 20px;
  flex-wrap: wrap; /* 适应小屏幕 */
}

.footer-section {
  flex: 1;
  min-width: 250px;
  //margin-bottom: 20px;
}

.footer-section h3 {
  color: white;
  margin-bottom: 20px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  padding-bottom: 10px;
}

.footer-section p, .footer-section li {
  line-height: 1.8;
  display: flex;
  align-items: center;
}

.footer-section .el-icon {
  margin-right: 8px;
  color: #409EFF;
}

.footer-section ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer-section ul li a {
  color: #ccc;
}

.footer-section ul li a:hover {
  color: #409EFF;
}

.social-icons .el-button {
  margin-right: 10px;
  color: #ccc;
  font-size: 24px;
}

.social-icons .el-button:hover {
  color: #409EFF;
}

.qr-code {
  width: 100px;
  height: 100px;
  margin-top: 15px;
  border: 1px solid #555;
}

.copyright {
  text-align: center;
  margin-top: 30px;
  margin-bottom: 10px;
  padding-top: 10px;
  border-top: 1px solid #333;
  color: #888;
}

/* Element Plus 覆盖样式 */
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>