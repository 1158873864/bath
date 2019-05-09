<template>
  <div>
    <!-- <div class="vux-demo">
      <img class="logo" src="../assets/vux_logo.png">
      <h1> </h1>
    </div>
    <group title="cell demo">
      <cell title="VUX" value="cool" is-link></cell>
    </group>-->

    <div class="inputBox">
      <div style="float:left;width:80%">
        <el-input>
          <template slot="prepend">请输入验证码</template>
          <template slot="append">123456</template>
        </el-input>
      </div>
      <div style="float:left;width:20%">
        <el-button type="primary" @click="open">点击抢券</el-button>
      </div>
    </div>
    <div style="clear:both"></div>
    <div class="tab-list">
      <el-tabs type="border-card">
        <el-tab-pane label="未使用" name="first">未使用</el-tab-pane>
        <el-tab-pane label="已使用" name="second">已使用</el-tab-pane>
        <!-- <el-tab-pane label="已过期" name="third">已过期</el-tab-pane> -->
      </el-tabs>
    </div>
  </div>
</template>

<script>
import { Group, Cell } from "vux";
import axios from "axios";
import * as config from "../../config";
export default {
  components: {
    Group,
    Cell
  },
  data() {
    return {};
  },
  created(){
    this.getMyCoupon();
  },
  methods: {
    open() {
      this.$alert("线上抢券出错：没有可供线上抢券的活动方案", "提示", {
        confirmButtonText: "关闭"
        // callback: action => {
        //   this.$message({
        //     type: "info",
        //     message: `action: ${action}`
        //   });
        // }
      });
    },
    // 获取优惠券列表
    getMyCoupon() {
      axios({
        method: "post",
        url: config.baseApi + "/coupon/myCoupon?openid",
        headers: {
          "X-Litemall-Admin-Token": sessionStorage.getItem("token")
        },
        data: {
          
        }
      }).then(reg => {
        console.log(reg);
      });
    }
  },

};
</script>

</script>

<style scoped>
/* .vux-demo {
  text-align: center;
}
.logo {
  width: 100px;
  height: 100px
} */
.inputBox {
  width: 100%;
  margin: 15px;
}
.inputBox /deep/ .el-input-group {
  border: 1px solid #dcdfe6;
}
.inputBox /deep/ .el-input-group__append {
  border-radius: 0px;
  color: red;
}
.el-button {
  border-top-left-radius: 0px;
  border-bottom-left-radius: 0px;
  background-color: green;
  opacity: 0.7;
  height: 42px;
}
.el-button:hover {
  opacity: 0.9;
  background-color: green;
}
.tab-list {
  margin: 20px;
}
</style>
/* .vux-demo {
  text-align: center;
}
.logo {
  width: 100px;
  height: 100px
} */
.inputBox{
  margin: 15px
}
.el-input-group{
  border: 1px solid #DCDFE6;
}
.el-input-group__append{
  border-radius: 0px;
  color: red
}
.el-button{
  border-top-left-radius: 0px;
  border-bottom-left-radius: 0px;
  background-color: green;
  opacity: 0.7;
  height: 42px;
  
}
.el-button:hover{
  opacity: 0.9;
  background-color: green;
}
.tab-list{
 margin: 20px
}
</style>
