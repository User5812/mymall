<template>
    <div style="width: 80%;">
        <div style="margin-bottom: 20px;">新增类别</div>
        <el-form :inline="true" :model="tableData" :rules="rules" ref="tableData" label-width="100px">
            <el-form-item label="类别" prop="categoryname">
                <el-input v-model="tableData.categoryname" placeholder="请输入序列号"></el-input>
            </el-form-item>
            <el-form-item label="序列号" prop="no">
                <el-input v-model="tableData.no" placeholder="请输入序列号"></el-input>
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
    name: 'AddCategory',

    data() {
        return {
          tableData: {},

          rules: {
            categoryname: [
              { required: true, message: '请输入类别名', trigger: 'blur' },
            ],
            no: [
              { required: true, message: '请输入类别名', trigger: 'blur' },
          ]}
        };
    },

    mounted() {

    },

    methods: {
        save(formName){
          this.$refs[formName].validate((valid) => {
            if (valid) {
              request.post('/category/save', this.tableData).then(res => {
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