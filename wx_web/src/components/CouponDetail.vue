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
              <div  class="type" >
                <span>{{item.type}}</span>
                <span class="limit">
                 有效期:
                 <span style="color:orange">{{item.limit}}</span>
                 </span>
              </div>
               <p class="price" >￥{{item.price}}元
                 <span class="priceold">￥{{item.price}}元</span>
                 <Button type="primary" class="more" @click="moreInfo()">更多详情</Button>
               </p>
               <p class="brief" >{{item.brief}}</p>       
        <div class="money">
          <div class="calute">
         <button class="mark"  @click="reduce()" >-</button>
        <input  v-on:input="calculate()" type="number" v-model="num" />
        <button class="mark"   @click="add()" >+</button>
         </div>
     
       <span class="allmoney">
             <button class="redee">金额</button>
             <input  style="width:110px;height:25px;background:#EEEEEE" label="金额 " type="number" :value="item.price*num"/>
       </span>
      </div>

      <div>
        <button type="primary" class="pay" @click="payfor">微信支付</button>
      </div>
      </div> 
      </div>      
    </div>
  
   <el-dialog title="成人门票更多详情" :visible.sync="dialogVisible"  width="90%" v-show="dialogVisible">
   <p>使用须知：
    此券包含：洗浴、汗蒸、高档海鲜美食自助、儿童嬉水、儿童乐园、各种时令水果、4D影院、VIP专属休息区、健身、网咖、WIFI、休闲娱乐
   </p>
    <span>
    温馨提示：此券2019年1月26日至2019年2月11日，不额外赠送足疗或者双人助浴
    每人每次限使用1张。
    此券不兑换现金、不找零；
    此券不与其他优惠同时使用；
    此券请在有效期内使用，过期作废；
    此券法定节假日及周末不可使用；
    御池宫汤泉主题酒店对此券拥有法律许可的最终解释权
    地址：南京市浦口区浦口大道11号明发新城中心2幢3单元
    御池宫服务热线：025-58659999或18068838855
     扬州中天管理免费热线：400-828-7676</span>
 
  <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="infoClose()">关闭</el-button>
  </span>

</el-dialog>

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
      num: 1,
      dialogVisible: false,
      couponDetail: [
        {
          id: 0,
          img: '../static/img/pay-success.png',
          type: '成人门票',
          limit: '2019.7.30',
          brief: '包含12小时成人门票抵用一次！不退不换！',
          price: '129.00',
          number: '33'
        }
      ]
    }
  },
  methods: {
    calculate () {
    },
    reduce () {
      if (this.num === 1) {
        this.num = 1
      } else {
        this.num--
      }
    },
    add () {
      this.num++
    },
    moreInfo () {
      this.dialogVisible = true
    },
    infoClose () {
      this.dialogVisible = false
    },
    payfor () {
      alert('此处为微信支付')
    }
  }
}
</script>

<style>
.detail{
 width: 100%;
}
.detailall{
 width: 100%;
 height: 100%
}
/* .detail_top{
 width:100%; 
 height: 100%
}
.image{
  width:100%;
  height: 70%
} */
.detail_bottom{
margin-top:15px;
font-size: 14px;
width:100%;
}
.money{
  display: flex;
  width:90%;
  margin-top:15px;
  height:35px;
  margin-left: 10px;
  position:relative;
}
.limit{
  color:gray;
   position: absolute;
   right: 2px;

}
.mark{
  background:#E6E6E6;
  height:27px;
  width:34px;
}
.redee{
  background:#EEEEEE;
  height:27px;
  width:55px;
}
.type{
  color:gray;
  margin-left:10px;
   width: 92%;
   position: relative;
}
.price{
   width: 92%;
   margin-top:10px;
  font-weight: 600;
  margin-left:12px;
  font-size: 18px;
}
.priceold{
  color:gray;
  text-decoration:line-through;
  font-weight: 100;
}
.more{
  margin-right:10px;
  background:#3175AF;
  border-radius: 6px;
  float: right;
}
.calute{
  float: left;
  display: flex;
  width:50%;
  justify-content: flex-start;
  text-align: center;
}
.calute input{
width: 80px;
height:25px;
background:#EEEEEE;
text-align: center;
}
.brief{
  margin-left:10px;
  margin-top:10px;
  font-size:14px;
}
.pay{
  width: 90%;
  height: 30px;
  background: #4FA84F;
  margin-left:14px;
  margin-top:15px;
  border-radius: 5px;
}
.allmoney{
  background:lightgray;
  height:25px; 
  position:absolute;
  right:0px;
  display:flex;
  justify-content: flex-end;
}
</style>
