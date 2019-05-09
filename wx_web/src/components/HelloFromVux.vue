<template>
  <div>
    <div class="vux-demo">
      <img class="logo" src="../assets/vux_logo.png">
      <h1></h1>
    </div>
    <group title="cell demo">
      <cell title="VUX" value="cool" is-link></cell>
    </group>
    <!-- <router-link :to="{name:'MyCoupon'}"> 我的优惠券 </router-link>
    <router-link :to="{name:'ShopCoupon'}"> 购买优惠券 </router-link>-->

    <footer>
      <div class="youhui" v-show="show_hidden">
        <router-link :to="{name:'MyCoupon'}">
          <p class="text-list">我的优惠券</p>
        </router-link>
        <router-link :to="{name:'ShopCoupon'}">
          <p>在线购券</p>
        </router-link>
      </div>
      <div class="huiyuan" v-show="show_hidden1">
        <router-link :to="{name:'MyMembership'}">
          <p class="text-list">我的会员卡</p>
        </router-link>
        <p>在线购卡</p>
      </div>
      <span class="shopBox" @click="ocShow">微信商城</span>
      <span class="menberBox" @click="ocShow1">会员中心</span>
      <span class="eventBox">
        <div class="menu-icon"></div>
      </span>
    </footer>
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
      msg: "Hello World!",
      show_hidden: false,
      show_hidden1: false
    };
  },
  methods: {
    ocShow() {
      this.show_hidden = !this.show_hidden;
    },
    ocShow1() {
      this.show_hidden1 = !this.show_hidden1;
    }
  }
};
</script>

<style >
.vux-demo {
  text-align: center;
}
.logo {
  width: 100px;
  height: 100px;
}
footer {
  width: 100%;
  height: 115px;
  position: fixed;
  bottom: 0px;
}
.youhui,
.huiyuan {
  position: fixed;
  width: 106px;
  height: 60px;
  text-align: center;
  border: 2px solid #f2f2f2;
  border-radius: 2px;
  background-color: white;
  font-size: 13px;
}
a {
  color: black;
}
.youhui {
  left: 28px;
}
.huiyuan {
  right: 52px;
}
.text-list {
  margin-top: 5px;
  margin-bottom: 10px;
}
.shopBox,
.menberBox {
  display: inline-block;
  position: absolute;
  bottom: 0px;
  width: 43%;
  height: 45px;
  float: left;
  text-align: center;
  line-height: 50px;
  border: 1px solid gainsboro;
}
.menberBox {
  border-left: 0;
  left: 40%;
}
.eventBox {
  display: inline-block;
  position: absolute;
  left: 83%;
  bottom: 0px;
  width: 14%;
  height: 45px;
  text-align: center;
  line-height: 50px;
  border: 1px solid gainsboro;
  font-size: 14px;
}
.menu-icon {
  width: 18px;
  height: 2px;
  border-top: 2px solid;
  border-top-color: currentcolor;
  border-top-style: solid;
  border-top-width: 2px;
  border-bottom: 2px solid black;
  background-color:black;
  padding: 5px 0;
  background-clip: content-box;
  margin: 17px auto;
}

.shopBox:hover {
  background-color: #f2f2f2;
}
.menberBox:hover {
  background-color: #f2f2f2;
}

</style>
