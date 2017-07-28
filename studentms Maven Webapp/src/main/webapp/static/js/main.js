/* 
* @Author: anchen
* @Date:   2017-07-25 11:03:37
* @Last Modified by:   anchen
* @Last Modified time: 2017-07-25 15:43:51
*/

$(document).ready(function(title, url){
    var addTabButton = $('div#mm1>div.menu-item,div#mm2>div.menu-item'),
        logoutDiv = $('.header__logout');
    logoutDiv.click(function(event) {
        $.messager.confirm('Confirm','确定要登出?',function(r){
            if (r) {
                // console.log('登出');
                // 登出操作
            }
        })
    });
    // 给菜单按钮绑定添加tab的点击事件
    addTabButton.map(function(){
        var title = $(this).attr('title'),
            url = $(this).attr('url');
        $(this).click(function(event) {
            console.log(title+'/'+url);
            addTab(title, url);
        });
    }).get(0);
//    addTabButton[2].click();
    // console.log(addTabButton);

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
});