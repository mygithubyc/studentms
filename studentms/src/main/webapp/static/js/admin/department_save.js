$(function(){
	// 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 院系管理 > 维护院系信息',
        iconCls: 'icon-page'
    });
	$('#add_school_a').linkbutton({
		iconCls: 'icon-add',
		onClick: function(){
			$('#school_fm').form('clear');
			$('#school_dlg').dialog('open');
			
		}
	});
	$('#add_depart_a').linkbutton({
		iconCls: 'icon-add',
		onClick: function(){
			$('#depart_fm').form('clear');
			$('#depart_dlg').dialog('open');
			$('#dCombobox').combobox({
		        editable: false,
		        required: true,
		        url: ctx+'/school/dCombobox',
		        valueField: 'schoolId',
		        textField: 'schoolName',
		        method: 'post'
		    });
		}
	});
	
	$('#school_dlg').dialog({
		modal: true,
		closed: true,
		buttons: [{
			text: '确定',
			iconCls: 'icon-ok',
			handler: function(){
				var schoolName = $('#school_dlg').find('#school_name').val().trim(),
					msg = '';
				if(schoolName !== ""){
					$.post(ctx+'/school/dAddSchool', {schoolName: schoolName}, function(data) {
						var result = JSON.parse(data);
						if(result.success){
							msg = '新增成功';
							$('#school_dlg').dialog('close');
							$('#school_dg').datagrid('reload');
						}else{
							msg = result.msg;
						}
						$.messager.show({
							title: '提示',
							msg: msg
						});
					});
				}
				
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$('#school_dlg').dialog('close');
			}
		}]
	});
	$('#depart_dlg').dialog({
		modal: true,
		closed: true,
		buttons: [{
			text: '确定',
			iconCls: 'icon-ok',
			handler: function(){
				$('#depart_fm').form('submit',{
					url: ctx+'/department/addDepartment',
					onSubmit: function(){
			            $.messager.progress();
			            var isValid = $(this).form('validate');
			            if (!isValid) {
			                $.messager.progress('close');
			            }
			            return isValid;
			        },
			
			        success: function(data){
			            var result = JSON.parse(data),
			                msg = '';

			            $.messager.progress('close');
			            if (result.success) {
			                msg = '新增成功!';
			                $('#depart_dlg').dialog('close');
							$('#school_dg').datagrid('reload');
			            }else{
			                msg = result.msg;
			            }
			            $.messager.show({
			                title: '执行结果',
			                msg: msg
			            });
			        }
				});
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				alert('取消!');
				$('#depart_dlg').dialog('close');
			}
		}]
	})
	$('#school_dg').datagrid({
	   
	    height: 750,
	    url: ctx+'/school/schoolDg',
	    fitColumns: true,
	    striped: true,
	    singleSelect: true,
	    toolbar: '#toolbar',
	    columns: [[
	        {field: 'schoolName', title: '学院名称', width: 100, align: 'left'},	        
	    ]],
	    view: detailview,
	    detailFormatter:function(index,row){
			return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
		},
		onExpandRow: function(index,row){
			$('#ddv-'+index).datagrid({
				url:ctx+'/department/showDepartmentDg?schoolId='+row.schoolId,
				fitColumns:true,
				singleSelect:true,
				rownumbers:true,
				loadMsg:'',
				height:'auto',
				columns:[[
					{field:'departName',title:'所含专业',width:200,align:'center', halign: 'left'},					
				]],
				onResize:function(){
					$('#school_dg').datagrid('fixDetailRowHeight',index);
				},
				onLoadSuccess:function(){
					setTimeout(function(){
						$('#school_dg').datagrid('fixDetailRowHeight',index);
					},0);
				}
			});
			$('#dg').datagrid('fixDetailRowHeight',index);
		}
	     
	});
});	