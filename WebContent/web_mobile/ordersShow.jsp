<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!-- HTML5文件 -->
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
<style type="text/css">
.tabbar_menu {
	display: table;
	overflow: hidden;
	font-size: 12px;
	font-weight: 400;
	min-height: 44px;
	margin-top: 45px;
	background-color: #ffffff;
}

.tab_menu {
	display: table-cell;
	width: 1%;
	padding-top: 14px;
	padding-bottom: 16px;
	overflow: hidden;
	line-height: 1;
	text-align: center;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.tab_menu:active,.tab_menu.active {
	-webkit-box-sizing: border-box;
	background-color: #ffffff;
	border-bottom: 2px solid #ff8000;
}

.tab_menu .iconfont {
	top: 3px;
	width: 24px;
	height: 24px;
	padding-top: 0;
	padding-bottom: 0;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<meta content="telephone=no" name="format-detection">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1;user-scalable=no;">

<title>我的订单</title>
</head>
<body>
	<div id="section_container">
		<section id="index_section" data-role="section" class="active">
			<header class="header header_1">
				<div class="fix_nav" style="background-color: #0099ff">
					<div class="nav_inner">
						<div class="tit">我的订单</div>
					</div>
				</div>
				<div class="tabbar_menu">
					<a
						class="tab_menu <%if("0".equals(request.getAttribute("status").toString())){%>active<%}%>"
						data-role="tab" href="OrderShowServlet" data-toggle="article">全部订单</a>
					<a
						class="tab_menu <%if("1".equals(request.getAttribute("status").toString())){%>active<%}%>"
						data-role="tab" href="OrderShowServlet?status=1"
						data-toggle="article">未完成</a> <a
						class="tab_menu <%if("2".equals(request.getAttribute("status").toString())){%>active<%}%>"
						data-role="tab" href="OrderShowServlet?status=2"
						data-toggle="article">已完成</a>
				</div>
			</header>

			<div class="container"
				style="top: 80px; bottom: 40px; background-color: white; width: 100%; height: 100%;">
				<div class="row rowcar">
					<%
						request.getSession().removeAttribute("doTime");

						List<Map<String, Object>> orderGoodsList = (List<Map<String, Object>>)request.getAttribute("orderGoodsList");
						int i = 0;
						for (Map<String, Object> orderGoodsMap : orderGoodsList) {
					%>
					<ul class="list-group">
						<li class="list-group-item"
							style="width: 100%; height: 30px; border-bottom: #ffffff; border-bottom: none;">
							<div style="margin-top: -6px; float: left;">
								订单ID：<%=orderGoodsMap.get("order_id")%></div>
							<div style="margin-top: -6px; float: right;">
								订单状态：<%=orderGoodsMap.get("order_status")%>
							</div>
						</li>
						<li class="list-group-item"
							style="width: 100%; height: 30px; border-bottom: #ffffff; border-top: none;">
							<div style="margin-top: -6px; float: left;">
								下单时间：<%=orderGoodsMap.get("order_date").toString().substring(0,19)%></div>
						</li>
						<%
							List<Map<String,Object>> goodsList = (List<Map<String, Object>>) orderGoodsMap.get("goodsList");
							for(Map<String,Object> goodsMap : goodsList){
						%>
						<li class="list-group-item hproduct clearfix">
							<div class="p-pic">
								<a href=""><img class="img-responsive"
									src="GoodsPictureShowServlet?id=1&goods_id=<%=goodsMap.get("goods_id")%>"></a>
							</div>
							<div class="p-info">
								<a href=""><p class="p-title"><%=goodsMap.get("goods_name")%></p></a>
								<p class="p-origin">
									<em class="old-price"><%=goodsMap.get("goods_price")%> ×<%=goodsMap.get("goods_number")%></em>
								</p>
							</div>
						</li>
						<%
							}
						%>
						<li class="list-group-item clearfix" style="padding: 3px 15px;">
							<div class="pull-left-order mt5" style="height: 25px;">
								<span class="gary">共<%=goodsList.size()%>件商品 合计：
								</span> <em class="red productTotalPrice">¥<%=orderGoodsMap.get("order_price")%></em>
							</div>

						</li>
						<li class="list-group-item clearfix"
							style="height: 45px; padding: 5px 15px; border-top: none; margin-top: -5px;">
							<div class="pull-left-order mt5">
								<%
									if(orderGoodsMap.get("order_status_num").toString().equals("1")){
								%>
								<div class="buy-btn-fix" style="padding: 0.3em;">
									<a id="<%=i%>"
										href="javascript:wechatPay('<%=orderGoodsMap.get("order_id")%>');"
										 class="btn btn-danger btn-buy">付款</a>
								</div>
								<div class="buy-btn-fix"
									style="padding: 0.3em; margin-right: 65px;">
									<a id="<%=i%>"
										href="OrderCancelServlet?order_id=<%=orderGoodsMap.get("order_id")%>"
										class="btn btn-danger btn-buy">取消订单</a>
								</div>
								<%
									}else if(orderGoodsMap.get("order_status_num").toString().equals("3")){
								%>
								<div class="buy-btn-fix"
									style="padding: 0.3em; margin-right: 65px;">
									<a id="<%=i%>"
										href="http://m.kuaidi100.com/index_all.html?type=<%=orderGoodsMap.get("logistics_company")%>&postid=<%=orderGoodsMap.get("logistics_number")%>&callbackurl=http://www.in-artoon.com/atongmu/OrderShowServlet"
										class="btn btn-danger btn-buy">查询物流</a>
								</div>
								<div class="buy-btn-fix"
									style="padding: 0.3em; margin-right: 155px;">
									<a id="<%=i%>"
										href="ConfirmGoodsReceiptServlet?order_id=<%=orderGoodsMap.get("order_id")%>"
										class="btn btn-danger btn-buy">确认收货</a>
								</div>
								<div class="buy-btn-fix"
									style="padding: 0.3em; margin-right: 245px;">
									<a id="<%=i%>" href=";" class="btn btn-danger btn-buy">申请退款</a>
								</div>
								<%
									}
								%>
							</div>
						</li>
					</ul>
					<%
						i++; }
					%>
					<%
						if(orderGoodsList.size() == 0){
					%>
					当前订单列表为空
					<%
						}
					%>
				</div>
			</div>
		</section>
	</div>
	<div class="clear"></div>
	<footer class="footer">
		<div class="foot-con">
			<div class="foot-con_2">
				<a href="GoodsShowServlet"> <i class="navIcon home"></i> <span
					class="text">首页</span>
				</a> <a href="ShowShoppingCarListServlet"> <i class="navIcon shop"></i>
					<span class="text">购物车</span>
				</a> <a href="OrderShowServlet" class="active"> <i
					class="navIcon sort"></i> <span class="text">我的订单</span>
				</a>
				<%
					Object roleObj = request.getSession().getAttribute("role");
					if(null != roleObj){
						int role = (Integer)roleObj;
						if(role == 1){
				%>
				<a href="web_mobile/userhome.jsp"> <i class="navIcon member"></i>
					<span class="text">个人中心</span></a>
				<%
						}else if(role == 2){
				%>
				<a href="web_mobile/salemanhome.jsp"> <i class="navIcon member"></i>
					<span class="text">我的分销</span></a>
				<%
						}
					}
				%>
			</div>
		</div>
	</footer>

	<!--- third --->
	<script src="web_mobile/js/jquery-2.1.3.min.js"></script>
	<!-- app -->
	<script type="text/javascript" src="web_mobile/js/weixin.js"></script>
	<script type="text/javascript">
		/* 	wx.config({
		    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '', // 必填，公众号的唯一标识
		    timestamp: '', // 必填，生成签名的时间戳
		    nonceStr: '', // 必填，生成签名的随机串
		    signature: '',// 必填，签名，见附录1
		    jsApiList: [] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		wx.ready(function(){

		    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		});
		wx.error(function(res){

		    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

		});  */
		function wechatPay(orderId) {
			$.ajax({
				url : "OrderPayInfoServlet",
				data : {
					orderId : orderId
				},
				dataType : "json",
				timeout : 10000,
				success : function(data) {
					/* 				 console.log(data);
					 console.log(data[0].appId);
					 console.log(data[0].timeStamp);
					 console.log(data[0].nonceStr);
					 console.log(data[0].package);
					 console.log(data[0].signType);
					 console.log(data[0].paySign); */
					/*	 wx.config({
					       debug: true,
					       appId: data[0].appId,
					       timestamp: data[0].timeStamp,
					       nonceStr: data[0].nonceStr,
					       signature: data[0].paySign,
					       jsApiList: [
					       'checkJsApi',
					       'onMenuShareTimeline',
					       'onMenuShareAppMessage',
					       'onMenuShareQQ',
					       'onMenuShareWeibo',
					       'hideMenuItems',
					       'showMenuItems',
					       'hideAllNonBaseMenuItem',
					       'showAllNonBaseMenuItem',
					       'translateVoice',
					       'startRecord',
					       'stopRecord',
					       'onRecordEnd',
					       'playVoice',
					       'pauseVoice',
					       'stopVoice',
					       'uploadVoice',
					       'downloadVoice',
					       'chooseImage',
					       'previewImage',
					       'uploadImage',
					       'downloadImage',
					       'getNetworkType',
					       'openLocation',
					       'getLocation',
					       'hideOptionMenu',
					       'showOptionMenu',
					       'closeWindow',
					       'scanQRCode',
					       'chooseWXPay',
					       'openProductSpecificView',
					       'addCard',
					       'chooseCard',
					       'openCard'
					       ]
					       });

					wx.chooseWXPay({
					 appId : data[0].appId,
					 timestamp : data[0].timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
					 nonceStr : data[0].nonceStr, // 支付签名随机串，不长于 32 位
					 package : data[0].package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
					 signType : data[0].signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
					 paySign : data[0].paySign, // 支付签名
					 success : function (res) {
					         // 支付成功后的回调函数
					           if(res.errMsg == "chooseWXPay:ok" ) {
							      //支付成功
							  }else{
							      alert(res.errMsg);
							  }
					    }
					}); */

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