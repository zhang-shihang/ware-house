<template>
  <div style="display: flex;line-height: 60px">
    <div>
      <!--在element.style中添加cursor：pointer可以让悬停鼠标样式改变-->
      <i :class="icon" style="font-size: 30px;line-height: 60px" @click="isShow"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 18px">
      <span>欢迎来到仓库管理系统</span>
    </div>
    <el-dropdown>
      <span style="font-size: 15px;" class="userName">{{user.name}}</span>
      <i class="el-icon-setting" style="margin-right: 14px;margin-left: 10px;font-size: 18px;"></i>
      <el-dropdown-menu slot="dropdown">
        <!--当事件添加到Vue组件上时，会变成自定义事件，需要加native变成事件监听-->
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="toOut">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "MnHeader",
  data(){
    return{
      user:JSON.parse(sessionStorage.getItem('CurUser')) ,
    }
  },
  methods: {
    toUser() {
      this.$router.push('/Home')
    },
    toOut() {
      this.$confirm('您确定要退出登录吗?', '提示', {
        confirmButtonText: '确定',  //确认按钮的文字显示
        type: 'warning',
        center: true, //文字居中显示

      })
          .then(() => {
            this.$message({
              type:'success',
              message:'退出登录成功'
            })

            this.$router.replace('/')
            sessionStorage.clear()
          })
          .catch(() => {
            this.$message({
              type:'info',
              message:'已取消退出登录'
            })
          })


    },
    isShow() {
      this.$emit('doCollapse')
    }
  },
  props: {
    icon: String
  },
  created() {
    this.$router.push('/Home')
  }
}
</script>

<style scoped>
</style>