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
    <script src="../plugin/jquery/jquery.min.js"></script>
	<script src="../plugin/seedsui/seedsui.min.js"></script>
	<link rel="stylesheet" href="css/agile.layout.css">
	<link rel="stylesheet" href="../plugin/seedsui/seedsui.min.css">
	<!--Exmobi能力-->
	<link rel="stylesheet" href="css/seedsui.min.css">
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
            window.location.href="../HealthAskBefore?goodsid="+goodsId;
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
			<a href="healthcenter.jsp"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%}else if(role == 2){
		        salemaninfo  =(Tbl_saleman)userObj;
		%>
			<a href="healthcenter.jsp"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%}

		}%>
			<h1>已提交产品使用感受</h1>
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
<div class="group" style="padding: 10px;margin-top:40px;">
	<%if(request.getSession().getAttribute("hdngoodid") != null && 0 != Integer.parseInt(request.getSession().getAttribute("hdngoodid").toString())){ %>
		<input type="hidden" id="hdngoodid" value="<%=request.getSession().getAttribute("hdngoodid")%>"/>
	<%}else{ %>
		<input type="hidden" id="hdngoodid" value=""/>
	<%} %>
	<div class="row" style="border-bottom:none;">
		<label class="row-left" style="width:95%;color:#333333;">请选择对应产品 </label>
	</div>
	<div class="row">
		<div class="input-box lrpadding0 row-right" >
			<select class="rl" id="asklist" name="asklist" style="padding-right:24px;">
				<%
				String[][]  askListArr = (String[][])request.getSession().getAttribute("askListArr");
				Tbl_health_investigation_answer answerInfo =(Tbl_health_investigation_answer) request.getSession().getAttribute("answerInfo");
				for(int i=0;i<askListArr.length;i++){
					if(askListArr[i][0] == null){
						break;
					}
					if(i==0){
						%>

						<option value="<%=askListArr[i][0] %>" selected><%=askListArr[i][1] %></option>
						<%
						continue;
					}
				%>
				<option value="<%=askListArr[i][0] %>" ><%=askListArr[i][1] %></option>
			<%} %>
			</select>
		</div>
	</div>
 </div>
 		<div class="group" >
