<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
<html lang="en">
<head>
<title>菜单管理</title>
<%@include file="../admin/common/head.jspf"%>
<script type="text/javascript">
  		//定义全局url 用于修改与添加操作
		var url;
  		$(function(){
  			getMenuList();
  			//InitParentMenu();
  		});
  		
		var toolbar = [{
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				//初始化父级菜单列表
				InitParentMenu();
				url = "${blog}/admin/menu/save.do";
				$("#dlg").dialog("open").dialog("setTitle", "添加菜单信息");
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				//获取选中要删除的行
		        var selectedRows = $("#dg").datagrid("getSelections");
		        //判断是否有选择的行
		        if(selectedRows.length == 0) {
		            $.messager.alert("系统提示", "请选择要删除的数据");
		            return;
		        }
		        //定义选中 选中id数组
		        var idsStr = [];
		        //循环遍历将选中行的id push进入数组
		        for(var i = 0; i < selectedRows.length; i++) {
		            idsStr.push(selectedRows[i].id);
		        }
		        //将数组安装,连接成字符串
		        var ids = idsStr.join(","); //1,2,3,4
		        //提示是否确认删除
		        $.messager.confirm("系统提示", "<font color=red>您确定要删除选中的"+selectedRows.length+"条数据么？</font>", function(r) {
		            if(r) {
		                $.post("${blog}/admin/menu/delete.do",
		                    {ids: ids}, function(result){
		                        if(result.success){
		                        	$.messager.alert("系统提示", "数据删除成功！");
		                            $("#dg").datagrid("reload");
		                        }else{
		                        	$.messager.alert("系统提示", "数据删除失败！");
		                        }
		                    }, "json");
		            }
		        });
			}
		},{
			text:'修改',
			iconCls:'icon-edit',
			handler:function(){
				//获取选中要修改的行
  			  	var selectedRows = $("#dg").datagrid("getSelections");
  				//确保被选中行只能为一行
		        if(selectedRows.length != 1) {
		            $.messager.alert("系统提示", "请选择一个要修改的菜单");
		            return;
		        }
		        InitParentMenu();
				
  				//获取选中行id
		        var row = selectedRows[0];
		        $("#menuName").textbox("setValue", row.menuName); //保存成功后将内容置空
                $("#menuUrl").textbox("setValue", row.menuUrl);
                $("#sort").textbox("setValue", row.sort);
                if(row.isLeaf){
                	$("[name='isLeaf']").attr("checked", 'true');
                }
                if(row.parentId !== null){
                	$("#parentId").combobox("setValue", row.parentId);
                }
                url = "${blog}/admin/menu/save.do?id=" + row.id;
                $("#dlg").dialog("open").dialog("setTitle", "修改菜单信息");
  				//console.log(row);
  				alert("修改菜单Id为:" + row.id);
			}
		},'-',{
            iconCls: 'icon-reload',
            text: '刷新',
            handler: function () {
				$("#dg").datagrid("reload");
            }
        }];
		
		function saveMenuDialog(){
			console.log(url);
			$("#fm").form("submit",{
	            url: url,
	            onSubmit: function() {
	            	//alert(1);
	                return $(this).form("validate");
	            }, //进行验证，通过才让提交
	            success: function(result) {
	            	//console.log(result);
	            	var data = eval("(" + result + ")");
	                /* var result = eval("(" + result + ")"); *///将json格式的result转换成js对象
	                if(data.error === 0) {
	                    $.messager.alert("系统提示", "菜单信息保存成功");
	                    $("#menuName").textbox("setValue", ""); //保存成功后将内容置空
	                    $("#menuUrl").textbox("setValue", "");
	                    $("#sort").textbox("setValue", "");
	                    $("[name='isLeaf']").attr("checked", 'false');
	                    $("#dlg").dialog("close"); //关闭对话框
	                    $("#dg").datagrid("reload"); //刷新一下
	                } else {
	                    $.messager.alert("系统提示", "菜单保存失败");
	                    return;
	                } 
	            }
	        });
		}
		
		//关闭对话框
		function closeMenuDialog(){
			$("#dlg").dialog("close"); //关闭对话框
		}
		
		
		//初始化上级菜单列表
		function InitParentMenu(){
			$.ajax({
				url: "${blog}/admin/menu/getNotLeafMenu.do",
				type: "post",
				dataType: "json",
				async: false,
				data: null,
				success: function(data){
					$("#parentId").combobox("loadData", data);
				}
			});
		}
		
  		//获取查询条件
  	   	function GetSqlWhere() {
  	       var strWhere = "1=1";
  	       var username = $.trim($("#stxtUserName").val());
  	       var phase = $("#ssPhase").val();
  	      if (username != "") {
  	          strWhere += " and UserName='" + username + "'";
  	      }
  	      if (phase != "0") {
  	          strWhere += " and Phase='" + phase + "'";
  	      }
  	      return strWhere;
  	  }
  	  
  	  //获取菜单列表
  	 function getMenuList() {
  	      $("#dg").datagrid({
  	          url: "${blog}/admin/menu/list.do",
  	      	  //载入提示信息
              loadMsg: 'loading...',
              toolbar:toolbar,
              //水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置
              fitColumns: true,
              //数据多的时候不换行
              nowrap: true,
              //指定id为标识字段，在删除，更新的时候有用，如果配置此字段，在翻页时，换页不会影响选中的项
              idField: 'id',
//   	          queryParams://每次请求的参数
//   	              {
//   	                  cmd: 'list',
//   	                  strWhere: strWhere
//   	              },
  	          pagination: true,//允许分页
  	          rownumbers: true,//行号
  	          singleSelect: true,//只选择一行
  	          pageSize: 20,//每一页数据数量
  	          //checkOnSelect: false,
  	          pageList: [5, 10, 15, 20, 25],
  	          columns: [[
  	        	  {field: '',checkbox: true,},
  	          	  {field: "id",title: "菜单ID",align: "center",width: 20}, 
  	          	  {field: "menuName",title: "菜单名称",align: "center",width: 100}, 
  	          	  {field: "menuUrl",title: "菜单地址",align: "center",width: 200}, 
  	          	  {field: "isLeaf",title: "是否叶子",align: "center",width: 100, formatter: function (val, row) {
  	          		  if(val){return "是";}else{return "否";}
  	          	  }}, 
  	          	  {field: "parentId",title: "上级菜单ID",align: "center",width: 100}, 
  	          	  {field: "status",title: "状态",align: "center",width: 100, formatter: function (val, row) {
  	          		  if(val) {return "正常";}else{return "已停用";}
  	          	  }},
  	          	  {field: "sort",title: "排序",align: "center",width: 50}
  	         ]],
  	 
  	         //点击每一行的时候触发
  	         //onClickRow: function (rowIndex, rowData) {
  	         //    alert(rowData["UserId"]);
  	         //}
  	     });
   	}

    </script>
</head>
<body>
	<table id="dg"></table>
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 480px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>菜单名称</td>
					<td><input type="text" id="menuName" name="menuName" class="easyui-textbox"
						class="easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td>菜单地址</td>
					<td><input type="text" id="menuUrl" name="menuUrl" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td>是否叶子</td>
					<td><input type="checkbox" id="isLeaf" name="isLeaf" value="true">叶子结点</td>
				</tr>
				<tr>
					<td>父级菜单</td>
					<td>
						<input class="easyui-combobox" id="parentId" name="parentId" style="width:200px" data-options="valueField:'id', textField:'menuName', panelHeight:'auto'" > 
					</td>
				</tr>
				<tr>
					<td>排序</td>
					<td><input type="text" id="sort" name="sort"
						class="easyui-numberbox" required="true" style="width: 60px">&nbsp;(菜单会根据序号从小到大排列)
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<div>
			<a href="javascript:saveMenuDialog()" class="easyui-linkbutton"
				iconCls="icon-ok" plain="true">保存</a> <a
				href="javascript:closeMenuDialog()" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true">关闭</a>
		</div>
	</div>
</body>
</html>