<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" import ="com.atongmu.bean.Tbl_commodity,com.atongmu.bean.Tbl_user"
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
<title>阿同木在线商城</title>
</head>
<body>
<script>
	onload=function(){
		document.getElementById("healthInfo").style.display="none";
	}
	var my_time;
	function closeDiv(){
		$("#banner_bar_lable").fadeOut("slow");
	}
	function showHealth(){
		if (my_time!=null){
			clearTimeout(my_time);
		}
		if(document.getElementById("healthInfo").style.display=="none"){
			$("#goodsList").fadeOut("slow");
			$("#healthInfo").fadeIn("slow");
			document.getElementById("goodsList").style.display="none";
			document.getElementById("healthInfo").style.display="";
			if(document.getElementById("banner_bar_lable").style.display=="none"){
				$("#banner_bar_lable").fadeIn("slow");
				document.getElementById("bar_text").innerHTML = "量身定制是按功能需要根据您的身体实际数据由具有研发临床经验能力的专家一对一定制的食疗方案。";
			}else{
				document.getElementById("bar_text").innerHTML = "量身定制是按功能需要根据您的身体实际数据由具有研发临床经验能力的专家一对一定制的食疗方案。";
			}
		}
		my_time = setTimeout("closeDiv() ", 10000 );

	}
	function showGreenType(){
		if (my_time!=null){
			clearTimeout(my_time);
		}
		$("#goodsList").fadeOut("slow");
		$("#healthInfo").fadeOut("slow");
		document.getElementById("goodsList").style.display="none";
		document.getElementById("healthInfo").style.display="none";
		if(document.getElementById("banner_bar_lable").style.display=="none"){
			$("#banner_bar_lable").fadeIn("slow");
			document.getElementById("bar_text").innerHTML = "绿色生态食品产自优良生态环境、按照绿色食品标准生产、实行全程质量控制并获得绿色食品标志使用权的安全、优质食用农产品及相关产品。我们将不定期的邀请会员到各生态基地免费实地考察。";
		}else{
			document.getElementById("bar_text").innerHTML = "绿色生态食品产自优良生态环境、按照绿色食品标准生产、实行全程质量控制并获得绿色食品标志使用权的安全、优质食用农产品及相关产品。我们将不定期的邀请会员到各生态基地免费实地考察。";
		}
		my_time = setTimeout("closeDiv() ", 10000 );

	}
	function showGoodsType1(){
		if (my_time!=null){
			clearTimeout(my_time);
		}
		if(document.getElementById("goodsList").style.display=="none"){
			$("#goodsList").fadeOut("slow");
			$("#healthInfo").fadeIn("slow");
			document.getElementById("healthInfo").style.display="none";
			document.getElementById("goodsList").style.display="";
			if(document.getElementById("banner_bar_lable").style.display=="none"){
				$("#banner_bar_lable").fadeIn("slow");
				document.getElementById("bar_text").innerHTML = "健康食品（Healthy food）是食品的一个种类，具有一般食品的共性，其原材料也主要取自天然的动植物，经先进生产工艺，将其所含丰富的功效成分作用发挥到极至，从而能调节人体机能，适用于有特定功能需求的相应人群食用的特殊食品。";
			}else{
				document.getElementById("bar_text").innerHTML = "健康食品（Healthy food）是食品的一个种类，具有一般食品的共性，其原材料也主要取自天然的动植物，经先进生产工艺，将其所含丰富的功效成分作用发挥到极至，从而能调节人体机能，适用于有特定功能需求的相应人群食用的特殊食品。";
			}
		}else{
			if(document.getElementById("banner_bar_lable").style.display=="none"){
				$("#banner_bar_lable").fadeIn("slow");
				document.getElementById("bar_text").innerHTML = "健康食品（Healthy food）是食品的一个种类，具有一般食品的共性，其原材料也主要取自天然的动植物，经先进生产工艺，将其所含丰富的功效成分作用发挥到极至，从而能调节人体机能，适用于有特定功能需求的相应人群食用的特殊食品。";
			}else{
				document.getElementById("bar_text").innerHTML = "健康食品（Healthy food）是食品的一个种类，具有一般食品的共性，其原材料也主要取自天然的动植物，经先进生产工艺，将其所含丰富的功效成分作用发挥到极至，从而能调节人体机能，适用于有特定功能需求的相应人群食用的特殊食品。";
			}
		}
		my_time = setTimeout("closeDiv() ", 10000 );
	}
