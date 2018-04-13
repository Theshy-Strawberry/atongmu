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
<title>查看商品详情</title>
<link rel="stylesheet" href="css/seedsui.min.css">
<!--Exmobi能力-->
   <script src="../plugin/jquery/jquery.min.js"></script>
<script src="../plugin/seedsui/seedsui.min.js"></script>
<!--Exmobi能力-->
<script src="../plugin/exmobi/exmobi.js"></script>
</head>

<body>
	<header>
		<div class="titlebar">
			<a href="javascript:history.go(-1);"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
			<h1>商品详情</h1>
		</div>
	</header>
	<div class="container"style="margin-top:30px;">
	<iframe style="width:100%;height:1000px;" scrolling="no" height="100%" frameborder="0"
	 src="<%=request.getSession().getAttribute("goods_url") %>"></iframe>
	</div>
	<div class="justify-content">
		<p>
			<button class="button submit block" type="button" onclick="javascript:history.go(-1);">返回</button>
		</p>
	</div>
</body>
</html>

