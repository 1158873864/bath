<template>
  <div>
    <div class="container">
      <div class="detail"  >
         <div class="detail_top">        
                <img class="image" :src="'https://www.junrongcenter.com/' + couponDetail.image" alt="">       
        </div>
        <div style="clear:both;"></div>
        <div class="detail_bottom">           
              <div  class="type" >
                <span>{{couponDetail.name}}</span>
                <span class="limit">
                 有效期:
                 <span style="color:orange">{{dateFormat(couponDetail.loseEffectTime)}}</span>
                 </span>
              </div>
               <p class="price" >￥{{couponDetail.price}}元
                 <span class="priceold">￥{{couponDetail.price}}元</span>
                 <Button type="primary" class="more" @click="moreInfo()">更多详情</Button>
               </p>
               <p class="brief" >{{couponDetail.brief}}</p>       
        <div class="money">
          <div class="calute">
         <span class="mark"  @click="reduce()" >-</span>
        <input  v-on:input="calculate()" type="number" v-model="num" />
        <span class="mark"   @click="add()" >+</span>
         </div>
         <!-- <template>
         <el-input-number v-model="num" size="small" @change="handleChange" :min="1"  label="描述文字"></el-input-number>
         </template> -->
        <div class="allmoney">
        <el-input  class="reder" size="small" :value="couponDetail.price*num">
            <template slot="prepend">金额</template>
        </el-input>
       </div>
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
</template>

<script>
import { Group, Cell } from 'vux'
import * as config from '../../config'
import axios from 'axios'

export default {
  components: {
    Group,
    Cell
  },
  data () {
    return {
      num: 1,
      dialogVisible: false,
      couponDetail: {}
    }
  },
  created () {
    console.log(this.$route.query.couponId, 'sddddddddddsdsdsds')
    this.getDetail()
  },
  methods: {
    handleChange (value) {
      console.log(value)
    },
    calculate (value) {
      console.log(value)
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
    getDetail () {
      var couponId = this.$route.query.couponId
      axios({
        method: 'post',
        headers: {
          'X-Litemall-Admin-Token': sessionStorage.getItem('token')
        },
        url: config.baseApi + 'groupon/view?id=' + couponId
      }).then(response => {
        console.log(response, 'detaildetail')
        this.couponDetail = response.data.groupon
      }).catch(error => {
        console.log(error)
      })
    },
    dateFormat (time) {
      var date = new Date(time)
      var year = date.getFullYear()
      var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
      var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      return year + '-' + month + '-' + day
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
* { touch-action: pan-y; } 
.container{  
 width: 100%;
 height: 100%;
 overflow: hidden;
}
.detail{
 width: 100%;
 height: 100%;
 overflow: hidden;
}
.detail_top{
 width:100%; 
 height:360px
}
.image{
 width:100%; 
 height:360px
}
.detail_bottom{
margin-top:15px;
font-size: 14px;
 width: 95%;
 overflow: hidden;
}
.money{
  display: flex;
  margin-top:15px;
  height:35px;
  margin-left: 10px;
  position:relative;
}
.limit{
  color:gray;
   position: absolute;
   right: 0px;

}
.mark{
  background:#F5F7FA;
  height:30px;
  width:34px;
  border:1px solid #E8E9EF;
}
.redee{
  background:#EEEEEE;
  height:27px;
  width:55px;
}
.reder{
  float: right;
}
.type{
  color:gray;
  margin-left:10px;
   position: relative;
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
  font-weight: 100;
}
.more{
  background:#3175AF;
  border-radius: 6px;
  float: right;
}
.calute{
  display: flex;
  width:50%;
  justify-content: flex-start;
  text-align: center;
}
.calute input{
    width: 80px;
    height:32px;
    border:1px solid #E8E9EF;
    background: #ffffff;;
    text-align: center;
}
.brief{
  margin-left:10px;
  margin-top:10px;
  font-size:14px;
}
.pay{
  width: 95%;
  height: 30px;
  background: #4FA84F;
  margin-left:14px;
  margin-top:15px;
  border-radius: 5px;
}
.allmoney{
  position:absolute;
  right:0px;
  width: 50%
}
</style>
