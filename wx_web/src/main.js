// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import App from './App'
import Home from './components/HelloFromVux'
import MyCoupon from '@/components/MyCoupon'
import ShopCoupon from '@/components/ShopCoupon'
import CouponDetail from '@/components/CouponDetail'

Vue.use(VueRouter)

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
