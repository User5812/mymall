<template>
  <div class="home">
    <el-descriptions title="用户信息" style="width: 800px">
      <el-descriptions-item label="编号">{{admin.id}}</el-descriptions-item>
      <el-descriptions-item label="用户名">{{admin.username}}</el-descriptions-item>
      <el-descriptions-item label="手机号">{{admin.phone}}</el-descriptions-item>
      <el-descriptions-item label="电子邮件">{{admin.email}}</el-descriptions-item>
      <el-descriptions-item label="备注">
        <el-tag size="small">学生</el-tag>
      </el-descriptions-item>

    </el-descriptions>
  </div>


</template>

<script>
import Cookies from "js-cookie";
import request from "@/utils/request";
export default {
  data() {
    return {
      admin: Cookies.get('admin')? JSON.parse(Cookies.get('admin')): {}
    }
  },
  created() {
    // console.log(JSON.parse(Cookies.get('admin')))
    /*request.get("/admin" + this.admin.id).then(res => {
      this.admin = res.data
    })*/
  }
}
</script>

<style>
.item {
  margin: 10px 0;
  color: blueviolet;
}
</style>
