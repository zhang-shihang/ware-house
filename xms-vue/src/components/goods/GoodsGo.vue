<template>
  <div>
    <div style="margin-bottom: 5px; float: right">
      <el-input v-model="name" size="small" placeholder="请输入物品名" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost"></el-input>
      <el-select v-model="storage" placeholder="请选择仓库" size="small" style="margin: 0px 5px 0px 5px">
        <el-option
            v-for="item in storageData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-select v-model="goodstype" placeholder="请选择分类" size="small">
        <el-option
            v-for="item in goodstypeData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 5px;" @click="loadPost" size="mini">查询</el-button>
      <el-button type="success" @click="resetParam" size="mini">重置</el-button>

      <el-button type="primary" style="margin-left: 5px;" @click="add" size="mini" v-if="user.roleId!=2">新增</el-button>
      <el-button type="primary" style="margin-left: 5px;" @click="instorage" size="mini" v-if="user.roleId!=2">入库</el-button>
      <el-button type="primary" style="margin-left: 5px;" @click="outstorage" size="mini" v-if="user.roleId!=2">出库</el-button>
    </div>
    <el-table :data="tableData"
              :header-cell-style="{ background: '#f2f5fc', color: '#555555' }"
              highlight-current-row
              @current-change="selectCurrentChange"
              ref="singleTable"
              border
    >
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="name" label="物品名" width="180">
      </el-table-column>
      <el-table-column prop="storage" label="仓库" width="180" :formatter="formatStorage">
      </el-table-column>
      <el-table-column prop="goodstype" label="分类" width="180" :formatter="formatgoodstype">
      </el-table-column>
      <el-table-column prop="count" label="数量" width="180">
      </el-table-column>
      <el-table-column prop="remark" label="备注">
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="mod(scope.row)" v-if="user.roleId!=2">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @confirm="del(scope.row.id)"
              style="margin-left: 5px;"
          >
            <el-button slot="reference" size="mini" type="danger" v-if="user.roleId!=2">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 20,30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>

      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="仓库名" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="仓库" prop="storage">
          <el-col :span="20">
            <el-select v-model="form.storage" placeholder="">
              <el-option
                  v-for="item in storageData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="分类" prop="goodstype">
          <el-col :span="20">
            <el-select v-model="form.goodstype" placeholder="">
              <el-option
                  v-for="item in goodstypeData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="form.count"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>

    <el-dialog
        title="提示"
        :visible.sync="inDialogVisible"
        width="30%"
        center>

      <el-dialog
          width="50%"
          title="用户选择"
          :visible.sync="innerVisible"
          append-to-body>
        <SelectUser @doSelectUser="doSelectUser" ></SelectUser>
      </el-dialog>
      <el-form ref="form1" :rules="rules1" :model="form1" label-width="80px">
        <el-form-item label="物品名">
          <el-col :span="20">
            <el-input v-model="form1.goodsname" readonly></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="申请人">
          <el-col :span="20">
            <el-input v-model="form1.username" readonly @click.native="selectuser"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="form1.count"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input type="textarea" v-model="form1.remark"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="inDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doSavein">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import SelectUser from "@/components/user/SelectUser";

