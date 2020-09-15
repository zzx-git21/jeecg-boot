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
      <a-button type="primary" icon="download" @click="handleExportXls('潜客,潜在客户')">导出</a-button>
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

    <wxPotentialCustomer-modal ref="modalForm" @ok="modalFormOk"></wxPotentialCustomer-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import WxPotentialCustomerModal from './modules/WxPotentialCustomerModal'
  export default {
    name: "WxPotentialCustomerList",
    mixins:[JeecgListMixin],
    components: {
      WxPotentialCustomerModal
    },
    data () {
      return {
        description: '潜客,潜在客户管理页面',
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
            title:'呼叫类型 1.呼入渠道 2.呼出渠道',
            align:"center",
            dataIndex: 'callType'
          },
          {
            title:'报读校区',
            align:"center",
            dataIndex: 'signupSchool'
          },
          {
            title:'学生姓名',
            align:"center",
            dataIndex: 'studentName'
          },
          {
            title:'学生年龄',
            align:"center",
            dataIndex: 'studentAge'
          },
          {
            title:'父亲姓名',
            align:"center",
            dataIndex: 'fatherName'
          },
          {
            title:'母亲姓名',
            align:"center",
            dataIndex: 'motherName'
          },
          {
            title:'联系电话',
            align:"center",
            dataIndex: 'telephone'
          },
          {
            title:'联系电话2',
            align:"center",
            dataIndex: 'telephoneTwo'
          },
          {
            title:'家长邮箱',
            align:"center",
            dataIndex: 'parentEmail'
          },
          {
            title:'家庭住址',
            align:"center",
            dataIndex: 'familyAddress'
          },
          {
            title:'现就读学校',
            align:"center",
            dataIndex: 'currentSchool'
          },
          {
            title:'现就读年级',
            align:"center",
            dataIndex: 'currentGrade'
          },
          {
            title:'信息渠道',
            align:"center",
            dataIndex: 'channelInformation'
          },
          {
            title:'是否有效 0.待审核  1.有效 2.无效',
            align:"center",
            dataIndex: 'isValid'
          },
          {
            title:'是否报读 0.待考虑 1.是 2.否',
            align:"center",
            dataIndex: 'isSignup'
          },
          {
            title:'归属人',
            align:"center",
            dataIndex: 'belonger'
          },
          {
            title:'收集人',
            align:"center",
            dataIndex: 'collecter'
          },
          {
            title:'学生英文名',
            align:"center",
            dataIndex: 'studentEnglishname'
          },
          {
            title:'测试结果',
            align:"center",
            dataIndex: 'testResult'
          },
          {
            title:'教师推荐',
            align:"center",
            dataIndex: 'recommendTeacher'
          },
          {
            title:'报读倾向',
            align:"center",
            dataIndex: 'studyTendency'
          },
          {
            title:'咨询课程  多个课程用英文逗号分隔","',
            align:"center",
            dataIndex: 'counselingCourse'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/wxPotentialCustomer/wxPotentialCustomer/list",
          delete: "/wxPotentialCustomer/wxPotentialCustomer/delete",
          deleteBatch: "/wxPotentialCustomer/wxPotentialCustomer/deleteBatch",
          exportXlsUrl: "/wxPotentialCustomer/wxPotentialCustomer/exportXls",
          importExcelUrl: "wxPotentialCustomer/wxPotentialCustomer/importExcel",
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