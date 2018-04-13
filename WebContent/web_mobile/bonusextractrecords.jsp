<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
import ="com.atongmu.bean.Tbl_bonus_audit"
import = "java.text.SimpleDateFormat"
	pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title>佣金提取记录</title>
<link rel="stylesheet" href="web_mobile/css/seedsui.min.css">
</head>

<body>
<script>
function submitForm(){
    document.getElementById("selectForm").submit();
}
</script>
	<header>
		<div class="titlebar">
			<a href="web_mobile/salemanhome.jsp"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
			<h1>查看佣金提取记录</h1>
		</div>
	</header>
<%
    Object list = request.getAttribute("auditslist");
%>

	<article data-role="article" id="info_article" style="top: 44px; bottom: 54px;">
			<details open>
				<summary class="sliver">佣金提取记录</summary>
				<ul class="list">
<%
    if(list != null){
	List<Tbl_bonus_audit> auditslist = (List<Tbl_bonus_audit>)list;
       for(int i=0;i<auditslist.size();i++){
	   int reqNo= auditslist.get(i).getReq_no();
	   Date oldReqDate = auditslist.get(i).getReq_date();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	   String reqDate=sdf.format(oldReqDate);
	   double reqBonus = auditslist.get(i).getReq_bouns();
	   String reqStatus = auditslist.get(i).getReq_status();
%>
					<li>
						<div class="justify-content">
							<small class="nowrap">
								<span style="float:left;color:#000000;">请求编号：<%=reqNo%></span>
								<span style="float:right;color:#000000;">请求日期：<%=reqDate%></span></small>
							<small class="nowrap">
							     <span style="margin-left:50px;color:#000000;">申请金额：&#165;<%=reqBonus%>元</span>

							     <%
							     if(reqStatus.equals("审核中")){ %>
							     <span style="float:none;color:#000000;margin-left:10px;">申请状态：</span><span style="color:#99CC00;"><%=reqStatus%></span></small>
							     <%}else if(reqStatus.equals("已驳回")){ %>
							     <span style="float:none;color:#000000;margin-left:10px;">申请状态：</span><span style="color:#FF0033;"><%=reqStatus%></span></small>
							     <%}else if(reqStatus.equals("已放款")){ %>
							     <span style="float:none;color:#000000;margin-left:10px;">申请状态：</span><span style="color:#0066CC;"><%=reqStatus%></span></small>
							     <%}else if(reqStatus.equals("提取成功")){ %>
							     <span style="float:none;color:#000000;margin-left:10px;">申请状态：</span><span style="color:#0066CC;"><%=reqStatus%></span></small>
							     <%} %>
						</div>
					</li>
		<%} %>
				</ul>
			</details>
<%
    }%>
	</article>

</body>
</html>