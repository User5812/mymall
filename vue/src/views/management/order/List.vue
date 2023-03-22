<template>
    <div>
      <!--    搜索表单-->
      <div style="margin-bottom: 20px;">
        <el-input style="width: 240px;" placeholder="请输入订单号" v-model="params.orderNo"></el-input>
        <el-input style="width: 240px;" placeholder="请输入状态" v-model="params.state"></el-input>
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
      <el-table-column prop="orderNo" label="订单编号"></el-table-column>
      <el-table-column prop="totalPrice" label="总价"></el-table-column>
      <el-table-column prop="userId" label="下单人id"></el-table-column>
      <el-table-column prop="linkUser" label="联系人"></el-table-column>
      <el-table-column prop="linkPhone" label="联系电话"></el-table-column>
      <el-table-column prop="linkAddress" label="送货地址"></el-table-column>
      <el-table-column prop="state" label="状态" ></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" @click="submitOrder(scope.row)">发货</el-button>

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
  name: 'Order',
  data() {
    return {
      state: {
        id: 0,
        state: '',
      },
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
    submitOrder(row){
      console.log(row.state)
      if(row.state === '待发货'){
        this.state.id = row.id
        this.state.state = '已发货'
        request.put('/order/update', this.state).then(res => {
          if(res.code === '200'){
            this.$notify.success('发货成功')
            this.load()
          }else{
            this.$notify.error(res.data)
          }
        })
      }else{
        this.$notify.warning("无需发货")
      }
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 5,
      }
      this.load()
    },
    del(id){
      request.delete("/order/delete/" + id).then(res => {
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
      request.get('/order/page',{params: this.params}).then(res => {
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