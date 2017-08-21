<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/home/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/view/home/common/include_css.jsp"></jsp:include>

</head>
<body>
    <table id="dg"></table>
    <div id="toolbar" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            <a href="#" onclick="enterMark()" class="easyui-linkbutton" iconCls="icon-add" plain="true" >录入成绩</a>
        </div>
        <div>
            录入时间: <input class="easyui-datebox" editable="false">
            到: <input class="easyui-datebox" editable="false">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
            班级:<input id="class_combobox">
        </div>
        
    </div>
    <!-- 这里班级虽然和工具栏中的 班级java代码一样 但是不要同个id 建议再写一遍combobox -->
    <div id="add_dlg" class="dlg">
        <form id="add_fm" class="fm" method="post">
            <div class="fitem">
                <label >课程名:</label>
                <input name="" >
            </div>
            <div class="fitem">
                <label >班级:</label>
                <input name="" >
            </div>
            <div class="fitem">
                <label >文件:</label>
                <input name="" >
            </div>
            <div class="fitem">
                <label >备注:</label>
                <input name="" >
            </div>
        </form>                
    </div>
</body>
<jsp:include page="/WEB-INF/view/home/common/include_js.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/static/js/home/teacher_mark.js"></script>

</html>