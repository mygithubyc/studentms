<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/common/js-modules/easyui/themes/bootstrap/easyui.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/common/js-modules/easyui/themes/icon.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/common/css/home/base.css" />
    </head>
    <body>
        <table id="dg"></table>
    </body>
    <script src="${pageContext.request.contextPath }/static/common/js-modules/easyui/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/common/js-modules/easyui/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/common/js-modules/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script>
        $('#dg').datagrid({

        });
    </script>
</html>