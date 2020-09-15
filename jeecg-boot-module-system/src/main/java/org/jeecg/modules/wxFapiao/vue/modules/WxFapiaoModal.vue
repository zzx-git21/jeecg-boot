<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
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
          label="姓名">
          <a-input placeholder="请输入姓名" v-decorator="['name', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="学籍号">
          <a-input placeholder="请输入学籍号" v-decorator="['xuejiNum', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="收据号">
          <a-input placeholder="请输入收据号" v-decorator="['shoujuNum', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="当前招生季">
          <a-input placeholder="请输入当前招生季" v-decorator="['currentZhaoshengji', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="发票抬头">
          <a-input placeholder="请输入发票抬头" v-decorator="['fapiaoTaitou', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="发票金额">
          <a-input placeholder="请输入发票金额" v-decorator="['fapiaoAmount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="发票状态">
          <a-input placeholder="请输入发票状态" v-decorator="['fapiaoStatus', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="操作人员">
          <a-input placeholder="请输入操作人员" v-decorator="['operatePerson', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="操作时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'operateTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否拒绝">
          <a-input placeholder="请输入是否拒绝" v-decorator="['ifRefuse', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注">
          <a-input placeholder="请输入备注" v-decorator="['mark', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="确认人（分校）">
          <a-input placeholder="请输入确认人（分校）" v-decorator="['querenPersonFenxiao', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="确认时间（分校）">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'querenTimeFenxiao', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="确认人（总校）">
          <a-input placeholder="请输入确认人（总校）" v-decorator="['querenPersonZongxiao', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="确认时间（总校）">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'querenTimeZongxiao', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="业务类型">
          <a-input placeholder="请输入业务类型" v-decorator="['businessType', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="业务id">
          <a-input placeholder="请输入业务id" v-decorator="['businessId', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "WxFapiaoModal",
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
          add: "/wxFapiao/wxFapiao/add",
          edit: "/wxFapiao/wxFapiao/edit",
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
          this.form.setFieldsValue(pick(this.model,'sysOrgCode','name','xuejiNum','shoujuNum','currentZhaoshengji','fapiaoTaitou','fapiaoAmount','fapiaoStatus','operatePerson','ifRefuse','mark','querenPersonFenxiao','querenPersonZongxiao','businessType','businessId'))
		  //时间格式化
          this.form.setFieldsValue({operateTime:this.model.operateTime?moment(this.model.operateTime):null})
          this.form.setFieldsValue({querenTimeFenxiao:this.model.querenTimeFenxiao?moment(this.model.querenTimeFenxiao):null})
          this.form.setFieldsValue({querenTimeZongxiao:this.model.querenTimeZongxiao?moment(this.model.querenTimeZongxiao):null})
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
            formData.operateTime = formData.operateTime?formData.operateTime.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.querenTimeFenxiao = formData.querenTimeFenxiao?formData.querenTimeFenxiao.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.querenTimeZongxiao = formData.querenTimeZongxiao?formData.querenTimeZongxiao.format('YYYY-MM-DD HH:mm:ss'):null;
            
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

</style>