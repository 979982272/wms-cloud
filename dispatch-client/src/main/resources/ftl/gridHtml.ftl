<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
${'<#include '}"/common/head.html"${'/>'}
</head>
<body style="overflow: hidden">
<div id="${htmlName}Grid" style="height: 100%"></div>
</body>
<script>
    var gridName = "${htmlName}Grid";
    $(function () {
        setGrid();
        setGridDataSource();
    });
    // 初始化表格
    function setGrid() {
        $("#" + gridName).kendoGrid({
                    toolbar: [
                        {
                            template: "<button id=\"opCreare${htmlName?capitalize}\" name=\"opCreare${htmlName?capitalize}\" class = \"k-button \" type=\"button\" data-role=\"button\" role=\"button\" aria-disabled=\"false\"  onclick=\"creare${htmlName?capitalize}();\"><span class=\"fa fa-plus fa-fw\"><\/span>创建<\/button>",
                            name: "opCreare${htmlName?capitalize}",
                            text: "新增"
                        }, {
                            template: "<button id=\"opDeleteBatch${htmlName?capitalize}\" name=\"opDeleteBatch${htmlName?capitalize}\" class = \"k-button \" type=\"button\" data-role=\"button\" role=\"button\" aria-disabled=\"false\"  onclick=\"deleteBatch${htmlName?capitalize}();\"><span class=\"fa fa-remove fa-fw\"><\/span>删除<\/button>",
                            name: "opDeleteBatch${htmlName?capitalize}",
                            text: "删除"
                        }
                    ],
                    pageable: {
                        pageSizes: [20, 50, 100, 500],
                        pageSize: 20,
                        refresh: true
                    },
                    sortable: {mode: "multiple", allowUnsort: true},
                    allowCopy: true,
                    filterable: {
                        mode: "row,menu"
                    },
                    resizable: true,
                    editable: false,
                    reorderable: true,
                    scrollable: {virtual: false},
                    columns: [
                        {
                            field: "${htmlName}CkAll",
                            sortable: false,
                            filterable: false,
                            width: "35px",
                            headerTemplate: '<input type="checkbox" class="k-checkbox" id="${htmlName}CkAll"/><label for="${htmlName}CkAll" class="k-checkbox-label" onclick="javascript:if($(\'#${htmlName}CkAll\').is(\':checked\')){$(\'input[name=op${htmlName}Check]\').prop(\'checked\',false);} else {$(\'input[name=op${htmlName}Check]\').prop(\'checked\',true)}"></label>',
                            template: function (e) {
                                return '<input type="checkbox" name="op${htmlName}Check" class="k-checkbox" data-type="ck" id="ck' + e.uid + '"/><label for="ck' + e.uid + '" class="k-checkbox-label" ></label>';
                            }
                        },
                        {
                            title: "操作",
                            width: "80px",
                            command: [{
                                name: "opEdit",
                                text: '<span class="fa fa-pencil fa-fw"></span>',
                                title: "编辑",
                                click: function (e) {
                                    e.preventDefault();
                                    var dataItem = $("#" + gridName).data("kendoGrid").dataItem($(e.currentTarget).closest("tr"));
                                    edit${htmlName?capitalize}Info(dataItem.id);
                                }
                            }, {
                                name: "opDelete",
                                text: '<span class="fa fa-remove fa-fw"></span>',
                                title: "删除",
                                click: function (e) {
                                    e.preventDefault();
                                    var dataItem = $("#" + gridName).data("kendoGrid").dataItem($(e.currentTarget).closest("tr"));
                                    delete${htmlName?capitalize}Info(dataItem.id);
                                }
                            }]
                        },
                    <#list developments as d>
                        {
                            field: "${d.columnName}",
                            width: "${d.width}px",
                            title: "${d.columnComment}"
                            <#if d.toolTip == "Y">
                                , attributes: {
                                style: "white-space: nowrap",
                                tipflag: "hasTooltip",
                                datafield: "${d.columnName}"
                            }
                            </#if>
                            <#if d.template ?? && d.template !=''>
                                , template: function (e) {
                                return ${d.template};
                            }
                            </#if>
                        }
                        <#if (d_has_next)>,</#if>
                    </#list>
                    ],
                    filterable: {
                        mode: "row,menu",
                        messages: messages,
                        operators: {
                            string: operators
                        }
                        ,
                        extra: true
                    }, dataBound: function (e) {
                        gridOnDataBound();
                    }
                }
        )
        ;
        $("#" + gridName).kendoTooltip({
            filter: "td[tipflag='hasTooltip']", position: "top", content: function (e) {
                var field = $(e.target).attr("datafield");
                var dataItem = $("#" + gridName).data("kendoGrid").dataItem(e.target.closest("tr"));
                return dataItem[field];
            }
        }).data("kendoTooltip");
    }
    // 设置数据源
    function setGridDataSource() {
        var data = getGridDataSource("${controllerSrc}/loadData")
        var grid = $("#" + gridName).data("kendoGrid");
        grid.setDataSource(data);
    }

    // 编辑
    function edit${htmlName?capitalize}Info(id) {
        window.location.href = $.core.addToken('${'$'}${'{ctx}'}${controllerSrc}/edit?id=' + id);
    }

    // 删除
    function delete${htmlName?capitalize}Info(id) {
        actionInfo({
            url: '${'$'}${'{ctx}'}${controllerSrc}/' + id + "/deleteById",
            gridName: gridName
        });
    }

    // 创建
    function creare${htmlName?capitalize}() {
        window.location.href = $.core.addToken('${'$'}${'{ctx}'}${controllerSrc}/edit');
    }

    // 批量删除
    function deleteBatch${htmlName?capitalize}() {
        batchActionByIds({
            url: '${'$'}${'{ctx}'}${controllerSrc}/deleteBatchByIds',
            gridName: gridName
        });
    }

</script>