</script>

	<div class="">
		<div id="slide">
			<div class="hd">
				<ul>
					<li class="on">1</li>
					<li class="on">2</li>
				</ul>
			</div>
			<div class="bd" style="padding-left:0px;padding-right:0px;">
				<div class="tempWrap" style="overflow: hidden; position: relative;">
					<ul style="width: 3840px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translateX(-768px);">
						<li
							style="display: table-cell; vertical-align: top; width: 768px;">
							<img src="web_mobile/img/goods1.jpg" alt="商品1"/>
						</li>
						<li
							style="display: table-cell; vertical-align: top; width: 768px;">
							 <img src="web_mobile/img/goods2.jpg" alt="商品2"/>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script charset="utf-8" src="web_mobile/js/TouchSlide.js"></script>

	<script type="text/javascript">
	TouchSlide({
		slideCell:"#slide",
		titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		mainCell:".bd ul",
		effect:"left",
		autoPlay:true,//自动播放
		autoPage:true, //自动分页
		switchLoad:"_src", //切换加载，真实图片路径为"_src"
		interTime:5000,
		delayTime:300
	});
	</script>

<%

Object roleObj = request.getSession().getAttribute("role");
if(null != roleObj){
    int role = (Integer)roleObj;
%>
	<div class="goodsMenu">
		<div class="goodsMenuItem">
			<div class="menuItemUp">
				<a href="web_mobile/healthcenter.jsp">
				<img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon1.png">
				</a>
			</div>
			<div class="menuItemDown" style="width:90px;height:35px;margin-left:0;"><a href="web_mobile/healthcenter.jsp" >健康管理中心</a></div>
		</div>
		<div class="goodsMenuItem">
		<%if (role==1){
			Tbl_user loginuser = (Tbl_user)session.getAttribute("loginuser");
			if(loginuser.getVip_flag() == 0){
		%>
					<div class="menuItemUp">
						<a href="web_mobile/updateUserInfo.jsp?errorcode=9">
				        <img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon3.png"></a>
				    </div>
		<% }else if(loginuser.getVip_flag() == 1){ %>
					<div class="menuItemUp">
				        <a href="web_mobile/userhome.jsp">
				        <img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon2.png"></a>
					</div>
			<%
			}else{
				%>
			<div class="menuItemUp">
				<a href="web_mobile/userhome.jsp">
				<img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon2.png">
				</a>
			</div>
			<%
		    }
		}else{
			%>
			<div class="menuItemUp">
				<a href="web_mobile/salemanhome.jsp">
				<img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon2.png">
				</a>
			</div>
			<%
		}
		%>


			<%if (role==1){
				Tbl_user loginuser = (Tbl_user)session.getAttribute("loginuser");
				if(loginuser.getVip_flag() == 0){
			%>
					<div class="menuItemDown" style="width:80px;height:35px;margin-left:0;">
						<a href="web_mobile/updateUserInfo.jsp?errorcode=9">注册会员</a>
					</div>
				<%
				}else{
					%>
					<div class="menuItemDown" style="width:80px;height:35px;margin-left:0;">
						<a href="web_mobile/userhome.jsp">个人中心</a>
					</div>
				<%
			    }
			}else{
				%>
				<div class="menuItemDown" style="width:80px;height:35px;margin-left:0;">
					<a href="web_mobile/salemanhome.jsp">个人中心</a>
				</div>
				<%
			}
			%>
		</div>
		<div class="goodsMenuItem">

		<%if (role==1){
			Tbl_user loginuser = (Tbl_user)session.getAttribute("loginuser");
			if(loginuser.getVip_flag() == 0){
		%>
					<div class="menuItemUp">
						<a href="jarascript:alert('暂未开放，敬请期待');"onclick="alert('暂未开放，敬请期待');">
				        <img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon7.png"></a>
				    </div>
		<% }else if(loginuser.getVip_flag() == 1){ %>
					<div class="menuItemUp">
				        <a href="jarascript:alert('暂未开放，敬请期待');"onclick="alert('暂未开放，敬请期待');">
				        <img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon7.png"></a>
					</div>
			<%
			}else{
				%>
			<div class="menuItemUp">
				<a href="jarascript:alert('暂未开放，敬请期待');"onclick="alert('暂未开放，敬请期待');">
				<img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon7.png">
				</a>
			</div>
			<%
		    }
		}else{
			%>
			<div class="menuItemUp">
				<a href="jarascript:alert('暂未开放，敬请期待');"onclick="alert('暂未开放，敬请期待');">
				<img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon7.png">
				</a>
			</div>
			<%
		}
		%>

		<%if (role==1){
			Tbl_user loginuser = (Tbl_user)session.getAttribute("loginuser");
			if(loginuser.getVip_flag() == 0){
		%>
				<div class="menuItemDown" style="width:80px;height:35px;margin-left:0;">
					<a href="jarascript:alert('暂未开放，敬请期待');"onclick="alert('暂未开放，敬请期待');">我的钱袋</a>
				</div>
			<%
			}else{
				%>
				<div class="menuItemDown" style="width:80px;height:35px;margin-left:0;">
					<a href="jarascript:alert('暂未开放，敬请期待');"onclick="alert('暂未开放，敬请期待');">我的钱袋</a>
				</div>
			<%
		    }
		}else{
			%>
			<div class="menuItemDown" style="width:80px;height:35px;margin-left:0;">
				<a href="jarascript:alert('暂未开放，敬请期待');"onclick="alert('暂未开放，敬请期待');">我的钱袋</a>
			</div>
			<%
		}
		%>
		</div>
		<div class="goodsMenuItem">
			<div class="menuItemUp">
				<a href="#"onclick="showGreenType();">
				<img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon4.png">
				</a>
			</div>
			<div class="menuItemDown" style="width:90px;height:35px;margin-left:-5;"><a href="#"onclick="showGreenType();">绿色生态食品</a></div>
		</div>
		<div class="goodsMenuItem">
			<div class="menuItemUp">
				<a href="javascript:showGoodsType1();" onclick="showGoodsType1();">
				<img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon5.png">
				</a>
			</div>
			<div class="menuItemDown" style="width:80px;height:35px;margin-left:0;"><a href="javascript:showGoodsType1();"onclick="showGoodsType1();">健康食品</a></div>
		</div>
		<div class="goodsMenuItem">
			<div class="menuItemUp">
				<a href="javascript:showHealth();"  onclick="showHealth();">
				<img style="width:80px;height:80px;margin-left:0;" src="web_mobile/images/icon6.png">
				</a>
			</div>
			<div class="menuItemDown" style="width:90px;height:35px;margin-left:-5;"><a href="javascript:showHealth();"  onclick="showHealth();">量身定制食品</a></div>
		</div>
	</div>
		<div class="banner_bar_lable" id="banner_bar_lable" style="height:auto;display:none;" >
			<div class="bar_text" id="bar_text">
			量身定制是按功能需要根据您的身体实际数据由具有研发临床经验能力的专家一对一定制的食疗方案。
			</div>
		</div>
		<div id="healthInfo"  style="display:none;">
			<div class="hotImage"style="">
				<img src="web_mobile/img/private.jpg" style="width:100%;height:35px;margin-top:10px;"/>
	   		</div>
	   		<div class="health_table_div">
		   		<table class="health_table">
		   			<tr class="health_table_tr">
		   				<td class="health_image">
		   					<img src="web_mobile/images/health4.jpg"/>
		   					<div class="health_text">针对肿瘤的食疗方案</div>
		   				</td>
		   				<td class="health_image" >
		   					<img src="web_mobile/images/health2.jpg"/>
		   					<div class="health_text">针对高血压血脂食疗方案</div>
		   				</td>
		   			</tr>
		   			<tr class="health_table_tr">
		   				<td class="health_btn"><a href="HealthInfoReadyServlet"><img src="web_mobile/images/privatebtn.png"/></a></td>
		   				<td class="health_btn"><a href="HealthInfoReadyServlet"><img src="web_mobile/images/privatebtn.png"/></a></td>
		   			</tr>
		   			<tr class="health_table_tr">
		   				<td class="health_image">
		   					<img src="web_mobile/images/health3.jpg"/>
		   					<div class="health_text">针对心脑血管食疗方案</div>
		   				</td>
		   				<td class="health_image">
		   					<img src="web_mobile/images/health1.jpg"/>
		   					<div class="health_text">针对高血糖并发症食疗方案</div>
		   				</td>
		   			</tr>

		   			<tr class="health_table_tr">
		   				<td class="health_btn"><a href="HealthInfoReadyServlet"><img src="web_mobile/images/privatebtn.png"/></a></td>
		   				<td class="health_btn"><a href="HealthInfoReadyServlet"><img src="web_mobile/images/privatebtn.png"/></a></td>
		   			</tr>
		   			<tr class="health_table_tr2">
		   				<td colspan="2" class="health_image2">
		   					<img src="web_mobile/images/health5.jpg"/>
		   					<div class="health_text">针对其他健康食疗方案定制</div>
		   				</td>
		   			</tr>
		   			<tr class="health_table_tr2">
		   				<td colspan="2">
		   					<div class="health_btn2">
		   						<a href="HealthInfoReadyServlet"><img src="web_mobile/images/privatebtn.png"/></a>
		   					</div>
		   				</td>
		   			</tr>
		   		</table>
	   		</div>
		</div>


<div id="goodsList">
		<div class="hotImage"style="">
			<img src="web_mobile/img/hot.png" style="width:100%;height:35px;margin-top:10px;"/>
	   </div>
	   <div class="goodsListView">
<%
Object obj1 = request.getAttribute("goodsList");
if(obj1 != null){
	List<Tbl_commodity> goodsList = (List<Tbl_commodity>)obj1;
	for(int i = 0;i<goodsList.size();i++){ %>
	   		<div class="goodItem">
	   			<div class="goodImage">
	   				<a href="GoodsDetailServlet?id=<%=goodsList.get(i).getGoods_id()%>&shopcartflag=0" class="goodsPic">
	   				<img src="http://www.in-artoon.com/goods_images/<%=goodsList.get(i).getGoods_id() %>_1.jpg" />
	   				</a>
	   			</div>
	   			<div class="goodName">
						<a href="GoodsDetailServlet?id=<%=goodsList.get(i).getGoods_id()%>&shopcartflag=0"><%=goodsList.get(i).getGoods_name() %></a>
				</div>
	   			<div class="goodPriceRow">
	   				<div class="goodPriceDiv">
	   					<strong class="price" >￥<%=goodsList.get(i).getGoods_price() %></strong>
	   				</div>
	   				<div class="goodBuyDiv">
<%
if(role == 1){
	//会员的场合
	Tbl_user loginuser = (Tbl_user)session.getAttribute("loginuser");
	if(loginuser.getVip_flag()==0){
		//非VIP购买时，要先弹dialog
	%>
						<div class="" style="vertical-align: middle;">
					    	<a href="#" onclick="disp_confirm('<%=goodsList.get(i).getGoods_id()%>')" class="btn btn-buy">立即购买</a>
				    	</div>
	<%
	}else{
	%>
						<div class="" style="vertical-align: middle;">
					    	<a href="OneGoodSettlementServlet?goodsId=<%=goodsList.get(i).getGoods_id()%>" class="btn btn-buy">立即购买</a>
				    	</div>
	<%
	}
}else{
	%>
						<div class="" style="vertical-align: middle;">
					    	<a href="OneGoodSettlementServlet?goodsId=<%=goodsList.get(i).getGoods_id()%>" class="btn btn-buy">立即购买</a>
				    	</div>
<%
}
%>
	   				</div>
	   			</div>
	   		</div>
<%}} %>
	   </div>
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
	<script type="text/javascript">
	function disp_confirm(goodid){
		var r=confirm("注册成为会员，立即享受购物返积分及私人健康顾问的权利。");
		  if (r==true)
		    {
			  window.location.href="web_mobile/updateUserInfo.jsp?errorcode=9";
			  return true;
		    }
		  else
		    {
			  window.location.href="OneGoodSettlementServlet?goodsId="+goodid;
			  return true;
		    }
	}
	</script>
</body>
</html>

