<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.atongmu.bean.Tbl_saleman" import="java.util.*" import ="java.util.List"  import="java.util.ArrayList"
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
<title>查看团队</title>
<link rel="stylesheet" href="web_mobile/css/agile.layout.css">
<link rel="stylesheet" href="web_mobile/css/seedsui.min.css">
<link rel="stylesheet" type="text/css" href="web_mobile/css/style.css">
</head>
<body>
   <% Object roleObj = request.getSession().getAttribute("role");
					    Object userObj = (Object)request.getSession().getAttribute("loginuser");
					    int role = -1;
					    if(null != roleObj){
					    	String leaderId=(String)request.getAttribute("leaderId");
					    	String leaderName=(String)request.getAttribute("leaderName");
					    	role = (Integer)roleObj;
					        if(role == 2){
					            Tbl_saleman userinfo = (Tbl_saleman)userObj;
					            String  salemanevel=userinfo.getSaleman_level();
					            if("I005".equals(salemanevel)){ %>

					            	<div id="section_container">
		<section id="index_section" data-role="section" class="active">
			<header>
				<div class="titlebar">
					<a href="javascript:back()"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
					<h1>查看团队</h1>
				</div>
			</header>
			<!-- 我的家族树设计 -->
			<article data-role="article" id="ui_article"class="active"
				style="top: 44px; ">
				<div class="scroller">
					<div class="tree-box">
						

						<ul class="tree" id="tree">
							<li>
								<div class="treetitle">
									<i class="treeicon icon-arrowright"></i> <a
										class="icontitle events-none">我的负责人</a> <small
										class="right middle events-none">1</small>
								</div>
								<ul>
									<li>
										<div class="treetitle">
											<i class="treeicon radiusround">阿</i> <a
												class="icontitle events-none">阿同木</a>
										</div>
									</li>
									
									
							<li>
								<div class="treetitle">
									<i class="treeicon icon-arrowright"></i> <a
										class="icontitle events-none">我的职称（主管经理）</a> <small
										class="right middle events-none">1</small>
								</div>
								<ul>
									<li>
										<div class="treetitle">
											<i class="treeicon radiusround"><%=userinfo.getSaleman_name().substring(0, 1) %></i> <a
												class="icontitle events-none"><%=userinfo.getSaleman_id() %> <%=userinfo.getSaleman_name() %></a>
										</div>
									</li>
									<li>
									<%Object obj1 = request.getAttribute("seniorSalesManager");
											if(obj1 != null){
											List<List<String>> seniorSalesManager = (List<List<String>>)obj1;
											int count=seniorSalesManager.get(0).size();%>
										<div class="treetitle">
											<i class="treeicon icon-arrowright"></i> <a
												class="icontitle events-none">高级销售经理</a> <small
												class="right middle events-none"><%=count %></small>
										</div>
										<ul>
											<%for(int j=0;j<seniorSalesManager.get(0).size();j++){ %>
											<li>
												<div class="treetitle">
															<i class="treeicon radiusround"><%=seniorSalesManager.get(1).get(j).substring(0, 1) %></i> <a
																class="icontitle events-none"><%=seniorSalesManager.get(0).get(j) %> <%=seniorSalesManager.get(1).get(j) %><br /> 团队人数：<%=seniorSalesManager.get(2).get(j) %>人<br />
																团队收益：￥<%=seniorSalesManager.get(3).get(j) %><br />
															</a>
												</div>
											</li>
											<%}
													}%>
											<li>
												<%Object obj2 = request.getAttribute("salesManager");
											if(obj2 != null){
											List<List<String>> salesManager = (List<List<String>>)obj2;
											int count=salesManager.get(0).size();%>
												<div class="treetitle">
													<i class="treeicon icon-arrowright"></i> <a
														class="icontitle events-none">销售经理</a> <small
														class="right middle events-none"><%=count %></small>
												</div>
												<ul>

													<%for(int j=0;j<salesManager.get(0).size();j++){ %>
													<li>
													<div class="treetitle">
															<i class="treeicon radiusround"><%=salesManager.get(1).get(j).substring(0, 1) %></i> <a
																class="icontitle events-none"><%=salesManager.get(0).get(j) %> <%=salesManager.get(1).get(j) %><br /> 团队人数：<%=salesManager.get(2).get(j) %>人<br />
																团队收益：￥<%=salesManager.get(3).get(j) %><br />
															</a>
														</div>


													<%} }%>
													</li>
													<li>
													<%Object obj3 = request.getAttribute("salesPerson");
													if(obj3 != null){
													List<List<String>> salesPerson = (List<List<String>>)obj3;
													int count=salesPerson.get(0).size();%>

														<div class="treetitle">
															<i class="treeicon icon-arrowright"></i> <a
																class="icontitle events-none">销售员</a> <small
																class="right middle events-none"><%=count %></small>
														</div>
														<ul>

														<%for(int j=0;j<salesPerson.get(0).size();j++){ %>
															<li>
																<div class="treetitle">
																	<i class="treeicon radiusround"><%=salesPerson.get(1).get(j).substring(0, 1) %></i> <a
																		class="icontitle events-none"><%=salesPerson.get(0).get(j) %> <%=salesPerson.get(1).get(j) %><br />
																		月零售额：￥<%=salesPerson.get(2).get(j) %><br />
																	</a>
																</div>
															</li>
															<%}} %>
														</ul>
													</li>
												</ul>
										</ul>
									</li>
								</ul>
								</li>
								</ul>
							</li>		
						</ul>
					</div>
				</div>
			</article>
		</section>
	</div>

					            <%
					            }   else if("I004".equals(salemanevel)){%>


					            	<div id="section_container">
		<section id="index_section" data-role="section" class="active">
			<header>
				<div class="titlebar">
					<a href="javascript:back()"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
					<h1>查看团队</h1>
				</div>
			</header>
			<!-- 我的家族树设计 -->
			<article data-role="article" id="ui_article"class="active"
				style="top: 44px; ">
				<div class="scroller">
					<div class="tree-box">
						<ul class="tree" id="tree">
							<li>
								<div class="treetitle">
									<i class="treeicon icon-arrowright"></i> <a
										class="icontitle events-none">我的负责人</a> <small
										class="right middle events-none">1</small>
								</div>
								<ul>
									<li>
										<div class="treetitle">
											<i class="treeicon radiusround">阿</i> <a
												class="icontitle events-none">阿同木</a>
										</div>
									</li>
									<li>
										<div class="treetitle">
											<i class="treeicon icon-arrowright"></i> <a
												class="icontitle events-none">我的职称（高级销售经理）</a> <small
												class="right middle events-none">1</small>
										</div>
										<ul>
											<li>
												<div class="treetitle"><!-- .toString().substring(0, 1) -->
													<i class="treeicon radiusround"><%=userinfo.getSaleman_name().substring(0, 1) %></i> <a
														class="icontitle events-none"><%=userinfo.getSaleman_id() %> <%=userinfo.getSaleman_name() %> </a>
												</div>
											</li>
											<li>
											<%Object obj2 = request.getAttribute("salesManager");
					        											if(obj2 != null){
					        											List<List<String>> salesManager = (List<List<String>>)obj2;
					        											int count=salesManager.get(3).size();%>
												<div class="treetitle">
													<i class="treeicon icon-arrowright"></i> <a
														class="icontitle events-none">销售经理</a> <small
														class="right middle events-none"><%=count %></small>
												</div>
												<ul>

														<%for(int j=0;j<salesManager.get(0).size();j++){ %>
													<li>
												<div class="treetitle">
					        															<i class="treeicon radiusround"><%=salesManager.get(1).get(j).substring(0, 1) %></i> <a
					        																class="icontitle events-none"><%=salesManager.get(0).get(j) %> <%=salesManager.get(1).get(j) %><br /> 团队人数：<%=salesManager.get(2).get(j) %>人<br />
					        																团队收益：￥<%=salesManager.get(3).get(j) %><br />
					        															</a>
					        														</div>


													<%} }%>
													</li>
													<li>
												<%Object obj3 = request.getAttribute("salesPerson");
					        											if(obj3 != null){
					        											List<List<String>> salesPerson = (List<List<String>>)obj3;
					        											int count=salesPerson.get(2).size();%>
														<div class="treetitle">
															<i class="treeicon icon-arrowright"></i> <a
																class="icontitle events-none">销售员</a> <small
																class="right middle events-none"><%=count %></small>
														</div>
														<ul>

																<%for(int j=0;j<salesPerson.get(0).size();j++){ %>
															<li>
															<div class="treetitle">
					        															<i class="treeicon radiusround"><%=salesPerson.get(1).get(j).substring(0, 1) %></i> <a
					        																class="icontitle events-none"><%=salesPerson.get(0).get(j) %> <%=salesPerson.get(1).get(j) %><br />
					        																月零售额：￥<%=salesPerson.get(2).get(j) %><br />
					        															</a>
					        														</div>
															</li>
															<%}} %>
														</ul>
													</li>
												</ul>
										</ul>
									</li>
								</ul>
								</li>
								</ul>
					</div>
				</div>
			</article>
		</section>
	</div>
					             <%	}   else if("I003".equals(salemanevel)){ %>

					          <div id="section_container">
		<section id="index_section" data-role="section" class="active">
			<header>
				<div class="titlebar">
					<a href="javascript:back()"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
					<h1>查看团队</h1>
				</div>
			</header>
			<!-- 我的家族树设计 -->
			<article data-role="article" id="ui_article"class="active"
				style="top: 44px; ">
				<div class="scroller">
					<div class="tree-box">
						<ul class="tree" id="tree">
							<li>
								<div class="treetitle">
									<i class="treeicon icon-arrowright"></i> <a
										class="icontitle events-none">我的负责人</a> <small
										class="right middle events-none">1</small>
								</div>
								<ul>
									<li>
										<div class="treetitle">
											<i class="treeicon radiusround">阿</i> <a
												class="icontitle events-none">阿同木</a>
										</div>
									</li>

									<li>
										<div class="treetitle">
											<i class="treeicon icon-arrowright"></i> <a
												class="icontitle events-none">我的职称（销售经理）</a> <small
												class="right middle events-none">1</small>
										</div>
										<ul>
											<li>
												<div class="treetitle"><!-- .toString().substring(0, 1) -->
													<i class="treeicon radiusround"><%=userinfo.getSaleman_name().substring(0, 1) %></i> <a
														class="icontitle events-none"><%=userinfo.getSaleman_id() %> <%=userinfo.getSaleman_name() %> </a>
												</div>
											</li>
											<li>
												<%Object obj3 = request.getAttribute("salesPerson");
					        											if(obj3 != null){
					        											List<List<String>> salesPerson = (List<List<String>>)obj3;
					        											int count=salesPerson.get(2).size();%>
												<div class="treetitle">
													<i class="treeicon icon-arrowright"></i> <a
														class="icontitle events-none">销售员</a> <small
														class="right middle events-none"><%=count %></small>
												</div>
												<ul>

													<%for(int j=0;j<salesPerson.get(2).size();j++){ %>
													<li>
														<div class="treetitle">
													<i class="treeicon radiusround"><%=salesPerson.get(1).get(j).substring(0, 1) %></i>
													<a class="icontitle events-none"><%=salesPerson.get(0).get(j) %>
														<%=salesPerson.get(1).get(j) %><br /> 月零售额：￥<%=salesPerson.get(2).get(j) %><br />
													</a>
												</div>
													</li>

													<%} }%>
												</ul>
											</li>
												</ul>
											</li>
										</ul>
									</li>
								</ul>
					</div>
				</div>
			</article>
		</section>
	</div>
					             <%

					            }   else if("I002".equals(salemanevel)){

					            	%>
					            	<div id="section_container">
		<section id="index_section" data-role="section" class="active">
			<header>
				<div class="titlebar">
					<a href="javascript:back()"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
					<h1>查看团队</h1>
				</div>
			</header>
			<!-- 我的家族树设计 -->
			<article data-role="article" id="ui_article"class="active"
				style="top: 44px; ">
				<div class="scroller">
					<div class="tree-box">
						<ul class="tree" id="tree">
							<li>
								<div class="treetitle">
									<i class="treeicon icon-arrowright"></i> <a
										class="icontitle events-none">我的负责人</a> <small
										class="right middle events-none">1</small>
								</div>
								<ul>
									<li>
										<div class="treetitle">
											<i class="treeicon radiusround">阿</i> <a
												class="icontitle events-none">阿同木</a>
										</div>
									</li>

									<li>
										<div class="treetitle">
											<i class="treeicon icon-arrowright"></i> <a
												class="icontitle events-none">我的职称（销售员）</a> <small
												class="right middle events-none">1</small>
										</div>
										<ul>
											<li>
												<div class="treetitle"><!-- .toString().substring(0, 1) -->
													<i class="treeicon radiusround"><%=userinfo.getSaleman_name().substring(0, 1) %></i> <a
														class="icontitle events-none"><%=userinfo.getSaleman_id() %> <%=userinfo.getSaleman_name() %> </a>
												</div>
											</li>
											<li>
											<%Object obj3 = request.getAttribute("salesPerson2");
					        											if(obj3 != null){
					        											List<List<String>> salesPerson2 = (List<List<String>>)obj3;
					        											int count=salesPerson2.get(2).size();%>
												<div class="treetitle">
													<i class="treeicon icon-arrowright"></i> <a
														class="icontitle events-none">销售员</a> <small
														class="right middle events-none"><%=count %></small>
												</div>
												<ul>

													<%for(int j=0;j<salesPerson2.get(2).size();j++){ %>
													<li>
														<div class="treetitle">
					        															<i class="treeicon radiusround"><%=salesPerson2.get(1).get(j).substring(0, 1) %></i> <a
					        																class="icontitle events-none"><%=salesPerson2.get(0).get(j) %> <%=salesPerson2.get(1).get(j) %><br />
					        																月零售额：￥<%=salesPerson2.get(2).get(j) %><br />
					        															</a>
					        														</div>
													</li>

													<%} }%>
												</ul>
											</li>
												</ul>
											</li>
										</ul>
									</li>
								</ul>
					</div>
				</div>
			</article>
		</section>
	</div>
					            	<%
					            }

							}else{
								//没有任何权限，则重定向到错误页面
								response.sendRedirect("web_mobile/error.jsp");
						    }
						}%>



	<!--- third -->
	<script src="web_mobile/js/jquery-2.1.3.min.js"></script>
	<script src="web_mobile/js/jquery.mobile.custom.min.js"></script>
	<!---  agile -->
	<script type="text/javascript" src="web_mobile/js/agile.js"></script>
	<!-- app -->
	<script type="text/javascript" src="web_mobile/js/app.seedsui.js"></script>
	<script type="text/javascript"src="web_mobile/js/seedsui.min.js"></script>
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
			//console.log("成少波".toPinyin().substr(0,1).toColor());

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