<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/admin/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/view/admin/common/include_css.jsp"></jsp:include>
</head>
<body>
    <div class="easyui-layout">
        <div data-options="region:'north'" class="layout__north" style="height: 90px;">
            <div class="layout__north__left">
                高校教学管理系统
            </div>
            
            <div class="layout__north__right">
                当前管理员:<span>admin</span>
            </div>
        </div>
        <div data-options="region: 'west', title: '管理菜单' ,split: true" class="layout__west" style="width: 180px;">
            <a  id="expandAll_a" >展开</a>|<a id="collapseAll_a">关闭</a>
            <ul id="tt" class="easyui-tree">
               <li>
                   <span>学生管理</span>
                   <ul>
                       
                       <li><span><a href= "${ctx }/student/add">录入学生信息</a></span></li>
                       <li><span><a>维护学生信息</a></span></li>
                   </ul>
               </li>
               <li>
                   <span>老师管理</span>
                   <ul>
                       <li><span><a href="${ctx }/teacher/add">录入教师信息</a></span></li>
                       <li><span><a>维护教师信息</a></span></li>
                   </ul>
               </li>
               <li>
                   <span>班级管理</span>
                   <ul>
                       <li><span><a href="${ctx }/class/add">添加班级</a></span></li>
                       <li><span><a>维护班级信息</a></span></li>
                   </ul>
               </li>
               <li>
                   <span>院系管理</span>
                   <ul>
                       <li><span><a href="${ctx }/school/add">添加学院</a></span></li>
                       <li><span><a href="${ctx }/department/add">添加院系</a></span></li>
                       <li><span><a href="${ctx }/school/department">维护院系信息</a></span></li>
                   </ul>
               </li>
               <li>
                   <span>课程管理(教学计划)</span>
                   <ul>
                       <li><span><a href="${ctx }/course/add">录入课程信息</a></span></li>
                       <li><span><a>维护课程信息</a></span></li>
                       <li><span><a href="${ctx }/course/assignment">专业(系)课程配置</a></span></li>
                       <li><span><a href="${ctx }/course/teachingPlan">安排教学计划</a></span></li>
                   </ul>
                   
               </li>
               <li>
                   <span>成绩管理</span>
                   <ul>
                       <li><span><a>维护成绩信息</a></span></li>
                   </ul>
               </li>
               <li>
                   <span>考试管理</span>
                   <ul>
                       <li><span><a href="${ctx }/admin/exam">维护考试安排文件</a></span></li>
                   </ul>
               </li>
               <li>
                   <span>系统管理</span>
                   <ul>
         
                       <li><span><a href="${ctx }/admin/loginInfo">查看登录记录</a></span></li>
                   </ul>
               </li>
               <li>
                   <span><a onclick="logout()">退出系统</a></span>
               </li>
           </ul>
        </div>
        <div data-options="region: 'center', title: '首页', split: true" class="layout__center" style="height: 80px;padding: 0">
        	<table id="dg"></table>
	        <div id="toolbar">
	            <a href="#" onclick="newExam()" class="easyui-linkbutton" iconCls="icon-add" plain="true">发布考试安排</a>
	            <a href="#" onclick="editExam()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改考试安排</a>
	            <a href="#" onclick="removeExam()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除安排</a>
	        </div>
	        <div id="dlg" class="dlg">
	            <form id="fm" class="fm" method="post">
	                <div class="fitem">
	                    <label >说明:</label>
	                    <input name="" class="easyui-textbox" data-options="multiline: true,required: true,height:80">
	                </div>
	                <div class="fitem">
	                    <label >安排文件:</label>
	                    <input name="" class="easyui-filebox">
	                </div>
	                
	            </form>
	        </div>
        </div>
    </div>
    
 </body>
 
 <jsp:include page="/WEB-INF/view/admin/common/include_js.jsp"></jsp:include>
 <script src="${pageContext.request.contextPath}/static/js/admin/exam_show.js"></script>
 </html>