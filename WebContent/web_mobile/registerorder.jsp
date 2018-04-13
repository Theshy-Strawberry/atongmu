<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.atongmu.bean.Tbl_commodity"
	import=" java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script charset="utf-8" src="web_mobile/js/jquery.min.js"></script>
<script charset="utf-8" src="web_mobile/js/global.js"></script>
<script charset="utf-8" src="web_mobile/js/bootstrap.min.js"></script>
<script charset="utf-8" src="web_mobile/js/template.js"></script>
<script charset="utf-8" src="web_mobile/js/shopCart.js?v=01291"></script>
<script charset="utf-8"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<link rel="stylesheet" href="web_mobile/css/order3.css">
<link rel="stylesheet" href="web_mobile/css/agile.layout.css">
<link rel="stylesheet" href="web_mobile/css/seedsui.min.css">
<link rel="stylesheet" type="text/css" href="web_mobile/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">

<title>确认订单</title>
</head>
<style>
.btn {
	display: inline-block;
	padding: 0.4em 1.4em;
	margin-bottom: 0;
	font-size: 0.8em;
	font-weight: normal;
	line-height: 1.5;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	background-image: none;
	border: 1.5px solid transparent;
	border-radius: 1.5em;
}

.btn:focus,.btn:active:focus,.btn.active:focus {
	outline: thin dotted;
	outline: 5px auto -webkit-focus-ring-color;
	outline-offset: -2px;
}

.btn:hover,.btn:focus {
	color: #333;
	text-decoration: none;
}

.btn:active,.btn.active {
	background-image: none;
	outline: 0;
	-webkit-box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
	box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
}

.btn.disabled,.btn[disabled],fieldset[disabled] .btn {
	pointer-events: none;
	cursor: not-allowed;
	filter: alpha(opacity = 65);
	-webkit-box-shadow: none;
	box-shadow: none;
	opacity: .65;
}

</style>
<body>
	<header class="header">
		<div class="fix_nav">
			<div
				style="max-width: 768px; margin: 0 auto; background: #363333; position: relative;">
				<a class="nav-left back-icon" href="javascript:history.go(-1);">返回</a>
				<div class="tit">确认订单</div>
			</div>
		</div>
	</header>
	<div class="container ">
		<ul class="list">
			<li class="nojustify">
				<div class="justify-content">
					<p>
						<span class="mark-success" style="width: 3.8rem;">收件人地址</span>
					</p>
					<%
						Map<String, Object> userMap = (Map<String, Object>) request.getAttribute("userInfo");
						String addr = userMap.get("userAddr").toString();
						if(addr.length()>=16){
					%>
					<div style="float: left; margin-top: -30px; margin-left: 100px;"><%=userMap.get("userAddr")%></div>
					<%
						}else{
					%>
					<div style="float: left; margin-top: -20px; margin-left: 100px;"><%=userMap.get("userAddr")%></div>
					<%
						}
					%>
				</div>
				<div class="justify-content">
					<p>
						<span class="mark-success" style="width: 3.8rem;">收件人姓名</span><%=userMap.get("userName")%>
					</p>
				</div>
				<div class="justify-content">
					<p>
						<span class="mark-success" style="width: 3.8rem;">收件人电话</span><%=userMap.get("userTel")%>
					</p>
				</div>
			</li>
			<li class="nojustify">
				<div class="justify-content">
					<p>
						<a href="web_mobile/updateInfo.jsp?fromurl=order"
							class="radius4 button block margin8">修改收货地址</a>
					</p>
				</div>
			</li>
		</ul>
		<div class="row rowcar">
			<ul class="list-group">
				<%
					List<Tbl_commodity> goodsList = (List<Tbl_commodity>)request.getSession().getAttribute("goodsList");
					for (Tbl_commodity tbl_commodity : goodsList) {
				%>
				<li class="list-group-item hproduct clearfix">
					<div class="p-pic">
						<img class="img-responsive" style="width: 100px; height: 100px;"
							src="GoodsPictureShowServlet?id=1&goods_id=<%=tbl_commodity.getGoods_id()%>">
					</div>
					<div class="p-info">
						<p class="p-title">
							商品名称：<%=tbl_commodity.getGoods_name()%></p>
						<p class=".p-info" style="margin-top: -14px;">
							商品价格：<%=tbl_commodity.getGoods_price()%></p>
						<p class=".p-info">
							商品数量：<%=tbl_commodity.getGoods_count()%></p>
						<p class=".p-info">
							商品总价：<%=tbl_commodity.getGoods_count()*tbl_commodity.getGoods_price()%>
						</p>
					</div>
				</li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<div class="clear"></div>
	<footer class="footer">
		<div class="fixed_inner">
			<div class="pay-point">
				<p>
					订单总价：<em class="red f22">¥<span id="totalPrice"><%=request.getAttribute("orderPrice")%></span></em>
				</p>
			</div>
			<div class="buy-btn-fix">
				<a
					href="javascript:wechatPay('<%=request.getAttribute("orderId")%>');"
					class="btn btn-danger btn-buy">立即支付</a>
			</div>
		</div>
	</footer>
	<script type="text/javascript">
		var contextPath = '';
	</script>
	<script type="text/javascript">
		function wechatPay(orderId) {
			$.ajax({
				url : "OrderPayInfoServlet",
				data : {
					orderId : orderId
				},
				dataType : "json",
				timeout : 10000,
				success : function(data) {
					//从PHP Demo里拿到的代码
					WeixinJSBridge.invoke('getBrandWCPayRequest', {
						"appId" : data[0].appId, //公众号名称，由商户传入
						"timeStamp" : data[0].timeStamp, //时间戳，自1970年以来的秒数
						"nonceStr" : data[0].nonceStr, //随机串
						"package" : data[0].package,
						"signType" : data[0].signType, //微信签名方式
						"paySign" : data[0].paySign
					//微信签名
					}, function(res) {
						if (res.err_msg != "get_brand_wcpay_request:ok") {
							alert("微信支付失败。");
							window.location.href = "OrderShowServlet";
							return;
						}
						// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
						window.location.href = "GoodsPayServlet?orderId="+ orderId;
					});
				},
				error : function() {
					alert("微信支付启动失败，请您确认微信支付功能是否开通。");
				}
			});
		}
	</script>
</body>
</html>
