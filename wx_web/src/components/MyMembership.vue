<template>
  <div>
    <div class="imgBox">
      <img src="../../static/img/pay-success.png" alt>
    </div>
    <div class="inputBox">
      <el-input v-model="input1" :disabled="true">
        <template slot="prepend">余额</template>
      </el-input>
    </div>
    <div class="btnBox">
      <router-link to='/MemberShipInfo'>
        <el-button type="primary" class="btn">绑定实体卡</el-button>
      </router-link>
      <el-button type="primary" class="btn">在线购卡</el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: "MyMembership",
  data() {
    return {
      input1: 0.0
    };
  }
};
</script>
<style >
.imgBox {
  margin: 10px;
  border: 1px solid rebeccapurple;
  border-radius: 10px;
  overflow: hidden;
}
.inputBox {
  margin: 10px;
}
.btnBox {
  margin: 10px;
  width: 100%;
}
.btn {
  width: 45%;
}
</style>


