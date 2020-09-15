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

        <a-form-item label="呼叫类型 1.呼入渠道 2.呼出渠道" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'callType', validatorRules.callType]" placeholder="请输入呼叫类型 1.呼入渠道 2.呼出渠道" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="报读校区" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'signupSchool', validatorRules.signupSchool]" placeholder="请输入报读校区"></a-input>
        </a-form-item>
          
        <a-form-item label="学生姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'studentName', validatorRules.studentName]" placeholder="请输入学生姓名"></a-input>
        </a-form-item>
          
        <a-form-item label="学生年龄" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'studentAge', validatorRules.studentAge]" placeholder="请输入学生年龄" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="父亲姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'fatherName', validatorRules.fatherName]" placeholder="请输入父亲姓名"></a-input>
        </a-form-item>
          
        <a-form-item label="母亲姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'motherName', validatorRules.motherName]" placeholder="请输入母亲姓名"></a-input>
        </a-form-item>
          
        <a-form-item label="联系电话" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'telephone', validatorRules.telephone]" placeholder="请输入联系电话"></a-input>
        </a-form-item>
          
        <a-form-item label="联系电话2" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'telephoneTwo', validatorRules.telephoneTwo]" placeholder="请输入联系电话2"></a-input>
        </a-form-item>
          
        <a-form-item label="家长邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'parentEmail', validatorRules.parentEmail]" placeholder="请输入家长邮箱"></a-input>
        </a-form-item>
          
        <a-form-item label="家庭住址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'familyAddress', validatorRules.familyAddress]" placeholder="请输入家庭住址"></a-input>
        </a-form-item>
          
        <a-form-item label="现就读学校" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'currentSchool', validatorRules.currentSchool]" placeholder="请输入现就读学校"></a-input>
        </a-form-item>
          
        <a-form-item label="现就读年级" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'currentGrade', validatorRules.currentGrade]" placeholder="请输入现就读年级"></a-input>
        </a-form-item>
          
        <a-form-item label="信息渠道" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'channelInformation', validatorRules.channelInformation]" placeholder="请输入信息渠道"></a-input>
        </a-form-item>
          
        <a-form-item label="是否有效 0.待审核  1.有效 2.无效" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'isValid', validatorRules.isValid]" placeholder="请输入是否有效 0.待审核  1.有效 2.无效"></a-input>
        </a-form-item>
          
        <a-form-item label="是否报读 0.待考虑 1.是 2.否" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'isSignup', validatorRules.isSignup]" placeholder="请输入是否报读 0.待考虑 1.是 2.否"></a-input>
        </a-form-item>
          
        <a-form-item label="归属人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'belonger', validatorRules.belonger]" placeholder="请输入归属人"></a-input>
        </a-form-item>
          
        <a-form-item label="收集人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'collecter', validatorRules.collecter]" placeholder="请输入收集人"></a-input>
        </a-form-item>
          
        <a-form-item label="学生英文名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'studentEnglishname', validatorRules.studentEnglishname]" placeholder="请输入学生英文名"></a-input>
        </a-form-item>
          
        <a-form-item label="测试结果" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'testResult', validatorRules.testResult]" placeholder="请输入测试结果"></a-input>
        </a-form-item>
          
        <a-form-item label="教师推荐" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'recommendTeacher', validatorRules.recommendTeacher]" placeholder="请输入教师推荐"></a-input>
        </a-form-item>
          
        <a-form-item label="报读倾向" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'studyTendency', validatorRules.studyTendency]" placeholder="请输入报读倾向"></a-input>
        </a-form-item>
          
        <a-form-item label="咨询课程  多个课程用英文逗号分隔","" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'counselingCourse', validatorRules.counselingCourse]" placeholder="请输入咨询课程  多个课程用英文逗号分隔",""></a-input>
        </a-form-item>
          
        
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  
  export default {
    name: "WxPotentialCustomerModal",
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
        callType:{},
        signupSchool:{},
        studentName:{},
        studentAge:{},
        fatherName:{},
        motherName:{},
        telephone:{},
        telephoneTwo:{},
        parentEmail:{},
        familyAddress:{},
        currentSchool:{},
        currentGrade:{},
        channelInformation:{},
        isValid:{},
        isSignup:{},
        belonger:{},
        collecter:{},
        studentEnglishname:{},
        testResult:{},
        recommendTeacher:{},
        studyTendency:{},
        counselingCourse:{},
        },
        url: {
          add: "/wxPotentialCustomer/wxPotentialCustomer/add",
          edit: "/wxPotentialCustomer/wxPotentialCustomer/edit",
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
          this.form.setFieldsValue(pick(this.model,'callType','signupSchool','studentName','studentAge','fatherName','motherName','telephone','telephoneTwo','parentEmail','familyAddress','currentSchool','currentGrade','channelInformation','isValid','isSignup','belonger','collecter','studentEnglishname','testResult','recommendTeacher','studyTendency','counselingCourse'))
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
        this.form.setFieldsValue(pick(row,'callType','signupSchool','studentName','studentAge','fatherName','motherName','telephone','telephoneTwo','parentEmail','familyAddress','currentSchool','currentGrade','channelInformation','isValid','isSignup','belonger','collecter','studentEnglishname','testResult','recommendTeacher','studyTendency','counselingCourse'))
      }
      
    }
  }
</script>