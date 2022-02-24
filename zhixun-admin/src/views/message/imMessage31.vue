<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.id" clearable class="filter-item" style="width: 150px;" placeholder="请输入序号" />
      <el-input v-model="listQuery.from" clearable class="filter-item" style="width: 180px;" placeholder="请输入消息发送者" />
      <el-input v-model="listQuery.target" clearable class="filter-item" style="width: 180px;" placeholder="请输入消息接收者" />
      <el-input v-model="listQuery.searchableKey" clearable class="filter-item" style="width: 180px;" placeholder="请输入消息内容" />
      <el-date-picker
        v-model="listQuery.tStart"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="选择起始时间"
        align="right"
        clearable
        class="filter-item"
      />
      <el-date-picker
        v-model="listQuery.tEnd"
        type="datetime"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="选择截至时间"
        align="right"
        clearable
        class="filter-item"
      />
      <el-button v-permission="['GET /admin/message/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <!--      <el-button v-permission="['POST /admin/message/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>-->
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="序号" prop="id" />
      <el-table-column align="center" label="消息id" prop="mid" />
      <el-table-column align="center" label="发送者" prop="from" />
      <el-table-column align="center" label="接收者" prop="target" />
      <el-table-column align="center" min-width="380px" label="消息内容" prop="searchableKey" />
      <el-table-column align="center" label="发送时间" prop="dt" value-format="yyyy-MM-dd HH:mm:ss" />
      <el-table-column align="center" label="消息类型" prop="contentType" width="130px">
        <template slot-scope="scope">
          <el-tag>{{ contentTypeDic[scope.row.contentType] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /admin/message/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗？" @onConfirm="handleDelete(scope.row)">
            <el-button slot="reference" v-permission="['POST /admin/message/delete']" type="danger" size="mini">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="消息id" prop="mid">
          <el-input v-model="dataForm.mid" />
        </el-form-item>
        <el-form-item label="发送者" prop="from">
          <el-input v-model="dataForm.from" />
        </el-form-item>
        <el-form-item label="接收者" prop="target">
          <el-input v-model="dataForm.target" />
        </el-form-item>
        <el-form-item label="消息内容" prop="searchableKey">
          <el-input v-model="dataForm.searchableKey" />
        </el-form-item>
        <el-form-item label="发送时间" prop="dt">
          <el-date-picker
            v-model="dataForm.dt"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间"
            align="right"
          />
        </el-form-item>
        <el-form-item label="消息类型" prop="contentType">
          <el-select v-model="dataForm.contentType" placeholder="请选择"><el-option v-for="(item, index) in contentTypeDic" :key="index" :label="item" :value="index" /></el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-permission="['GET /admin/message/update']" v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>
  </div>
</template>

<script>
import { listMessage, createMessage, updateMessage, deleteMessage } from '@/api/message'
import { uploadPath } from '@/api/storage'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import BackToTop from '@/components/BackToTop'

export default {
  name: 'Message',
  components: { BackToTop, Pagination },
  data() {
    return {
      uploadPath,
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        id: undefined,
        from: undefined,
        target: undefined,
        searchableKey: undefined,
        tStart: undefined,
        tEnd: undefined,
        order: 'desc'
      },
      dataForm: {
        id: undefined,
        mid: undefined,
        from: undefined,
        target: undefined,
        dt: '',
        contentType: undefined,
        searchableKey: ''

      },
      contentTypeDic: {'0':'未知消息', '1':'文本消息', '2':'语音消息','3':'图片消息','4':'位置消息',
        '5':'文件消息','6':'视频消息','7':'动态表情消息','8':'图文消息混排','80':'撤回消息',
        '90':'提醒消息', '91':'正在输入提醒消息','92':'未知消息','93':'未知消息','104':'创建群组消息',
        '105': '添加群组成员', '106':'移出群组成员','107':'退出群组','108':'解散群组',
        '109':'转让群组','110':'修改群组名称', '111':'修改群昵称','112':'修改群头像',
        '400':'网络电话发起','401':'网络电话接听', '402':'网络电话结束','403':'网络电话信令',
        '404':'网络电话变更'
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      downloadLoading: false
    }
  },
  computed: {
    headers() {
      return {
        'X-Litemall-Admin-Token': getToken()
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      listMessage(this.listQuery)
        .then(response => {
          this.list = response.data.data.list
          this.total = response.data.data.total
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetForm() {
      this.dataForm = {
        id: undefined,
        mid: undefined,
        from: undefined,
        target: undefined,
        searchableKey: undefined,
        dt: undefined,
        contentType: undefined
      }
    },
    handleCreate() {
      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          createMessage(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '创建成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    handleUpdate(row) {
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          updateMessage(this.dataForm)
            .then(() => {
              for (const v of this.list) {
                if (v.id === this.dataForm.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.dataForm)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '更新成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    handleDelete(row) {
      deleteMessage(row)
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
          const tHeader = [
            'ID',
            '消息id',
            '发送者',
            '接收者',
            '数据',
            '消息内容',
            '发送时间',
            '消息类型'
          ]
          const filterVal = ['id', 'mid', 'from', 'target', 'data', 'searchableKey', 'dt', 'contentType']
          excel.export_json_to_excel2(
            tHeader,
            this.list,
            filterVal,
            '消息信息'
          )
          this.downloadLoading = false
        })
    }
  }
}
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }
  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }
</style>
