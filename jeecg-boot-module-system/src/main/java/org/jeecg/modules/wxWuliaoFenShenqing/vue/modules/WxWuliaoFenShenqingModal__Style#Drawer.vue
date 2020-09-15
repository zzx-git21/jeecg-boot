<template>
  <a-drawer
      :title="title"
      :width="800"
      placement="right"
      :closable="false"
      @close="close"
      :visible="visible"
  >

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="所属部门">
          <a-input placeholder="请输入所属部门" v-decorator="['sysOrgCode', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="总校库存Id">
          <a-input placeholder="请输入总校库存Id" v-decorator="['zongKucunId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="物料名称">
          <a-input placeholder="请输入物料名称" v-decorator="['wuliaoId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请单位">
          <a-input placeholder="请输入申请单位" v-decorator="['shenqingDanwei', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请数量">
          <a-input placeholder="请输入申请数量" v-decorator="['shenqingNum', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请用途">
          <a-input placeholder="请输入申请用途" v-decorator="['shenqingYongtu', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注">
          <a-input placeholder="请输入备注" v-decorator="['shenqingDesc', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请类型">
          <a-input placeholder="请输入申请类型" v-decorator="['shenqingType', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'shenqingTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请人">
          <a-input placeholder="请输入申请人" v-decorator="['shenqingMan', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="分校审核状态">
          <a-input placeholder="请输入分校审核状态" v-decorator="['fenCheckStatus', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="总校审核状态">
          <a-input placeholder="请输入总校审核状态" v-decorator="['zongCheckStatus', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="申请单状态">
          <a-input placeholder="请输入申请单状态" v-decorator="['shenqingStatus', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="发货日期">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'fahuoTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="发货订单号">
          <a-input placeholder="请输入发货订单号" v-decorator="['fahuoNum', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否入库">
          <a-input placeholder="请输入是否入库" v-decorator="['ifRuku', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "WxWuliaoFenShenqingModal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        },
        url: {
          add: "/wxWuliaoFenShenqing/wxWuliaoFenShenqing/add",
          edit: "/wxWuliaoFenShenqing/wxWuliaoFenShenqing/edit",
        },
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'sysOrgCode','zongKucunId','wuliaoId','shenqingDanwei','shenqingNum','shenqingYongtu','shenqingDesc','shenqingType','shenqingMan','fenCheckStatus','zongCheckStatus','shenqingStatus','fahuoNum','ifRuku'))
		  //时间格式化
          this.form.setFieldsValue({shenqingTime:this.model.shenqingTime?moment(this.model.shenqingTime):null})
          this.form.setFieldsValue({fahuoTime:this.model.fahuoTime?moment(this.model.fahuoTime):null})
        });

      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            //时间格式化
            formData.shenqingTime = formData.shenqingTime?formData.shenqingTime.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.fahuoTime = formData.fahuoTime?formData.fahuoTime.format('YYYY-MM-DD HH:mm:ss'):null;
            
            console.log(formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })



          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>