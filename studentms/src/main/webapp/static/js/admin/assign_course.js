/* 
* @Author: anchen
* @Date:   2017-08-16 16:17:33
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-17 09:23:00
*/

var map = new Map(),
    mapSave = new Map();
$(function(){
    // 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 课程管理 > 分配课程',
        iconCls: 'icon-page'
    });
    $('.top_div').panel({
        
        title: '专业'
    });
    $('#semester').combobox({
        onSelect: function(rec){

            var checkboxs = $('.right_div input:checkbox');
            if(checkboxs.length !== 0){
                for(var i = 0; i < checkboxs.length; i ++){
                    $(checkboxs.get(i)).parent('label.course_label').remove();
                }
            }
        }
    });
    $('#add_a').linkbutton({
        iconCls: 'icon-arrow_right',
        iconAlign: 'top',

    });
    $('#del_a').linkbutton({
        iconCls: 'icon-arrow_left',
        iconAlign: 'top',
    
    });
    
    $('#asign_save').click(function(event) {
        var checkboxs = $('.right_div input:checkbox'),
            
            json = [],
            semester = $('#semester').val(),
            departId = $('#depart_id').val();

        for(var i = 0; i < checkboxs.length; i ++){
            var obj = {};
        	obj.id = checkboxs.get(i).value;
            json.push(obj);
        }
        json = JSON.stringify(json);
        // arr便是选中课程的id数组  想办法把系的id也加上
        $.post(ctx+'/courseAssign/addCourseAssign', {ids: json, semester: semester, departId: departId}, function(data) {
            var result = JSON.parse(data);
            if(result.success){
            	$.messager.show({
            		title: '提示',
            		msg: '保存成功'
            	})
            }else{
            	$.messager.show({
            		title: '提示',
            		msg: '保存失败'
            	})
            }
        });
        

    });

    $('#asign_cancle').click(function(event) {
        // 做下取消的返回跳转
        // location.href="";                
    });
    $('#dg').datagrid({
        url: ctx+'/course/showAssignCourse',
        height: 500,
        method: 'post',
        fitColumns:true,
        striped: true,
        rownumbers: true,
        pagination: true,
        singleSelect: false,
        selectOnCheck: true,
        checkOnSelect: true,
        columns: [[
            {field: 'ck', checkbox: true},
            {field:'courseName', title: '课程名',width:100},
            {field: 'schoolName', title: '开课学院',width:100}
        ]],
        toolbar: '#toobar'
//        data: [{
//            id: 1,
//            course_name: 'C语言编程',
//            college_name: '计算机学院'
//        },{
//            id: 2,
//            course_name: 'C#语言编程',
//            college_name: '计算机学院'
//        },{
//            id: 3,
//            course_name: 'C++语言编程',
//            college_name: '计算机学院'
//        }]
        
    });
    // 将左边的课程移动至右边
    $('#add_a').click(function(event) {
        var checkedItems = $('#dg').datagrid('getChecked'),
            html = '';
        $.each(checkedItems, function(index, item){
            if(!map.get(item.courseId)){
                map.set(item.courseId, item.courseName);
                console.log(map);
                html += "<label class='course_label'><input type='checkbox' name='course' value="+item.courseId+">"+item.courseName+"</label>";   
            }                    
        });                
        $('.right_div span').append(html);
        
    });
    // 将右边的选中的课程删除
    $('#del_a').click(function(event) {

        var checkboxs = $('.right_div input:checked'),
            item;
        if(checkboxs.length === 0){
            return ;
        }else{
            
            for(var i = 0; i < checkboxs.length; i ++){
                item = checkboxs.get(i);
                map.delete(parseInt(item.value));
                console.log(map.delete(parseInt(item.value)));
                console.log(map);
                $(item).parent('label.course_label').remove();
            }
        }

        
    });
});  
function doSearch(){
	var course_name = $('#course_name').val();
	$('#dg').datagrid('load',{
		course_name: course_name
	});
}