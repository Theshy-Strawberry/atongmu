<%@page import="com.atongmu.bean.Tbl_order"%>
<%@page import="com.sun.xml.internal.ws.api.ha.StickyFeature"%>
<%@page import="com.atongmu.bean.Tbl_code_master"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List" import="com.atongmu.bean.Tbl_saleman"
	import="com.atongmu.bean.Tbl_code_master"
	import="com.atongmu.bean.Tbl_order" pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售员一览</title>
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<script src="web_browser/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
	$(window).load(function() {
		var value = $("#hidlevel").val();
		$("#sallevel").val(value);

	});
</script>
</head>
<body style="padding-left:2%">
		<div class="titlebar">
			<h3>销售员一览</h3>
		</div>
		<div class="line"></div>
	<%
		Object objsalid = request.getAttribute("saleman");
		Object objweixin = request.getAttribute("weixin");
		Object objlevel = request.getAttribute("level");
		String salid = (String) objsalid;
		String weixin = (String) objweixin;
		String level = (String) objlevel;
	%>
	<form id="" name="" action="SalemanInfoShowServlet" method="post" class="form form-horizontal responsive" style="margin-top:15px">
		<label>销售员ID：</label>
		<input id="salemanid" name="salemanid" value="<%=salid%>" class="input-text" style="width:200px;">
		<label>微信号：</label>
		<input id="weixin" name="weixin" value="<%=weixin%>"class="input-text" style="width:200px;" >
		<label>销售等级</label>
		<input type="hidden" id="hidlevel" name="hidlevel" value="<%=level%>">
		<span class="select-box" style="width:210px;">
			<select id="sallevel" name="sallevel"  class="select"  style="width:200px;">
				<option value="" selected="selected">请选择----</option>
				<option value="I002">销售员</option>
				<option value="I003">销售经理</option>
				<option value="I004">高级销售经理</option>
				<option value="I005">主管经理</option>
			</select>
		</span>
		<input type="submit" value="搜索" class="btn btn-primary radius" style="margin-left:10px;">
	</form>
	<table class="table table-border table-bg table-bordered table-hover" style="width:100%;margin-top:15px;">
		<tr>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">销售ID</th>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">加入日期</th>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">微信号</th>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">真实姓名</th>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">性别</th>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">所属区域</th>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">手机号</th>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">当前等级</th>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">总销售额</th>
			<th width="5%" class="text-c" style="background-color:#DFF0D8;">详情</th>
		</tr>
		<%
			Object obj = request.getAttribute("salemanList");
			if (obj != null) {
				List<List<Object>> salemanList = (List<List<Object>>) obj;
				for (int i = 0; i < salemanList.size(); i++) {
					List<Object> list = salemanList.get(i);
					Tbl_saleman saleman = (Tbl_saleman) list.get(0);
					Tbl_code_master code_master = (Tbl_code_master) list.get(1);
					Tbl_code_master code_master2 = (Tbl_code_master) list
							.get(2);
					Tbl_order order = (Tbl_order) list.get(3);
		%>
		<tr>

			<td class="text-c"><%=saleman.getSaleman_id()%></td>
			<td class="text-c"><%=saleman.getReg_date()%></td>
			<td class="text-c"><%=saleman.getWeixin_id()%></td>
			<td class="text-c"><%=saleman.getSaleman_name()%></td>
			<td class="text-c"><%=saleman.getSaleman_sex()%></td>
			<td class="text-c"><%=code_master.getCode_value()%></td>
			<td class="text-c"><%=saleman.getSaleman_tel_num()%></td>
			<td class="text-c"><%=code_master2.getCode_value()%></td>
			<td class="text-c"><%=order.getOrder_price()%></td>
			<td class="text-c"><a
				href="SalemanInfoUpdateServlet?saleManId=<%=saleman.getSaleman_id() %>"  class="btn btn-primary radius">详情</a></td>
		</tr>
		<%}} %>
	</table>
</body>
</html>