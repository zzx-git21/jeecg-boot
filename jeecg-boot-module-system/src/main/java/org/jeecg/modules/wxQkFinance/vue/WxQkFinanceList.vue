<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('潜客缴费')">导出</a-button>
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
        
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="图片不存在" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>

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

    <wxQkFinance-modal ref="modalForm" @ok="modalFormOk"></wxQkFinance-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import WxQkFinanceModal from './modules/WxQkFinanceModal'
  export default {
    name: "WxQkFinanceList",
    mixins:[JeecgListMixin],
    components: {
      WxQkFinanceModal
    },
    data () {
      return {
        description: '潜客缴费管理页面',
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
            title:'潜客姓名',
            align:"center",
            dataIndex: 'qkName'
          },
          {
            title:'报读学校',
            align:"center",
            dataIndex: 'schoolName'
          },
          {
            title:'所属学校id',
            align:"center",
            dataIndex: 'schoolId'
          },
          {
            title:'登录名',
            align:"center",
            dataIndex: 'userName'
          },
          {
            title:'招生季',
            align:"center",
            dataIndex: 'zhaoshengji'
          },
          {
            title:'缴费类型',
            align:"center",
            dataIndex: 'jiaofeiType'
          },
          {
            title:'课程系列',
            align:"center",
            dataIndex: 'kechengCate'
          },
          {
            title:'课程名称',
            align:"center",
            dataIndex: 'kechengName'
          },
          {
            title:'所属班级',
            align:"center",
            dataIndex: 'kechengBanji'
          },
          {
            title:'支付类型',
            align:"center",
            dataIndex: 'payType'
          },
          {
            title:'票据编号',
            align:"center",
            dataIndex: 'piaoju'
          },
          {
            title:'实际缴纳',
            align:"center",
            dataIndex: 'shijifeiyong'
          },
          {
            title:'折扣',
            align:"center",
            dataIndex: 'zhekou'
          },
          {
            title:'liping',
            align:"center",
            dataIndex: 'liping'
          },
          {
            title:'缴费类型',
            align:"center",
            dataIndex: 'addType'
          },
          {
            title:'发票开头',
            align:"center",
            dataIndex: 'fapiaoTitle'
          },
          {
            title:'发票金额',
            align:"center",
            dataIndex: 'fapiaoJine'
          },
          {
            title:'已上课时',
            align:"center",
            dataIndex: 'hasKeshi'
          },
          {
            title:'本次消费',
            align:"center",
            dataIndex: 'benciXiaofei'
          },
          {
            title:'剩余金额',
            align:"center",
            dataIndex: 'shengyuJine'
          },
          {
            title:'应缴金额',
            align:"center",
            dataIndex: 'yinjiaoJine'
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'mark'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/wxQkFinance/wxQkFinance/list",
          delete: "/wxQkFinance/wxQkFinance/delete",
          deleteBatch: "/wxQkFinance/wxQkFinance/deleteBatch",
          exportXlsUrl: "/wxQkFinance/wxQkFinance/exportXls",
          importExcelUrl: "wxQkFinance/wxQkFinance/importExcel",
        },
        dictOptions:{
        } 
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
      }
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>