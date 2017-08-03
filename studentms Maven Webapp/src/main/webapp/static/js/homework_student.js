/* 
* @Author: anchen
* @Date:   2017-07-25 15:54:30
* @Last Modified by:   anchen
* @Last Modified time: 2017-07-25 15:59:55
*/

function upload(){

    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $('#dlg').dialog('open').dialog('setTitle','提交作业');
        $('#fm').form('load',row);
        url = ''+row.jid;
    }
}


//搜索用 
function doSearch(){
  
    var teacherName = $('#teacherName').val();
        formDate = $('#formDate').val();
        toDate = $('#toDate').val();
    if(!checkDateTime(formDate))	formDate = null;
    if(!checkDateTime(toDate))	toDate = null;   
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
        return value.substring(index+1);
    }else{
        return '';
    }
}
