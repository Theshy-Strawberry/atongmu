<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未发货订单一览</title>
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<script src="web_browser/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
$(window).load(function(){
	var hdnOrderIdSearch = $("#hdnOrderIdSearch").val();
	if(hdnOrderIdSearch !=""){
		$("#orderIdSearch").val(hdnOrderIdSearch);
	}
});

</script>
</head>
<body style="padding-left:2%">
		<div class="titlebar">
			<h3>未发货订单一览</h3>
		</div>
		<div class="line"></div>
	<div class="body">
		<form id="userslistshow" action="OrderDeliveryServlet" method="post" class="form form-horizontal responsive" style="margin-top:15px">
				<label for="orderNo" id="orderNo">订单ID：<input type="text" name="orderIdSearch" id="orderIdSearch"  class="input-text" style="width:200px;"></label>
				<input type="submit" name="search" value="搜索" class="btn btn-primary radius" style="margin-left:10px;">
		</form>
		<%
			Object orderIdSearchObj = request.getAttribute("ordrIdSearch");
			String orderIdSearch = "";
			if(orderIdSearchObj != null){
				orderIdSearch = (String)orderIdSearchObj;
			}
		%>
		<input type="hidden" value="<%=orderIdSearch %>" id="hdnOrderIdSearch"/>
		<table class="table table-border table-bg table-bordered table-hover" style="width:100%;margin-top:15px;">
			<tr>
				<th width="10%" class="text-c" style="background-color:#DFF0D8;">订单ID</td>
				<th width="8%" class="text-c" style="background-color:#DFF0D8;">用户ID</td>
				<th width="10%" class="text-c" style="background-color:#DFF0D8;">提交日期</td>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">收件人</td>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">收件人电话</td>
				<th width="20%" class="text-c" style="background-color:#DFF0D8;">收件人地址</td>
				<th width="10%" class="text-c" style="background-color:#DFF0D8;">发货</td>
				<th width="10%" class="text-c" style="background-color:#DFF0D8;">打印发货单</td>
			</tr>
			<%
				List<Map<String, Object>> orderNotPayList = (List<Map<String, Object>>)request.getSession().getAttribute("orderNotPayList");
				for(Map<String,Object> tempMap:orderNotPayList){
				%>
			<tr>
				<td width="5%" class="text-c" ><%=tempMap.get("orderId") %></td>
				<td width="8%" class="text-c" ><%=tempMap.get("orderUser") %></td>
				<td width="10%" class="text-c" ><%=tempMap.get("orderDate") %></td>
				<td width="5%" class="text-c" ><%=tempMap.get("userName") %></td>
				<td width="5%" class="text-c" ><%=tempMap.get("userTel") %></td>
				<td width="20%" class="text-c" ><%=tempMap.get("userAddr") %></td>
				<td class="text-c" >
				<input type="submit" name="update" id="update" value="发货"  class="btn btn-primary radius"
						onclick="javascript:window.location.href='web_browser/orderDeliverDetail.jsp?orderId=<%=tempMap.get("orderId") %>'">

				<td class="text-c" >
				<input type="submit" name="update" id="update" value="打印发货单"  class="btn btn-primary radius"
						onclick="javascript:window.open('PrintOrderServlet?orderid=<%=tempMap.get("orderId") %>')">

			</tr>
			<%
				}
            %>
		</table>
	</div>
</body>
</html>