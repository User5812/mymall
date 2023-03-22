<template>
    <div>
      <!--    搜索表单-->
      <div style="margin-bottom: 20px;">
        <el-input style="width: 240px;" placeholder="请输入联系人" v-model="params.linkUser"></el-input>
        <el-input style="width: 240px; margin-left: 5px;" placeholder="请输入联系电话" v-model="params.linkPhone"></el-input>
        <el-button style="margin-left: 5px;" type="primary" @click="load">
          <i class="el-icon-search"></i>
          <span>搜索</span>
        </el-button>
        <el-button type="warning" @click="reset">
          <i class="el-icon-refresh"></i>
          <span>重置</span>
        </el-button>
      </div>
    <!-- 表单 -->
    <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="编号" width="60px"></el-table-column>
      <el-table-column prop="linkUser" label="联系人"></el-table-column>
      <el-table-column prop="linkAddress" label="联系地址"></el-table-column>
      <el-table-column prop="linkPhone" label="联系电话"></el-table-column>
      <el-table-column prop="userId" label="关联Id"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editAddress?id=' + scope.row.id)">编辑</el-button>

          <el-popconfirm
              title="这是一段内容确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button style="margin-left: 5px;margin-right: 5px" type="danger" slot="reference">删除</el-button>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>

      <!-- 分页 -->
      <div class="block" style="margin-top: 20px;">
        <el-pagination
            background=""
            layout="prev, pager, next"
            @current-change="handleCurrentChange"
            :current-page="params.pageNum"
            :page-size="params.pageSize"
            :total="total">
        </el-pagination>
      </div>

    </div>
</template>

<script>
import request from '@/utils/request'
import Cookies from "js-cookie";



export default {
  name: 'Address',
  data() {
    return {
      formLabelWidth: '120px',
      admin :Cookies.get('admin')? JSON.parse(Cookies.get('admin')): {},
      tableData: [],
      total:0,
      dialogFormVisible: false,
      form: {},
      params: {
        pageNum: 1,
        pageSize: 5,
      },
      rules: {
        newPass: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在3-10个字符', trigger: 'blur'},
        ]
      }

    }
  },
  created() {
    this.load()
  },
  methods: {
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 5,
      }
      this.load()
    },
    del(id){
      request.delete("/address/delete/" + id).then(res => {
        if(res.code === '200'){
          this.$notify.success("删除成功")
          this.load();
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    load(){
      // fetch('http://localhost:9090/user/list').then(res => res.json()).then(res => {
      //   console.log(res);
      //   this.tableData = res.data;
      // })
      request.get('/address/page',{params: this.params}).then(res => {
        if(res.code === '200'){
          this.tableData = res.data.list
          this.total = res.data.total
        }

      })

    },
    handleCurrentChange(pageNum){
      //点击分页按钮触发
      //console.log(pageNum);
      this.params.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style lang="scss" scoped>

</style>