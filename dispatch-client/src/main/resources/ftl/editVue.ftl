<template>
  <edit-form
    :tool-bar="toolBar"
    :form-info="formInfo"
    :model-data="modelData"
    :columns="columns"
    v-on:saveEditInfo="saveEditInfo"
    <#list developments as d>
        <#if d.dataType == 'autocomplete' && d.changeEvent??>
    v-on:${d.changeEvent}='${d.changeEvent}'
        </#if >
    </#list>
    ref="editform"
  >
  </edit-form>
</template>

<script>
import EditForm from '@/components/editForm'

export default {
  components: {EditForm},
  name: 'edit${htmlName?cap_first}',
  data () {
    return {
      toolBar: [
        {name: 'opSave', text: '保存', click: 'saveEditInfo', type: 'primary'}
      ],
      columns: [
        <#list developments as d>
            <#if (developments?size-1 == d_index)>
            {label: '${d.columnComment}', prop: '${d.columnName}' <#if d.required ?? && d.required=='true'>,required: true</#if> <#if d.dataType==''>,type: 'input'</#if><#if d.dataType=='datePicker'>,type: 'datePicker'</#if> <#if d.dataType == 'text'>,type: 'input'</#if><#if d.dataType == 'inputNumber'>,type: 'inputNumber'</#if><#if d.dataType == 'autocomplete'>,type: 'autocomplete',dataSource: [],modelData: '',dataField: 'text'<#if d.changeEvent??>,callMethods: '${d.changeEvent}'</#if> </#if> }
            <#else >
            {label: '${d.columnComment}', prop: '${d.columnName}' <#if d.required ?? && d.required=='true'>,required: true</#if>  <#if d.dataType==''>,type: 'input'</#if><#if d.dataType=='datePicker'>,type: 'datePicker'</#if> <#if d.dataType == 'text'>,type: 'input'</#if><#if d.dataType == 'inputNumber'>,type: 'inputNumber'</#if><#if d.dataType == 'autocomplete'>,type: 'autocomplete',dataSource: [],modelData: '',dataField: 'text'<#if d.changeEvent??>,callMethods: '${d.changeEvent}'</#if></#if> },
            </#if>
        </#list>
      ],
      formInfo: {formName: 'modelData'},
      modelData: {<#list developments as d><#if d.dataType == 'autocomplete'>${d.columnName}:''</#if></#list>},
      op: 'create'
    }
  },
  methods: {
    getModelDataInfo (id) {
      this.$post('${controllerSrc}/' + id + '/getDataInfoById').then((res) => {
        this.modelData = res.other.modelData
        this.op = 'update'
      })
    },
    saveEditInfo (formName) {
      this.$refs.editform.$refs[formName].validate((valid) => {
        if (valid) {
          var data = JSON.parse(JSON.stringify(this.modelData))
          this.$post('${controllerSrc}/' + this.op, data, {headers: {'Content-Type': 'application/json'}})
            .then((res) => {
              if (res.status) {
                this.$successMsg(res.msg)
                this.modelData = res.other.entity
                this.op = 'update'
              } else {
                this.$errorMsg(res.msg)
              }
            })
        }
      })
    }
<#list developments as d>
    <#if d.dataType == 'autocomplete'>
    ,
    <#if d.changeEvent ??>
    ${d.changeEvent} (text, vue) {

    },
    </#if>
    getAutocompletDataSourceBy${d.columnName?cap_first} (){
        this.$post('${d.dataSource}', {}, {headers: {'Content-Type': 'application/json'}},false)
                .then((res) => {
            let data = res
            data.forEach(function (item) {
            item['value'] = item.id
        })
        this.columns[${d_index}].dataSource = res
    })
    }
    </#if>
</#list>
  },
  mounted () {
    if (this.$route.query.id !== undefined) {
      this.getModelDataInfo(this.$route.query.id)
    }
<#list developments as d>
    <#if d.dataType == 'autocomplete'>
        this.getAutocompletDataSourceBy${d.columnName?cap_first} ()
    </#if>
</#list>
  }
}
</script>

<style scoped lang="stylus">
</style>
