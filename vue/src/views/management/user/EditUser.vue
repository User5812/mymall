<template>
  <div style="width: 80%;">
    <div style="margin-bottom: 20px;">编辑用户</div>
    <el-form :inline="true" :model="form" label-width="100px">
      <el-form-item label="用户名">
        <el-input v-model="form.username" disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickName" placeholder="请输入昵称"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" plaveholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="余额">
        <el-input v-model="form.account" placeholder="请输入年龄"></el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center; margin-top: 20px;">
      <el-button type="primary" @click="update" size="medium">更新</el-button>
      <!-- <el-button type="danger"></el-button> -->
    </div>

    <div></div>
  </div>
</template>

<script>
import request from '@/utils/request';

export default {
  name: 'EditCategory',

  data() {
    return {
      form: {

      }
    };
  },
  created() {
    const id = this.$route.query.id
    request.get("/user/" + id).then(res => {
      this.form = res.data
    })
  },
  mounted() {

  },

  methods: {
    update(){
      request.put('/user/update', this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
          this.$router.push("/userList")
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  },
};
</script>

<style lang="scss" scoped>

</style>