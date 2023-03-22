<template>
    <div style="width: 80%;">
        <div style="margin-bottom: 20px;">新增管理员</div>
        <el-form :inline="true" :model="formAdmin" :rules="rules" ref="formAdmin" label-width="100px">
            <el-form-item label="名称" prop="username">
                <el-input v-model="formAdmin.username" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="formAdmin.email" placeholder="请输入电子邮箱"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
                <el-input v-model="formAdmin.phone" placeholder="请输入电话"></el-input>
            </el-form-item>
        </el-form>

        <div style="text-align: center; margin-top: 20px;">
            <el-button type="primary" @click="save('formAdmin')" size="medium">提交</el-button>
            <!-- <el-button type="danger"></el-button> -->
        </div>

        <div></div>
    </div>
</template>

<script>
import request from '@/utils/request';

export default {
    name: 'AddAdmin',

    data() {


      var checkPhone = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('电话号码不能为空'));
        }
        setTimeout(() => {
          if (!(/^1[3456789]\d{9}$/.test(value))) {
            callback(new Error('请输入合法的手机号'));
          } else {
              callback();
            }
        }, 1000);}
        return {
          formAdmin: {},

          rules: {
            username: [
              { required: true, message: '请输入姓名', trigger: 'blur' },
              { min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur' }
            ],
          phone: [
            {validator: checkPhone, trigger: 'blur'}
          ]}
        };
    },

    mounted() {

    },

    methods: {
        save(formName){
          this.$refs[formName].validate((valid) => {
            if (valid) {
              request.post('/admin/save', this.formAdmin).then(res => {
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