<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人博客后台管理</title>
    <%@include file="../admin/common/head.jspf" %>
    <style type="text/css">
        body {
            font-family: microsoft yahei;
        }
    </style>
   	<script type="text/javascript">
   	
	    function openTab(text, url, icon) {  
	        if ($('#tabs').tabs('exists', text)) {  
	            $("#tabs").tabs("select", text);  
	        } else {  
	            $("#tabs")  
	                    .tabs(  
	                            'add',  
	                            {  
	                                title : text,  
	                                closable : true, //是否允许选项卡摺叠。  
	                                iconCls : icon, //显示图标  
	                                content : "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath }/admin/"  
	                                        + url + "'></iframe>"  
	                            //url 后台的controller的某个匹配路径  
	                            })  
	        }  
	    }  
	   	/**
	     * 打开选项卡
	     * @param text  选项卡标题
	     * @param url   请求打开路径
	     * @param icon  选项卡图标
	     */
// 	    function openTab(text,url,icon) {
// 	        //判断当前选项卡是否存在
// 	        if($('#tabs').tabs('exists',text)){
// 	            //如果存在 显示
// 	            $("#tabs").tabs("select",text);
// 	        }else{
// 	            //如果不存在 则新建一个
// 	            $("#tabs").tabs('add',{
// 	                title:text,
// 	                closable:true,      //是否允许选项卡摺叠。
// 	                iconCls:icon,    //显示图标
// 	                content:"<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${blog}/admin/"+url+"'></iframe>"
// 	                //url 远程加载所打开的url
// 	            })
// 	        }
// 	    }
   	</script>
</head>
<body class="easyui-layout">
	<div region="north" style="height: 78px; background-color: #E0ECFF">  
		<table style="padding: 5px" width="100%">
	        <tr>
	            <td width="50%">
	                <h2>博客后台系统</h2>
	            </td>
	            <td valign="bottom" align="right" width="50%">
	                <font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${userinfo.userName }</font>
	                <a href="${pageContext.request.contextPath}/userCenter/logout.do">退出</a>
	            </td>
	        </tr>
		</table>
	</div>
	<div region="west" style="width: 200px" title="导航菜单" split="true">   
		    <div region="west" style="width: 250px" title="功能菜单" split="true">  
            <div class="easyui-accordion" data-options="fit:true,border:false" style="width: 250px; height: 386px;">  
                <div title="常用操作" data-options="selected:true,iconCls:'icon-ok'"  
                    style="padding: 10px">  
                    <a href="javascript:openTab('写博客','writeBlog','icon-edit')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-edit'" style="width: 150px">写博客</a>  
                    <a href="javascript:openTab('博客管理','manageBlog','icon-tip')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-tip'" style="width: 150px">博客管理</a>  
                    <a href="javascript:openTab('博客类别管理','manageTypeBlog','icon-tip')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-tip'" style="width: 150px;">博客类别管理</a>  
                </div>  
      
                <div title="博客类别管理" data-options="iconCls:'icon-ok'"  
                    style="padding: 10px">  
                    <a href="javascript:openTab('博客类别管理','blogTypeManage.jsp','icon-tip')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-tip'" style="width: 150px;">博客类别管理</a>  
                </div>  
      
                <div title="个人信息管理" data-options="iconCls:'icon-ok'"  
                    style="padding: 10px">  
                    <a href="javascript:openTab('修改个人信息','modifyPerInfo','icon-edit')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-mini-edit'"  
                        style="width: 150px;">修改个人信息</a> <a  
                        href="javascript:openTab('修改密码','modifyPassword.jsp','icon-edit')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-mini-edit'"  
                        style="width: 150px;">修改密码</a>  
                </div>  
                <div title="菜单管理" data-options="iconCls:'icon-ok'"  
                    style="padding: 10px">  
                    <a href="javascript:openTab('菜单管理','menuManage.jsp','icon-tip')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-tip'" style="width: 150px;">菜单管理</a>  
                </div> 
                <div title="博客管理" data-options="iconCls:'icon-ok'"  
                    style="padding: 10px;">  
                    <a href="javascript:openTab('写博客','writeBlog','icon-edit')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-edit'" style="width: 150px;">写博客</a>  
                    <a href="javascript:openTab('博客信息管理','manageBlog','icon-tip')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-tip'" style="width: 150px;">博客信息管理</a>  
                </div>  
                <div title="评论管理" data-options="iconCls:'icon-ok'"  
                    style="padding: 10px">  
                    <a href="javascript:openTab('评论审核','checkComment','icon-tip')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-ok'" style="width: 150px">评论审核</a>  
                    <a  
                        href="javascript:openTab('评论信息管理','manageCommentInfo','icon-tip')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-tip'" style="width: 150px;">评论信息管理</a>  
                </div>  
      
                <div title="系统管理" data-options="iconCls:'icon-ok'"  
                    style="padding: 10px">  
                    <a href="javascript:openTab('友情链接管理','managefriendLink','icon-tip')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-tip'" style="width: 150px">友情链接管理</a>  
      
                    <a href="javascript:openTab('刷新系统缓存','refreshcache','icon-reload')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-reload'"  
                        style="width: 150px;">刷新系统缓存</a> <a  
                        href="javascript:openTab('安全退出','exit','icon-cancel')"  
                        class="easyui-linkbutton"  
                        data-options="plain:true,iconCls:'icon-cancel'"  
                        style="width: 150px;">安全退出</a>  
                </div>  
            </div>  
        </div>  
	</div>
	<div data-options="region:'center'" style="background:#eee;">  
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
	        <div title="首页" data-options="iconCls:'icon-home'">
	            <div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
	        </div>
	    </div>
	</div>
	<div region="south" style="height: 25px;padding: 5px" align="center">
	    Copyright © 2012-2018 LMH的SSM博客系统 版权所有
	</div>
</body>
</html>