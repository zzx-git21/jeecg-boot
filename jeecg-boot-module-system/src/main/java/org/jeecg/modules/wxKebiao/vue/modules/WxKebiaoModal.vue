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

        <a-form-item label="课时" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'courseTime', validatorRules.courseTime]" placeholder="请输入课时" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="周几" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'zhouji', validatorRules.zhouji]" placeholder="请输入周几"></a-input>
        </a-form-item>
          
        <a-form-item label="开始时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择开始时间" v-decorator="[ 'startTime', validatorRules.startTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="结束时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择结束时间" v-decorator="[ 'endTime', validatorRules.endTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="上课开始时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择上课开始时间" v-decorator="[ 'courseStartTime', validatorRules.courseStartTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="上课结束时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择上课结束时间" v-decorator="[ 'courseEndTime', validatorRules.courseEndTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="课程状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'keshiStatus', validatorRules.keshiStatus]" placeholder="请输入课程状态" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mark', validatorRules.mark]" placeholder="请输入备注"></a-input>
        </a-form-item>
          
        <a-form-item label="考勤状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'kaoqingStatus', validatorRules.kaoqingStatus]" placeholder="请输入考勤状态" style="width: 100%"/>
        </a-form-item>
          
        <a-form-item label="所属班级" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'banjiCode', validatorRules.banjiCode]" placeholder="请输入所属班级"></a-input>
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
    name: "WxKebiaoModal",
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
        courseTime:{},
        zhouji:{},
        startTime:{},
        endTime:{},
        courseStartTime:{},
        courseEndTime:{},
        keshiStatus:{},
        mark:{},
        kaoqingStatus:{},
        banjiCode:{},
        },
        url: {
          add: "/wxKebiao/wxKebiao/add",
          edit: "/wxKebiao/wxKebiao/edit",
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
          this.form.setFieldsValue(pick(this.model,'courseTime','zhouji','startTime','endTime','courseStartTime','courseEndTime','keshiStatus','mark','kaoqingStatus','banjiCode'))
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
        this.form.setFieldsValue(pick(row,'courseTime','zhouji','startTime','endTime','courseStartTime','courseEndTime','keshiStatus','mark','kaoqingStatus','banjiCode'))
      }
      
    }
  }
</script>