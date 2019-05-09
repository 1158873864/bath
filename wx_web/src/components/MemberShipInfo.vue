<template>
  <div>
    <div class="menber-text">
      实体会员卡信息
    </div>
    <div style="margin:10px">
      <el-input placeholder="请输入登记的会员姓名" v-model="input1">
        <template slot="prepend">真实姓名</template>
      </el-input>
      <el-input placeholder="请输入完整的11位手机号" v-model="input1">
        <template slot="prepend">手机号码</template>
      </el-input>
      <el-input  v-model="input1">
        <template slot="prepend" style="width: 56px">验证码</template>
        <el-button type="warning" slot="append" class="getNum">获取验证码</el-button>
      </el-input>
      <div class="btnBox">
        <el-button type="success" class="btn">提交绑定</el-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "MemberShipInfo"
};
</script>
<style scoped>
.menber-text{
  width: 100%;
  border-bottom: 1px solid gainsboro;
  font-size: 18px;
  margin: 10px
  
}
.getNum {
  background-color: orange;
}
.btnBox {
  margin-top:10px;
  
}
.btn{
  width: 100%;
}
</style>

