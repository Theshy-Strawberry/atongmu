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
<title>商城首页</title>
</head>
<body>

	<header class="header header_1">
		<div class="fix_nav" style="background-color:#0099ff">
			<div class="nav_inner">
				<div class="tit">阿同木在线商城</div>
			</div>
		</div>
	</header>

	<div class="container">
		<div id="slide">
			<div class="hd">
				<ul>
					<li class="on">1</li>
					<li class="on">2</li>
					<li class="on">3</li>
				</ul>
			</div>
			<div class="bd">
				<div class="tempWrap" style="overflow: hidden; position: relative;">
					<ul
						style="width: 3840px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(-768px);">
						<li
							style="display: table-cell; vertical-align: top; width: 768px;">
							<img src="web_mobile/img/goods1.jpg" alt="商品1"
								>
						</li>
						<li
							style="display: table-cell; vertical-align: top; width: 768px;">
							 <img src="web_mobile/img/goods2.jpg" alt="商品2"
								>
						</li>
						<li
							style="display: table-cell; vertical-align: top; width: 768px;">
							<img src="web_mobile/img/goods3.jpg" alt="商品3"
								>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script charset="utf-8" src="web_mobile/js/TouchSlide.js"></script>

	<script type="text/javascript">
		TouchSlide({
			slideCell : "#slide",
			titCell : ".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
			mainCell : ".bd ul",
			effect : "left",
			autoPlay : true,//自动播放
			autoPage : true, //自动分页
			switchLoad : "_src" //切换加载，真实图片路径为"_src"
		});
	</script>

	<!--产品块-->
	<div class="tb_box">

		<div class="hotImage">
			<img src="web_mobile/img/hot.jpg" style="width:100%"/>
		</div>

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
					    	<a href="GoodsDetailServlet?id=<%=goodsList.get(i).getGoods_id()%>&shopcartflag=0" class="btn btn-buy">查看详情</a>
				    	</div>
					</p>
				</div></li>
				<%}} %>
		</ul>
	</div>

	<footer class="footer">
		<div class="foot-con">
			<div class="foot-con_2">
				<a href="GoodsShowServlet" class="active"> <i class="navIcon home"></i>
					<span class="text">首页</span>
				</a> <a href="ShowShoppingCarListServlet"> <i class="navIcon shop"></i> <span
					class="text">购物车</span>
				</a> <a href="OrderShowServlet"> <i class="navIcon sort"></i> <span
					class="text">我的订单</span>
				</a>
				<%
				    Object roleObj = request.getSession().getAttribute("role");
				    if(null != roleObj){
				        int role = (Integer)roleObj;
				        if(role == 1){%>
				    <a href="web_mobile/userhome.jsp"> <i class="navIcon member"></i> <span class="text">个人中心</span></a>
				<%
				        }else if(role == 2){%>
				    <a href="web_mobile/salemanhome.jsp"> <i class="navIcon member"></i> <span class="text">我的分销</span></a>
				<%
				        }
				    }
				%>
			</div>
		</div>
	</footer>

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

