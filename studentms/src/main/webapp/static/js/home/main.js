/* 
* @Author: anchen
* @Date:   2017-07-25 11:03:37
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-21 11:28:30
*/

$(function(){
    var addTabButton = $('#main_content .add_tab_span'),
        logoutDiv = $('.header__logout'),
        teacher_job = $('#teacher_job'),
        teacher_marks = $('#teacher_marks'),
        teacher_plan = $('#teacher_plan'),
        teacher_message = $('#teacher_message'),
        teacher_info_menu = $('#teacher_info'),
        student_job = $('#student_job'),
        student_marks = $('#student_marks'),
        student_info_menu = $('#student_info'),
        exam_menu = $('#exam_menu'),
        message_menu = $('#message_menu'),
        job_menu = $('#job_menu');
    console.log(addTabButton);
    // 右上角点击登出操作
    logoutDiv.click(function(event) {
        $.messager.confirm('Confirm','确定要登出?',function(r){
            if (r) {
                // console.log('登出');
                // 登出操作
            }
        });
    });
    // 老师菜单栏美化
    $(teacher_job).linkbutton({
        iconCls: 'icon-add',
        plain: true
    });
    $(teacher_marks).linkbutton({
        iconCls: 'icon-2012080412511',
        plain: true
    });
    $(teacher_plan).linkbutton({
        iconCls: 'icon-book_open_mark',
        plain: true
    });
    $(teacher_message).linkbutton({
        iconCls: 'icon-email',
        plain: true
    });
    
    // 老师菜单美化栏结束

    $(student_job).linkbutton({
        iconCls: 'icon-book_edit',
        plain: true
    });
    $(student_marks).linkbutton({
        iconCls: 'icon-book_open_mark',
        plain: true
    });
    // 共用个人信息菜单
    $('#userinfo_menu').menubutton({
        iconCls: 'icon-man',
        menu: '#userinfo_mm'
    });
    // 右边消息提示菜单
    
    exam_menu.menubutton({
        iconCls: 'icon-date',
        noline: true,
        menu: '#exam_menu_div',
    });
    message_menu.menubutton({
        iconCls: 'icon-email_magnify',
        noline: true,
        menu: '#message_menu_div'
    });
    job_menu.menubutton({
        iconCls: 'icon-note',
        noline: true,
        menu: '#job_menu_div'
    });
    // 跳出修改密码dlg
    $('.change_password').click(function(event) {
        $('#password_dlg').dialog('open');
    });
    // 跳出修改密码dialog
    $('.complete_userinfo').click(function(event) {
        $('#userinfo_dlg').dialog('open');
    });
    // 修改密码表单   buttons为表单下俩个按钮   
    $('#password_dlg').dialog({
        modal: true,
        closed: true,
        buttons:[{
            text: '提交',
            iconCls: 'icon-ok',
            handler: function(){
                // 这里就是点击提交按钮的事件
                alert('提交修改密码');
            }
        },{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function(){
                $('#password_dlg').dialog('close');
            }
        }]
    });
    // 班主任发布通知表单   buttons为表单下俩个按钮   
    $('#message_dlg').dialog({
        modal: true,
        closed: true,
        width: 350,
        buttons:[{
            text: '发布通知',
            iconCls: 'icon-ok',
            handler: function(){
                // 这里就是点击提交按钮的事件
                alert('提交发布通知');
            }
        },{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function(){
                $('#message_dlg').dialog('close');
            }
        }]
    });
    // 个人信息完善表单
    $('#userinfo_dlg').dialog({
        modal: true,
        closed: true,
        buttons:[{
            text: '保存',
            iconCls: 'icon-ok',
            handler: function(){
                // 这里就是点击提交按钮的事件
                alert('保存个人信息');
            }
        },{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function(){
                $('#userinfo_dlg').dialog('close');
            }
        }]
    })
    
    // 给菜单按钮绑定添加tab的点击事件
    addTabButton.map(function(){
        var title = $(this).attr('title'),
            url = $(this).attr('url');
        $(this).click(function(event) {
            console.log(title+'/'+url);
            addTab(title, url);
        });
    }).get(0);
    // addTabButton[1].click();
    // console.log(addTabButton);
    

});

// 增加同浏览器效果的页中页
// 用法     addTab("标题", "http://www.baidu.com");   写在$(function(){})中
function addTab(title, url){
    if ($('#tt').tabs('exists', title)) {
        $('#tt').tabs('select', title);
    }else{
        var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
        $('#tt').tabs('add',{
            title: title,
            content: content,
            closable: true
        });
    }

}
function showMessageDlg(){
    $('#message_dlg').dialog('open');
    $('#message_fm').form('clear');
}
// 帮你封装的 alert   也可以传html
// uiAlert('这几天骗子很多,同学们小心!');
function uiAlert(msg){
    $.messager.alert('班主任紧急通知',msg);
}

// easyui show封装
// uiShow('好好复习,准备考试.');
function uiShow(msg){
    $.messager.show({
        title: '班主任温馨提醒',
        msg: msg
    });
}