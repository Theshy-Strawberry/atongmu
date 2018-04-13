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
<title>健康管理中心</title>
<link rel="stylesheet" href="css/agile.layout.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/seedsui.min.css">
<link rel="stylesheet" type="text/css" href="css/userhome.css">
<script charset="utf-8" src="js/jquery.min.js?v=01291"></script>
</head>
<body>
	<div id="section_container">
		<section id="index_section" data-role="section" class="active">
			<header class="header">
				<div class="fix_nav">
					<div style="max-width:768px;margin:0 auto;background:#363333;position: relative;">
						<a class="nav-left back-icon" href="javascript:history.back(-1);">返回</a>
						<div class="tit">健康管理中心</div>
					</div>
				</div>
			</header>

	<div class="scroller" style="margin-top:45px;">
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
							<img src="images/health5.jpg" alt="商品1"/>
						</li>
						<li
							style="display: table-cell; vertical-align: top; width: 768px;">
							 <img src="img/goods2.jpg" alt="商品2"/>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script charset="utf-8" src="js/TouchSlide.js"></script>

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
			<!-- 阿同木中心设计 -->
			<article data-role="article" id="index_article" class="active"
				style="top: 10px; bottom: 54px;margin-top:200px;">
				<div class="scroller">

					<details open>
						<summary class="sliver">健康管理</summary>
						<ul>
							<div class="ps-lt">
								<li href="../HealthInfoReadyServlet" data-toggle="html">
									<div class="lt-dsb">
										<p>健康管理基础数据</p>
										<i class="arr-right"></i>
									</div>
								</li>
								<li href="../HealthAskBefore" data-toggle="html"onclick="">
									<div class="lt-dsb">
										<p>提交产品使用感受</p>
										<i class="arr-right"></i>
									</div>
								</li>
								<li href="comesoon.jsp" data-toggle="html"onclick="alert('暂未开放，敬请期待');">
									<div class="lt-dsb">
										<p>查询专家回复</p>
										<i class="arr-right"></i>
									</div>
								</li>
								<li href="bindingsaleman.jsp" data-toggle="html">
									<div class="lt-dsb">
										<p>我的健康顾问</p>
										<i class="arr-right"></i>
									</div>
								</li>
							</div>
						</ul>
					</details>

				</div>
				<a data-role="scrollTop" data-toggle="scrollTop"
					href="#index_article" style="bottom: 60px; right: 4px;">
				</a>
			</article>
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