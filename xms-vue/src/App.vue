<template>
 <div id="app">
   <router-view></router-view>
 </div>
</template>

<script>



export default {
  name: 'App',
  components: {
  },
  data(){
    return{
      user:JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },
  watch:
      {
        '$store.state.menu':{
          handler(val,old){
            if (!old &&this.user&&this.user.no){
              this.$store.commit('setMenu',val)
            }
          },
          immediate:true
        }
      }
}
</script>

<style>
/*去掉elementui组件的侧边滚动条，但保留滚动效果*/
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;height: 0;
}


</style>
