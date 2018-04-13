<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
import ="com.atongmu.bean.Tbl_commodity"
import = " java.text.SimpleDateFormat"
    pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="web_mobile/js/jquery.min.js"></script>
<script charset="utf-8" src="web_mobile/js/global.js"></script>
<script charset="utf-8" src="web_mobile/js/bootstrap.min.js"></script>
<script charset="utf-8" src="web_mobile/js/template.js"></script>
<script charset="utf-8" src="web_mobile/js/shopCart.js?v=01291"></script>

<link rel="stylesheet" href="web_mobile/css/bootstrap.css">
<link rel="stylesheet" href="web_mobile/css/style.css">
<link rel="stylesheet" href="web_mobile/css/member.css">
<link rel="stylesheet" href="web_mobile/css/order3.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">

<title>购物车</title>
</head>
<style type="text/css">
.container{
padding-left:15px;
padding-right:15px;
}
.price{color: #d01415; font-style: normal; font-size:1.2em; margin-right:5px; vertical-align: top; }
</style>
<body>
	<header class="header header_1">
		<div class="fix_nav" style="background-color:#0099ff">
			<div class="nav_inner">
				<div class="tit">购物车</div>
			</div>
		</div>
	</header>
       <%
            Object list = request.getAttribute("shoppingcarlist");
            if(list != null){
        	    List<Tbl_commodity> shoppingcarlist = (List<Tbl_commodity>)list;
        	    if(shoppingcarlist.size() == 0){
        	    	%>
		        	<div class="container ">
						<div class="row rowcar">
		               		<img src="web_mobile/img/nullcart.png" style="width:300px;height:140px;margin-top:80px;margin-left:20px;"/>
		            	</div>
		            </div>
        	    	<%
        	    }
		%>
	<div class="container ">
		<div class="row rowcar">
			<ul class="list-group">
			<%
			 for(int i=0;i<shoppingcarlist.size();i++){
					int shopping_cart_id = shoppingcarlist.get(i).getShopping_cart_id();
	            	String add_user_id = shoppingcarlist.get(i).getAdd_user_id();
			    	int addcount = shoppingcarlist.get(i).getGoods_count()+1;
			    	int minuscount = shoppingcarlist.get(i).getGoods_count()-1;
			    	int goods_id = shoppingcarlist.get(i).getGoods_id();
			    	String goods_name = shoppingcarlist.get(i).getGoods_name();
			%>
				<li class="list-group-item hproduct clearfix">
					<div class="p-pic">
						<a href="/views/663"><img class="img-responsive"
							src="GoodsPictureShowServlet?id=1&goods_id=<%=shoppingcarlist.get(i).getGoods_id()%>"></a>
					</div>
					<div class="p-info">
						<p class="p-title"><%=goods_name%></p>
						<p class="p-origin">
							<a class="close p-close"
								onclick="deleteShopCart('','<%=goods_name%>','663','1358')"
								href="DeleteShoppingCarGoodsServlet?shopping_cart_id=<%=shopping_cart_id%>&add_user_id=<%=add_user_id%>&goods_id=<%=goods_id%>">×</a> <em class="price"> <%=shoppingcarlist.get(i).getGoods_price()%></em>
						</p>
					</div>
				</li>

				<li class="list-group-item clearfix">
					<div class="pull-left mt5">
						<span class="gary">小计：</span> <em class="red productTotalPrice"><%=shoppingcarlist.get(i).getGoods_price()*shoppingcarlist.get(i).getGoods_count() %></em>
					</div>
					<div class="btn-group btn-group-sm control-num">
						<a href="UpdateShoppingCarCountServlet?shopping_cart_id=<%=shopping_cart_id%>&add_user_id=<%=add_user_id%>&goods_count=<%=minuscount%>"class="btn btn-default">
						    <span class="glyphicon glyphicon-minus gary"></span>
						</a>
						<input type="tel" class="btn gary2 Amount" readonly="readonly" value="<%=shoppingcarlist.get(i).getGoods_count()%>"
							maxlength="4" itemkey="" >
						 <a href="UpdateShoppingCarCountServlet?shopping_cart_id=<%=shopping_cart_id%>&add_user_id=<%=add_user_id%>&goods_count=<%=addcount%>" class="btn btn-default">
						     <span class="glyphicon glyphicon-plus gary"></span>
						 </a>
					</div>
				</li>
			<%}
			%>
			</ul>
		</div>
	</div>
	<%
	 if(shoppingcarlist.size() > 0){
	%>
	<div class="fixed_inner">
		<div class="pay-point">

			<p>
			  <%
		  Object talprice = request.getAttribute("totalprice");
		  double totalprice = Double.parseDouble(talprice.toString());
		  %>
				合计：<em class="red f22">¥<span id="totalPrice"><%=totalprice%></span></em>
			</p>
		</div>
		<div class="buy-btn-fix">
			<a href="GoodsSettlementServlet" class="btn btn-danger btn-buy">去结算</a>
		</div>
	</div>
      <%
		 }
     }
	  %>
	<div class="clear"></div>
	<footer class="footer">
		<div class="foot-con">
			<div class="foot-con_2">
				<a href="GoodsShowServlet" > <i class="navIcon home"></i>
					<span class="text">首页</span>
				</a> <a href="ShowShoppingCarListServlet" class="active"> <i class="navIcon shop"></i> <span
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
		var contextPath = '';
	</script>
</body>
</html>
