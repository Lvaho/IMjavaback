<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input  v-model="listQuery.uid" clearable class="filter-item" style="width: 400px;" placeholder="请输入用户Id" />
      <el-button v-permission="['GET /admin/imPromoter/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
    </div>
    <div><h1 align="center">以下是所查用户的推广者</h1> </div>
    <!-- 查询结果 -->
    <el-table  v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" width="100px" label="序号" prop="id" sortable />
      <el-table-column align="center" label="用户id" prop="uid" />
      <el-table-column align="center" label="在线状态" prop="name" />
      <el-table-column align="center" label="昵称" prop="displayName" />
      <el-table-column align="center" label="用户头像" prop="portrait">
        <template slot-scope="scope">
          <img v-if="scope.row.portrait" :src="scope.row.portrait" width="60">
        </template>
      </el-table-column>
      <el-table-column align="center" label="手机号码" prop="mobile" />
      <el-table-column align="center" label="用户类型" prop="type">
        <template slot-scope="scope">
          <el-tag>{{ typeDic[scope.row.type] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" prop="createtime" />
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>
  </div>
</template>

<script>
import { fetchList } from '@/api/imPromoter'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import BackToTop from '@/components/BackToTop'
export default {
  name: 'Promoter',
  components: { BackToTop, Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: false,
      listQuery: {
        page: 1,
        limit: 20,
        uid: undefined,
        order: 'desc'
      },
      downloadLoading: false,
      genderDic: ['未知', '男', '女'],
      typeDic: ['员工', '机器人', '系统管理员', '客户']
    }
  },
  methods: {
    getList() {
      fetchList(this.listQuery).then(response => {
        this.list = response.data.data.list
        this.total = response.data.data.total
        this.listLoading = false
      }).catch(response => {
        this.$notify.error({
          title: '失败',
          message: response.data.errmsg
        })
      })
    },
    handleFilter(){
      this.listQuery.page = 1
      this.getList()
    }
  }
}
</script>
