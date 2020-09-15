<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="用户ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'userId', validatorRules.userId]" placeholder="请输入用户ID"></a-input>
        </a-form-item>
        <a-form-item label="学生姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'studyName', validatorRules.studyName]" placeholder="请输入学生姓名"></a-input>
        </a-form-item>
        <a-form-item label="发生时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择发生时间" v-decorator="[ 'paymentTime', validatorRules.paymentTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="缴费类型  0.押金  1.罚款" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'paymentType', validatorRules.paymentType]" placeholder="请输入缴费类型  0.押金  1.罚款" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="原押金" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'originalDesposit', validatorRules.originalDesposit]" placeholder="请输入原押金" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="本次所缴押金" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'paymentDesposit', validatorRules.paymentDesposit]" placeholder="请输入本次所缴押金" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="应缴罚款" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'shouldPenalty', validatorRules.shouldPenalty]" placeholder="请输入应缴罚款" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="实缴罚款" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'paidPenalty', validatorRules.paidPenalty]" placeholder="请输入实缴罚款" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="押金金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'balanceDesposit', validatorRules.balanceDesposit]" placeholder="请输入押金金额" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="退费金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'returnDesposit', validatorRules.returnDesposit]" placeholder="请输入退费金额" style="width: 100%"/>
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
  import JDate from '@/components/jeecg/JDate'  
  
  export default {
    name: "WxDespositLogModal",
    components: { 
      JDate,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
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
        validatorRules:{
        userId:{},
        studyName:{},
        paymentTime:{},
        paymentType:{},
        originalDesposit:{},
        paymentDesposit:{},
        shouldPenalty:{},
        paidPenalty:{},
        balanceDesposit:{},
        returnDesposit:{},
        },
        url: {
          add: "/wxDespositLog/wxDespositLog/add",
          edit: "/wxDespositLog/wxDespositLog/edit",
        }
     
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
          this.form.setFieldsValue(pick(this.model,'userId','studyName','paymentTime','paymentType','originalDesposit','paymentDesposit','shouldPenalty','paidPenalty','balanceDesposit','returnDesposit'))
        })
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
            console.log("表单提交数据",formData)
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
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'userId','studyName','paymentTime','paymentType','originalDesposit','paymentDesposit','shouldPenalty','paidPenalty','balanceDesposit','returnDesposit'))
      }
      
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