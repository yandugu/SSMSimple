<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改密码</title>
    <%@include file="../admin/common/head.jspf" %>
    <script type="text/javascript">
    
    	function savePwd(){
    		//进行表单校验
            var v = $("#editPasswordForm").form("validate");//对应表单中的所有输入框进行校验
            if(v){//表单校验通过
                //判断两次输入是否一致
                var v1 = $("#txtNewPass").val();
                var v2 = $("#txtRePass").val();
                if(v1 == v2){
                    //输入一致，发送ajax请求，修改当前用户的密码
                    var url = "${pageContext.request.contextPath}/userCenter/modifyPassword.do";
//                     $.post(url,{"password":v1},function(data){
//                     	console.log(data);
//                         if(data == '1'){
//                             //修改密码成功
//                             $.messager.alert("提示信息","密码修改成功！","info");
//                         }else{
//                             //修改失败
//                             $.messager.alert("提示信息","密码修改失败！","warning");
//                         }
//                     });
					$.ajax({
						url: url,
						type: "POST",
						dataType:"json",
						//contentType: "application/json;charset=UTF-8",
						//<!-- 向后端传输的数据 -->
						data: {"password":v1},
						success:function(result) {
							//<!-- 处理后端返回的数据 -->
							var message= JSON.stringify(result);
							console.log(message);
							if(result.error == "0"){
								$.messager.alert("提示信息","密码修改成功！","warning");
								window.parent.closeTab("修改密码");  
							}else{
								$.messager.alert("提示信息","密码修改失败" + message.message + "！","error");
							}
						},
						error:function(result){
							console.log(result);
							$.messager.alert("提示信息",result,"error");
						}
					});
                }else{
                    //输入不一致，提示用户输入不一致
                    $.messager.alert("提示信息","两次输入密码不一致！","warning");
                }
            }
    	}
      //为“确定”按钮绑定事件
        $("#btnEp").click(function(){
        	alert(1);
            //进行表单校验
            var v = $("#editPasswordForm").form("validate");//对应表单中的所有输入框进行校验
            if(v){//表单校验通过
                //判断两次输入是否一致
                var v1 = $("#txtNewPass").val();
                var v2 = $("#txtRePass").val();
                if(v1 == v2){
                    //输入一致，发送ajax请求，修改当前用户的密码
                    var url = "${pageContext.request.contextPath}/userCenter/modifyPassword.do";
                    $.post(url,{"password":v1},function(data){
                        if(data == '1'){
                            //修改密码成功
                            $.messager.alert("提示信息","密码修改成功！","info");
                        }else{
                            //修改失败
                            $.messager.alert("提示信息","密码修改失败！","warning");
                        }
                        //关闭修改密码的窗口 
                        $("#editPwdWindow").window("close");
                    });
                }else{
                    //输入不一致，提示用户输入不一致
                    $.messager.alert("提示信息","两次输入密码不一致！","warning");
                }
            }
        });
    </script>
</head>
<body>
	 <table cellpadding=3>
 <tr>
    <td>新密码：</td>
    <td><input id="txtNewPass" type="Password" class="txt01 easyui-validatebox" 
               required="true" data-options="validType:'length[4,8]'" />
    </td>
 </tr>
 <tr>
    <td>确认密码：</td>
    <td><input id="txtRePass" type="Password" class="txt01 easyui-validatebox" 
                                required="true" data-options="validType:'length[4,8]'"/>
    </td>
 </tr>
 </table>
        </div>
            <div region="south" border="false" style="text-align: left; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="savePwd()">确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
</body>
</html>