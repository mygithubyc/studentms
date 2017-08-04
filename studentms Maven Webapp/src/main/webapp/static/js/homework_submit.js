/* 
* @Author: anchen
* @Date:   2017-07-26 16:29:52
* @Last Modified by:   anchen
* @Last Modified time: 2017-07-26 16:35:42
*/

function doSearch(){
    // alert('submit doSearch');
    var title = $('div#toolbar_submit #title').val(),
    studentName = $('div#toolbar_submit #studentName').val();
    console.log(title+'/'+studentName);
    $('#dg_submit').datagrid('load',{
        title: title,
        cUsername: studentName
    })
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
			rst = '<a href="'+ctx+'/job/download?type='+1+'&fileName='+filename+'">'+filename+'</a>';
	        return rst;
	    }else{
	        return '';
	    }
	}