export default {
  name: "GoodsGo",
  components: {SelectUser},
  data() {
    let checkCount = (rule, value, callback) => {
      if (value > 9999) {
        callback(new Error('数量输⼊过⼤'));
      } else {
        callback();
      }
    };
    return {
      innerVisible: false,
      user: JSON.parse(sessionStorage.getItem('CurUser')),
      storage: '',
      goodstype: '',
      goodstypeData: [],
      storageData: [],
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      name: '',
      centerDialogVisible: false,
      inDialogVisible: false,
      form: {
        id: '',
        name: '',
        storage: '',
        goodstype: '',
        count: '',
        remark: ''
      },
      form1: {
        goods: '',
        goodsname: '',
        count: '',
        userid: '',
        username: '',
        adminId: '',
        remark: ''
      },
      rules1: {},
      rules: {
        name: [
          {required: true, message: '请输入分类名', trigger: 'blur'}
        ],
        count: [
          {required: true, message: '请输⼊数量', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,4}$/, message: '数量必须为正整数字', trigger: "blur"},
          {validator: checkCount, trigger: 'blur'}
        ],
        goodstype: [
          {required: true, message: '请选择分类', trigger: 'blur'}
        ],
        storage: [
          {required: true, message: '请选择仓库', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    outstorage(){
      if(!this.currentRow.id){
        alert('请选择记录');
        return;
      }
      this.inDialogVisible = true
      this.$nextTick(()=>{
        this.resetInForm()
      })

      this.form1.goodsname = this.currentRow.name
      this.form1.goods = this.currentRow.id
      this.form1.adminId=this.user.id
      this.form1.action='2'

    },
    doSelectUser(val) {
      this.form1.username = val.name
      this.form1.userid = val.id
      this.innerVisible = false
    },
    setCurrent(row) {
      this.$refs.singleTable.setCurrentRow(row);
    },
    selectuser() {
      this.innerVisible = true
    },
    //入库选中栏
    selectCurrentChange(val) {
      this.currentRow = val;
    },
    //入库
    instorage() {
      if (!this.currentRow.id) {
        alert('请选择物品');
        return;
      }
      this.inDialogVisible = true
      this.$nextTick(() => {
        this.resetinForm()
        this.form1.username = ''
        this.form1.userid = ''
      })
      this.form1.goodsname = this.currentRow.name
      this.form1.goods = this.currentRow.id
      this.form1.adminId = this.user.id
    },
    //查找仓库id和分类id变成仓库的名字和分类名字
    formatStorage(row) {
      let temp = this.storageData.find(item => {
        return item.id == row.storage
      })
      return temp && temp.name
    },
    formatgoodstype(row) {
      let temp = this.goodstypeData.find(item => {
        return item.id == row.goodstype
      })
      return temp && temp.name
    },
    resetinForm() {
      this.$refs.form1.resetFields();
    },
    resetForm() {
      this.$refs.form.resetFields();
    },
    del(id) {
      console.log(id)

      this.$axios.get(this.$https + 'goods/delete?id=' + id).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.loadPost()
        } else {
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    mod(row) {
      this.centerDialogVisible = true
      this.$nextTick(() => {
        //赋值到表单
        this.form.id = row.id
        this.form.name = row.name
        this.form.storage = row.storage
        this.form.goodstype = row.goodstype
        this.form.count = row.count
        this.form.remark = row.remark
      })
    },
    add() {
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.resetForm()
      })
      //解决清除表单清理不了id的问题
      this.form.id = ""
    },
    doSave() {
      this.$axios.post(this.$https + 'goods/save', this.form).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.loadPost()
          this.resetForm()
        } else {
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    doSavein() {
      this.$axios.post(this.$https + 'record/save', this.form1).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.inDialogVisible = false
          this.loadPost()
          this.resetinForm()
        } else {
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    doMod() {
      this.$axios.post(this.$https + 'goods/update', this.form).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.loadPost()
          this.resetForm()
        } else {
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id) {
            this.doMod();
          } else {
            this.doSave();
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1
      this.pageSize = val
      this.loadPost()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val
      this.loadPost()
    },
    resetParam() {
      this.name = '',
          this.storage = "",
          this.goodstype = ''
    },
    loadPost() {
      this.$axios.post(this.$https + 'goods/listpage', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        name: this.name,
        storage: this.storage,
        goodstype: this.goodstype
      }).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.tableData = res.data
          this.total = res.total
          this.setCurrent(this.tableData[0])
        } else {
          alert('获取数据失败')
        }

      })
    },
    loadStorage() {
      this.$axios.get(this.$https + 'storage/list').then(res => res.data).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.storageData = res.data
        } else {
          alert('获取失败')
        }
      })
    },
    loadgoodstype() {
      this.$axios.get(this.$https + 'goodstype/list').then(res => res.data).then(res => {
        console.log(res)
        if (res.code === 200) {
          this.goodstypeData = res.data
        } else {
          alert('获取失败')
        }
      })
    }
  },
  beforeMount() {
    this.loadPost()
    this.loadStorage()
    this.loadgoodstype()
  }
}
</script>

<style scoped>

</style>