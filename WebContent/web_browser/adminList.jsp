<%@page import="com.atongmu.bean.Tbl_code_master"%>
<%@page import="com.atongmu.bean.Tbl_manager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map" import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员一览</title>

</head>
<body style="padding-left:2%">
		<div class="titlebar">
			<h3>销售审核一览</h3>
		</div>
		<div class="line"></div>
	<div class="body">
		<table class="table table-border table-bg table-bordered table-hover" style="width:100%;margin-top:15px;">
			<tr>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">管理员ID</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">管理员用户姓名</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">管理员密码</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">管理员姓名</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">管理员电话号码</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">管理员角色</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">管理员直辖地区</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">管理员加入时间</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">操作</th>
			</tr>

				<%
				List<Tbl_manager> adminList = (List<Tbl_manager>)request.getAttribute("adminList");
				for(Tbl_manager manager:adminList){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String orderdate = df.format(manager.getJoin_date());
			    String homearea = manager.getManager_home_area();
			    String homeareainfo ="";
				if(homearea==null){
					homeareainfo = "最高管理员";
					} else if(homearea.equals("M001")){
						homeareainfo = "总部";
					}else if(homearea.equals("M002")){
						homeareainfo="贵州地区";
					}else if(homearea.equals("M003")){
						homeareainfo="云南地区";
					}else if(homearea.equals("M004")){
						homeareainfo="新疆地区";
					}else if(homearea.equals("M005")){
						homeareainfo="沈阳地区";
					}
				String role = manager.getManager_role();
				String roleinfo="";
				if(role.equals("L001")){
					roleinfo = "高级管理员";
				}else if(role.equals("L002")){
					roleinfo = "仓库管理员";
				}else if(role.equals("L003")){
					roleinfo = "订单管理员";
				}else if(role.equals("L004")){
					roleinfo = "地区管理员";
				}
				%>
				<tr>
					<td width="5%" class="text-c"><%=manager.getManager_ID() %></td>
					<td width="10%" class="text-c"><%=manager.getManager_username() %></td>
					<td width="10%" class="text-c"><%=manager.getManager_password() %></td>
					<td width="10%" class="text-c"><%=manager.getManager_name() %></td>
					<td width="10%" class="text-c"><%=manager.getManager_telno() %></td>
					<td width="10%" class="text-c"><%=roleinfo %></td>
					<td width="10%" class="text-c"><%=homeareainfo%></td>
					<td width="15%" class="text-c"><%=orderdate %></td>
					<td class="text-c"><a href="" class="btn btn-primary radius">修正</a></td>
				</tr>
				<%
				}
				
            %>
	</table>
</div>
</body>
</html>