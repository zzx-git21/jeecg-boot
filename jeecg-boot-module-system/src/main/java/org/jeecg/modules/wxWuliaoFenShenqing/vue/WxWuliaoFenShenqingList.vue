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
            <a-form-item label="总校库存Id">
              <a-input placeholder="请输入总校库存Id" v-model="queryParam.zongKucunId"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="物料名称">
              <a-input placeholder="请输入物料名称" v-model="queryParam.wuliaoId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="申请单位">
              <a-input placeholder="请输入申请单位" v-model="queryParam.shenqingDanwei"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="申请数量">
              <a-input placeholder="请输入申请数量" v-model="queryParam.shenqingNum"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('分校物料申请')">导出</a-button>
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
    <wxWuliaoFenShenqing-modal ref="modalForm" @ok="modalFormOk"></wxWuliaoFenShenqing-modal>
  </a-card>
</template>

<script>
  import WxWuliaoFenShenqingModal from './modules/WxWuliaoFenShenqingModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "WxWuliaoFenShenqingList",
    mixins:[JeecgListMixin],
    components: {
      WxWuliaoFenShenqingModal
    },
    data () {
      return {
        description: '分校物料申请管理页面',
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
            title: '总校库存Id',
            align:"center",
            dataIndex: 'zongKucunId'
           },
		   {
            title: '物料名称',
            align:"center",
            dataIndex: 'wuliaoId'
           },
		   {
            title: '申请单位',
            align:"center",
            dataIndex: 'shenqingDanwei'
           },
		   {
            title: '申请数量',
            align:"center",
            dataIndex: 'shenqingNum'
           },
		   {
            title: '申请用途',
            align:"center",
            dataIndex: 'shenqingYongtu'
           },
		   {
            title: '备注',
            align:"center",
            dataIndex: 'shenqingDesc'
           },
		   {
            title: '申请类型',
            align:"center",
            dataIndex: 'shenqingType'
           },
		   {
            title: '申请日期',
            align:"center",
            dataIndex: 'shenqingTime'
           },
		   {
            title: '申请人',
            align:"center",
            dataIndex: 'shenqingMan'
           },
		   {
            title: '分校审核状态',
            align:"center",
            dataIndex: 'fenCheckStatus'
           },
		   {
            title: '总校审核状态',
            align:"center",
            dataIndex: 'zongCheckStatus'
           },
		   {
            title: '申请单状态',
            align:"center",
            dataIndex: 'shenqingStatus'
           },
		   {
            title: '发货日期',
            align:"center",
            dataIndex: 'fahuoTime'
           },
		   {
            title: '发货订单号',
            align:"center",
            dataIndex: 'fahuoNum'
           },
		   {
            title: '是否入库',
            align:"center",
            dataIndex: 'ifRuku'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/wxWuliaoFenShenqing/wxWuliaoFenShenqing/list",
          delete: "/wxWuliaoFenShenqing/wxWuliaoFenShenqing/delete",
          deleteBatch: "/wxWuliaoFenShenqing/wxWuliaoFenShenqing/deleteBatch",
          exportXlsUrl: "wxWuliaoFenShenqing/wxWuliaoFenShenqing/exportXls",
          importExcelUrl: "wxWuliaoFenShenqing/wxWuliaoFenShenqing/importExcel",
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