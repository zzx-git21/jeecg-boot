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

        <a-form-item label="所属校区" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'owernShcool', validatorRules.owernShcool]" placeholder="请输入所属校区"></a-input>
        </a-form-item>
          
        <a-form-item label="招生季code" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'seasonCode', validatorRules.seasonCode]" placeholder="请输入招生季code"></a-input>
        </a-form-item>
          
        <a-form-item label="招生季名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'seasonName', validatorRules.seasonName]" placeholder="请输入招生季名称"></a-input>
        </a-form-item>
          
        <a-form-item label="班级名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'banjiName', validatorRules.banjiName]" placeholder="请输入班级名称"></a-input>
        </a-form-item>
          
        <a-form-item label="课程系列" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'courseCode', validatorRules.courseCode]" placeholder="请输入课程系列"></a-input>
        </a-form-item>
          
        <a-form-item label="课程名称code" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'courseNameCode', validatorRules.courseNameCode]" placeholder="请输入课程名称code"></a-input>
        </a-form-item>
          
        <a-form-item label="开始日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择开始日期" v-decorator="[ 'startTime', validatorRules.startTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="结束日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择结束日期" v-decorator="[ 'endTime', validatorRules.endTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="预计学生数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'studentsNum', validatorRules.studentsNum]" placeholder="请输入预计学生数" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="开课时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择开课时间" v-decorator="[ 'courseStartTime', validatorRules.courseStartTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="课时数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'courseTime', validatorRules.courseTime]" placeholder="请输入课时数" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="描述" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mark', validatorRules.mark]" placeholder="请输入描述"></a-input>
        </a-form-item>
          
        
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import JDate from '@/components/jeecg/JDate'  
  
  export default {
    name: "WxBanjiModal",
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
        owernShcool:{},
        seasonCode:{},
        seasonName:{},
        banjiName:{},
        courseCode:{},
        courseNameCode:{},
        startTime:{},
        endTime:{},
        studentsNum:{},
        courseStartTime:{},
        courseTime:{},
        mark:{},
        },
        url: {
          add: "/wxBanji/wxBanji/add",
          edit: "/wxBanji/wxBanji/edit",
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
          this.form.setFieldsValue(pick(this.model,'owernShcool','seasonCode','seasonName','banjiName','courseCode','courseNameCode','startTime','endTime','studentsNum','courseStartTime','courseTime','mark'))
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
        this.form.setFieldsValue(pick(row,'owernShcool','seasonCode','seasonName','banjiName','courseCode','courseNameCode','startTime','endTime','studentsNum','courseStartTime','courseTime','mark'))
      }
      
    }
  }
</script>