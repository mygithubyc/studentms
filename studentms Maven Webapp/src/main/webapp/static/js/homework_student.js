/* 
* @Author: anchen
* @Date:   2017-07-25 15:54:30
* @Last Modified by:   anchen
* @Last Modified time: 2017-07-25 15:59:55
*/

function uploadForm(){

    var row = $('#dg_student').datagrid('getSelected');
    if (row) {
        $('#dlg_student').dialog('open').dialog('setTitle','提交作业');
        $('#fm_student').form('load',row);
        url = ctx+'/comJob/submitJob';
    }
}
// upload中的url 来自 uploadForm的url
function upload(){
    console.log('upload');
    $('#fm_student').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
        	var result = JSON.parse(result);
            if (result.success) {
                
                $('#dg_student').datagrid('reload');
                $.messager.show({
                	title: 'Success',
                    msg: '作业提交成功!'
                	
                });
            }else{
            	$('#dlg_student').dialog('close');
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}


//搜索用 
function doSearch(){
  
    var teacherName = $('#teacherName').val();
        formDate = $('#formDate').val();
        toDate = $('#toDate').val();
//        验证日期的合法性    不符合时传empty
    if(!checkDateTime(formDate))	formDate = 'invaild';
    if(!checkDateTime(toDate))	toDate = 'invaild';   
    
//    console.log(teacherName+'/'+formDate+'/'+toDate);
    //  表格的load函数会去访问其属性url status 未提交: 1 已提交:2
    $('#dg_student').datagrid('load',{
        username: teacherName,
        formDate: formDate,
        toDate: toDate
    });


}
//判断格式是否为 mm/dd/yyyy   
function checkDateTime(date){
	date = date.trim()
	var reg = /^(\d{2})\/(\d{2})\/(\d{4})$/;
	var r = reg.exec(date);
	if(r == null) return false;
	r[1] = r[1]-1;
	var d = new Date(r[3],r[1],r[2]);
	
	if(d.getFullYear() != r[3]) return false;
	if(d.getMonth() != r[1]) return false;
	if(d.getDate() != r[2]) return false;
	
	return true;
	
}








var statusFormater = function(value,row,index){
	if(value == 1){
		return '<span color="blue">已提交</span>';
	}else{
		return '<span color="red">未提交</span>';
	}
}


var myformater = function(value,row,index){
    if (value){
        return new Date(parseInt(value)).toLocaleString();
    }else{
        return '';
    }
}
var fileFormater = function(value,row,index){
	 if (value){
		var index = value.lastIndexOf("\\");
		var filename = value.substring(index+1)
		rst = '<a href="'+ctx+'/job/download?type='+2+'&fileName='+filename+'">'+filename+'</a>';
        return rst;
    }else{
        return '';
    }
}
