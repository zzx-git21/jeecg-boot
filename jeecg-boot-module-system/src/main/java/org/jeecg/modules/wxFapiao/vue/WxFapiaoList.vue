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
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="学籍号">
              <a-input placeholder="请输入学籍号" v-model="queryParam.xuejiNum"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="收据号">
              <a-input placeholder="请输入收据号" v-model="queryParam.shoujuNum"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="当前招生季">
              <a-input placeholder="请输入当前招生季" v-model="queryParam.currentZhaoshengji"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('发票')">导出</a-button>
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
    <wxFapiao-modal ref="modalForm" @ok="modalFormOk"></wxFapiao-modal>
  </a-card>
</template>

<script>
  import WxFapiaoModal from './modules/WxFapiaoModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "WxFapiaoList",
    mixins:[JeecgListMixin],
    components: {
      WxFapiaoModal
    },
    data () {
      return {
        description: '发票管理页面',
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
            title: '姓名',
            align:"center",
            dataIndex: 'name'
           },
		   {
            title: '学籍号',
            align:"center",
            dataIndex: 'xuejiNum'
           },
		   {
            title: '收据号',
            align:"center",
            dataIndex: 'shoujuNum'
           },
		   {
            title: '当前招生季',
            align:"center",
            dataIndex: 'currentZhaoshengji'
           },
		   {
            title: '发票抬头',
            align:"center",
            dataIndex: 'fapiaoTaitou'
           },
		   {
            title: '发票金额',
            align:"center",
            dataIndex: 'fapiaoAmount'
           },
		   {
            title: '发票状态',
            align:"center",
            dataIndex: 'fapiaoStatus'
           },
		   {
            title: '操作人员',
            align:"center",
            dataIndex: 'operatePerson'
           },
		   {
            title: '操作时间',
            align:"center",
            dataIndex: 'operateTime'
           },
		   {
            title: '是否拒绝',
            align:"center",
            dataIndex: 'ifRefuse'
           },
		   {
            title: '备注',
            align:"center",
            dataIndex: 'mark'
           },
		   {
            title: '确认人（分校）',
            align:"center",
            dataIndex: 'querenPersonFenxiao'
           },
		   {
            title: '确认时间（分校）',
            align:"center",
            dataIndex: 'querenTimeFenxiao'
           },
		   {
            title: '确认人（总校）',
            align:"center",
            dataIndex: 'querenPersonZongxiao'
           },
		   {
            title: '确认时间（总校）',
            align:"center",
            dataIndex: 'querenTimeZongxiao'
           },
		   {
            title: '业务类型',
            align:"center",
            dataIndex: 'businessType'
           },
		   {
            title: '业务id',
            align:"center",
            dataIndex: 'businessId'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/wxFapiao/wxFapiao/list",
          delete: "/wxFapiao/wxFapiao/delete",
          deleteBatch: "/wxFapiao/wxFapiao/deleteBatch",
          exportXlsUrl: "wxFapiao/wxFapiao/exportXls",
          importExcelUrl: "wxFapiao/wxFapiao/importExcel",
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