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
            <a-form-item label="库存Id">
              <a-input placeholder="请输入库存Id" v-model="queryParam.kucunId"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="物料">
              <a-input placeholder="请输入物料" v-model="queryParam.wuliaoId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="批号">
              <a-input placeholder="请输入批号" v-model="queryParam.batchNum"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="领取数量">
              <a-input placeholder="请输入领取数量" v-model="queryParam.lingquNum"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('物料领取')">导出</a-button>
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
    <wxWuliaoLingqu-modal ref="modalForm" @ok="modalFormOk"></wxWuliaoLingqu-modal>
  </a-card>
</template>

<script>
  import WxWuliaoLingquModal from './modules/WxWuliaoLingquModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "WxWuliaoLingquList",
    mixins:[JeecgListMixin],
    components: {
      WxWuliaoLingquModal
    },
    data () {
      return {
        description: '物料领取管理页面',
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
            title: '库存Id',
            align:"center",
            dataIndex: 'kucunId'
           },
		   {
            title: '物料',
            align:"center",
            dataIndex: 'wuliaoId'
           },
		   {
            title: '批号',
            align:"center",
            dataIndex: 'batchNum'
           },
		   {
            title: '领取数量',
            align:"center",
            dataIndex: 'lingquNum'
           },
		   {
            title: '领取人',
            align:"center",
            dataIndex: 'lingquMan'
           },
		   {
            title: '领取日期',
            align:"center",
            dataIndex: 'lingquTime'
           },
		   {
            title: ' 领取部门',
            align:"center",
            dataIndex: 'lingquDep'
           },
		   {
            title: '领用说明',
            align:"center",
            dataIndex: 'lingquDesc'
           },
		   {
            title: '领用单位',
            align:"center",
            dataIndex: 'lingquDanwei'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/wxWuliaoLingqu/wxWuliaoLingqu/list",
          delete: "/wxWuliaoLingqu/wxWuliaoLingqu/delete",
          deleteBatch: "/wxWuliaoLingqu/wxWuliaoLingqu/deleteBatch",
          exportXlsUrl: "wxWuliaoLingqu/wxWuliaoLingqu/exportXls",
          importExcelUrl: "wxWuliaoLingqu/wxWuliaoLingqu/importExcel",
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