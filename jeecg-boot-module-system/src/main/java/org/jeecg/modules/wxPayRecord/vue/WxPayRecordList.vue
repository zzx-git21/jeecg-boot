<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="所属部门">
              <a-input placeholder="请输入所属部门" v-model="queryParam.sysOrgCode"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="校区">
              <a-input placeholder="请输入校区" v-model="queryParam.xiaoqu"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="登录名">
              <a-input placeholder="请输入登录名" v-model="queryParam.username"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="当前招生季">
              <a-input placeholder="请输入当前招生季" v-model="queryParam.currentEnrollment"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="存取类型">
              <a-input placeholder="请输入存取类型" v-model="queryParam.cunquType"></a-input>
            </a-form-item>
          </a-col>
          </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('缴费记录')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <wxPayRecord-modal ref="modalForm" @ok="modalFormOk"></wxPayRecord-modal>
  </a-card>
</template>

<script>
  import WxPayRecordModal from './modules/WxPayRecordModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "WxPayRecordList",
    mixins:[JeecgListMixin],
    components: {
      WxPayRecordModal
    },
    data () {
      return {
        description: '缴费记录管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '所属部门',
            align:"center",
            dataIndex: 'sysOrgCode'
           },
		   {
            title: '校区',
            align:"center",
            dataIndex: 'xiaoqu'
           },
		   {
            title: '登录名',
            align:"center",
            dataIndex: 'username'
           },
		   {
            title: '当前招生季',
            align:"center",
            dataIndex: 'currentEnrollment'
           },
		   {
            title: '存取类型',
            align:"center",
            dataIndex: 'cunquType'
           },
		   {
            title: '课程系列',
            align:"center",
            dataIndex: 'courseSeries'
           },
		   {
            title: '课程名称',
            align:"center",
            dataIndex: 'courseName'
           },
		   {
            title: '班级',
            align:"center",
            dataIndex: 'banji'
           },
		   {
            title: '课程费用',
            align:"center",
            dataIndex: 'courseCost'
           },
		   {
            title: '支付类型',
            align:"center",
            dataIndex: 'payType'
           },
		   {
            title: '折扣',
            align:"center",
            dataIndex: 'discount'
           },
		   {
            title: '礼品',
            align:"center",
            dataIndex: 'gift'
           },
		   {
            title: '学费金额',
            align:"center",
            dataIndex: 'xuefeiAmount'
           },
		   {
            title: '往届学费金额',
            align:"center",
            dataIndex: 'beforeXuefeiAmount'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/wxPayRecord/wxPayRecord/list",
          delete: "/wxPayRecord/wxPayRecord/delete",
          deleteBatch: "/wxPayRecord/wxPayRecord/deleteBatch",
          exportXlsUrl: "wxPayRecord/wxPayRecord/exportXls",
          importExcelUrl: "wxPayRecord/wxPayRecord/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>