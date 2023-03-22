<template>
  <div>
    <!-- 弹窗   -->
    <el-dialog title="信息" :visible.sync="dialogFormVisible" width="30%"
               :close-on-click-modal="false" :show-close="false">
      <el-form :model="entity">
        <el-form-item label="商品名称" label-width="150px">
          <el-input v-model="entity.name" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="商品描述" label-width="150px">
          <el-input type="textarea" v-model="entity.description" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="商品编号" label-width="150px">
          <el-input v-model="entity.no" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="原价" label-width="150px">
          <el-input v-model="entity.price" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="折扣" label-width="150px">
          <el-input v-model="entity.discount" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="库存" label-width="150px">
          <el-input v-model="entity.store" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="分类" label-width="150px">
          <el-select v-model="entity.categoryId" placeholder="请选择" style="width: 80%">
            <el-option v-for="item in options" :key="item.id" :label="item.categoryname" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否推荐" label-width="150px">
          <el-switch
              v-model="entity.recommend"
              active-color="#13ce66"
              inactive-color="#ccc">
          </el-switch>
        </el-form-item>
        <el-form-item label="是否启用" label-width="150px">
          <el-switch
              v-model="entity.status"
              active-color="#13ce66"
              inactive-color="#ccc">
          </el-switch>
        </el-form-item>
        <el-form-item label="图片" label-width="150px">
          <template slot-scope="scope">
            <el-image style="width: 100px; height: 100px" :src="entity.imgs" :preview-src-list="[entity.imgs]"></el-image>
          </template>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "EditGoods",
  data(){
    return{
      entity: [],
      options: [],
      dialogFormVisible: true,

    }
  },

  created() {
    const id = this.$route.query.id
    request.get('/goods/' + id).then(res => {
      this.entity = res.data
    })
    request.get('/goods/option').then(res => {
      this.options = res.data
    })
  },

  methods: {
    closeDialog() {
      this.dialogFormVisible = false
      this.$router.push('/goodsList')

    },
    save() {
      request.put('/goods/update',this.entity).then(res => {
        if(res.code === '200'){
          this.$notify.success("修改商品成功")
        }else{
          this.$notify.error(res.msg)
        }
      })
      this.entity = []
      this.dialogFormVisible = false
      this.$router.push('/goodsList')

    },
  }
}
</script>