<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.displayName" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户昵称" />
      <el-input v-model="listQuery.userId" clearable class="filter-item" style="width: 200px;" placeholder="请输入序号" />
      <el-input v-model="listQuery.uid" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户Id" />
      <el-input v-model="listQuery.mobile" clearable class="filter-item" style="width: 200px;" placeholder="请输入手机号" />
      <el-button v-permission="['GET /admin/imUser/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" width="100px" label="序号" prop="id" sortable />
      <el-table-column align="center" label="用户id" prop="uid" />
      <el-table-column align="center" label="在线状态" prop="name" />
      <el-table-column align="center" label="昵称" prop="displayName" />
      <!--      <el-table-column align="center" label="性别" prop="gender">-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-tag >{{ genderDic[scope.row.gender] }}</el-tag>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column align="center" label="用户头像" prop="portrait">
        <template slot-scope="scope">
          <img v-if="scope.row.portrait" :src="scope.row.portrait" width="60">
        </template>
      </el-table-column>
      <el-table-column align="center" label="手机号码" prop="mobile" />
      <el-table-column align="center" label="用户类型" prop="type">
        <template slot-scope="scope">
          <el-tag>{{ statusDic[scope.row.type] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" prop="createtime" />
      <el-table-column align="center" label="操作" width="250" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleDetail(scope.row)">详情</el-button>
          <el-popconfirm title="确认删除吗？" @onConfirm="handleDelete(scope.row)">
            <el-button slot="reference" v-permission="['POST /admin/imUser/delete']" type="danger" size="mini" >删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 用户编辑对话框 -->
    <el-dialog :visible.sync="userDialogVisible" title="用户编辑">
      <el-form ref="userDetail" :model="userDetail" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="用户昵称" prop="dispalyName">
          <el-input v-model="userDetail.displayName" />
        </el-form-item>
        <el-form-item label="用户头像" prop="portrait">
          <el-input v-model="userDetail.portrait" />
        </el-form-item>
        <el-form-item label="用户手机" prop="mobile">
          <el-input v-model="userDetail.mobile" />
        </el-form-item>
        <!--        <el-form-item label="用户性别" prop="gender">-->
        <!--          <el-select v-model="userDetail.gender" placeholder="请选择"><el-option v-for="(item, index) in genderDic" :key="index" :label="item" :value="index" /></el-select>-->
        <!--        </el-form-item>-->
        <el-form-item label="用户类型" prop="type">
          <el-select v-model="userDetail.type" placeholder="请选择"><el-option v-for="(item, index) in statusDic" :key="index" :label="item" :value="index" /></el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userDialogVisible = false">取消</el-button>
        <el-button v-permission="['GET /admin/imUser/update']" type="primary" @click="handleUserUpdate">确定</el-button>
      </div>
    </el-dialog>

    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>
  </div>
</template>

<script>
import { fetchList, userDetail, updateUser, deleteUser } from '@/api/imUser'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import BackToTop from '@/components/BackToTop'

export default {
  name: 'User',
  components: { BackToTop, Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        displayName: undefined,
        mobile: undefined,
        userId: undefined,
        uid: undefined,
        order: 'desc'
      },
      downloadLoading: false,
      genderDic: ['未知', '男', '女'],
      statusDic: ['员工', '机器人', '系统管理员', '客户'],
      userDialogVisible: false,
      userDetail: {
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      if (this.listQuery.userId) {
        userDetail(this.listQuery.userId).then(response => {
          this.list = []
          if (response.data.data) {
            this.list.push(response.data.data)
            this.total = 1
            this.listLoading = false
          } else {
            this.list = []
            this.total = 0
            this.listLoading = false
          }
        }).catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
      } else {
        fetchList(this.listQuery).then(response => {
          this.list = response.data.data.list
          this.total = response.data.data.total
          this.listLoading = false
        }).catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
      }
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleDelete(row) {
      deleteUser(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除成功'
          })
          this.getList()
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['用户id', '在线状态', '昵称', '手机号', '用户类型', '创建时间']
        const filterVal = ['uid', 'name', 'displayName', 'mobile', 'type', 'createtime']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
        this.downloadLoading = false
      })
    },
    handleDetail(row) {
      this.userDetail = row
      this.userDialogVisible = true
    },
    handleUserUpdate() {
      updateUser(this.userDetail)
        .then((response) => {
          this.userDialogVisible = false
          this.$notify.success({
            title: '成功',
            message: '更新用户成功'
          })
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    }
  }
}
</script>
