<template>
  <el-container style="height: 100vh; border: 1px solid #eee">
    <el-aside :width="aWidth" style="background-color: rgb(238, 241, 246) ;height: 100vh">
      <MnAside :isCollapse="isCollapse"></MnAside>
    </el-aside>

    <el-container style="height:100vh ">
      <el-header style="text-align: right; font-size: 12px;border-bottom: 1px solid darkblue">
        <MnHeader @doCollapse="Collapse" :icon="icon"></MnHeader>
      </el-header>
      <el-main style="height: 100vh">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import MnAside from "@/components/MnAside";
import MnHeader from "@/components/MnHeader";

export default {
  name: "MyIndex",
  data() {
    return {
      isCollapse: false,
      aWidth: '200px',
      icon: 'el-icon-s-fold',
    }
  },
  props: {},
  methods: {
    //控制侧边栏是否缩放
    Collapse() {
      this.isCollapse = !this.isCollapse
      if (!this.isCollapse) {
        this.aWidth = '200px'
        this.icon = 'el-icon-s-fold'
      } else {
        this.aWidth = '64px'
        this.icon = 'el-icon-s-unfold'
      }
    }
  },
  components: { MnAside,MnHeader},

};
</script>
<style scope>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}


</style>