<!--<template>-->
<!--  <div>-->
<!--    <div style=" float: right">-->
<!--      <el-input v-model="name" size="small" placeholder="请输入名字"-->
<!--                style="width: 200px; margin-bottom: 10px;margin-right: 10px"-->
<!--                suffix-icon="el-icon-search" @keyup.enter.native="loadPost"></el-input>-->
<!--      <el-select v-model="sex" placeholder="请选择" style="width:90px"-->
<!--                 size="small">-->
<!--        <el-option-->
<!--            v-for="item in sexs"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value">-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--      <el-button size="mini" type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>-->
<!--      <el-button size="mini" type="success" @click="reset">重置</el-button>-->
<!--      <el-button size="mini" type="primary" style="margin-left: 50px;" @click="addbt">新增</el-button>-->

<!--    </div>-->
<!--    <el-table :data="tableData" style="" :header-cell-style="{ background: '#f3f6fd'}">-->
<!--      <el-table-column prop="id" label="ID" width="60px">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="no" label="账号" width="180">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="name" label="姓名" width="100">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="age" label="年龄" width="80">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="sex" label="性别" width="80">-->
<!--        <template slot-scope="scope">-->
<!--          <el-tag-->
<!--              :type=" scope.row.sex === 0 ? 'primary' : (scope.row.sex === 1 ? 'success' : 'info')"-->
<!--              disable-transitions>{{ scope.row.sex === 0 ? '女' : (scope.row.sex === 1 ? '男' : '未知') }}-->
<!--          </el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="roleId" label="角色" width="120">-->
<!--        <template slot-scope="scope">-->
<!--          <el-tag-->
<!--              :type=" scope.row.roleId === 0 ? 'primary' : (scope.row.roleId === 1 ? 'success' : 'info')"-->
<!--              disable-transitions>{{ scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '管理员' : '用户') }}-->
<!--          </el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="phone" label="电话" width="150">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="" label="操作">-->
<!--        &lt;!&ndash;              外圈包一层插槽，用scope.row传值&ndash;&gt;-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--              size="mini"-->
<!--              @click="updtb(scope.row)">编辑-->
<!--          </el-button>-->
<!--          <el-popconfirm-->
<!--              title="这是一段内容确定删除吗？"-->
<!--              @confirm="dodel(scope.row.id)"-->
<!--          >-->
<!--            <el-button slot="reference"-->
<!--                       size="mini"-->
<!--                       type="danger"-->
<!--                       style="margin-left: 5px">删除-->
<!--            </el-button>-->
<!--          </el-popconfirm>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->
<!--    <el-pagination-->
<!--        @size-change="handleSizeChange"-->
<!--        @current-change="handleCurrentChange"-->
<!--        :current-page="pageNum"-->
<!--        :page-sizes="[2, 5, 10, 20]"-->
<!--        :page-size="pageSize"-->
<!--        layout="total, sizes, prev, pager, next, jumper"-->
<!--        :total="total">-->
<!--    </el-pagination>-->
<!--    <el-dialog-->
<!--        title="提示"-->
<!--        :visible.sync="dialogVisible"-->
<!--        width="30%"-->
<!--    >-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="70px" style="padding-left: 50px">-->
<!--        <el-form-item label="账号" prop="no">-->
<!--          <el-col :span="14">-->
<!--            <el-input v-model="form.no"></el-input>-->
<!--          </el-col>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="密码" prop="password">-->
<!--          <el-col :span="14">-->
<!--            <el-input v-model="form.password"></el-input>-->
<!--          </el-col>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="名字" prop="name">-->
<!--          <el-col :span="14">-->
<!--            <el-input v-model="form.name"></el-input>-->
<!--          </el-col>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="年龄" prop="age">-->
<!--          <el-col :span="10">-->
<!--            <el-input v-model="form.age"></el-input>-->
<!--          </el-col>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="性别" prop="sex">-->
<!--          <el-radio-group v-model="form.sex">-->
<!--            <el-radio label="1">男</el-radio>-->
<!--            <el-radio label="0">女</el-radio>-->
<!--            <el-radio label="2">未知</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="电话" prop="phone">-->
<!--          <el-col :span="14">-->
<!--            <el-input v-model="form.phone"></el-input>-->
<!--          </el-col>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--    <el-button @click="dialogVisible = false">取 消</el-button>-->
<!--    <el-button type="primary" @click="addorupt">确 定</el-button>-->
<!--       </span>-->
<!--    </el-dialog>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "MnIndex",-->
<!--  data() {-->
<!--    let checkDuplicate = (rule, value, callback) => {-->
<!--      if (this.form.id) {-->
<!--        return callback();-->
<!--      }-->
<!--      this.$axios.get(this.$https + "user/findByno?no=" + this.form.no).then(res => res.data).then(res => {-->
<!--        if (res.code != 200) {-->
<!--          callback()-->
<!--        } else {-->
<!--          callback(new Error('账号已经存在'));-->
<!--        }-->
<!--      })-->
<!--    };-->

<!--    let checkAge = (rule, value, callback) => {-->
<!--      if (value > 150) {-->
<!--        callback(new Error('年龄输⼊过⼤'));-->
<!--      } else {-->
<!--        callback();-->
<!--      }-->
<!--    };-->
<!--    return {-->
<!--      rules: {-->
<!--        no: [-->
<!--          {required: true, message: '账号', trigger: 'blur'},-->
<!--          {min: 3, max: 15, message: '长度在 3 到 15个字符', trigger: 'blur'},-->
<!--          {validator: checkDuplicate, trigger: 'blur'}-->
<!--        ],-->
<!--        password: [-->
<!--          {required: true, message: '密码', trigger: 'blur'},-->
<!--          {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}-->
<!--        ],-->
<!--        name: [-->
<!--          {required: true, message: '姓名', trigger: 'blur'},-->
<!--          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}-->
<!--        ],-->
<!--        age: [-->
<!--          {required: true, message: '请输⼊年龄', trigger: 'blur'},-->
<!--          {min: 1, max: 3, message: '⻓度在 1 到 3 个位', trigger: 'blur'},-->
<!--          {pattern: /^([1-9][0-9]*){1,3}$/, message: '年龄必须为正整数字', trigger: "blur"},-->
<!--          {validator: checkAge, trigger: 'blur'}-->
<!--        ],-->
<!--        sex: [-->
<!--          {required: true, message: '请选择性别', trigger: 'change'}-->
<!--        ],-->
<!--        phone: [-->
<!--          {required: true, message: "⼿机号不能为空", trigger: "blur"},-->
<!--          {-->
<!--            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输⼊正确的⼿机号码", trigger:-->
<!--                "blur"-->
<!--          }-->
<!--        ]-->
<!--      },-->
<!--      form: {-->
<!--        id: "",-->
<!--        no: "",-->
<!--        password: "",-->
<!--        name: "",-->
<!--        age: "",-->
<!--        sex: "",-->
<!--        phone: "",-->
<!--        roleId: "1"-->
<!--      },-->
<!--      dialogVisible: false,-->
<!--      sexs: [{-->
<!--        value: 1,-->
<!--        label: '男'-->
<!--      }, {-->
<!--        value: 0,-->
<!--        label: '女'-->
<!--      }, {-->
<!--        value: 2,-->
<!--        label: '  '-->
<!--      }],-->
<!--      sex: 2,-->
<!--      pageSize: 2,-->
<!--      pageNum: 1,-->
<!--      total: 0,-->
<!--      name: this.name,-->
<!--      tableData: [],-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    dodel(id) {-->
<!--      this.$axios.get(this.$https + 'user/delete?id=' + id).then(res => res.data).then(res => {-->
<!--        if (res.code === 200) {-->
<!--          this.$message({-->
<!--            showClose: true,-->
<!--            message: '操作成功',-->
<!--            type: 'success'-->
<!--          });-->
<!--          this.loadPost()-->
<!--          this.dialogVisible = false-->
<!--        } else {-->
<!--          this.$message({-->
<!--            showClose: true,-->
<!--            message: '操作失败',-->
<!--            type: 'warning'-->
<!--          });-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    updtb(row) {-->
<!--      this.form = {-->
<!--        id: row.id,-->
<!--        no: row.no,-->
<!--        name: row.name,-->
<!--        password: row.password,-->
<!--        age: row.age + "",-->
<!--        sex: row.sex + "",-->
<!--        phone: row.phone + "",-->
<!--        roleId: row.roleId-->
<!--      }-->
<!--      this.dialogVisible = true;-->
<!--      console.log(row)-->
<!--    },-->
<!--    //搜索重置-->
<!--    reset() {-->
<!--      this.sex = 2,-->
<!--          this.name = ' '-->
<!--    },-->
<!--    //分页-->
<!--    handleSizeChange(val) {-->
<!--      console.log(`每页 ${val} 条`);-->
<!--      this.pageSize = val,-->
<!--          this.pageNum = 1,-->
<!--          this.loadPost()-->
<!--    },-->
<!--    handleCurrentChange(val) {-->
<!--      console.log(`当前页: ${val}`);-->
<!--      this.pageNum = val,-->
<!--          this.loadPost()-->
<!--    },-->
<!--    loadGet() {-->
<!--      this.$axios.get(this.$https + 'user/getuser').then(res => res.data).then(res => {-->
<!--        console.log(res)-->
<!--      })-->
<!--    },-->
<!--    //模糊查询-->
<!--    loadPost() {-->
<!--      this.$axios.post(this.$https + 'user/listpage', {-->
<!--        sex: this.sex,-->
<!--        name: this.name,-->
<!--        pageSize: this.pageSize,-->
<!--        pageNum: this.pageNum,-->
<!--      }).then(res => res.data).then(res => {-->
<!--        console.log(res)-->
<!--        if (res.code === 200) {-->
<!--          this.tableData = res.data,-->
<!--              this.total = res.total-->
<!--        } else {-->
<!--          alert('获取失败')-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    //增加按钮-->
<!--    addbt() {-->
<!--      this.dialogVisible = true;-->
<!--      this.form = {};-->
<!--    },-->
<!--    dosave() {-->
<!--      this.$axios.post(this.$https + 'user/save', this.form).then(res => res.data).then(res => {-->
<!--        if (res.code === 200) {-->
<!--          this.$message({-->
<!--            showClose: true,-->
<!--            message: '操作成功',-->
<!--            type: 'success'-->
<!--          });-->
<!--          this.loadPost()-->
<!--          this.dialogVisible = false-->
<!--        } else {-->
<!--          this.$message({-->
<!--            showClose: true,-->
<!--            message: '操作失败',-->
<!--            type: 'warning'-->
<!--          });-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    doupt() {-->
<!--      this.$axios.post(this.$https + 'user/update', this.form).then(res => res.data).then(res => {-->
<!--        console.log(res)-->
<!--        if (res.code === 200) {-->
<!--          this.$message({-->
<!--            showClose: true,-->
<!--            message: '操作成功',-->
<!--            type: 'success'-->
<!--          });-->
<!--          this.loadPost()-->
<!--          this.dialogVisible = false-->
<!--        } else {-->
<!--          this.$message({-->
<!--            showClose: true,-->
<!--            message: '操作失败',-->
<!--            type: 'warning'-->
<!--          });-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    //增加操作,判断id是否存在，若存在，则是更改操作-->
<!--    addorupt() {-->
<!--      this.$refs.form.validate((valid) => {-->
<!--        if (valid) {-->
<!--          if (this.form.id) {-->
<!--            this.doupt();-->
<!--          } else {-->
<!--            this.dosave();-->
<!--          }-->

<!--        } else {-->
<!--          console.log('error submit!!');-->
<!--          return false;-->
<!--        }-->
<!--      });-->

<!--    }-->
<!--  },-->
<!--  beforeMount() {-->
<!--    // this.loadGet();-->
<!--    this.loadPost();-->
<!--  }-->


<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->