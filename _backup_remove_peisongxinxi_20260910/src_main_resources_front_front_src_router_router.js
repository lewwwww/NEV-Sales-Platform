import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import pinpaileibieList from '../pages/pinpaileibie/list'
import pinpaileibieDetail from '../pages/pinpaileibie/detail'
import pinpaileibieAdd from '../pages/pinpaileibie/add'
import xinnengyuanqicheList from '../pages/xinnengyuanqiche/list'
import xinnengyuanqicheDetail from '../pages/xinnengyuanqiche/detail'
import xinnengyuanqicheAdd from '../pages/xinnengyuanqiche/add'
import qichedingdanList from '../pages/qichedingdan/list'
import qichedingdanDetail from '../pages/qichedingdan/detail'
import qichedingdanAdd from '../pages/qichedingdan/add'
import peisongdingdanList from '../pages/peisongdingdan/list'
import peisongdingdanDetail from '../pages/peisongdingdan/detail'
import peisongdingdanAdd from '../pages/peisongdingdan/add'
import peisongxinxiList from '../pages/peisongxinxi/list'
import peisongxinxiDetail from '../pages/peisongxinxi/detail'
import peisongxinxiAdd from '../pages/peisongxinxi/add'
import yuyuekancheList from '../pages/yuyuekanche/list'
import yuyuekancheDetail from '../pages/yuyuekanche/detail'
import yuyuekancheAdd from '../pages/yuyuekanche/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'pinpaileibie',
					component: pinpaileibieList
				},
				{
					path: 'pinpaileibieDetail',
					component: pinpaileibieDetail
				},
				{
					path: 'pinpaileibieAdd',
					component: pinpaileibieAdd
				},
				{
					path: 'xinnengyuanqiche',
					component: xinnengyuanqicheList
				},
				{
					path: 'xinnengyuanqicheDetail',
					component: xinnengyuanqicheDetail
				},
				{
					path: 'xinnengyuanqicheAdd',
					component: xinnengyuanqicheAdd
				},
				{
					path: 'qichedingdan',
					component: qichedingdanList
				},
				{
					path: 'qichedingdanDetail',
					component: qichedingdanDetail
				},
				{
					path: 'qichedingdanAdd',
					component: qichedingdanAdd
				},
				{
					path: 'peisongdingdan',
					component: peisongdingdanList
				},
				{
					path: 'peisongdingdanDetail',
					component: peisongdingdanDetail
				},
				{
					path: 'peisongdingdanAdd',
					component: peisongdingdanAdd
				},
				{
					path: 'peisongxinxi',
					component: peisongxinxiList
				},
				{
					path: 'peisongxinxiDetail',
					component: peisongxinxiDetail
				},
				{
					path: 'peisongxinxiAdd',
					component: peisongxinxiAdd
				},
				{
					path: 'yuyuekanche',
					component: yuyuekancheList
				},
				{
					path: 'yuyuekancheDetail',
					component: yuyuekancheDetail
				},
				{
					path: 'yuyuekancheAdd',
					component: yuyuekancheAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
