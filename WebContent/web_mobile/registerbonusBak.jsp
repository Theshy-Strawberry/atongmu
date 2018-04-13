<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*,com.atongmu.bean.Tbl_saleman,com.atongmu.bean.Bonus_bean"
	pageEncoding="utf-8"%>
<!-- HTML5文件 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>佣金系统</title>
<link rel="stylesheet" href="web_mobile/css/agile.layout.css">
<link rel="stylesheet" href="web_mobile/css/seedsui.min.css">
<link rel="stylesheet" type="text/css" href="web_mobile/css/style.css">
</head>
<body>
	<div id="section_container">
	<header>
		<div class="titlebar">
			<a href="web_mobile/salemanhome.jsp"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
			<h1>我的佣金</h1>
		</div>
	</header>

<%
					    Object roleObj = request.getSession().getAttribute("role");
					    Object userObj = (Object)request.getSession().getAttribute("loginuser");
					    Object bonusBeanObj = request.getAttribute("bonus_bean");
					    Bonus_bean bonusBean = null;
					    if(bonusBeanObj!=null){
					    	bonusBean = (Bonus_bean)bonusBeanObj;
					    }
					    int role = -1;
					    if(null != roleObj){
					    	role = (Integer)roleObj;
					        if(role == 2){
					            Tbl_saleman userinfo = (Tbl_saleman)userObj;

						%>
			<article data-role="article" id="ui_article"class="active" style="top: 44px; ">
				<details open>
					<summary class="sliver">佣金提取</summary>
					<form id="form1" method="post" action = "GetBonusServlet?shoriType=1">
						<ul class="list">
							<li class="nojustify">
								<div class="justify-content">
									<p>
										<span class="mark-success" style="width: 2rem;">可提取佣金</span>￥<%=bonusBean.getActiveBouns() %>
										元
									</p>
								</div>
							</li>
							<li class="nojustify">
								<div class="justify-content">
									<p>
										<span class="mark-success" style="width: 2rem;">已提取佣金</span>￥<%=bonusBean.getSelfGetBonus() %>
										元
									</p>
								</div>
							</li>
							<li class="nojustify">
								<div class="justify-content">
									<p>
										<span class="mark-success" style="width: 2rem;">已冻结佣金</span>￥<%=bonusBean.getLockedBouns() %>
										元
									</p>
								</div>
							</li>
							<li class="nojustify">
								<div class="justify-content">
									<div class="row">
										<div class="row-right input-box">
											<input type="text" class="company"
											    id="regBonus" name="regBonus"
											    data-rule-field="佣金金额" data-rule="required regBonus number maxlength:8"
												placeholder="可提取金额：￥<%=bonusBean.getActiveBouns() %>元" />
										</div>
									</div>
									<div class="justify-content">
										<p>
											<button class="button submit block" onclick="this.form.submit()">申请提取佣金</button>
										</p>
									</div>
								</div>
							</li>
						</ul>
					</form>
				</details>
				<details open>
					<summary class="sliver">设置查询月份</summary>
					<form id="form2" method="post" action = "GetBonusServlet?shoriType=2">
					<ul class="list">
						<li class="nojustify">
							<div class="justify-content">
								<div class="row">
									<div class="row-right input-box">
										<input type="month" value="<%=(String)request.getAttribute("currentMonth")%>" id="month" name="month"><i
											class="color-placeholder icon icon-calendar"></i>
									</div>
								</div>
								<div class="justify-content">
									<p>
										<button class="button submit block" onclick="this.form.submit()">查询该月佣金</button>
									</p>
								</div>
							</div>
						</li>
					</ul>
					</form>
				</details>
				<details open>
					<summary class="sliver">该月零售额明细列表</summary>
					<ul class="list">
						<li class="nojustify">
							<div class="justify-content">
								<p>
									<span class="mark-info" style="width: 2rem;">我的零售额</span>￥<%=bonusBean.getOwn_turnover() %>
									元
								</p>
								<input type="hidden" id="own_turnover" name="own_turnover" value="<%=bonusBean.getOwn_turnover() %>"/>
							</div>
						</li>
						<% if(userinfo.getSaleman_level()!=null && "I005".equals(userinfo.getSaleman_level())){ %>
						<li class="nojustify">
							<div class="justify-content">
								<p>
									<span class="mark-info" style="width: 2rem;">高级销售经理</span>￥<%=bonusBean.getSeniorSalesManagerTurnover() %>
									元
								</p>
								<input type="hidden" id="seniorSalesManagerTurnover" name="seniorSalesManagerTurnover" value="<%=bonusBean.getSeniorSalesManagerTurnover() %>"/>
							</div>
						</li>
						<%} %>
						<% if(userinfo.getSaleman_level()!=null && ("I005".equals(userinfo.getSaleman_level()) || "I004".equals(userinfo.getSaleman_level()))){ %>
						<li class="nojustify">
							<div class="justify-content">
								<p>
									<span class="mark-info" style="width: 2rem;">销售经理</span>￥<%=bonusBean.getSalesManagerTurnover() %>
									元
								</p>
								<input type="hidden" id="salesManagerTurnover" name="salesManagerTurnover" value="<%=bonusBean.getSalesManagerTurnover() %>"/>
							</div>
						</li>
						<%} %>
						<% if(userinfo.getSaleman_level()!=null && ("I005".equals(userinfo.getSaleman_level()) || "I004".equals(userinfo.getSaleman_level())
								                                 || "I003".equals(userinfo.getSaleman_level()) || "I002".equals(userinfo.getSaleman_level()) )){ %>
						<li class="nojustify">
							<div class="justify-content">
								<p>
									<span class="mark-info" style="width: 2rem;">销售员</span>￥<%=bonusBean.getSalesturnover() %>
									元
								</p>
								<input type="hidden" id="salesturnover" name="salesturnover" value="<%=bonusBean.getSalesturnover() %>"/>
							</div>
						</li>
						<%} %>
					</ul>
				</details>
				<details open>
					<summary class="sliver">该月非冻结佣金明细</summary>
					<ul class="list">
						<li class="nojustify">
							<div class="justify-content">
								<p>
									<span class="mark-info" style="width: 2rem;">销售额佣金</span>￥<%=bonusBean.getTalPrizeMoney() %>
									元
									<input type="hidden" id="talPrizeMoney" name="talPrizeMoney" value="<%=bonusBean.getTalPrizeMoney() %>"/>
								</p>
							</div>
						</li>
						<li class="nojustify">
							<div class="justify-content">
								<p>
									<span class="mark-info" style="width: 2rem;">制度奖佣金</span>￥<%=bonusBean.getSysteAward() %>
									元
									<input type="hidden" id="systeAward" name="systeAward" value="<%=bonusBean.getSysteAward() %>"/>
								</p>
							</div>
						</li>
						<% if(userinfo.getSaleman_level()!=null && "I005".equals(userinfo.getSaleman_level())){ %>
						<li class="nojustify">
							<div class="justify-content">
								<p>
									<span class="mark-info" style="width: 2rem;">高级销售经理</span>￥<%=bonusBean.getSeniorSalesManagerBonus() %>
									元
									<input type="hidden" id="seniorSalesManagerBonus" name="seniorSalesManagerBonus" value="<%=bonusBean.getSeniorSalesManagerBonus() %>"/>
								</p>
							</div>
						</li>
						<%} %>
						<% if(userinfo.getSaleman_level()!=null && ("I005".equals(userinfo.getSaleman_level()) || "I004".equals(userinfo.getSaleman_level()))){ %>
						<li class="nojustify">
							<div class="justify-content">
								<p>
									<span class="mark-info" style="width: 2rem;">销售经理</span>￥<%=bonusBean.getBonusSalesManager() %>
									元
									<input type="hidden" id="bonusSalesManager" name="bonusSalesManager" value="<%=bonusBean.getBonusSalesManager() %>"/>
								</p>
							</div>
						</li>
						<%} %>
						<% if(userinfo.getSaleman_level()!=null && ("I005".equals(userinfo.getSaleman_level()) || "I004".equals(userinfo.getSaleman_level())
								                                 || "I003".equals(userinfo.getSaleman_level()) || "I002".equals(userinfo.getSaleman_level()) )){ %>
						<li class="nojustify">
							<div class="justify-content">
								<p>
									<span class="mark-info" style="width: 2rem;">销售员</span>￥<%=bonusBean.getSalesBonus() %>
									元
									<input type="hidden" id="salesBonus" name="salesBonus" value="<%=bonusBean.getSalesBonus() %>"/>
								</p>
							</div>
						</li>
						<%} %>
					</ul>
				</details>

			</article>
		<%}
		}%>
		</div>
	<!--- third -->
	<script src="web_mobile/js/jquery-2.1.3.min.js"></script>
	<script src="web_mobile/js/jquery.mobile.custom.min.js"></script>
	<!---  agile -->
	<script type="text/javascript" src="web_mobile/js/agile.js"></script>
	<!-- app -->
	<script type="text/javascript" src="web_mobile/js/app.seedsui.js"></script>
	<script type="text/javascript" src="web_mobile/js/seedsui.min.js"></script>
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