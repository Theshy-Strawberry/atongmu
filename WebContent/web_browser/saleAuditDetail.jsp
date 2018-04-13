<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="web_browser/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="web_browser/lib/icheck/icheck.css" />
<title>销售审核详细</title>
</head>
<body style="padding-left:2%">
		<div class="titlebar">
			<h3>准销售信息明细</h3>
		</div>
		<div class="line"></div>
	<div class="body">
		<form id="" name="" action="SaleAuditDetailServlet" method="post" class="form form-horizontal responsive"  style="margin-top:15px;width:90%" >
			<%
				Map<String,Object> saleInfoDetailMap = (Map<String,Object>)request.getAttribute("saleInfoDetailMap");
			%>
			<div class="row cl">
				<label class="form-label col-xs-2">身份证正面</label>
				<div class="formControls col-xs-5">
					<a target="blank" href="<%=saleInfoDetailMap.get("userIdCard1")%>"><img alt="身份证正面" width="300px" height="200px" src="<%=saleInfoDetailMap.get("userIdCard1") %>"></a>
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">身份证反面</label>
				<div class="formControls col-xs-5">
					<a target="blank" href="<%=saleInfoDetailMap.get("userIdCard2")%>"><img alt="身份证正面" width="300px" height="200px" src="<%=saleInfoDetailMap.get("userIdCard2") %>"></a>
				</div>
				<div class="col-xs-5"> </div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-2">微信号:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userid" value="<%=saleInfoDetailMap.get("weixinId") %>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-2">用户姓名:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userid" value="<%=saleInfoDetailMap.get("salemanName") %>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">用户性别:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userid" value="<%=saleInfoDetailMap.get("salemanSex") %>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">支付宝账号:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userid" value="<%=saleInfoDetailMap.get("alipayId") %>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">银行开户行:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userid" value="<%=saleInfoDetailMap.get("cardBank") %>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">银行开户名:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userid" value="<%=saleInfoDetailMap.get("cardName") %>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">银行卡号:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userid" value="<%=saleInfoDetailMap.get("cardNumber") %>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">本月消费额:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userid" value="<%=saleInfoDetailMap.get("mouthSale") %>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl" style="margin-top:10px;padding-bottom:50px;margin-left:150px;">
						<input type="submit" name="doPass" value="通过" class="btn btn-primary radius" style="margin-left:15px;">
						<input type="submit" name="doBack" value="驳回" class="btn btn-primary radius" style="margin-left:50px;">
						<input type="hidden" name="reqNo" value="<%=saleInfoDetailMap.get("reqNo") %>" >
			</div>
		</form>
	</div>
</body>
</html>