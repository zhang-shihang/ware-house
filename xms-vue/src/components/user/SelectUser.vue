<template>
  <div>
    <div style=" float: right">
      <el-input v-model="name" size="small" placeholder="请输入名字"
                style="width: 200px; margin-bottom: 10px;margin-right: 10px"
                suffix-icon="el-icon-search" @keyup.enter.native="loadPost"></el-input>
      <el-button size="mini" type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
      <el-button size="mini" type="success" @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData" :header-cell-style="{ background: '#f3f6fd'}"
              @current-change="selectCurrentChange"
              border>
      <el-table-column prop="id" label="ID" width="180px">
      </el-table-column>
      <el-table-column prop="no" label="账号" width="200">
      </el-table-column>
      <el-table-column prop="name" label="姓名">
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 15, 30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "SelectUser",
  data() {
    let checkDuplicate = (rule, value, callback) => {
      if (this.form.id) {
        return callback();
      }
      this.$axios.get(this.$https + "user/findByno?no=" + this.form.no).then(res => res.data).then(res => {
        if (res.code != 200) {
          callback()
        } else {
          callback(new Error('账号已经存在'));
        }
      })
    };
    return {

      form: {
        id: "",
        no: "",
        password: "",
        name: "",
        age: "",
        sex: "",
        phone: "",
        roleId: "2"
      },
      dialogVisible: false,
      sexs: [{
        value: 1,
        label: '男'
      }, {
        value: 0,
        label: '女'
      }, {
        value: 2,
        label: '  '
      }],
      sex: "",
      pageSize: 5,
      pageNum: 1,
      total: 0,
      name: this.name,
      tableData: [],
      roleId: "2",
    }
  },
  methods: {
    selectCurrentChange(val) {
      this.$emit("doSelectUser",val)
    },
    //搜索重置
    reset() {
          this.name = ' '
    },
    //分页
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val,
          this.pageNum = 1,
          this.loadPost()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val,
          this.loadPost()
    },
    loadGet() {
      this.$axios.get(this.$https + 'user/getuser').then(res => res.data).then(res => {
        console.log(res)
      })
    },
    //模糊查询
    loadPost() {
      this.$axios.post(this.$https + 'user/listpage', {
        sex: this.sex,
        name: this.name,
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        roleId: this.roleId
      }).then(res => res.data).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.$nextTick(()=>{
            this.tableData
          })
          this.tableData = res.data,
              this.total = res.total
        } else {
          alert('获取失败')
        }
      })
    },
    beforeMount(){

      this.loadPost();
    }
  }
}

</script>

<style scoped>

</style>