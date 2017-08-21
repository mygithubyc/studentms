/* 
* @Author: anchen
* @Date:   2017-08-15 14:37:05
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-15 14:56:14
*/

$(function(){
    var menuTree = $('#tt');
    // 右上角登出按钮
    $('#logout_span').linkbutton({
        iconCls: 'icon-head_ico',
        iconAlign: 'right',
        onClick: logout,
    });
    // 菜单树美化
    menuTree.tree({
        animate: true,
        lines: true
    });
    // 展开 收齐菜单树
    $('#collapseAll_a').click(function(event) {
        
        menuTree.tree('collapseAll');
    });
    $('#expandAll_a').click(function(event) {
        menuTree.tree('expandAll');
    });
});
// 登出方法
function logout(){
    
    $.messager.confirm('提示','确认要登出系统?',function(r){
        if (r) {
            // 登出操作  location.href 跳转就行
        	
        }
    });
    
}