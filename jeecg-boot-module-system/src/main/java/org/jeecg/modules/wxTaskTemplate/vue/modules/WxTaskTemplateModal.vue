<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="课程目标" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'courseTask', validatorRules.courseTask]" placeholder="请输入课程目标"></a-input>
        </a-form-item>
          
        <a-form-item label="教材" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'teachingMaterial', validatorRules.teachingMaterial]" placeholder="请输入教材"></a-input>
        </a-form-item>
          
        <a-form-item label="家庭作业" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'homeWork', validatorRules.homeWork]" placeholder="请输入家庭作业"></a-input>
        </a-form-item>
          
        <a-form-item label="复习语言" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'reviewLanguage', validatorRules.reviewLanguage]" placeholder="请输入复习语言"></a-input>
        </a-form-item>
          
        <a-form-item label="重点语言" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'keyLanguage', validatorRules.keyLanguage]" placeholder="请输入重点语言"></a-input>
        </a-form-item>
          
        <a-form-item label="补充语言" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'suppLanguage', validatorRules.suppLanguage]" placeholder="请输入补充语言"></a-input>
        </a-form-item>
          
        <a-form-item label="课次" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'kcCode', validatorRules.kcCode]" placeholder="请输入课次"></a-input>
        </a-form-item>
          
        <a-form-item label="课次名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'kcName', validatorRules.kcName]" placeholder="请输入课次名称"></a-input>
        </a-form-item>
          
        <a-form-item label="课程级别" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'kcjbCode', validatorRules.kcjbCode]" placeholder="请输入课程级别"></a-input>
        </a-form-item>
          
        <a-form-item label="课程级别名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'kcjbName', validatorRules.kcjbName]" placeholder="请输入课程级别名称"></a-input>
        </a-form-item>
          
        
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  
  export default {
    name: "WxTaskTemplateModal",
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
        courseTask:{},
        teachingMaterial:{},
        homeWork:{},
        reviewLanguage:{},
        keyLanguage:{},
        suppLanguage:{},
        kcCode:{},
        kcName:{},
        kcjbCode:{},
        kcjbName:{},
        },
        url: {
          add: "/wxTaskTemplate/wxTaskTemplate/add",
          edit: "/wxTaskTemplate/wxTaskTemplate/edit",
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
          this.form.setFieldsValue(pick(this.model,'courseTask','teachingMaterial','homeWork','reviewLanguage','keyLanguage','suppLanguage','kcCode','kcName','kcjbCode','kcjbName'))
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
        this.form.setFieldsValue(pick(row,'courseTask','teachingMaterial','homeWork','reviewLanguage','keyLanguage','suppLanguage','kcCode','kcName','kcjbCode','kcjbName'))
      }
      
    }
  }
</script>