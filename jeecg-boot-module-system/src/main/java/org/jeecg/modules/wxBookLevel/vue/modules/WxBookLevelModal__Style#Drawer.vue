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

        <a-form-item label="等级名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'levelName', validatorRules.levelName]" placeholder="请输入等级名称"></a-input>
        </a-form-item>
        <a-form-item label="等级编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'levelCode', validatorRules.levelCode]" placeholder="请输入等级编号"></a-input>
        </a-form-item>
        <a-form-item label="是否限制" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'isLimit', validatorRules.isLimit]" placeholder="请输入是否限制"></a-input>
        </a-form-item>
        <a-form-item label="借出次数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'borrowFrequency', validatorRules.borrowFrequency]" placeholder="请输入借出次数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="借出数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'borrowCount', validatorRules.borrowCount]" placeholder="请输入借出数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="借出天数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'borrowTime', validatorRules.borrowTime]" placeholder="请输入借出天数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'marks', validatorRules.marks]" placeholder="请输入备注"></a-input>
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
  
  export default {
    name: "WxBookLevelModal",
    components: { 
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
        levelName:{},
        levelCode:{},
        isLimit:{},
        borrowFrequency:{},
        borrowCount:{},
        borrowTime:{},
        marks:{},
        },
        url: {
          add: "/wxBookLevel/wxBookLevel/add",
          edit: "/wxBookLevel/wxBookLevel/edit",
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
          this.form.setFieldsValue(pick(this.model,'levelName','levelCode','isLimit','borrowFrequency','borrowCount','borrowTime','marks'))
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
        this.form.setFieldsValue(pick(row,'levelName','levelCode','isLimit','borrowFrequency','borrowCount','borrowTime','marks'))
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