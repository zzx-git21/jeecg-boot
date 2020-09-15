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
          label="批号">
          <a-input placeholder="请输入批号" v-decorator="['batchNum', {}]" />
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
          label="总数量">
          <a-input placeholder="请输入总数量" v-decorator="['totalNum', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="当前数量">
          <a-input placeholder="请输入当前数量" v-decorator="['currentNum', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="单价">
          <a-input placeholder="请输入单价" v-decorator="['unitPrice', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="预计到货时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'yujiDaohuoTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="物料类型">
          <a-input placeholder="请输入物料类型" v-decorator="['wuliaoType', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入库类型">
          <a-input placeholder="请输入入库类型" v-decorator="['rukuType', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="预警值">
          <a-input placeholder="请输入预警值" v-decorator="['yujingzhi', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否可领用">
          <a-input placeholder="请输入是否可领用" v-decorator="['ifCanDingyong', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="物料存放地点">
          <a-input placeholder="请输入物料存放地点" v-decorator="['wuliaoCunfangAddr', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="包装说明">
          <a-input placeholder="请输入包装说明" v-decorator="['baozhuangDesc', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="物料参数">
          <a-input placeholder="请输入物料参数" v-decorator="['wuliaoParam', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="已上传图片">
          <a-input placeholder="请输入已上传图片" v-decorator="['uploadPhone', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入库时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'rukuTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入库人姓名">
          <a-input placeholder="请输入入库人姓名" v-decorator="['rukuMan', {}]" />
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
    name: "WxWuliaoZongKucunModal",
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
          add: "/wxWuliaoZongKucun/wxWuliaoZongKucun/add",
          edit: "/wxWuliaoZongKucun/wxWuliaoZongKucun/edit",
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
          this.form.setFieldsValue(pick(this.model,'sysOrgCode','batchNum','wuliaoId','totalNum','currentNum','unitPrice','wuliaoType','rukuType','yujingzhi','ifCanDingyong','wuliaoCunfangAddr','baozhuangDesc','wuliaoParam','uploadPhone','rukuMan'))
		  //时间格式化
          this.form.setFieldsValue({yujiDaohuoTime:this.model.yujiDaohuoTime?moment(this.model.yujiDaohuoTime):null})
          this.form.setFieldsValue({rukuTime:this.model.rukuTime?moment(this.model.rukuTime):null})
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
            formData.yujiDaohuoTime = formData.yujiDaohuoTime?formData.yujiDaohuoTime.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.rukuTime = formData.rukuTime?formData.rukuTime.format('YYYY-MM-DD HH:mm:ss'):null;
            
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