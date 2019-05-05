<template>
  <div>
    <div class="container">
      <div class="detail" v-for="(item, index) in couponDetail" :key="index">
      <div class="detailall"> 
         <div class="detail_top">
            <div>
                <img class="image" :src="item.img" alt="">
            </div>
        </div>
            <div class="detail_bottom">
              
              <p  class="type" >
                <span>{{item.type}}</span>
              </p>
               <p class="price" >￥{{item.price}}元
                 <span class="priceold">￥{{item.price}}元</span>
                 <Button type="primary" class="more">更多详情</Button>
               </p>
               <p class="brief" >{{item.brief}}</p>       
       </div> 
        <div class="money">
         <button class="mark"  @click="reduce" >-</button>
        <input style="width: 80px;height:25px;background:lightgray;" type="number"  :value="item.number" />
        <button class="mark"   @click="crease" >+</button>
     
       <span style="width:75px;background:lightgray;height:35px;">
         金额 
         <input  style="width:120px;height:25px;background:lightgray" label="金额 " type="number" :value="item.price"/>
       </span>
      </div>

      <div>
        <button type="primary" class="pay">微信支付</button>
      </div>
      </div>      
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
      couponDetail: [
        {
          id: 0,
          img: '../static/img/pay-success.png',
          type: '成人门票',
          limit: '2019.7.30',
          brief: '包含12小时成人门票抵用一次！不退不换！',
          price: '129.00',
          number: '1'
        }
      ]
    }
  }
}
</script>

<style>
.container{
 width: 100%;
 height: 100%
}
.detail{
 width: 100%;
 height: 100%
}
.detailall{
 width: 100%;
 height: 100%
}
.detail_top{
 width:100%; 
 height: 40%
}
.image{
  width:100%;
  height: 40%
}
.detail_bottom{
margin-top:15px;
font-size: 14px;
width:85%
}
.money{
  margin-top:15px;
  height:35px;
}
.mark{
  background:lightgray;
  height:25px;
  width:20px;
}
.type{
  color:gray;
  margin-left:10px;
}
.price{
  margin-top:10px;
  font-weight: 600;
 margin-left:12px;
 font-size: 18px;
}
.priceold{
  color:gray;
  text-decoration:line-through;
}
.more{
  margin-right:10px;
  background:rgb(119, 0, 255);
  border-radius: 6px;
  float: right;
}
.brief{
  margin-left:10px;
  margin-top:10px;
  font-size:14px;
}
.pay{
  width: 80%;
  height: 30px;
  background: green;
  margin-left:14px;
  margin-top:15px;
  border-radius: 5px;
}
</style>
