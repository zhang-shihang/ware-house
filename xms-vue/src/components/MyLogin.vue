<template>
  <div class="loginBody" style="" title="no:1231230/psd:123">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title" style="font-family: ttf">用户登录</h1>
        <el-form :model="loginForm" label-width="100px"
                 :rules="rules" ref="loginForm">
          <el-form-item label="账号" prop="no">
            <el-input style="width: 200px" type="text" v-model="loginForm.no"
                      autocomplete="off" size="small"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input  style="width: 200px" type="password" v-model="loginForm.password"
                      show-password autocomplete="off" size="small" @focus="changeImg"  @keyup.enter.native="confirm"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="confirm" :disabled="confirm_disabled">确 定</el-button>
          </el-form-item>
        </el-form>
          <img class="img" :src='imgUrl'
               style="width: 100px;height: 100px;float: right; position: relative;right: -25px;bottom: 59px;border-radius:18px">
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "MyLogin",
  data() {
    return {
      imgUrl:require("@/assets/login/open.png"),
      confirm_disabled: false,
      loginForm: {
        no: '',
        password: ''
      },
      rules: {
        no: [
          {required: true, message: '请输入账号', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输密码', trigger: 'blur'}
        ],
      }
    }
  },
  watch:{

  },
  methods: {
    changeImg(e){
      if (e.target.type=="password"){
    this.imgUrl=require("@/assets/login/open.png")
      }else {
        this.imgUrl=require("@/assets/login/after.png")
      }
    },
    confirm() {
      //防止多次提交
      this.confirm_disabled = true;
      this.$refs.loginForm.validate((valid) => {
        if (valid) { //valid成功为true，失败为false
          //去后台验证用户名密码
          this.$axios.post(this.$https + 'user/login',this.loginForm).then(res => res.data).then(res => {
            if (res.code == 200) {
              //存储
              sessionStorage.setItem("CurUser", JSON.stringify(res.data.user))
              this.$store.commit("setMenu",res.data.menu);
              //跳转到主页
              this.$router.replace('/MyIndex');
            } else {
              this.confirm_disabled = false;
              this.$message({
                type:'error',
                message:'账号或密码错误'
              })
              return false;
            }
          });
        } else {
          this.confirm_disabled = false;
          console.log('校验失败');
          return false;
        }
      });

    }
  },
  beforeMount() {
  }
}
</script>

<style scoped>
.loginBody {
  position: absolute;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-image: url("~@/assets/login/login.png");
}



.loginDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -200px;
  margin-left: -250px;
  width: 450px;
  height: 330px;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 5%;
}

.login-title {
  margin: 20px 0;
  text-align: center;
}

.login-content {
  width: 400px;
  height: 250px;
  position: absolute;
  top: 25px;
  left: 25px;
}
</style>