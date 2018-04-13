<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*,com.atongmu.bean.Tbl_user,com.atongmu.bean.Bonus_bean"
	pageEncoding="utf-8"%>
	<%@ include file="roleCheck.jsp"%>
<!-- HTML5文件 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>我的积分</title>
<link rel="stylesheet" href="css/agile.layout.css">
<link rel="stylesheet" href="css/seedsui.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="section_container">
	<header>
		<div class="titlebar">
			<a href="javascript:history.go(-1);"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
			<h1>我的积分</h1>
		</div>
	</header>
		<%

		    Object userObj = (Object)request.getSession().getAttribute("loginuser");
			double points = 0.0;
		    Tbl_user userinfo = (Tbl_user)userObj;
		    points = userinfo.getUser_integral();

			%>
			<article data-role="article" id="ui_article"class="active" style="top: 44px; ">

						<ul class="list">
							<li class="nojustify">
								<div class="justify-content">
									<p>
										<span class="mark-success" style="width: 1.5rem;">当前积分</span><%=points %>
									</p>
								</div>
							</li>
							<li class="nojustify">
								<div class="justify-content">
									<p>
										<button class="button submit block" type="button" onclick="javascript:history.go(-1);">返回</button>
									</p>
								</div>
							</li>
						</ul>

			</article>
		</div>
			<!--- third -->
	<script src="js/jquery-2.1.3.min.js"></script>
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
</body>
</html>