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
            学年: <input >
            学期: 
            <select id="">
                <option value="1">上学期</option>
                <option value="2">下学期</option>
            </select>
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
        </div>
        
    </div>
</body>
<jsp:include page="/WEB-INF/view/home/common/include_js.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/static/js/home/teacher_plan.js"></script>
</html>