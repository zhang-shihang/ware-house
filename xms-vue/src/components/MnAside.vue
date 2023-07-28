<template>
<!--  el-menu内加入router即可实现跳转 default-active="home"默认选中-->
  <el-menu style="height:100vh ;"
           background-color="#545c64"
           text-color="#fff"
           default-active="Home"
           active-text-color="#ffd04b"
           :collapse="isCollapse"
           :collapse-transition="false"
           router
  >
    <el-menu-item index="Home">
      <i class="el-icon-s-promotion"></i>
<!--      span标签中添加slot="title"可以悬停图标展示标题-->
      <span slot="title">首页</span>
    </el-menu-item>
    <el-menu-item :index="'/'+item.menuclick" v-for="(item,i) in menu" :key="i">
      <i :class="item.menuicon"></i>
      <span slot="title">{{item.menuname}}</span>
    </el-menu-item>


  </el-menu>
</template>

<script>
export default {
  name: "MnAside",
  computed:{
    "menu":{
      get(){
        return this.$store.state.menu
      }
    },
  },
  beforeMount() {
    console.log(this.$store)
    console.log(this.menu)
  },
  props:{
    isCollapse:Boolean,
  },
  data() {
    return {
      // menu:[
      //   {
      //     menuClick:'Admin',
      //     menuName:'管理员管理',
      //     menuIcon:'el-icon-s-custom'
      //   },
      //   {
      //     menuClick:'User',
      //     menuName:'用户管理',
      //     menuIcon:'el-icon-user-solid'
      //   },
      // ]
    };
  },



}
</script>

<style scoped>
.el-aside {
  color: #333;
}
</style>