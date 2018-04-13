<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售审核一览</title>
	<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.css" />
    <link rel="stylesheet" type="text/css" href="web_browser/lib/Hui-iconfont/1.0.7/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="web_browser/lib/icheck/icheck.css" />

</head>
<body style="padding-left:2%">
		<div class="titlebar">
			<h3>销售审核一览</h3>
		</div>
		<div class="line"></div>
	<div class="body">
		<form id="userslistshow" action="SaleAuditListServlet" method="post" class="form form-horizontal responsive" style="margin-top:15px">
			<div class="row cl">
				<div class="formControls skin-minimal col-xs-5">
					<label class="form-label col-xs-2">申请状态：</label>
					<div class="radio-box" >
						<input type="radio" name="auditStatus" value="H003" id="auditStatus-1"checked><label for="auditStatus-1">审核中</label>
					</div>
					<div class="radio-box" style="margin-left:10px;">
						<input type="radio" name="auditStatus" value="H001" id="auditStatus-2"><label for="auditStatus-2">已通过</label>
					</div>
					<div class="radio-box" style="margin-left:10px;">
						<input type="radio" name="auditStatus" value="H002" id="auditStatus-3"><label for="auditStatus-3">未通过</label>
					</div>
				</div>
			</div>
			<div class="row cl" >
				<div class="formControls skin-minimal col-xs-5">
					<label class="form-label col-xs-2">用户ID：</label>
					<input type="text" name="orderIdSearch"  class="input-text" style="width:200px;">
					<input type="submit" name="search" value="搜索"  class="btn btn-primary radius" style="margin-left:15px;">
				</div>

			</div>
		</form>
		<table class="table table-border table-bg table-bordered table-hover" style="width:100%;margin-top:15px;">
			<tr>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">用户ID</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">用户姓名</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">上级销售ID</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">申请状态</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">月消费额</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">详细</th>
			</tr>

				<%
				List<Map<String, Object>> saleInfoList = (List<Map<String, Object>>)request.getAttribute("saleInfoList");
				for(Map<String,Object> tempMap:saleInfoList){
				%>
				<tr>
					<td width="10%" class="text-c"><%=tempMap.get("reqUserId") %></td>
					<td width="10%" class="text-c"><%=tempMap.get("userName") %></td>
					<td width="10%" class="text-c"><%=tempMap.get("salemanId") %></td>
					<td width="10%" class="text-c"><%=tempMap.get("codeValue") %></td>
					<td width="10%" class="text-c"><%=tempMap.get("mouthSale") %></td>
					<td class="text-c"><a href="SaleAuditDetailServlet?reqNo=<%=tempMap.get("reqNo") %>" class="btn btn-primary radius">详细</a></td>
				</tr>
				<%
				}
            %>
	</table>
</div>
<script type="text/javascript" src="web_browser/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="web_browser/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="web_browser/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="web_browser/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="web_browser/lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript" src="web_browser/lib/bootstrap-Switch/bootstrapSwitch.js"></script>
<script type="text/javascript" src="web_browser/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="web_browser/lib/Validform/5.3.2/passwordStrength-min.js"></script>
<script type="text/javascript" src="web_browser/static/h-ui/js/H-ui.js"></script>
<script>
//var navigation = responsiveNav("Hui-navbar", {customToggle: ".nav-toggle"});

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#demoform").Validform({
		tiptype:2
	});
});
</script>
</body>
</html>