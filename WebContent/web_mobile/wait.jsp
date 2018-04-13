<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.atongmu.bean.Tbl_saleman,com.atongmu.bean.Tbl_user"
	pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title>支付成功</title>
<link rel="stylesheet" href="css/seedsui.min.css">
<!--Exmobi能力-->
   <script src="../plugin/jquery/jquery.min.js"></script>
<script src="../plugin/seedsui/seedsui.min.js"></script>
<!--Exmobi能力-->
<script src="../plugin/exmobi/exmobi.js"></script>
</head>
<%
String orderID = request.getParameter("orderId");
request.getSession().setAttribute("token", true);
%>
<body>
	<header>
		<div class="titlebar">
			<h1>微信支付成功</h1>
		</div>
	</header>
	<div style="margin-top:50px;padding:10px 10px 10px 10px;">
	<h3>订单ID：<%=orderID %></h3><p/><br/>
	<h3>付款成功！</h3><p/><br/>
	<a href="#" onclick="window.location.href='../GoodsPayServlet?orderId=<%=orderID%>'">确定</a>  
	<%-- <a href="../GoodsShowServlet">返回商城</a> --%>
	</div>
</body>
</html>

