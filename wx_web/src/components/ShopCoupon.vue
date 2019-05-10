<template>
  <div>
    <div class="container">
      <div class="list" v-for="(item, index) in couponList" :key="index">
      <router-link :to="{path:'/CouponDetail',query:{couponId: item.id,}}">
      <div class="listall"> 
         <div class="list_left">
            <div>
               <img class="image"  :src="'https://www.junrongcenter.com/' + item.image" style="width: 100%;
                height: 134px;" alt="">
            </div>
        </div>
            <div class="list_right">
              
              <p  class="type" >
                <span>{{item.name}}</span>
                 <span style="color:gray;float:right;">
                 有效期:
                 <span style="color:orange">{{dateFormat(item.loseEffectTime)}}</span>
                 </span>
              </p>
              <p class="brief" >{{item.description}}</p>
               <p class="price" >￥{{item.price}}元
                 <span class="priceold">￥{{item.originalPrice}}元</span>
               </p>
              <p style="color:blue;float:right;">
                 已售：
                <span class="number">{{item.amount}}&ensp;张</span>
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
import axios from 'axios'
import * as config from '../../config'

export default {
  name: 'couponList',
  components: {
    Group,
    Cell
  },
  data () {
    return {
      couponList: []
    }
  },
  created () {
    this.getlist()
  },
  methods: {
    getlist () {
      axios({
        method: 'get',
        url: config.baseApi + '/groupon/ordinary/available',
        headers: {
          'X-Litemall-Admin-Token': sessionStorage.getItem('token')
        }
      }).then(result => {
        console.log(result, 'getlisttlistlis')
        this.couponList = result.data.groupons
      })
    },
    dateFormat (time) {
      var date = new Date(time)
      var year = date.getFullYear()
      var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
      var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      return year + '-' + month + '-' + day
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
 font-weight: 100;
}
</style>
