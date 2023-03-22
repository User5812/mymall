<template>
  <div style="width: 80%;">
    <div style="margin-bottom: 20px;">编辑类别</div>
    <el-form :inline="true" :model="tableData" label-width="100px">
      <el-form-item label="联系人" >
        <el-input v-model="tableData.linkUser" placeholder="请输入联系用户"></el-input>
      </el-form-item>
      <el-form-item label="联系人地址">
        <el-input v-model="tableData.linkAddress" placeholder="请输入联系地址"></el-input>
      </el-form-item>
      <el-form-item label="联系人电话">
        <el-input v-model="tableData.linkPhone" placeholder="请输入联系电话"></el-input>
      </el-form-item>
      <el-form-item label="关联用户" >
        <el-select v-model="tableData.userId" placeholder="请选择" style="width: 100%">
          <el-option v-for="item in options" :key="item.userId" :label="item.nickName" :value="item.userId"></el-option>
        </el-select>
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
  name: 'EditAddress',

  data() {
    return {
      tableData: {},
      options: []
    };
  },
  created() {
    const id = this.$route.query.id
    request.get("/address/" + id).then(res => {
      this.tableData = res.data
    })
    request.get('/address/userOptions').then(res => {
      this.options = res.data
    })
  },
  mounted() {

  },

  methods: {
    update(){
      request.put('/address/update', this.tableData).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
          this.$router.push("/AddressList")
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