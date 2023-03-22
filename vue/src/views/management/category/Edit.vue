<template>
  <div style="width: 80%;">
    <div style="margin-bottom: 20px;">编辑类别</div>
    <el-form :inline="true" :model="tableData" :rules="rules" ref="tableData" label-width="100px">
      <el-form-item label="编号" prop="id">
        <el-input v-model="tableData.id" placeholder="编号"></el-input>
      </el-form-item>
      <el-form-item label="类别">
        <el-input v-model="tableData.categoryname" placeholder="请输入类别"></el-input>
      </el-form-item>
      <el-form-item label="序列号" prop="no">
        <el-input v-model="tableData.no" placeholder="请输入序列号"></el-input>
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
      tableData: {}
    };
  },
  created() {
    const id = this.$route.query.id
    request.get("/category/" + id).then(res => {
      this.tableData = res.data
    })
  },
  mounted() {

  },

  methods: {
    update(){
      request.put('/category/update', this.tableData).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
          this.$router.push("/categoryList")
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