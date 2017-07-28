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
