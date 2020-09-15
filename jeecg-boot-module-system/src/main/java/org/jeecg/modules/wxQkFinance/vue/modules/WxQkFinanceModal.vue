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

        <a-form-item label="潜客姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'qkName', validatorRules.qkName]" placeholder="请输入潜客姓名"></a-input>
        </a-form-item>
          
        <a-form-item label="报读学校" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'schoolName', validatorRules.schoolName]" placeholder="请输入报读学校"></a-input>
        </a-form-item>
          
        <a-form-item label="所属学校id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'schoolId', validatorRules.schoolId]" placeholder="请输入所属学校id"></a-input>
        </a-form-item>
          
        <a-form-item label="登录名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'userName', validatorRules.userName]" placeholder="请输入登录名"></a-input>
        </a-form-item>
          
        <a-form-item label="招生季" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'zhaoshengji', validatorRules.zhaoshengji]" placeholder="请输入招生季"></a-input>
        </a-form-item>
          
        <a-form-item label="缴费类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'jiaofeiType', validatorRules.jiaofeiType]" placeholder="请输入缴费类型"></a-input>
        </a-form-item>
          
        <a-form-item label="课程系列" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'kechengCate', validatorRules.kechengCate]" placeholder="请输入课程系列"></a-input>
        </a-form-item>
          
        <a-form-item label="课程名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'kechengName', validatorRules.kechengName]" placeholder="请输入课程名称"></a-input>
        </a-form-item>
          
        <a-form-item label="所属班级" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'kechengBanji', validatorRules.kechengBanji]" placeholder="请输入所属班级"></a-input>
        </a-form-item>
          
        <a-form-item label="支付类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'payType', validatorRules.payType]" placeholder="请输入支付类型"></a-input>
        </a-form-item>
          
        <a-form-item label="票据编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'piaoju', validatorRules.piaoju]" placeholder="请输入票据编号"></a-input>
        </a-form-item>
          
        <a-form-item label="实际缴纳" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'shijifeiyong', validatorRules.shijifeiyong]" placeholder="请输入实际缴纳"></a-input>
        </a-form-item>
          
        <a-form-item label="折扣" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'zhekou', validatorRules.zhekou]" placeholder="请输入折扣"></a-input>
        </a-form-item>
          
        <a-form-item label="liping" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'liping', validatorRules.liping]" placeholder="请输入liping"></a-input>
        </a-form-item>
          
        <a-form-item label="缴费类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'addType', validatorRules.addType]" placeholder="请输入缴费类型"></a-input>
        </a-form-item>
          
        <a-form-item label="发票开头" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'fapiaoTitle', validatorRules.fapiaoTitle]" placeholder="请输入发票开头"></a-input>
        </a-form-item>
          
        <a-form-item label="发票金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'fapiaoJine', validatorRules.fapiaoJine]" placeholder="请输入发票金额"></a-input>
        </a-form-item>
          
        <a-form-item label="已上课时" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'hasKeshi', validatorRules.hasKeshi]" placeholder="请输入已上课时"></a-input>
        </a-form-item>
          
        <a-form-item label="本次消费" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'benciXiaofei', validatorRules.benciXiaofei]" placeholder="请输入本次消费"></a-input>
        </a-form-item>
          
        <a-form-item label="剩余金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'shengyuJine', validatorRules.shengyuJine]" placeholder="请输入剩余金额"></a-input>
        </a-form-item>
          
        <a-form-item label="应缴金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'yinjiaoJine', validatorRules.yinjiaoJine]" placeholder="请输入应缴金额"></a-input>
        </a-form-item>
          
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mark', validatorRules.mark]" placeholder="请输入备注"></a-input>
        </a-form-item>
          
        
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  
  export default {
    name: "WxQkFinanceModal",
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
        qkName:{},
        schoolName:{},
        schoolId:{},
        userName:{},
        zhaoshengji:{},
        jiaofeiType:{},
        kechengCate:{},
        kechengName:{},
        kechengBanji:{},
        payType:{},
        piaoju:{},
        shijifeiyong:{},
        zhekou:{},
        liping:{},
        addType:{},
        fapiaoTitle:{},
        fapiaoJine:{},
        hasKeshi:{},
        benciXiaofei:{},
        shengyuJine:{},
        yinjiaoJine:{},
        mark:{},
        },
        url: {
          add: "/wxQkFinance/wxQkFinance/add",
          edit: "/wxQkFinance/wxQkFinance/edit",
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
          this.form.setFieldsValue(pick(this.model,'qkName','schoolName','schoolId','userName','zhaoshengji','jiaofeiType','kechengCate','kechengName','kechengBanji','payType','piaoju','shijifeiyong','zhekou','liping','addType','fapiaoTitle','fapiaoJine','hasKeshi','benciXiaofei','shengyuJine','yinjiaoJine','mark'))
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
        this.form.setFieldsValue(pick(row,'qkName','schoolName','schoolId','userName','zhaoshengji','jiaofeiType','kechengCate','kechengName','kechengBanji','payType','piaoju','shijifeiyong','zhekou','liping','addType','fapiaoTitle','fapiaoJine','hasKeshi','benciXiaofei','shengyuJine','yinjiaoJine','mark'))
      }
      
    }
  }
</script>