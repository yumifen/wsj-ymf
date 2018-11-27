<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>水晶米粉 | S级管理平台
        <sitemesh:write property='title'/>
    </title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="yumifen">
    <meta name="description" content="yumifen">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <link rel="shortcut icon" href="${req.contextPath}/favicon.ico">
    <link href="${req.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${req.contextPath}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet"/>
    <link href="${req.contextPath}/css/animate.css?v=4.4.0" rel="stylesheet"/>
    <link href="${req.contextPath}/css/style.css?v=4.1.0" rel="stylesheet"/>
    <link href="${req.contextPath}/css/plugins/easyUI/easyui.css?v=4.4.0" rel="stylesheet"/>
    <link href="${req.contextPath}/css/plugins/toastr/toastr.min.css?v=4.4.0" rel="stylesheet">
    <link href="${req.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <!--jquery全家桶-->
    <script src="${req.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${req.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${req.contextPath}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${req.contextPath}/js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="${req.contextPath}/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${req.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="${req.contextPath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!--bootstrap-->
    <script src="${req.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>

    <script src="${req.contextPath}/js/hplus.js"></script>
    <script src="${req.contextPath}/js/contabs.js" type="text/javascript"></script>

    <script src="${req.contextPath}/js/plugins/easyUI/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${req.contextPath}/js/plugins/layer/layer.min.js"></script>
    <script src="${req.contextPath}/js/plugins/toastr/toastr.min.js" type="text/javascript"></script>

    <!-- VUE js 文件-->
    <script src="${req.contextPath}/js/vue.min.js" type="text/javascript"></script>
    <!-- Require js文件 -->
    <script src="${req.contextPath}/js/require.js" type="text/javascript"></script>

    <script src="${req.contextPath}/js/wsj/config.js" type="text/javascript"></script>

    <script src="${req.contextPath}/js/wsj/grid.js" type="text/javascript"></script>

</head>
<body class="fixed-sidebar full-height-layout gray-bg">
<sitemesh:write property='body' />
 <!--自定一的js文件-->
 <script src="${req.contextPath}/js/wsj/wsjUtils.js" type="text/javascript"></script>
 <script src="${req.contextPath}/js/slife/toast.js" type="text/javascript"></script>
</body>
<sitemesh:write property='head' />

<#--<script type="text/html" id="pagegation-template-app">-->
    <#--<div class="btn-group">-->
        <#--<button class="btn btn-white" type="button"  data-bind="visible: leftTag ,click: $root.fristPage"><i class="fa fa-chevron-left"></i></button>-->

        <#--<button class="btn btn-white " data-bind="css:$root.currentPage()== $data?'active':'',text: $data,visible: $data>0,click: $root.goPage.bind()">2</button>-->

        <#--<button class="btn btn-white" type="button" data-bind="visible: rightTag,click: $root.lastPage"><i class="fa fa-chevron-right"></i></button>-->
    <#--</div>-->
<#--</script>-->
</html>
