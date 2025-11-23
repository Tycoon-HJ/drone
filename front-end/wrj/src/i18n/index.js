import { createI18n } from 'vue-i18n'
import zh from '../locales/zh'
import en from '../locales/en'

// 获取本地存储的语言，如果没有则默认中文
const savedLocale = localStorage.getItem('lang') || 'zh'

const i18n = createI18n({
    legacy: false, // 使用 Composition API 模式
    locale: savedLocale,
    fallbackLocale: 'zh', // 备用语言
    messages: {
        zh,
        en
    }
})

export default i18n