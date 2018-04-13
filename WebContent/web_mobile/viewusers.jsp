<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
import ="com.atongmu.bean.Tbl_user"
import = " java.text.SimpleDateFormat"
	pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title>查看客户群</title>
<link rel="stylesheet" href="web_mobile/css/seedsui.min.css">
</head>

<body>
<script>
function submitForm(){
    document.getElementById("selectForm").submit();
}
</script>
	<header>
		<div class="titlebar">
			<a href="web_mobile/salemanhome.jsp"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
			<h1>查看客户群</h1>
		</div>
	</header>
	<article data-role="article" id="info_article"
		style="top: 44px; bottom: 54px;">
		 <div class="scroller">
			<details open>
				<summary class="sliver">设置查询月份</summary>
				<form id="selectForm" action="ViewUsersServlet" method="post">
				<ul class="list">
					<li class="nojustify">
						<div class="justify-content">
							<div class="row">
							<%
							    Object objectdate = request.getAttribute("orderdate");
							    String date = (String)objectdate;
							%>
								<div class="row-right input-box">
									<input type="month" value="<%=date%>" id="order_date" name="order_date">
									<i class="color-placeholder icon icon-calendar"></i>
								</div>
							</div>
							<div class="justify-content">
								<p>
									<a class="radius4 button block margin8" href="#" onclick="submitForm();">查询该月客户消费明细</a>
								</p>
							</div>
						</div>
					</li>
				</ul>
				</form>
			</details>
			<%
                    Object objectlist =  request.getAttribute("viewusers");
			           if(objectlist==null){
			 %>
			                <h2>您还没有发展客户</h2>
			            <%
			           }else{
			        	   List<Tbl_user> tbl_users_list  = (List<Tbl_user>)objectlist;

			        %>
			<details open>
				<summary class="sliver">客户列表</summary>
				<ul class="list">
				<%
				for(int i=0;i<tbl_users_list.size();i++){
                    String name = tbl_users_list.get(i).getName();
                    String userName = tbl_users_list.get(i).getUser_name();
                    double orderPrice = tbl_users_list.get(i).getOrder_price();
                    double occurBonus = tbl_users_list.get(i).getOccur_bonus();
				%>
					<li>
						<div class="justify box box-middle">
							<div class="photo bg-primary">
								<i class="treeicon radiusround"><%=name%></i>
							</div>
						</div>
						<div class="justify-content">
							<p><%=userName%></p>
							<small class="nowrap">该月消费￥<%=orderPrice %> 元</small>
							<small class="nowrap">您从中共获得佣金￥<%=occurBonus%>元</small>
						</div>
					</li>
<%} %>
				</ul>
			</details>
<%} %>
		</div>
	</article>

</body>
</html>