<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="web_mobile/js/jquery.min.js"></script>
<script charset="utf-8" src="web_mobile/js/global.js"></script>
<script charset="utf-8" src="web_mobile/js/bootstrap.min.js"></script>
<script charset="utf-8" src="web_mobile/js/template.js"></script>

<script type="text/javascript">
function changecount(){
        	 var count1 = document.getElementById('prodCount').value;
        	 var count2 = document.getElementById('prodStock').innerText;
             if(parseInt(count1) >= parseInt(count2)){
            	 document.getElementById('check').click();
            	 if(count1==1){
            		  document.getElementById('prodCount').value = 0;
              }
         }
       }

</script>
<link rel="stylesheet" href="web_mobile/css/bootstrap.css">
<link rel="stylesheet" href="web_mobile/css/style.css">
<link rel="stylesheet" href="web_mobile/css/member.css">
<link rel="stylesheet" href="web_mobile/css/order3.css"><meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">
 <%
 Object objectname = request.getAttribute("goods_name");
 String goodsName = (String)objectname;
 %>
<title><%=goodsName %></title>
<link rel="stylesheet" href="web_mobile/css/productDetail.css">
<script charset="utf-8" src="web_mobile/js/TouchSlide.js"></script>
<script charset="utf-8" src="web_mobile/js/prodDetail.js"></script>