<%
				List<Tbl_health_investigation> investigationList = (List<Tbl_health_investigation>)request.getSession().getAttribute("investigationList");
				int current =1;
				for (int j=0;j<investigationList.size();j++ ){
					if(investigationList.get(j).getQuestion_type() == 1){
						//填空
						%>
					<div class="row"  style="border-bottom:none;">
					    <label class="row-left" style="width:95%;color:#333333;"><%=investigationList.get(j).getQuestion() %></label>
					</div>
					<div class="row">
	    				<div class="input-box lrpadding0 row-right" >
							<%switch(current){
							case 1:
								current++;%><input type="text" value="<%=answerInfo.getAnswer1() %>" readonly="readonly" /><%
								break;
							case 2:
								current++;%><input type="text" value="<%=answerInfo.getAnswer2() %>" readonly="readonly" /><%
								break;
							case 3:
								current++;%><input type="text" value="<%=answerInfo.getAnswer3() %>" readonly="readonly" /><%
								break;
							case 4:
								current++;%><input type="text" value="<%=answerInfo.getAnswer4() %>" readonly="readonly" /><%
								break;
							case 5:
								current++;%><input type="text" value="<%=answerInfo.getAnswer5() %>" readonly="readonly" /><%
								break;
							case 6:
								current++;%><input type="text" value="<%=answerInfo.getAnswer6() %>" readonly="readonly" /><%
								break;
							case 7:
								current++;%><input type="text" value="<%=answerInfo.getAnswer7() %>" readonly="readonly" /><%
								break;
							case 8:
								current++;%><input type="text" value="<%=answerInfo.getAnswer8() %>" readonly="readonly" /><%
								break;
							case 9:
								current++;%><input type="text" value="<%=answerInfo.getAnswer9() %>" readonly="readonly" /><%
								break;
							case 10:
								current++;%><input type="text" value="<%=answerInfo.getAnswer10() %>" readonly="readonly" /><%
								break;
							case 11:
								current++;%><input type="text" value="<%=answerInfo.getAnswer11() %>" readonly="readonly" /><%
								break;
							case 12:
								current++;%><input type="text" value="<%=answerInfo.getAnswer12() %>" readonly="readonly" /><%
								break;
							case 13:
								current++;%><input type="text" value="<%=answerInfo.getAnswer13() %>" readonly="readonly" /><%
								break;
							case 14:
								current++;%><input type="text" value="<%=answerInfo.getAnswer14() %>" readonly="readonly" /><%
								break;
							case 15:
								current++;%><input type="text" value="<%=answerInfo.getAnswer15() %>" readonly="readonly" /><%
								break;
							case 16:
								current++;%><input type="text" value="<%=answerInfo.getAnswer16() %>" readonly="readonly" /><%
								break;
							case 17:
								current++;%><input type="text" value="<%=answerInfo.getAnswer17() %>" readonly="readonly" /><%
								break;
							case 18:
								current++;%><input type="text" value="<%=answerInfo.getAnswer18() %>" readonly="readonly" /><%
								break;
							case 19:
								current++;%><input type="text" value="<%=answerInfo.getAnswer19() %>" readonly="readonly" /><%
								break;
							case 20:
								current++;%><input type="text" value="<%=answerInfo.getAnswer20() %>" readonly="readonly" /><%
								break;
							case 21:
								current++;%><input type="text" value="<%=answerInfo.getAnswer21() %>" readonly="readonly" /><%
								break;
							case 22:
								current++;%><input type="text" value="<%=answerInfo.getAnswer22() %>" readonly="readonly" /><%
								break;
							case 23:
								current++;%><input type="text" value="<%=answerInfo.getAnswer23() %>" readonly="readonly" /><%
								break;
							case 24:
								current++;%><input type="text" value="<%=answerInfo.getAnswer24() %>" readonly="readonly" /><%
								break;
							case 25:
								current++;%><input type="text" value="<%=answerInfo.getAnswer25() %>" readonly="readonly" /><%
								break;
							case 26:
								current++;%><input type="text" value="<%=answerInfo.getAnswer26() %>" readonly="readonly" /><%
								break;
							case 27:
								current++;%><input type="text" value="<%=answerInfo.getAnswer27() %>" readonly="readonly" /><%
								break;
							case 28:
								current++;%><input type="text" value="<%=answerInfo.getAnswer28() %>" readonly="readonly" /><%
								break;
							case 29:
								current++;%><input type="text" value="<%=answerInfo.getAnswer29() %>" readonly="readonly" /><%
								break;
							case 30:
								current++;%><input type="text" value="<%=answerInfo.getAnswer30() %>" readonly="readonly" /><%
								break;
							}%>
							</div>
						</div>

						<%
					}else if(investigationList.get(j).getQuestion_type() == 2){
						//年月日
						%>
					<div class="row">
					    <label class="row-left" style="color:#333333;"><%=investigationList.get(j).getQuestion() %></label>
	    				<div class="row-right input-box" >
					<%switch(current){
							case 1:
								current++;%><input type="date" value="<%=answerInfo.getAnswer1() %>" readonly="readonly" /><%
								break;
							case 2:
								current++;%><input type="date" value="<%=answerInfo.getAnswer2() %>" readonly="readonly" /><%
								break;
							case 3:
								current++;%><input type="date" value="<%=answerInfo.getAnswer3() %>" readonly="readonly" /><%
								break;
							case 4:
								current++;%><input type="date" value="<%=answerInfo.getAnswer4() %>" readonly="readonly" /><%
								break;
							case 5:
								current++;%><input type="date" value="<%=answerInfo.getAnswer5() %>" readonly="readonly" /><%
								break;
							case 6:
								current++;%><input type="date" value="<%=answerInfo.getAnswer6() %>" readonly="readonly" /><%
								break;
							case 7:
								current++;%><input type="date" value="<%=answerInfo.getAnswer7() %>" readonly="readonly" /><%
								break;
							case 8:
								current++;%><input type="date" value="<%=answerInfo.getAnswer8() %>" readonly="readonly" /><%
								break;
							case 9:
								current++;%><input type="date" value="<%=answerInfo.getAnswer9() %>" readonly="readonly" /><%
								break;
							case 10:
								current++;%><input type="date" value="<%=answerInfo.getAnswer10() %>" readonly="readonly" /><%
								break;
							case 11:
								current++;%><input type="date" value="<%=answerInfo.getAnswer11() %>" readonly="readonly" /><%
								break;
							case 12:
								current++;%><input type="date" value="<%=answerInfo.getAnswer12() %>" readonly="readonly" /><%
								break;
							case 13:
								current++;%><input type="date" value="<%=answerInfo.getAnswer13() %>" readonly="readonly" /><%
								break;
							case 14:
								current++;%><input type="date" value="<%=answerInfo.getAnswer14() %>" readonly="readonly" /><%
								break;
							case 15:
								current++;%><input type="date" value="<%=answerInfo.getAnswer15() %>" readonly="readonly" /><%
								break;
							case 16:
								current++;%><input type="date" value="<%=answerInfo.getAnswer16() %>" readonly="readonly" /><%
								break;
							case 17:
								current++;%><input type="date" value="<%=answerInfo.getAnswer17() %>" readonly="readonly" /><%
								break;
							case 18:
								current++;%><input type="date" value="<%=answerInfo.getAnswer18() %>" readonly="readonly" /><%
								break;
							case 19:
								current++;%><input type="date" value="<%=answerInfo.getAnswer19() %>" readonly="readonly" /><%
								break;
							case 20:
								current++;%><input type="date" value="<%=answerInfo.getAnswer20() %>" readonly="readonly" /><%
								break;
							case 21:
								current++;%><input type="date" value="<%=answerInfo.getAnswer21() %>" readonly="readonly" /><%
								break;
							case 22:
								current++;%><input type="date" value="<%=answerInfo.getAnswer22() %>" readonly="readonly" /><%
								break;
							case 23:
								current++;%><input type="date" value="<%=answerInfo.getAnswer23() %>" readonly="readonly" /><%
								break;
							case 24:
								current++;%><input type="date" value="<%=answerInfo.getAnswer24() %>" readonly="readonly" /><%
								break;
							case 25:
								current++;%><input type="date" value="<%=answerInfo.getAnswer25() %>" readonly="readonly" /><%
								break;
							case 26:
								current++;%><input type="date" value="<%=answerInfo.getAnswer26() %>" readonly="readonly" /><%
								break;
							case 27:
								current++;%><input type="date" value="<%=answerInfo.getAnswer27() %>" readonly="readonly" /><%
								break;
							case 28:
								current++;%><input type="date" value="<%=answerInfo.getAnswer28() %>" readonly="readonly" /><%
								break;
							case 29:
								current++;%><input type="date" value="<%=answerInfo.getAnswer29() %>" readonly="readonly" /><%
								break;
							case 30:
								current++;%><input type="date" value="<%=answerInfo.getAnswer30() %>" readonly="readonly" /><%
								break;
							}%>
						</div>
					</div>
						<%
					}else if(investigationList.get(j).getQuestion_type() == 3){
						//选择
						%>
						<div class="row" style="border-bottom:none;">
							<label class="row-left" style="width:95%;color:#333333;"><%=investigationList.get(j).getQuestion() %></label>
						</div>
						<div class="row">
							<div class="input-box lrpadding0 row-right" >
								<select class="rl" id="ask" name="ask" style="padding-right:24px;">
								<%switch(current){
									case 1:
										current++;%><option value="<%=answerInfo.getAnswer1() %>" selected><%=answerInfo.getAnswer1()%></option><%
										break;
									case 2:
										current++;%><option value="<%=answerInfo.getAnswer2() %>" selected><%=answerInfo.getAnswer2()%></option><%
										break;
									case 3:
										current++;%><option value="<%=answerInfo.getAnswer3() %>" selected><%=answerInfo.getAnswer3()%></option><%
										break;
									case 4:
										current++;%><option value="<%=answerInfo.getAnswer4() %>" selected><%=answerInfo.getAnswer4()%></option><%
										break;
									case 5:
										current++;%><option value="<%=answerInfo.getAnswer5() %>" selected><%=answerInfo.getAnswer5()%></option><%
										break;
									case 6:
										current++;%><option value="<%=answerInfo.getAnswer6() %>" selected><%=answerInfo.getAnswer6()%></option><%
										break;
									case 7:
										current++;%><option value="<%=answerInfo.getAnswer7() %>" selected><%=answerInfo.getAnswer7()%></option><%
										break;
									case 8:
										current++;%><option value="<%=answerInfo.getAnswer8() %>" selected><%=answerInfo.getAnswer8()%></option><%
										break;
									case 9:
										current++;%><option value="<%=answerInfo.getAnswer9() %>" selected><%=answerInfo.getAnswer9()%></option><%
										break;
									case 10:
										current++;%><option value="<%=answerInfo.getAnswer10() %>" selected><%=answerInfo.getAnswer10()%></option><%
										break;
									case 11:
										current++;%><option value="<%=answerInfo.getAnswer11() %>" selected><%=answerInfo.getAnswer11()%></option><%
										break;
									case 12:
										current++;%><option value="<%=answerInfo.getAnswer12() %>" selected><%=answerInfo.getAnswer12()%></option><%
										break;
									case 13:
										current++;%><option value="<%=answerInfo.getAnswer13() %>" selected><%=answerInfo.getAnswer13()%></option><%
										break;
									case 14:
										current++;%><option value="<%=answerInfo.getAnswer14() %>" selected><%=answerInfo.getAnswer14()%></option><%
										break;
									case 15:
										current++;%><option value="<%=answerInfo.getAnswer15() %>" selected><%=answerInfo.getAnswer15()%></option><%
										break;
									case 16:
										current++;%><option value="<%=answerInfo.getAnswer16() %>" selected><%=answerInfo.getAnswer16()%></option><%
										break;
									case 17:
										current++;%><option value="<%=answerInfo.getAnswer17() %>" selected><%=answerInfo.getAnswer17()%></option><%
										break;
									case 18:
										current++;%><option value="<%=answerInfo.getAnswer18() %>" selected><%=answerInfo.getAnswer18()%></option><%
										break;
									case 19:
										current++;%><option value="<%=answerInfo.getAnswer19() %>" selected><%=answerInfo.getAnswer19()%></option><%
										break;
									case 20:
										current++;%><option value="<%=answerInfo.getAnswer20() %>" selected><%=answerInfo.getAnswer20()%></option><%
										break;
									case 21:
										current++;%><option value="<%=answerInfo.getAnswer21() %>" selected><%=answerInfo.getAnswer21()%></option><%
										break;
									case 22:
										current++;%><option value="<%=answerInfo.getAnswer22() %>" selected><%=answerInfo.getAnswer22()%></option><%
										break;
									case 23:
										current++;%><option value="<%=answerInfo.getAnswer23() %>" selected><%=answerInfo.getAnswer23()%></option><%
										break;
									case 24:
										current++;%><option value="<%=answerInfo.getAnswer24() %>" selected><%=answerInfo.getAnswer24()%></option><%
										break;
									case 25:
										current++;%><option value="<%=answerInfo.getAnswer25() %>" selected><%=answerInfo.getAnswer25()%></option><%
										break;
									case 26:
										current++;%><option value="<%=answerInfo.getAnswer26() %>" selected><%=answerInfo.getAnswer26()%></option><%
										break;
									case 27:
										current++;%><option value="<%=answerInfo.getAnswer27() %>" selected><%=answerInfo.getAnswer27()%></option><%
										break;
									case 28:
										current++;%><option value="<%=answerInfo.getAnswer28() %>" selected><%=answerInfo.getAnswer28()%></option><%
										break;
									case 29:
										current++;%><option value="<%=answerInfo.getAnswer29() %>" selected><%=answerInfo.getAnswer29()%></option><%
										break;
									case 30:%><option value="<%=answerInfo.getAnswer30() %>" selected><%=answerInfo.getAnswer30()%></option><%
										current++;
										break;
									} %>
								</select>
							</div>
			            </div>
						<%
					}else if(investigationList.get(j).getQuestion_type() == 4){
						//评分
						%>

					<div class="row" style="border-bottom:none;">
						<label class="row-left" style="width:95%;color:#333333;"><%=investigationList.get(j).getQuestion() %></label>
					</div>
					<div class="row">
						<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
						<%switch(current){
							case 1:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer1() %>"><%=answerInfo.getAnswer1()%>分</label><br/><%
								break;
							case 2:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer2() %>"><%=answerInfo.getAnswer2()%>分</label><br/><%
								break;
							case 3:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer3() %>"><%=answerInfo.getAnswer3()%>分</label><br/><%
								break;
							case 4:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer4() %>"><%=answerInfo.getAnswer4()%>分</label><br/><%
								break;
							case 5:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer5() %>"><%=answerInfo.getAnswer5()%>分</label><br/><%
								break;
							case 6:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer6() %>"><%=answerInfo.getAnswer6()%>分</label><br/><%
								break;
							case 7:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer7() %>"><%=answerInfo.getAnswer7()%>分</label><br/><%
								break;
							case 8:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer8() %>"><%=answerInfo.getAnswer8()%>分</label><br/><%
								break;
							case 9:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer9() %>"><%=answerInfo.getAnswer9()%>分</label><br/><%
								break;
							case 10:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer10() %>"><%=answerInfo.getAnswer10()%>分</label><br/><%
								break;
							case 11:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer11() %>"><%=answerInfo.getAnswer11()%>分</label><br/><%
								break;
							case 12:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer12() %>"><%=answerInfo.getAnswer12()%>分</label><br/><%
								break;
							case 13:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer13() %>"><%=answerInfo.getAnswer13()%>分</label><br/><%
								break;
							case 14:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer14() %>"><%=answerInfo.getAnswer14()%>分</label><br/><%
								break;
							case 15:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer15() %>"><%=answerInfo.getAnswer15()%>分</label><br/><%
								break;
							case 16:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer16() %>"><%=answerInfo.getAnswer16()%>分</label><br/><%
								break;
							case 17:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer17() %>"><%=answerInfo.getAnswer17()%>分</label><br/><%
								break;
							case 18:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer18() %>"><%=answerInfo.getAnswer18()%>分</label><br/><%
								break;
							case 19:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer19() %>"><%=answerInfo.getAnswer19()%>分</label><br/><%
								break;
							case 20:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer20() %>"><%=answerInfo.getAnswer20()%>分</label><br/><%
								break;
							case 21:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer21() %>"><%=answerInfo.getAnswer21()%>分</label><br/><%
								break;
							case 22:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer22() %>"><%=answerInfo.getAnswer22()%>分</label><br/><%
								break;
							case 23:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer23() %>"><%=answerInfo.getAnswer23()%>分</label><br/><%
								break;
							case 24:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer24() %>"><%=answerInfo.getAnswer24()%>分</label><br/><%
								break;
							case 25:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer25() %>"><%=answerInfo.getAnswer25()%>分</label><br/><%
								break;
							case 26:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer26() %>"><%=answerInfo.getAnswer26()%>分</label><br/><%
								break;
							case 27:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer27() %>"><%=answerInfo.getAnswer27()%>分</label><br/><%
								break;
							case 28:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer28() %>"><%=answerInfo.getAnswer28()%>分</label><br/><%
								break;
							case 29:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer29() %>"><%=answerInfo.getAnswer29()%>分</label><br/><%
								break;
							case 30:
								current++;%><label><input checked type="radio" value="<%=answerInfo.getAnswer30() %>"><%=answerInfo.getAnswer30()%>分</label><br/><%
								break;
							} %>
						</div>
					</div>
		    		<%} %>
				<%
				}
				%>

 	</div>
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

