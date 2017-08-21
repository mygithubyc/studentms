/* 
* @Author: anchen
* @Date:   2017-07-25 11:03:37
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-20 18:49:49
*/

$(function(){
    var addTabButton = $('div#mm2>div.menu-item'),
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
    $(teacher_info_menu).menubutton({
        iconCls: 'icon-man',
        menu: '#teacher_info_mm'
    })
    // 老师菜单美化栏结束

    $(student_job).linkbutton({
        iconCls: 'icon-book_edit',
        plain: true
    });
    $(student_marks).linkbutton({
        iconCls: 'icon-book_open_mark',
        plain: true
    });
    $(student_info_menu).menubutton({
        iconCls: 'icon-man',
        menu: '#student_info_mm'
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
    $('.change_password').click(function(event) {
        $('#password_dlg').dialog('open');
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
                alert('提交修改密码表单');
            }
        },{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function(){
                $('#password_dlg').dialog('close');
            }
        }]
    });


    //addTab("布置作业", 'teacher_job.jsp');
    addTab("布置作业",ctx+'/job/publishJob');
    // 给菜单按钮绑定添加tab的点击事件
    // addTabButton.map(function(){
    //     var title = $(this).attr('title'),
    //         url = $(this).attr('url');
    //     $(this).click(function(event) {
    //         console.log(title+'/'+url);
    //         addTab(title, url);
    //     });
    // }).get(0);
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

//


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