<style type="text/css">
.details_con li .tb-out-of-stock{
border: 1px dashed #bbb;
color:#bbb;
cursor: not-allowed;
}
.no-selected{
background: #ffe8e8 none repeat scroll 0 0;
border: 2px solid #be0106;
margin: -1px;
}
.container{
padding-left:15px;
padding-right:15px;
}
.price{color: #d01415; font-style: normal; font-size:1.2em; margin-right:5px; vertical-align: top; }
</style>
</head>

<body>
<%
    Object status = request.getParameter("status");
    if(status != null){
    	int returncode = Integer.parseInt(status.toString());
    	if(returncode == 1 ){
    		//更新成功
    		%>
				<script>
				alert("加入购物车成功！");
				</script>
    	<%
    	}
    }
    	%>
<div class="fanhui_cou">
	<div class="fanhui_1"></div>
	<div class="fanhui_ding">顶部</div>
</div>

<header class="header">
	<div class="fix_nav">
		<div style="max-width:768px;margin:0 auto;background:#363333;position: relative;">
			<a class="nav-left back-icon" href="javascript:history.back(-1);">返回</a>
			<div class="tit">商品详细</div>
		</div>
	</div>
</header>
<input type="hidden" id="prodId" value="658"/>
<input id="currSkuId" value="" type="hidden"/>
<div class="container">
    <div class="row white-bg">
        <div id="slide">
            <div class="hd">
                <ul>
                	<li class="on">1</li>
                	<li class="on">2</li>
                	<li class="on">3</li>
                	<li class="on">4</li>
                	<li class="on">5</li>
                </ul>
            </div>

            <div class="bd">
            <%
             Object objectgoodsid = request.getAttribute("goodsid");
             String goods_id = String.valueOf(objectgoodsid.toString());
            %>
                <div class="tempWrap" style="overflow:hidden; position:relative;">
                	<ul style="width: 3072px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(-768px);">
                        <li style="display: table-cell; vertical-align: middle; max-width: 768px;">
                        		<a href="#">
                        		<img style="max-width:100vw;max-height:80vw;margin:auto;" src="http://www.in-artoon.com/goods_images/<%=goods_id %>_1.jpg"></a>
                        </li>
                        <li style="display: table-cell; vertical-align: middle; max-width: 768px;">
                        		<a href="#">
                        		<img style="max-width:100vw;max-height:80vw;margin:auto;" src="http://www.in-artoon.com/goods_images/<%=goods_id %>_2.jpg"></a>
                        </li>
                        <li style="display: table-cell; vertical-align: middle; max-width: 768px;">
                        		<a href="#">
                        		<img style="max-width:100vw;max-height:80vw;margin:auto;" src="http://www.in-artoon.com/goods_images/<%=goods_id %>_3.jpg"></a>
                        </li>
                        <li style="display: table-cell; vertical-align: middle; max-width: 768px;">
                        		<a href="#">
                        		<img style="max-width:100vw;max-height:80vw;margin:auto;" src="http://www.in-artoon.com/goods_images/<%=goods_id %>_4.jpg"></a>
                        </li>
                        <li style="display: table-cell; vertical-align: middle; max-width: 768px;">
                        		<a href="#">
                        		<img style="max-width:100vw;max-height:80vw;margin:auto;" src="http://www.in-artoon.com/goods_images/<%=goods_id %>_5.jpg"></a>
                        </li>
                    </ul>
               </div>

            </div>
        </div>
    </div>

	<%
	   Object objectprice = request.getAttribute("goods_price");
	   Double goodsPrice = (Double)objectprice;
	   Object objectintegral = request.getAttribute("goods_integral");
	   Double goodsIntegral = (Double)objectintegral;
	   Object objectstock = request.getAttribute("goods_stock");
	   Integer goodsStock = (Integer)objectstock;
	   Object objectgoodssalesvolume = request.getAttribute("goods_sales_volume");
	   Integer goodssalevolume = (Integer)objectgoodssalesvolume;
	   Object objectgoodscount = request.getAttribute("goods_count");
	   Integer goodscount = (Integer)objectgoodscount;
	   Object objeceusergoodscount = request.getAttribute("user_goods_count");
	   Integer usergoodscount = (Integer)objeceusergoodscount;
	   Object objectgoods_spec = request.getAttribute("goods_spec");
	   String goods_spec = (String)objectgoods_spec;
	   Object objectgoods_describe = request.getAttribute("goods_describe");
	   String goods_describe = (String)objectgoods_describe;

	%>
    <div class="row gary-bg" style="padding-bottom:60px;margin-top:10px;">
         <div class="white-bg p10 details_con">
         <h2 class="item-name" id="prodName" style="color:#333333;font-size:20px;"><%=goodsName%></h2>
         <ul style="margin-top:10px;">
			<li>
				<label>商城价格：</label>
				<span class="price">¥<span class="price" id="prodCash" > <%=goodsPrice%></span></span>
			</li>
		<%
	  		Object roleObj = request.getSession().getAttribute("role");
			int role = -1;
	    	if(null != roleObj){
	    		role = (Integer)roleObj;
	            if(role == 1){
		%>
			<li>
				<label>可获积分：<%=goodsIntegral%>分</label>
			</li>
		<%} }%>

			<li>
				<label>购买数量：</label>
				<div class="count_div" style="height: 30px; width: 130px;">
					<a href="javascript:void(0);" id="check" class="minus" ></a>
					  <input type="text" class="count" value="1" id="prodCount" readonly="readonly"/>
					<a href="javascript:void(0);" class="add" onclick="return changecount();" ></a>
				</div>
			</li>
			<li>
				<label>商品销量：<%=goodssalevolume%>件</label>
			</li>
			<li>
				<label>商品库存：<span id="prodStock"><%=goodsStock%></span></label>
			</li>
			<li>
				<label>商品规格：<%=goods_spec%></label>
			</li>
			<li>
				<label>适用人群：<%=goods_describe%></label>
			</li>
			<li>
				<label>咨询电话：4006898199</label>
			</li>
			<li>
			<%request.getSession().setAttribute("goods_url", request.getAttribute("goods_url")); %>

				<div class="justify-content" style="margin-top:-15px;">
					<p>
					  <a class="btn btn-info btn-cart" style="width:100%;" onclick="javascript:window.location.href='web_mobile/productdetails.jsp';" >查看商品介绍</a>
					</p>
				</div>
			</li>
		</ul>
    </div>
</div>

<div class="fixed-foot">
	<div class="fixed_inner">
		<a class="cart-wrap" href="ShowShoppingCarListServlet">
			<i class="i-cart"></i>
			<span>购物车</span>
			<span class="add-num" id="totalNum"><%=usergoodscount%></span>
		</a>
		<div class="buy-btn-fix">
		    <a class="btn btn-info btn-cart"  onclick="return addShopCart(1);" >加入购物车</a>
		    <a class="btn btn-danger btn-buy" onClick="return addShopCart(2);">立即购买</a>
		</div>
	</div>
</div>
</div>
<div class="clear"></div>

<script type="text/javascript">
//插件：图片轮播
TouchSlide({
	slideCell:"#slide",
	titCell:".hd ul",
	mainCell:".bd ul",
	effect:"left",
	autoPlay:true,//自动播放
	autoPage:true, //自动分页
	switchLoad:"_src", //切换加载，真实图片路径为"_src"
	interTime:5000,
	delayTime:300
});

function addShopCart(type){
	var count = document.getElementById("prodCount").value;
	var count2 = document.getElementById('prodStock').innerText;
	if(parseInt(count) > parseInt(count2)){
		alert("您购买的数量大于库存数量，无法加入购物车");
		return false;
	}
	var goodid = <%=goods_id%>;
	if(type==1){
		window.location.href="AddShoppingCartServlet?goodsid="+goodid+"&count="+count;
		return true;
	}else{
		window.location.href="OneGoodSettlementServlet?goodsId="+goodid+"&count="+count;
		return true;
	}

}
</script>
<script type="text/javascript">
          function showHideCode(){
             $("#showdiv").toggle();
          }
</script>
</body>
</html>
