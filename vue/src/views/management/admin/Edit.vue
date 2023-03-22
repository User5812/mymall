<template>
  <div style="width: 80%;">
    <div style="margin-bottom: 20px;">编辑管理员</div>
    <el-form :inline="true" :model="formAdmin" :rules="rules" ref="formAdmin" label-width="100px">
      <el-form-item label="名称" prop="username">
        <el-input v-model="formAdmin.username" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="formAdmin.email" placeholder="请输入电子邮箱"></el-input>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="formAdmin.phone" placeholder="请输入电话"></el-input>
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
  name: 'EditAdmin',

  data() {
    return {
      formAdmin: {}
    };
  },
  created() {
    const id = this.$route.query.id
    request.get("/admin/" + id).then(res => {
      this.formAdmin = res.data
    })
  },
  mounted() {

  },

  methods: {
    update(){
      request.put('/admin/update', this.formAdmin).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
          this.$router.push("/adminList")
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