<template>
  <div>
    <div class="container">
      <div class="list" v-for="(item, index) in couponList" :key="index">
      <router-link :to="{path:'/CouponDetail',query:{couponId: item.id,}}">
      <div class="listall"> 
         <div class="list_left">
            <div>
                <img class="image" :src="item.img" alt="">
            </div>
        </div>
            <div class="list_right">
              
              <p  class="type" >
                <span>{{item.type}}</span>
                 <span style="color:gray;">
                 有效期:
                 <span style="color:orange">{{item.limit}}</span>
                 </span>
              </p>
              <p class="brief" >{{item.brief}}</p>
               <p class="price" >￥{{item.price}}元
                 <span class="priceold">￥{{item.price}}元</span>
               </p>
              <p style="color:blue;float:right;">
                 已售：
                <span class="number" v-text="item.number">&emsp;张</span>
              </p>
       </div>   
      </div>      
            </router-link> 
    </div>
  </div>
  </div>
</template>

<script>
import { Group, Cell } from 'vux'

export default {
  components: {
    Group,
    Cell
  },
  data () {
    return {
      couponList: [
        {
          id: 0,
          img: '../static/img/pay-success.png',
          type: '成人门票',
          limit: '2019.7.30',
          brief: '包含12小时成人门票抵用一次！不退不换！',
          price: '129.00',
          number: '3924'
        },
        {
          id: 1,
          img: '../static/img/pay-success.png',
          type: '特价儿童门票',
          limit: '2019.7.30',
          brief: '包含12小时儿童门票抵用一次！不退不换！',
          price: '59.00',
          number: '3924'
        },
        {
          id: 2,
          img: '../static/img/pay-success.png ',
          type: '特价军人门票',
          limit: '2019.7.30',
          brief: '包含12小时成人门票抵用一次！不退不换！',
          price: '0.00',
          number: '3924'
        }
      ]
    }
  }
}
</script>

<style>
.container{
  height:100%;
}
.list{
  width:100%;
}
.listall{
  display: flex;
  /* justify-content: space-between; */
  height: 150px;
  border: 0.5px solid gray;
}
.list_left{
 float: left;
 width: 35%;
 height: 134px;
}
.list_right{
  margin-left:5px;
  width: 58%;
  font-size: 14px;
  color: black;
}
.image{
  width: 100%;
  height: 134px;
}
.type{
  margin-top:4px;
}
.brief{
  /* display: block;
  word-break: break-all;
  word-wrap:break-word; */

  margin-top:4px;
  font-size: 14px;
}
.price{
  margin-top:4px;
}
.number{
  margin-top:4px;
  color: blue;
}
.priceold{
 color:gray;
 text-decoration:line-through;
}
</style>
