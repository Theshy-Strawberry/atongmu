<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" import ="com.atongmu.bean.Tbl_commodity,com.atongmu.bean.Tbl_user"
    pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!-- HTML5文件 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>阿同木产品分类</title>
<link rel="stylesheet" href="web_mobile/css/agile.layout.css">
<link rel="stylesheet" href="web_mobile/css/style.css">
<link rel="stylesheet" href="web_mobile/css/seedsui.min.css">
<link rel="stylesheet" type="text/css" href="web_mobile/css/userhome.css">
<link rel="stylesheet" href="web_mobile/css/bootstrap.css">
</head>
<body>
	<div id="section_container">
		<section id="index_section" data-role="section" class="active">
			<header>
				<div class="titlebar">
					<a href="GoodsShowServlet"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
					<h1>产品分类</h1>
				</div>
			</header>
			<!-- 阿同木中心设计 -->
			<article data-role="article" id="index_article" class="active"
				style="top: 10px; ">

					<details style="margin-top:40px;">
						<summary class="sliver">生态绿色食品</summary>
						<div style="margin-left:20px;margin-top:10px;"><p>敬请期待</p></div>
					</details>
					<details style="margin-top:0px;">
						<summary class="sliver">健康食品</summary>

					<!--产品块-->
					<div class="tb_box" style="margin-top:-10px;">
						<ul>
						<%
				Object obj1 = request.getAttribute("goodsList");
				if(obj1 != null){
					List<Tbl_commodity> goodsList = (List<Tbl_commodity>)obj1;
					for(int i = 0;i<goodsList.size();i++){ %>
							<li style="border: 1px solid #e5e5e5;"><a href="#" class="goodsPic"> <img src="GoodsPictureShowServlet?goods_id=<%=goodsList.get(i).getGoods_id() %> &id=1" /></a>
								<div class="goodsInfor">
									<h2>
										<a href="GoodsDetailServlet?id=<%=goodsList.get(i).getGoods_id()%>&shopcartflag=0"><%=goodsList.get(i).getGoods_name() %></a>
									</h2>
									<p style="margin-top:-4px">
										<strong class="price"><%=goodsList.get(i).getGoods_price() %></strong>
									</p>
									<p>
										<strong class="salecount">&nbsp;&nbsp;销量：<%=goodsList.get(i).getGoods_sales_volume() %>件</strong>
									</p>

									<p>
										<div class="cartButton">
									    	<a href="OneGoodSettlementServlet?goodsId=<%=goodsList.get(i).getGoods_id()%>" class="btn btn-buy" style="color:white;">立即购买</a>
								    	</div>
										<div class="cartButton">
									    	<a href="GoodsDetailServlet?id=<%=goodsList.get(i).getGoods_id()%>&shopcartflag=0" class="btn btn-buy"style="color:white;">查看详情</a>
								    	</div>
									</p>
								</div></li>
								<%}} %>
						</ul>
					</div>
					</details>
					<details  style="margin-top:0px;">
						<summary class="sliver">营养食品</summary>
						<div style="margin-left:20px;margin-top:10px;"><p>敬请期待</p></div>
					</details>
					<details  style="margin-top:0px;">
						<summary class="sliver">儿童食品</summary>
						<div style="margin-left:20px;margin-top:10px;"><p>敬请期待</p></div>
					</details>
			</article>


		</section>
	</div>
	<!--- third -->
	<script src="web_mobile/js/jquery-2.1.3.js"></script>
	<script src="web_mobile/js/jquery.mobile.custom.min.js"></script>
	<!---  agile -->
	<script type="text/javascript" src="web_mobile/js/agile.js"></script>
	<!-- app -->
	<script type="text/javascript" src="web_mobile/js/app.seedsui.js"></script>
	<script type="text/javascript" src="web_mobile/js/seedsui.min.js"></script>

</body>
</html>