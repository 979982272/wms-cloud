<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
${'<#include '}"/common/head.html"${'/>'}
</head>
<body style="overflow: hidden">
<!-- SPA container define -->
<div id="application"></div>
<!-- loyout template define -->
<script id="layout-template" type="text/x-kendo-template">
    <div id="content"></div>
</script>
<!-- edit view template define -->
<script id="${htmlName}Form-template" type="text/x-kendo-template">
    <div data-role="toolbar">
        <div style="padding-left: 15px">
            <button name="save${htmlName?capitalize}Info" class="k-button" type="button" data-role="button"
                    role="button" id="save${htmlName?capitalize}Info"
                    data-bind="events: {click: save${htmlName?capitalize}Info}"><span
                    class="fa fa-save"></span>保存
            </button>
            <button name="back" class="k-button" type="button" data-role="button"
                    role="button"
                    data-bind="events: {click: back}"><span
                    class="fa fa-mail-reply"></span>返回
            </button>
        </div>
    </div>
    <div id="${htmlName}Form-pane" style="overflow: auto;" data-bind="style: {height: contentHeight}">
        <form id="${htmlName}Form" style="padding: 15px;">
            <div class="row">
            <#list developments as d>
                <div class="col-md-3 col-sm-3 col-xs-3">
                    <#if d.dataType="checkbox">
                        <input type="checkbox" name="${d.columnName}Check" class="k-checkbox" data-type="ck"
                               id="${d.columnName}Check" data-bind="value:${htmlName}ModelData.${d.columnName}">
                        <label for="${d.columnName}Check" class="k-checkbox-label"></label>
                    <#else>
                        <label for="${d.columnName}"
                               class="top-label <#if d.required=="Y">required</#if>">${d.columnComment}：</label>
                        <#if d.dataType == "numerictextbox">
                            <input id="${d.columnName}" class="<#if d.required=="Y">validate[required]</#if>"
                                   data-bind="value:${htmlName}ModelData.${d.columnName}"
                                   data-role="numerictextbox" <#if d.readOnly == "Y">readonly</#if>>
                        <#elseif d.dataType == "dropdownlist">
                            <input id="${d.columnName}" data-role="dropdownlist" data-value-field="id"
                                   data-text-field="text"
                                   class=" <#if d.required=="Y">validate[required]</#if>"
                                   data-bind="value:${htmlName}ModelData.${d.columnName},source:${d.columnName}DataSource"
                                   data-value-primitive="true"
                                   data-auto-bind="true" data-filter="contains"
                                    >
                        <#elseif d.dataType == "combobox">
                            <input id="${d.columnName}" data-role="combobox" data-value-field="id"
                                   data-text-field="text"
                                   data-bind="value:${htmlName}ModelData.${d.columnName},source:${d.columnName}DataSource"
                                   data-value-primitive="true"
                                   data-auto-bind="true" class="<#if d.required=="Y">validate[required]</#if>"
                                    >
                        <#elseif d.dataType == "datepicker">
                            <input id="${d.columnName}" class="<#if d.required=="Y">validate[required]</#if>"
                                   data-bind="value:${htmlName}ModelData.${d.columnName}"
                                   <#if d.readOnly == "Y">readonly</#if>  data-role="datepicker"
                                    >
                        <#else>
                            <input id="${d.columnName}" class="k-textbox <#if d.required=="Y">validate[required]</#if>"
                                   data-bind="value:${htmlName}ModelData.${d.columnName}"
                                   <#if d.readOnly == "Y">readonly</#if>
                                    >
                        </#if>
                    </#if>
                </div>
            </#list>
            </div>
        </form>
    </div>
</script>
</body>
<script>
    // 数据模型
    var ${htmlName}FormModel = kendo.observable({
        ${htmlName}ModelData: {},
        op: "create",
        contentHeight: document.documentElement.clientHeight - 37 + "px",
        reset: function () {
            var that = this;
            that.set("${htmlName}ModelData", {});
        },
        save${htmlName?capitalize}Info: function () {
            var that = this;
            if ($("#${htmlName}Form").validationEngine("validate")) {
                var req = {};
                var info;
                info = jQuery.extend(true, {}, that.get("${htmlName}ModelData"));
                $.extend(req, info);
                $.core.ajax({
                    url: "${controllerSrc}/" + that.op,
                    type: "post",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(req),
                    success: function (res) {
                        if (res.status) {
                            that.set("op", "update");
                            that.set("${htmlName}ModelData",res.other.entity)
                            $.core.showMsg(res.msg)
                        } else {
                            $.core.showErrogMsg(res.msg);
                        }
                    }
                })
            }
        },
        back: function () {
            history.back(-1);
        }
    <#list developments as d>
        <#if d.dataSource ??>
            , ${d.columnName}DataSource: getDropDownDataSource('${d.dataSource}')
        </#if>
    </#list>
    });
    // 初始化
    function init${htmlName?capitalize}Form() {
        var id = '${'$'}${'{id}'}';
        if (isNotEmpty(id)) {
            readOnlyById("id")
            ${htmlName}FormModel.op = "update";
            getDataInfoById("${controllerSrc}/" + id + "/getDataInfoById", function (res) {
                if (res.status) {
                    ${htmlName}FormModel.set("${htmlName}ModelData", res.other.modelData);
                } else {
                    $.core.showErrogMsg(res.msg);
                }
            });
        } else {
            ${htmlName}FormModel.op = "create";
        }
    }
    //基本信息页面
    var ${htmlName}FormView = new kendo.View("${htmlName}Form-template", {
        model: ${htmlName}FormModel, init: init${htmlName?capitalize}Form
    });
    // Routing
    var router = new kendo.Router();
    // Layout
    var layout = new kendo.Layout("layout-template");
    // 渲染
    layout.render("#application");
    // 基本信息
    router.route("/", function () {
        layout.showIn("#content", ${htmlName}FormView);
    });
    $(function () {
        //开户路由
        router.start();
    });
</script>