<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.atongmu.bean.Tbl_saleman,com.atongmu.bean.Tbl_user,com.atongmu.dao.SaleManDao,com.atongmu.daoImpl.SaleManDaoImpl"
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
		<header class="header header_1">
			<div class="fix_nav" style="background-color:#0099ff">
				<div class="nav_inner">
					<div class="tit">个人中心</div>
				</div>
			</div>
		</header>
			<!-- 阿同木中心设计 -->
			<article data-role="article" id="index_article" class="active"
				style="top: 10px; bottom: 54px;">
				<div class="scroller">
					<div class="myhead">
						<div class="myhead-img">
							<img src="<%=request.getSession().getAttribute("userhead").toString() %>" style="border-radius:1rem;"/>
						</div>
						<%
					    Object roleObj = request.getSession().getAttribute("role");
					    Object userObj = (Object)request.getSession().getAttribute("loginuser");
					    Tbl_user userinfo = null;
					    int role = -1;
					    if(null != roleObj){
					    	role = (Integer)roleObj;
					        if(role == 1){
					            userinfo = (Tbl_user)userObj;

						%>
						<div class="myhead-dsb">
							<p class="dsb-name"><%=userinfo.getUser_name() %></p>
							<%if(userinfo.getVip_flag()==0){ %>
								<p class="dsb-id">游客ID：<%=userinfo.getUser_id() %></p>
							<%}else{ %>
								<p class="dsb-id">会员ID：<%=userinfo.getUser_id() %></p>
							<%} %>
							<p class="dsb-grade">加入时间：<%=userinfo.getReg_date().toString().substring(0, 10) %></p>
						</div>
						<%}else{
								//没有任何权限，则重定向到错误页面
								response.sendRedirect("web_mobile/error.jsp");
						    }
						}%>
					</div>

					<details open>
						<summary class="sliver">个人信息</summary>
						<ul>
							<div class="ps-lt">
							<%
							if(userinfo.getVip_flag() == 0){
							%>
								<li href="updateUserInfo.jsp?errorcode=9" data-toggle="html">
									<div class="lt-dsb">
										<p>认证成为会员</p>
										<i class="arr-right"></i>
									</div>
								</li>
							<%}else if(userinfo.getVip_flag() == 1){
							%>
								<li href="updateUserInfo.jsp?errorcode=9" data-toggle="html">
									<div class="lt-dsb">
										<p>修改个人资料</p>
										<i class="arr-right"></i>
									</div>
								</li>
							<%} %>
								<li href="myPoints.jsp" data-toggle="html">
									<div class="lt-dsb">
										<p>我的积分</p>
										<i class="arr-right"></i>
									</div>
								</li>
							</div>
						</ul>
					</details>
					<details open>
						<summary class="sliver">销售功能</summary>
						<ul>
							<div class="ps-lt">
								<li href="registertosaleman.jsp" data-toggle="html">
									<div class="lt-dsb">
										<p>申请成为销售</p>
										<i class="arr-right"></i>
									</div>
								</li>
							</div>
						</ul>
						<ul>
							<div class="ps-lt">
								<li href="salemanAuthenticate.jsp" data-toggle="html">
									<div class="lt-dsb">
										<p>手动提交资料的销售员认证</p>
										<i class="arr-right"></i>
									</div>
								</li>
							</div>
						</ul>
					</details>
					<details open>
						<summary class="sliver">帮助</summary>
						<div class="ps-lt">
							<li href="salemanRole.jsp" data-toggle="html">
								<div class="lt-dsb">
									<p>销售的条件</p>
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
				<a href="../GoodsShowServlet" > <i class="navIcon home"></i>
					<span class="text">首页</span>
				</a> <a href="../ShowShoppingCarListServlet"> <i class="navIcon shop"></i> <span
					class="text">购物车</span>
				</a> <a href="../OrderShowServlet"> <i class="navIcon sort"></i> <span
					class="text">我的订单</span>
				</a>
				<%
				    if(null != roleObj){
				        if(role == 1){%>
				    <a href="userhome.jsp" class="active"> <i class="navIcon member"></i> <span class="text">个人中心</span></a>
				<%
				        }else if(role == 2){%>
				    <a href="salemanhome.jsp" class="active"> <i class="navIcon member"></i> <span class="text">我的分销</span></a>
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
	<script type="text/javascript">
		$('.check-on').click(function() {
			$(this).toggleClass('check-off');
		})
	</script>
	<script>
		var tr;
		window.addEventListener("load", function(e) {
			//注入数据时，注意根据姓氏获得颜色

			//树结构
			tr = new Tree("#tree", {
				"onTap" : function(s) {
					console.log("您点击了：");
					console.log(s.target);
					console.log("当前节点的li为：");
					console.log(s.elLI);
				},
				"onTapLastChild" : function(s) {//没有子节点
					console.log("没有子节点");
				}
			});
		}, false);
	</script>
</body>
</html>