<template>
    <grid
            :data="gridData"
            :columns="gridColumns"
            :check="check"
            :requestInfo="requestInfo"
            :command="command"
            :toolbar="toolbar"
            v-on:callGetInfo="callGetInfo"
            v-on:editInfo="editInfo"
            v-on:create="create"
            v-on:deleteInfo="deleteInfo"
            ref="grid"
    >
    </grid>
</template>

<script>

    import Grid from '@/components/grid'

    export default {
        components: {Grid},
        name: 'list${htmlName?cap_first}',
        data () {
            return {
                // 请求信息
                requestInfo: {
                    // 刷新页面的回调方法
                    'callMethod': 'callGetInfo',
                    // 每页大小
                    'pageSize': 18,
                    // 数据初始化完成后的回调
                    'dataBound': 'onDataBound'
                },
                // 列信息
                gridColumns: [
                    <#list developments as d>
                    <#if (developments?size-1 == d_index)>
                    {name: '${d.columnName}', title: '${d.columnComment}' <#if d.toolTip?? && d.toolTip == "true"> , tooltip: true</#if> <#if d.template??>, format:${d.template}</#if> <#if d.width??>,width:${d.width}</#if>}
                        <#else >
                    {name: '${d.columnName}', title: '${d.columnComment}' <#if d.toolTip?? && d.toolTip == "true"> , tooltip: true</#if> <#if d.template??>, format:${d.template}</#if> <#if d.width??>,width:${d.width}</#if>},
                    </#if>
                    </#list>
                ],
                // 列数据
                gridData: {},
                // 列按钮信息
                command: [
                    {name: 'opEdit', text: '<span class="el-icon-edit"></span>', tooltip: '编辑', click: 'editInfo'},
                    {name: 'opDelete', text: '<span class="el-icon-close"></span>', tooltip: '删除', click: 'deleteInfo'}
                ],
                // 自定义事件回调方法
                callMethods: {'editInfo': 'editInfo'},
                // 是否显示复选框
                check: true,
                // 上方工具条
                toolbar: [
                    {name: 'opCreate', text: '新建', click: 'create', type: 'primary'}
                ]
            }
        },
        methods: {
            callGetInfo (page, filter) {
                const param = {}
                if (page === undefined) {
                    page = 1
                }
                param['page'] = page
                param['pageSize'] = this.requestInfo.pageSize
                param['filter'] = filter
                this.$post('${controllerSrc}/loadData', param, {headers: {'Content-Type': 'application/json'}})
                        .then((res) => {
                    this.gridData = res
            })
            },
            editInfo (item) {
                this.$router.push({path: '${controllerSrc}/edit${htmlName ?capitalize}', query: {id: item.id}})
            },
            create (checkedInfo) {
                this.$router.push('${controllerSrc}/edit${htmlName ?capitalize}')
            },
            deleteInfo (item) {
                this.$delete('${controllerSrc}/' + item.id + '/deleteById').then((res) => {
                    if (res.status) {
                    this.callGetInfo(this.$refs.grid.$refs.pagination._data.internalCurrentPage, this.$refs.grid._data.filter)
                }
            })
            }
        },
        mounted () {
            this.callGetInfo(1)
        }
    }
</script>
<style scoped lang="stylus">

</style>
