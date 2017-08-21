<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/home/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/view/home/common/include_css.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js-modules/easyui/themes/IconExtension.css">
</head>
<body>
    <div class="header">
        <div class="header__wrap">
            <div class="header__logo">
                学生管理平台 欢迎你,xxx 同学/老师
            </div>
            <div class="header__logout">
                登出
            </div>
        </div>
        
    </div>
    <div id="main_content" style="background:#fafafa;padding:5px;width:100%;border:1px solid #ccc">
        <!-- 教师菜单栏   其中仅有班主任才可以显示发布通知按钮 -->
        <span id="teacher_job" class="add_tab_span" url="${ctx }/teacher/job" title="布置作业">布置作业</span>
        <span id="teacher_marks" class="add_tab_span" url="${ctx }/teacher/mark" title="录入成绩">录入成绩</span>  
        <span id="teacher_plan" class="add_tab_span" url="${ctx }/teacher/plan" title="下载教学计划">下载教学计划</span>  
        <span id="teacher_message" onclick="showMessageDlg()">发布通知</span>  
                  
        
        <!-- 教师菜单栏结束 -->

        <!-- 学生菜单栏 -->
        <span id="student_job" class="add_tab_span" url="${ctx }/student/job" title="提交作业">提交作业</span>
        <span id="student_marks" class="add_tab_span" url="${ctx }/student/mark" title="成绩查询">成绩查询</span>   
                   
        
        <!-- 学生菜单栏结束 -->
        <span id="userinfo_menu">个人信息</span> 
        <div id="userinfo_mm" style="width:100px;">
            <div class="complete_userinfo">完善个人信息</div>
            <div class="change_password">修改密码</div>               
        </div>
        <!-- 右边通知提示 学生:考试安排 老师布置作业情况 班主任消息列表 教师:考试安排 作业提交情况-->
        <span id="exam_menu" >考试安排</span>    
        <span id="message_menu" >班主任通知</span>    
        <span id="job_menu">作业情况</span>    
        
        <!-- 考试通知 -->
        <div id="exam_menu_div" class="menu-content my_menu_content">
            <img src="${ctx }/static/img/exam.png" >
            <p><a href="#">2017-2018 上学期 期末考试安排表</a></p><hr>
        </div>
        <!-- 班主任通知  不是班主任的话将100行的表单也if下   红色 紧急通知  橙色 一般通知  黑色 普通通知 -->
        <div id="message_menu_div" class="menu-content my_menu_content" >
            <img src="${ctx }/static/img/message.png" >
            <p class="important_message">最近骗子很多,同学们小心!xxxxxxxxxxxxxxxxxxxx</p><hr>
            <p class="common_message">快考试了,好好复习!</p><hr>
            <p >大家中秋快乐!</p><hr>
        </div>
        <!-- 作业通知 -->
        <div id="job_menu_div" class="menu-content my_menu_content" >
            <img src="${ctx }/static/img/job.png" >
            <p>C语言xxx老师在 2017-08-01 发布了作业 快去看看asddddddd</p> <hr>
            <p>D语言xxx老师在 2017-08-01 发布了作业 快去看看</p> <hr>
            <p>E语言xxx老师在 2017-08-01 发布了作业 快去看看</p> <hr>
        </div>
        <!-- easyui tabs -->
        <div id="tt" class="easyui-tabs" style="width:100%;height:820px;">
            <div title="Home">
                <div  style="margin-left:300px;margin-top: 100px;">
                    <img src="${ctx }/static/img/welcome.png"  alt="" >
                </div>
            </div>
        </div>

    </div>
    <!-- 密码修改表单 -->
    <div id="password_dlg" class="dlg">
        <div class="ftitle">修改密码</div>
        <form id="password_fm" class="fm" method="post">
            <div class="fitem">
                <label >旧密码:</label>
                <input name="" >
            </div>
            <div class="fitem">
                <label >新密码:</label>
                <input name="" >
            </div>
            <div class="fitem">
                <label >确认密码:</label>
                <input name="" >
            </div>
            
        </form>
    </div>
    <!-- 班主任发布通知表单 -->
    <div id="message_dlg" class="dlg">
        <div class="ftitle">班主任发布通知</div>
        <form id="message_fm" class="fm" method="post">
            <div class="fitem">
                <label >内容:</label>
                <input name="" >
            </div>
            <div class="fitem">
                <label >通知等级:</label>
                <select name="" id="" style="width:140px;">
                    <option value="1">紧急通知</option>
                    <option value="2">一般通知</option>
                    <option value="3">更一般通知</option>
                </select>
            </div>
            <div class="fitem">
                <label >过期时间:</label>
                <input name="" class="easyui-datebox" editable="false">
            </div>
            <div class="fitem">
                <label >附件:</label>
                <input name="" >
            </div>
            
        </form>
    </div>
    <!-- 完善个人信息 -->
    <div id="userinfo_dlg" class="dlg">
        <div class="ftitle">个人信息完善</div>
        <form id="userinfo_fm" class="fm" method="post">
            <table cellPadding="10px" cellSpacing="5px">
                <tr>
                    <td align="right">姓名:</td>
                    <td><input type="text"></td>
                    <td align="right">性别:</td>
                    <td><input type="text"></td>
                    <td>上传照片(3:2):</td>
                    <td><input type="text"></td>
                </tr>
                
                <tr>
                    <td align="right">电话:</td>
                    <td><input type="text"></td>
                    <td align="right">邮箱:</td>
                    <td><input type="text"></td>
                    <td colspan="2" rowspan="2">
                        <img src="${ctx }/static/img/zhaopian.jpg" alt="" id="user_image">
                    </td>
                </tr>
                <tr>
                    <td align="right">身份证号:</td>
                   <td colspan="3">
                       <input type="text" style="width: 100%">
                   </td> 
                </tr>
                <tr>
                    <td>家庭住址:</td>
                </tr>
                <tr>
                    <td><input type="text"></td>
                    <td>省</td>
                    <td><input type="text"></td>
                    <td>市</td>
                    <td><input type="text"></td>
                    <td>县</td>
                </tr>
                <tr>
                    <td align="right">详细地址:</td>
                    <td colspan="3">
                       <input type="text" style="width: 100%">
                    </td> 
                </tr>
                <tr>
                    <td align="right">家庭地址详情:</td>
                    <td colspan="3">
                       <input type="text" style="width: 100%">
                    </td> 
                </tr>
                <tr>
                    <td align="right">学院:</td>
                    <td><input type="text"></td>
                    <td align="right">系:</td>
                    <td ><input type="text"></td>
                    <td align="right">所在班级/所带班级:</td>
                    <td><input type="text"></td>                        
                </tr>
            </table>
            
        </form>
    </div>
</body>
<jsp:include page="/WEB-INF/view/home/common/include_js.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/static/js/home/main.js"></script>
</html>