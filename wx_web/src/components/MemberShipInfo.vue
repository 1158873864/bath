<template>
  <div>
    <el-input placeholder="请输入登记的会员姓名" v-model="input1">
      <template slot="prepend">真实姓名</template>
    </el-input>
    <el-input placeholder="请输入完整的11位手机号" v-model="input1">
      <template slot="prepend">手机号码</template>
    </el-input>
    <el-input placeholder="请输入内容" v-model="input1">
      <template slot="prepend">验证码</template>
    </el-input>
     <div>
      <el-button type="success">提交绑定</el-button>
  </div>
  </div>
 
</template>
