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

        <a-form-item label="资料标题" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'dataName', validatorRules.dataName]" placeholder="请输入资料标题"></a-input>
        </a-form-item>
          
        <a-form-item label="资料描述" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'dataDesc', validatorRules.dataDesc]" placeholder="请输入资料描述"></a-input>
        </a-form-item>
          
        <a-form-item label="资料正文" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'dataText', validatorRules.dataText]" placeholder="请输入资料正文"></a-input>
        </a-form-item>
          
        <a-form-item label="资料地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'dataUrl', validatorRules.dataUrl]" placeholder="请输入资料地址"></a-input>
        </a-form-item>
          
        <a-form-item label="年级ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'gradeId', validatorRules.gradeId]" placeholder="请输入年级ID" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="是否推荐" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'isRecommend', validatorRules.isRecommend]" placeholder="请输入是否推荐" style="width: 100%"/>
        </a-form-item>
          
        
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  
  export default {
    name: "WxTutorialDataModal",
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
        dataName:{},
        dataDesc:{},
        dataText:{},
        dataUrl:{},
        gradeId:{},
        isRecommend:{},
        },
        url: {
          add: "/wxTutorialData/wxTutorialData/add",
          edit: "/wxTutorialData/wxTutorialData/edit",
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
          this.form.setFieldsValue(pick(this.model,'dataName','dataDesc','dataText','dataUrl','gradeId','isRecommend'))
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
        this.form.setFieldsValue(pick(row,'dataName','dataDesc','dataText','dataUrl','gradeId','isRecommend'))
      }
      
    }
  }
</script>