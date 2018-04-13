<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退换货订单一览</title>
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<script src="web_browser/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
$(window).load(function(){
	var goodsclassified = $("#hiddengoodsclassified").val();
	if(goodsclassified !="null"){
		$("#classified").val(goodsclassified);
	}
});

</script>
<body style="padding-left:2%">
		<div class="titlebar">
			<h3>退换货订单一览</h3>
		</div>
		<div class="line"></div>
	<div class="body">

		<table class="table table-border table-bg table-bordered table-hover" style="width:100%;margin-top:15px;">
			<tr>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">订单ID</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">用户ID</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">订单状态</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">发起时间</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">退货理由</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">收货物流公司</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">收货物流单号</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">发货物流公司</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">发货物流单号</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">退换货时间</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">设置订单状态</th>
			</tr>
		</table>
		<form id="" action="OrderReturnServlet" method="post">
			<table>
				<%
					List<Map<String, Object>> getReturnList = (List<Map<String, Object>>)request.getAttribute("getReturnList");
					for(Map<String,Object> tempMap:getReturnList){
					%>
				<tr>
					<td width="10%"><%=tempMap.get("orderId") %></td>
					<td width="10%"><%=tempMap.get("userName") %></td>
					<td width="10%"><%=tempMap.get("codeValue") %></td>
					<td width="10%"><%=tempMap.get("requestDate") %></td>
					<td width="10%"><%=tempMap.get("returnReason") %></td>
					<td width="10%"><%=tempMap.get("fromLogisticsCompany") %></td>
					<td width="10%"><%=tempMap.get("fromLogisticsNumber") %></td>
					<td width="10%"><input type="text" name="toLogisticsCompany"
						value="<%=tempMap.get("toLogisticsCompany") %>"></td>
					<td width="10%"><input type="text" name="toLogisticsNumber"
						value="<%=tempMap.get("toLogisticsNumber") %>"></td>
					<td width="10%"><input type="text" name="returnDate"
						value="<%=tempMap.get("returnDate") %>"></td>
					<td width="10%"><select id="statusIn" name="statusIn">
							<option value="">请选择...</option>
							<%
							List<Map<String, Object>> getReturnStatusList = (List<Map<String, Object>>)request.getAttribute("getReturnStatusList");
							for(Map<String,Object> statusMap:getReturnStatusList){
							%>
							<option value="<%=statusMap.get("code") %>"><%=statusMap.get("codeValue") %></option>
							<%
							}
							%>
					</select></td>
					<td><input type="submit" value="设置"></td>
				</tr>
				<%
					}
	            %>
			</table>
		</form>
	</div>
</body>
</html>