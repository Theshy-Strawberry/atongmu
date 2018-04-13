<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.atongmu.bean.Tbl_user"
	import="com.atongmu.bean.Tbl_order" import="java.text.SimpleDateFormat"
	pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<title>游客一览</title>
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<script src="web_browser/js/jquery-2.1.3.min.js"></script>

<script type="text/javascript">
	$(window).load(function() {
		var statusvalue = $("#hidstatus").val();
		if(statusvalue!="null"){
			$("#status").val(statusvalue);
		}

	});

</script>
</head>

<body style="padding-left:2%">
		<div class="titlebar">
			<h3>游客一览</h3>
		</div>
		<div class="line"></div>
	<div class="body">
		<form id="userslistshow" action="ShowUsersListServlet" method="post" class="form form-horizontal responsive" style="margin-top:15px">
			<label>游客ID：<input type="text" name="usersid" class="input-text" style="width:200px;"></label>
			<label>微信号：<input type="text" name="weixinid" class="input-text" style="width:200px;"></label>
			<input type="submit" name="search" value="搜索" class="btn btn-primary radius" style="margin-left:10px;">
		</form>
		<table class="table table-border table-bg table-bordered table-hover" style="width:100%;margin-top:15px;">
			<tr>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">游客ID</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">注册日期</th>
				<th width="10%" class="text-c" style="background-color:#DFF0D8;">游客姓名</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">游客手机号</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">月消费额</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">游客微信号</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">操作</th>
			</tr>

				<%
				Object objectuserslist = request.getAttribute("userslist");
				List<List<Object>> userslist = (List<List<Object>>)objectuserslist;
				for(int i=0;i<userslist.size();i++){
					List<Object> listobject= userslist.get(i);
					Tbl_user beanusers = (Tbl_user)listobject.get(0);
					Tbl_order beanorder = (Tbl_order)listobject.get(1);
					String userid = beanusers.getUser_id();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String reqdate = df.format(beanusers.getReg_date());
					double integral = beanusers.getUser_integral();
					String name = beanusers.getUser_name();
					String tel = beanusers.getUser_tel_num();
                    double sumprice = beanorder.getOrder_price();
                    String weixinid = beanusers.getWeixin_id();
                    String saleid = beanusers.getSaleman_id();
				%>
				<tr>
					<td class="text-c"><%=userid%></td>
					<td class="text-c"><%=reqdate %></td>
					<td class="text-c"><%=name %></td>
					<td class="text-c"><%=tel %></td>
					<td class="text-c"><%=sumprice %></td>
					<td class="text-c"><%=weixinid %></td>
					<td class="text-c"><input type="submit" class="btn btn-primary radius" value="详情" onclick="javascritp:window.location.href='VipInfoShowServlet?userId=<%=userid%>&type=${pagetype}'"></td>
				</tr>
				<%
				}
            %>
			</table>

	</div>
</body>
</html>