import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import MyCoupon from '@/components/MyCoupon'
import ShopCoupon from '@/components/ShopCoupon'
import CouponDetail from '@/components/CouponDetail'
import MyMembership from '@/components/MyMembership'
import MemberShipInfo from '@/components/MemberShipInfo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/MyCoupon',
      name: 'MyCoupon',
      component: MyCoupon
    },
    {
      path: '/ShopCoupon',
      name: 'ShopCoupon',
      component: ShopCoupon
    },
    {
      path: '/CouponDetail', // 优惠券细节
      name: 'CouponDetail',
      component: CouponDetail
    },
    {
      path:'/MyMembership', //我的会员卡
      name:'MyMembership',
      component:MyMembership,
      children:[
        {
        path:'MemberShipInfo',
        name:'MemberShipInfo',
        component:MemberShipInfo
        }
      ]
      
    }
  ]
})
