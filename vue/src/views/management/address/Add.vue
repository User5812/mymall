<template>
    <div style="width: 80%;">
        <div style="margin-bottom: 20px;">新增收获地址</div>
        <el-form :inline="true" :model="tableData" :rules="rules" ref="tableData" label-width="100px">
            <el-form-item label="联系人" prop="linkUser">
                <el-input v-model="tableData.linkUser" placeholder="请输入联系用户"></el-input>
            </el-form-item>
            <el-form-item label="联系人地址" prop="linkAddress">
                <el-input v-model="tableData.linkAddress" placeholder="请输入联系地址"></el-input>
            </el-form-item>
          <el-form-item label="联系人电话" prop="linkPhone">
            <el-input v-model="tableData.linkPhone" placeholder="请输入联系电话"></el-input>
          </el-form-item>
          <el-form-item label="关联用户" prop="userId">
            <el-select v-model="tableData.userId" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in options" :key="item.userId" :label="item.nickName" :value="item.userId"></el-option>
            </el-select>
          </el-form-item>
        </el-form>

        <div style="text-align: center; margin-top: 20px;">
            <el-button type="primary" @click="save('tableData')" size="medium">提交</el-button>
            <!-- <el-button type="danger"></el-button> -->
        </div>

        <div></div>
    </div>
</template>

<script>
import request from '@/utils/request';

export default {
    name: 'AddAddress',

    data() {
        return {
          tableData: {
            linkUser: '',
            linkAddress: '',
            linkPhone: '',
            userId: ''
          },
          options: [],
          rules: {
            linkUser: [
              { required: true, message: '请输入联系人', trigger: 'blur' },
            ],
            linkAddress: [
              { required: true, message: '请输入联系人地址', trigger: 'blur' },
          ],
            linkPhone: [
              { required: true, message: '请输入联系人电话', trigger: 'blur' },
            ],
            userId: [
              { required: true, message: '选择地址关联的用户', trigger: 'blur' },
            ]
          }
        }
    },
  created() {
      request.get('/address/userOptions').then(res => {
        this.options = res.data
      })
  },
    methods: {
        save(formName){
          this.$refs[formName].validate((valid) => {
            if (valid) {
              request.post('/address/save', this.tableData).then(res => {
                if(res.code === '200'){
                  this.$notify.success('新增成功')
                  this.$refs[formName].resetFields();
                }else{
                  this.$notify.error('新增失败'+ res.msg)
                }
              })
            }

          })

        },
    },
};
</script>

<style lang="scss" scoped>

</style>