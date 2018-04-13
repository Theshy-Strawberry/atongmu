<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" import ="com.atongmu.bean.Tbl_commodity"
    pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="web_mobile/js/jquery.min.js?v=01291"></script>
<script charset="utf-8" src="web_mobile/js/global.js?v=01291"></script>
<script charset="utf-8" src="web_mobile/js/bootstrap.min.js?v=01291"></script>
<script charset="utf-8" src="web_mobile/js/template.js?v=01291"></script>
<script charset="utf-8" src="web_mobile/js/swiper.min.js"></script>

<link rel="stylesheet" href="web_mobile/css/bootstrap.css">
<link rel="stylesheet" href="web_mobile/css/style.css">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
<title>首页</title>
</head>
<body>
	<header class="header">

		<div class="fix_nav">
			<div style="max-width: 100%; margin: 0 auto; height: 44px; position: relative; background: #cccccc;">
				<form action="../atongmu/TurnoverByMonthServlet" method="get" id="searchform"
					name="searchform">
					<div class="navbar-search left-search">
						<input type="text" id="keyword" name="yearmonth" value=""
							placeholder="请输入关键字" class="form-control">
					</div>
					<div class="nav-right">
						<input type="button" value="搜索" onclick="searchproduct();"
							class="img-responsive"
							style="text-align: center; background-color: #000000; color: #ffffff; border-radius: 5px; border: none; height: 34px; vertical-align: middle; clear: both; padding: 0px; width: 45px;" />
					</div>
				</form>
			</div>
		</div>
	</header>

<%
Object obj1 = request.getAttribute("profit");
if(obj1 != null){
	String profit=(String)obj1;%>

<p style="font-size:35px;color:red"> 销售额总计<%=profit %>￥</p>

<%} %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#slide img").each(function() {
				var img_src = $(this).attr("_src");
				$(this).attr("src", img_src);
			});
		});

		function searchproduct() {
			var keyword = document.getElementById("keyword").value;
			if (keyword == undefined || keyword == null || keyword == "") {
				alert("请输入搜索关键字！");
				return false;
			}
			document.getElementById("searchform").submit();
		}
	</script>
</body>
</html>

