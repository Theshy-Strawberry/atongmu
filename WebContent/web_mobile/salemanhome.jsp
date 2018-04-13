<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.atongmu.bean.Tbl_saleman"
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
<title>阿同木用户中心</title>
<link rel="stylesheet" href="css/agile.layout.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/seedsui.min.css">
<link rel="stylesheet" type="text/css" href="css/userhome.css">
</head>
<body>
	<div id="section_container">
		<section id="index_section" data-role="section" class="active">
			<header>
				<div class="fix_nav" style="background-color:#0099ff">
					<div class="nav_inner">
						<div class="tit">我的分销</div>
					</div>
				</div>
			</header>
			<!-- 阿同木中心设计 -->
			<article data-role="article" id="index_article" class="active"
				style="top: 10px; bottom: 54px;">
				<div class="scroller">
					<div class="myhead">
						<div class="myhead-img">
							<img src="<%=request.getSession().getAttribute("userhead").toString() %>"style="border-radius:1rem;"/>
						</div>
						<%
					    Object roleObj = request.getSession().getAttribute("role");
					    Object userObj = (Object)request.getSession().getAttribute("loginuser");
					    int role = -1;
					    if(null != roleObj){
					    	role = (Integer)roleObj;
					        if(role == 2){
					            Tbl_saleman userinfo = (Tbl_saleman)userObj;

						%>
						<div class="myhead-dsb">
							<p class="dsb-name"><%=userinfo.getSaleman_name() %></p>
							<p class="dsb-id">销售ID：<%=userinfo.getSaleman_id() %></p>
							<p class="dsb-grade">加入时间：<%=userinfo.getReg_date().toString().substring(0, 10) %></p>
						</div>
						<%}
						}
						%>
					</div>

					<div class="mynav">
						<ul>
							<li><i class="idt"></i>
								<p
									style="margin-top: 0.05rem; margin-left: -0.5rem; width:3rem; height: 15px;">月零售额　￥<%=request.getSession().getAttribute("saleSum") %></p>
							</li>
							<li class="pt-line"><i class="clt"></i>
								<p
									style="margin-top: 0.05rem; margin-left: -0.5rem; width:3rem; height: 15px;">本月工资　￥0.00</p>
							</li>
						</ul>
					</div>

					<details open>
						<summary class="sliver">个人与团队</summary>
						<ul>
							<div class="ps-lt">
								<li href="updateSalemanInfo.jsp" data-toggle="html">
									<div class="lt-dsb">
										<p>修改个人资料</p>
										<i class="arr-right"></i>
									</div>
								</li>
								<li href="comesoon.jsp" data-toggle="html">
									<div class="lt-dsb">
										<p>我的销售级别</p>
										<i class="arr-right"></i>
									</div>
								</li>
								<li href="../DependencyRelationServlet" data-toggle="html">
									<div class="lt-dsb">
										<p>我的推广</p>
										<i class="arr-right"></i>
									</div>
								</li>
								<li href="../ViewUsersServlet" data-toggle="html">
									<div class="lt-dsb">
										<p>我的客户</p>
										<i class="arr-right"></i>
									</div>
								</li>
							</div>
						</ul>
					</details>
					<details open>
						<summary class="sliver">收益相关</summary>
						<ul>
							<div class="ps-lt">
								<li href="../ShowBonusServlet" data-toggle="html">
									<div class="lt-dsb">
										<p>我的佣金</p>
										<i class="arr-right"></i>
									</div>
								</li>
								<li href="../BonusExtractRecordsServlet" data-toggle="html">
									<div class="lt-dsb">
										<p>佣金申请记录</p>
										<i class="arr-right"></i>
									</div>
								</li>
							</div>
						</ul>
					</details>
					<details open>
						<summary class="sliver">帮助</summary>
						<div class="ps-lt">
							<li href="comesoon.jsp" data-toggle="html">
								<div class="lt-dsb">
									<p>佣金系统说明</p>
									<i class="arr-right"></i>
								</div>
							</li>
							<li href="comesoon.jsp" data-toggle="html">
								<div class="lt-dsb">
									<p>等级制度说明</p>
									<i class="arr-right"></i>
								</div>
							</li>
							<li href="comesoon.jsp" data-toggle="html">
								<div class="lt-dsb">
									<p>购物过程中出现问题</p>
									<i class="arr-right"></i>
								</div>
							</li>
						</div>
					</details>
				</div>
				<a data-role="scrollTop" data-toggle="scrollTop"
					href="#index_article" style="bottom: 60px; right: 4px;">
				</a>
			</article>

	<footer class="footer">
		<div class="foot-con">
			<div class="foot-con_2">
				<a href="../GoodsShowServlet"> <i class="navIcon home"></i>
					<span class="text">首页</span>
				</a> <a href="../ShowShoppingCarListServlet"> <i class="navIcon shop"></i> <span
					class="text">购物车</span>
				</a> <a href="../OrderShowServlet"> <i class="navIcon sort"></i> <span
					class="text">我的订单</span>
				</a>
				<%
				    if(null != roleObj){
				        if(role == 1){%>
				    <a href="userhome.jsp"  class="active"> <i class="navIcon member"></i> <span class="text">个人中心</span></a>
				<%
				        }else if(role == 2){%>
				    <a href="salemanhome.jsp"  class="active"> <i class="navIcon member"></i> <span class="text">我的分销</span></a>
				<%
				        }
				    }
				%>
			</div>
		</div>
	</footer>
		</section>
	</div>
	<!--- third -->
	<script src="js/jquery-2.1.3.js"></script>
	<script src="js/jquery.mobile.custom.min.js"></script>
	<!---  agile -->
	<script type="text/javascript" src="js/agile.js"></script>
	<!-- app -->
	<script type="text/javascript" src="js/app.seedsui.js"></script>
	<script type="text/javascript" src="js/seedsui.min.js"></script>
	<script>
		(function(doc, win) {
			var docEl = doc.documentElement, resizeEvt = 'orientationchange' in window ? 'orientationchange'
					: 'resize', recalc = function() {
				var clientWidth = docEl.clientWidth;
				if (!clientWidth)
					return;
				docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
			};

			if (!doc.addEventListener)
				return;
			win.addEventListener(resizeEvt, recalc, false);
			doc.addEventListener('DOMContentLoaded', recalc, false);
		})(document, window);
	</script>
	<script>
		function changeTitle(menuName) {
			$("#top_title").html(menuName);
		}
	</script>
	<script>
		$('a[data-href]').on(A.options.clickEvent, function() {
			var href = $(this).data('href');
			try {
				NativeUtil.browser(href);
			} catch (e) {
				location.href = href;
			}
		});
	</script>
</body>
</html>