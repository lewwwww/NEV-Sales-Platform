import axios from 'axios'
import router from '@/router/router-static'

const http = axios.create({
    timeout: 1000 * 86400,
    withCredentials: true,
    baseURL: '/springbootsnu6t',
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    }
})
// 请求拦截
http.interceptors.request.use(config => {
    // 生产环境化改造：Token 已由后端写入 HttpOnly Cookie，请求自动携带，无需再从 localStorage 取
    return config
}, error => {
    return Promise.reject(error)
})
// 响应拦截
http.interceptors.response.use(response => {
    if (response.data && response.data.code === 401) { // 401, token失效
        router.push({ name: 'login' })
    }
    return response
}, error => {
    return Promise.reject(error)
})
export default http