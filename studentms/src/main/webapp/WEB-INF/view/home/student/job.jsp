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
    <!-- 工具栏 -->
    <div id="toolbar" style="padding:5px;height:auto">
        <div style="margin-bottom:5px">
            <a href="#" onclick="doJob()" class="easyui-linkbutton" iconCls="icon-add" plain="true">提交作业</a>
            <a href="#" onclick="redoJob()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">重新提交作业</a>
        </div>
        <div >
            老师:<input id="teacher_combobox">
            课程名称:<input style="width:130px" >
            截止日期: <input class="easyui-datebox" editable="false">
            到: <input class="easyui-datebox" editable="false">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
        </div>
        <!-- 提交作业表单 可以与重新提交共用 -->
        <div id="dlg" class="dlg">
            <form id="fm" class="fm" method="post">
                <div class="fitem">
                    <label >课程:</label>
                    <input name="" >
                </div>
                <div class="fitem">
                    <label >标题:</label>
                    <input name="" >
                </div>
                <div class="fitem">
                    <label >说明:</label>
                    <input name="" >
                </div>
                <div class="fitem">
                    <label >截止日期:</label>
                    <input name="" >
                </div>
                <div class="fitem">
                    <label >文件:</label>
                    <input name="" >
                </div>
                
            </form>                
        </div>
        
    </div>
</body>
<jsp:include page="/WEB-INF/view/home/common/include_js.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/static/js/home/student_job.js"></script>
</html>