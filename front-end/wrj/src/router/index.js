// router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ProductListView from '../views/ProductListView.vue'
import ProductDetailView from '../views/ProductDetailView.vue'
import NewsListView from '../views/NewsListView.vue'
import NewsDetailView from '../views/NewsDetailView.vue'
import AboutView from '../views/AboutView.vue'

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/products',
        name: 'products',
        component: ProductListView
    },
    {
        path: '/products/:id', // 产品详情页
        name: 'productDetail',
        component: ProductDetailView
    },
    {
        path: '/news',
        name: 'news',
        component: NewsListView
    },
    {
        path: '/news/:id', // 新闻详情页
        name: 'newsDetail',
        component: NewsDetailView
    },
    {
        path: '/about',
        name: 'about',
        component: AboutView
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router