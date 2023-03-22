<template>
    <div>
        <!--    搜索表单-->
    <div style="margin-bottom: 20px;">
      <el-input style="width: 240px;" placeholder="请输入名称" v-model="params.name"></el-input>
      <el-input style="width: 240px; margin-left: 5px;" placeholder="请输入联系方式" v-model="params.phone"></el-input>
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
        <el-table-column prop="username" label="用户名" width="80px"></el-table-column>
        <el-table-column prop="nickName" label="昵称" width="80px"></el-table-column>
        <el-table-column prop="email" label="邮箱" width="150px"></el-table-column>
        <el-table-column prop="phone" label="电话" width="100px"></el-table-column>
        <el-table-column prop="address" label="地址" width="60px"></el-table-column>
        <el-table-column prop="age" label="年龄" width="60px"></el-table-column>
        <el-table-column prop="account" label="余额" width="100px"></el-table-column>


      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/edituser?id=' + scope.row.id)">编辑</el-button>

          <el-popconfirm
              title="这是一段内容确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button style="margin-left: 5px" type="danger" slot="reference">删除</el-button>
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



export default {
  name: 'User',
  data() {
    return {
      tableData: [],
      total:0,
      params: {
        pageNum: 1,
        pageSize: 5,
        name: '',
        phone: '',
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    del(id){
      request.delete("/user/delete/" + id).then(res => {
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
      request.get('/user/page',{params: this.params}).then(res => {
        if(res.code === '200'){
          this.tableData = res.data.list
          this.total = res.data.total
        }
        
      })
      
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 5,
        name: '',
        phone: '',
      }
      this.load()
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