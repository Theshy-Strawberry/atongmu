<%@page import="com.atongmu.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.atongmu.bean.*,com.atongmu.util.*"
	pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
    <script src="plugin/jquery/jquery.min.js"></script>
	<script src="plugin/seedsui/seedsui.min.js"></script>
	<link rel="stylesheet" href="web_mobile/css/agile.layout.css">
	<link rel="stylesheet" href="plugin/seedsui/seedsui.min.css">
	<!--Exmobi能力-->
	<link rel="stylesheet" href="web_mobile/css/seedsui.min.css">
	<style>
	.row-left{width:40%;color:#000000}
	</style>
	<title>产品使用感受</title>
</head>
<body>
<script>
	$().ready(function (){
		$("#asklist").change(function(){
            var goodsId = $(this).val();
            window.location.href="HealthAskBefore?goodsid="+goodsId;
        });
		var hdngoodsId = $("#hdngoodid").val();
		if(hdngoodsId != ""){
			$("#asklist").val(hdngoodsId);
		}
	});
</script>
<%
    Object userObj = (Object)request.getSession().getAttribute("loginuser");
    Object returncodeObj = request.getParameter("errorcode");
    int returncode = 1;
    Tbl_user userinfo = null;
    Tbl_saleman salemaninfo = null;
    if(returncodeObj != null){
    	returncode = Integer.parseInt(returncodeObj.toString());%>
	<header>
		<div class="titlebar">
		<%
	  		Object roleObj = request.getSession().getAttribute("role");
			int role = -1;
	    	if(null != roleObj){
	    		role = (Integer)roleObj;
	            if(role == 1){
	            	userinfo = (Tbl_user)userObj;
		%>
			<a href="web_mobile/healthcenter.jsp""><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%}else if(role == 2){
		        salemaninfo  =(Tbl_saleman)userObj;
		%>
			<a href="web_mobile/healthcenter.jsp"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%}

		}%>
			<h1>产品使用感受调查</h1>
		</div>
	</header>


<%
    	if(returncode == 0 ){
    		//更新成功
    		%>
				<script>
					var at=new Alert("您本日已完成该产品的使用调查，记得明天继续来填写使用感受哦！");
					at.show();
				</script>
    		<%
}else{
	%>
				<script>
					var at=new Alert("请认真填写产品使用感受，我们的专家将对您的健康进行全面的分析，并免费给您提供针对您身体的解决方案哦！");
					at.show();
				</script>
	<%
		}
    }
     %>
<script>
function displayDiv(selectedValue,displayDivName){
	if(selectedValue == "0"){
		document.getElementById(displayDivName).style.display="block";
	}else{
		document.getElementById(displayDivName).style.display="none";
	}
}
function displayDiv2(selectedID,displayDivName){
	if(document.getElementById(selectedID).checked ==true){
		document.getElementById(displayDivName).style.display="block";
	}else{
		document.getElementById(displayDivName).style.display="none";
	}
}
</script>
            <form id="form1" style="padding: 10px;margin-top:20px;" method="post" action = "HealthAskCommitServlet">

			<div class="group" >
				<%if(request.getSession().getAttribute("hdngoodid") != null && 0 != Integer.parseInt(request.getSession().getAttribute("hdngoodid").toString())){ %>
					<input type="hidden" id="hdngoodid" value="<%=request.getSession().getAttribute("hdngoodid")%>"/>
				<%}else{ %>
					<input type="hidden" id="hdngoodid" value=""/>
				<%} %>
				<div class="row" style="border-bottom:none;">
					<label class="row-left" style="width:100%;color:#333333;">请选择对应产品 </label>
				</div>
				<div class="row">
					<div class="input-box lrpadding0 row-right" >
						<select class="rl" id="asklist" name="asklist" style="padding-right:24px;">
							<%
							String[][]  askListArr = (String[][])request.getSession().getAttribute("askListArr");
							for(int i=0;i<askListArr.length;i++){
								if(askListArr[i][0] == null){
									break;
								}
								if(i==0){
									%>

									<option value="<%=askListArr[i][0] %>" name="goodid" selected><%=askListArr[i][1] %></option>
									<%
									continue;
								}
							%>
							<option value="<%=askListArr[i][0] %>"   name="goodid"><%=askListArr[i][1] %></option>

							<%}%>
							</select>
						</div>
	            	</div>
	            </div>
	            <div class="group" >
							<%
							List<Tbl_health_investigation> investigationList = (List<Tbl_health_investigation>)request.getSession().getAttribute("investigationList");
							int i =1;
							for (Tbl_health_investigation inverstigation :investigationList ){
								if(inverstigation.getQuestion_type() == 1){
									//填空
									%>
								<div class="row"  style="border-bottom:none;">
								    <label class="row-left" style="width:100%;color:#333333;"><%=inverstigation.getQuestion() %></label>
								</div>
								<div class="row">
				    				<div class="input-box lrpadding0 row-right" >
					    				<input type="text" name="ask<%=i %>" id="ask<%=i %>" value="" placeholder="<%=StringUtil.nvl(inverstigation.getOption1()) %>" />
									</div>
								</div>
									<%
								}else if(inverstigation.getQuestion_type() == 2){
									//年月日
									%>
								<div class="row">
								    <label class="row-left" style="color:#333333;"><%=inverstigation.getQuestion() %></label>
				    				<div class="row-right input-box" >
					    				  <input type="date" name="ask<%=i %>" id="ask<%=i %>" value="" placeholder=""/>
									</div>
								</div>
									<%
								}else if(inverstigation.getQuestion_type() == 3){
									//选择
									%>
									<div class="row" style="border-bottom:none;">
										<label class="row-left" style="width:100%;color:#333333;"><%=inverstigation.getQuestion() %></label>
									</div>
									<div class="row">
										<div class="input-box lrpadding0 row-right" >
											<select class="rl" id="ask<%=i %>" name="ask<%=i %>" style="padding-right:24px;">
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption1()))){ %>
													<option value="<%=inverstigation.getOption1() %>" selected><%=inverstigation.getOption1()%></option>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption2()))){ %>
													<option value="<%=inverstigation.getOption2() %>" ><%=inverstigation.getOption2()%></option>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption3()))){ %>
													<option value="<%=inverstigation.getOption3() %>" ><%=inverstigation.getOption3()%></option>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption4()))){ %>
													<option value="<%=inverstigation.getOption4() %>" ><%=inverstigation.getOption4()%></option>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption5()))){ %>
													<option value="<%=inverstigation.getOption5() %>" ><%=inverstigation.getOption5()%></option>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption6()))){ %>
													<option value="<%=inverstigation.getOption6() %>" ><%=inverstigation.getOption6()%></option>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption7()))){ %>
													<option value="<%=inverstigation.getOption7() %>" ><%=inverstigation.getOption7()%></option>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption8()))){ %>
													<option value="<%=inverstigation.getOption8() %>" ><%=inverstigation.getOption8()%></option>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption9()))){ %>
													<option value="<%=inverstigation.getOption9() %>" ><%=inverstigation.getOption9()%></option>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption10()))){ %>
													<option value="<%=inverstigation.getOption10() %>" ><%=inverstigation.getOption10()%></option>
												<%} %>
											</select>
										</div>
						            </div>
									<%
								}else if(inverstigation.getQuestion_type() == 4){
									//评分
									%>

								<div class="row" style="border-bottom:none;">
									<label class="row-left" style="width:100%;color:#333333;"><%=inverstigation.getQuestion() %></label>
								</div>
								<div class="row">
									<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption1()))){ %>
													<label><input checked type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption1_score() %>"><%=inverstigation.getOption1()%></label><br/>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption2()))){%>
													<label><input type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption2_score() %>"><%=inverstigation.getOption2()%></label><br/>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption3()))){ %>
													<label><input type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption3_score() %>"><%=inverstigation.getOption3()%></label><br/>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption4()))){ %>
													<label><input type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption3_score() %>"><%=inverstigation.getOption3()%></label><br/>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption5()))){ %>
													<label><input type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption5_score() %>"><%=inverstigation.getOption5()%></label><br/>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption6()))){ %>
													<label><input type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption6_score() %>"><%=inverstigation.getOption6()%></label><br/>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption7()))){ %>
													<label><input type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption7_score() %>"><%=inverstigation.getOption7()%></label><br/>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption8()))){ %>
													<label><input type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption8_score() %>"><%=inverstigation.getOption8()%></label><br/>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption9()))){%>
													<label><input type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption9_score() %>"><%=inverstigation.getOption9()%></label><br/>
												<%} %>
												<%if(!"".equals(StringUtil.nvl(inverstigation.getOption10()))){ %>
													<label><input type="radio" id="option<%=i %>" name="ask<%=i %>"  value="<%=inverstigation.getOption10_score() %>"><%=inverstigation.getOption10()%></label><br/>
												<%} %>
									</div>
								</div>
									<%
								}
								i++;

							}
							%>
			</div>

		<button class="button submit block" type="submit">提交</button>


		</form>

	<script>
		window.addEventListener("load", function() {
			var f = new Form("#form1");
			f.container.addEventListener("submit", function(e) {
				e.preventDefault();
				var returnflg = f.validate();

				if(returnflg == true){
					submitForm();
				}
			}, false);

		}, false);

		//定义exmobi返回
		function back() {
			history.go(-1);
		}
	    function submitForm(){
	        document.getElementById("form1").submit();
	    }
	</script>
</body>
</html>

