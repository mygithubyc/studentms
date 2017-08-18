/* 
* @Author: anchen
* @Date:   2017-08-15 15:20:59
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-15 15:21:15
*/

$(function(){
    // 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页',
        iconCls: 'icon-page'
    });
    // // 首页显示系统信息
    $('.content_div').panel({
        title: '系统登录信息',
        width: 800,
    });

});