<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看提交</title>
	<jsp:include page="/WEB-INF/view/common/include_css.jsp"></jsp:include>
  </head>
  
  <body>
    <table id="dg_submit" title="提交作业" class="easyui-datagrid" style="width:100%;height:800px;margin-left:0;"  url="${ctx }/comJob/getComJob" toolbar="#toolbar_submit" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
            <thead>
                <tr>
                    <th field="title" width="50px">标题</th>
                    <th field="content" width="50px">内容</th>
                    <th field="username" width="50px">学生</th>
                    <th field="uploadTime" width="50px" sortable="true" data-options={formatter:myformater}>提交日期</th>
                    
                    <th field="path" width="50px">文件名</th>
                    
                </tr>
            </thead>
        </table>
        <div id="toolbar_submit">
            <div>
                标题:<input type="text" class="easyui-textbox" width="100px" id="title" name="" value="" placeholder="">
                学生: <input class="easyui-textbox" style="width:100px" id="studentName" >
                
                <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
            </div>
           

            
        </div>
  </body>
  	<jsp:include page="/WEB-INF/view/common/include_js.jsp"></jsp:include>
	<script type="text/javascript"
	src="${ctx }/static/js/homework_submit.js"></script>
</html>
