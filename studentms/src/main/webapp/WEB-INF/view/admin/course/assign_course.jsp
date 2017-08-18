<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/admin/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/view/admin/common/include_css.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/assign_course.css">

</head>
<body>
    <div class="easyui-layout">
        <div data-options="region:'north'" class="layout__north" style="height: 90px;">
            <div class="layout__north__left">
                高校教学管理系统
            </div>
            
            <div class="layout__north__right">
                当前管理员:<span>admin</span><span id="logout_span">登出</span>
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
                       <li><span><a href="#">维护院系信息</a></span></li>
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
                   <span>系统管理</span>
                   <ul>
                       <li><span><a>维护管理员信息</a></span></li>
                       <li><span><a>修改密码</a></span></li>
                   </ul>
               </li>
               <li>
                   <span><a onclick="logout()">退出系统</a></span>
               </li>
           </ul>
        </div>
        <div data-options="region: 'center', title: '首页', split: true" class="layout__center" style="height: 80px;padding: 0;">
            <div class="top_div">
                当前专业: <span style="color: red;">市场营销</span>
                <select name="" id="semester">
                    <option value="1">第一学期</option>
                    <option value="2">第二学期</option>
                    <option value="3">第三学期</option>
                    <option value="4">第四学期</option>
                    <option value="5">第五学期</option>
                    <option value="6">第六学期</option>
                    <option value="7">第七学期</option>
                    <option value="8">第八学期</option>
                    <option value="9">第九学期</option>
                    <option value="10">第十学期</option>
                    <option value="11">第十一学期</option>
                    <option value="12">第十二学期</option>
                    <option value="13">第十三学期</option>
                    <option value="14">第十四学期</option>
                    <option value="15">第十五学期</option>
                    <option value="16">第十六学期</option>
                </select>
                <span>
                    提示:请尽可能按照学期顺序添加
                </span>
            </div>
            <div class="left_div">
                <table id="dg"></table>
            </div>
            <div class="add_div">
                <a  id="add_a">添加</a>
            </div>
            <div class="del_div">
                <a  id="del_a">删除</a>
            </div>
            <div class="right_div">
                <span></span>
            </div>
            <div class="bottom_div">
                <span id="asign_save">保存</span> <span id="asign_cancle">取消</span>
            </div>
            
        </div>
        
    </div>
</body>
<jsp:include page="/WEB-INF/view/admin/common/include_js.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/static/js/admin/assign_course.js"></script>
</html>