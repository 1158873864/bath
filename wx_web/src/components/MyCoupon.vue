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
      <div style="float:left;">
      <el-input  v-model="input1">
        <template slot="prepend">请输入验证码</template>
        <template slot="append">123456</template>
      </el-input>
    </div>
    <div style="float:left">
      <el-button type="primary">主要按钮</el-button>
    </div>
    </div>
    <div style="clear:both"></div>
    <div class="tab-list">
      <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
        <el-tab-pane label="未使用" name="first">未使用</el-tab-pane>
        <el-tab-pane label="已使用" name="second">已使用</el-tab-pane>
        <!-- <el-tab-pane label="已过期" name="third">已过期</el-tab-pane> -->
      </el-tabs>
    </div>
  </div>
</template>

<script>
import { Group, Cell } from "vux";

export default {
  components: {
    Group,
    Cell
  },
  data() {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      
    };
  },
  
};
</script>

<style scoped>
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
