// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import App from './App'
import Home from './components/HelloFromVux'
import MyCoupon from '@/components/MyCoupon'
import ShopCoupon from '@/components/ShopCoupon'
import CouponDetail from '@/components/CouponDetail'
import MyMembership from '@/components/MyMembership'
import MemberShipInfo from '@/components/MemberShipInfo'
import axios from 'axios'

Vue.use(VueRouter)
Vue.use(ElementUI)

Vue.prototype.$axios = axios
axios.defaults.baseURL = '/api'
// /*axios.defaults.baseURL = '/192.168.43.20';*/
axios.defaults.headers.post['Content-Type'] = 'application/json'
// axios.defaults.headers['token'] = global.token;
// axios.defaults.headers['token'] = store.state.token;
// 532c41e3-7f37-4760-91f4-ed9e2f2327fa X-Litemall-Admin-Token

const routes = [{
  path: '/',
  component: Home
},
{
  path: '/MyCoupon', // 我的优惠券
  name: 'MyCoupon',
  component: MyCoupon
},
{
  path: '/ShopCoupon', // 购买优惠券
  name: 'ShopCoupon',
  component: ShopCoupon
},
{
  path: '/CouponDetail', // 优惠券细节
  name: 'CouponDetail',
  component: CouponDetail
},
{
  path: '/MyMembership',
  name: 'MyMembership',
  component: MyMembership
},
{
  path:'/MemberShipInfo',
  name:'MemberShipInfo',
  component:MemberShipInfo

}
]

const router = new VueRouter({
  routes
})

FastClick.attach(document.body)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app-box')
