<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.atongmu.bean.Tbl_order"
	import="java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<title>订单一览</title>
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
			<h3>订单一览</h3>
		</div>
		<div class="line"></div>

	<div class="body">
			<form id="orderlistform" action="OrderListShowServlet" method="post" class="form form-horizontal responsive" style="margin-top:15px">
					<%
						Object objnoflag = request.getAttribute("noflag");
						Object objnameflag = request.getAttribute("nameflag");
						Object objstatusflag = request.getAttribute("statusflag");
						String noflag = (String) objnoflag;
						String nameflag = (String) objnameflag;
						String statusflag = (String) objstatusflag;
					%>
					<label for="orderNo" id="orderNo">订单ID：<input type="text" name="orderno" value="<%=noflag%>" class="input-text" style="width:200px;"></label>
					<label for="orderName" id="orderName">用户ID：<input type="text" name="ordername" value="<%=nameflag%>" class="input-text" style="width:200px;"></label>
					<input type="hidden" name="hidstatus" id="hidstatus" value="<%=statusflag%>">
					<label for="status">订单状态：
					<span class="select-box" style="width:210px;">
					<select id="status" name="status" class="select"  style="width:200px;">
							<option value="" selected>请选择订单状态----</option>
							<option value="">全部订单</option>
							<option value="D001">待付款</option>
							<option value="D002">待发货</option>
							<option value="D003">已发货</option>
							<option value="D004">已取消</option>
							<option value="D005">已完成</option>
					</select>
					</span>
				</label><input type="submit" name="search" value="搜索" class="btn btn-primary radius" style="margin-left:10px;">
			</form>
			<table class="table table-border table-bg table-bordered table-hover" style="width:100%;margin-top:15px;">
				<tr>
					<th width="10%" class="text-c" style="background-color:#DFF0D8;">订单ID</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">用户ID</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">用户姓名</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">身份</th>
					<th width="10%" class="text-c" style="background-color:#DFF0D8;">提交日期</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">订单状态</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">订单总金额</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">使用积分</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">物流公司</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">物流号</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">详情</th>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">关闭订单</th>
				</tr>
				<%
					Object objectlist = request.getAttribute("orders_list");
					if (objectlist == null) {
				%>
				<h1>没有订单</h1>
				<%
					} else {
						List<Tbl_order> list = (List<Tbl_order>) objectlist;
						for (int i = 0; i < list.size(); i++) {
							String orderid = list.get(i).getOrder_id();
							String orderuser = list.get(i).getOrder_user();
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							String orderdate = df.format(list.get(i).getOrder_date());
							String orderstatus = list.get(i).getOrder_status();
							double orderprice = list.get(i).getOrder_price();
							String paymentmethod = list.get(i).getPayment_method();
							String logisticscompany = list.get(i)
									.getLogistics_company();
							String logisticsnumber = list.get(i).getLogistics_number();
							double use_integral = list.get(i).getUse_integral();
							String user_name = list.get(i).getUser_name();
							String user_type = list.get(i).getUserType();
				%>
				<tr>
					<td class="text-c"><%=orderid%></td>
					<td class="text-c"><%=orderuser%></td>
					<td class="text-c"><%=user_name %></td>
					<td class="text-c"><%=user_type %></td>
					<td class="text-c"><%=orderdate%></td>
					<td class="text-c"><%=orderstatus%></td>
					<td class="text-c"><%=orderprice%></td>
					<td class="text-c"><%=use_integral%></td>
					<td class="text-c">
					<%if("".equals(logisticscompany)){ %>

					<%}else{ %>
					<a id="logisticscompany" class="btn btn-success radius"
						href="http://m.kuaidi100.com/index_all.html?type=<%=logisticscompany%>&postid=<%=logisticsnumber%>&callbackurl=http://localhost:8080/atongmu/OrderListShowServlet"><%=logisticscompany%></a>
					<%} %>
					</td>
					<!-- www.in-artoon.com/atongmu/OrderListShowServlet -->
					<td class="text-c">
					<%if("".equals(logisticscompany)){ %>

					<%}else{ %>
					<a id="logisticsnumber" class="btn btn-success radius"
						href="http://m.kuaidi100.com/index_all.html?type=<%=logisticscompany%>&postid=<%=logisticsnumber%>&callbackurl=http://localhost:8080/atongmu/OrderListShowServlet"><%=logisticsnumber%></a>
					<%} %>
					</td>
					<td class="text-c"><input type="button" name="update" id="update" value="修改订单" class="btn btn-primary radius"
						onclick="window.location.href = 'UpdateOrderListShowServlet?orderId=<%=orderid%>&user_type=<%=user_type%>'">
					<td class="text-c">
						<%
							if (!(orderstatus.equals("已取消"))&&(orderstatus.equals("待付款"))) {
						%> <input type="button" name="close" id="close" value="关闭订单"  class="btn btn-danger radius"
						onclick="window.location.href = 'OrderListShowServlet?flag=1&orderId=<%=orderid%>'">
					</td>
					<%
						}
					%>
				</tr>
				<%
					}
					}
				%>
			</table>
	</div>
</body>
</html>