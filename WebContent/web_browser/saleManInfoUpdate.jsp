<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="web_mobile/js/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="lib/icheck/icheck.css" />
<title>销售资料修改</title>
</head>
<body style="padding-left:2%">
	<div class="titlebar">
		<h3>修改销售信息</h3>
	</div>
	<div class="line"></div>
	<div class="body">
		<form id="wuliu" action="SalemanInfoUpdateServlet" method="post" class="form form-horizontal responsive"  style="margin-top:15px;width:90%">
			<%
			Map<String, Object> saleManInfoMap = (Map<String, Object>)request.getAttribute("saleManMap");
			%>
			<div class="row cl">
				<label class="form-label col-xs-2">销售员ID:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="saleManId" value="<%=saleManInfoMap.get("salemanId") %>" style="border:0px;" readonly="readonly">
					<input type="hidden" name="saleManId" value="<%=saleManInfoMap.get("salemanId") %>">
					<input type="hidden" name="userIntegral" value="0">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">加入日期:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="regDate" value="<%=saleManInfoMap.get("regDate") %>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">上级销售员ID:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="upSalemanId" value="<%=saleManInfoMap.get("upSalemanId") %>" style="">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">销售员手机号：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="salemanTelNum" value="<%=saleManInfoMap.get("salemanTelNum") %>" style="">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">销售员姓名：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="salemanName" value="<%=saleManInfoMap.get("salemanName") %>" style="">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">当前级别：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="salemanName" value="<%=saleManInfoMap.get("codeValue") %>" readonly style="border:0px;">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">销售员微信号：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="weixinId" value="<%=saleManInfoMap.get("weixinId") %>" readonly style="">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">销售员性别：</label>
				<div class="formControls col-xs-5">
					<div class="radio-box" >
						<input type="radio" name="salemanSex" value="0" <%if("0".equals(saleManInfoMap.get("salemanSex"))) out.print("checked"); %> />保密
					</div>
					<div class="radio-box" >
						<input type="radio" name="salemanSex" value="1" <%if("1".equals(saleManInfoMap.get("salemanSex"))) out.print("checked"); %> />男
					</div>
					<div class="radio-box" >
						<input type="radio" name="salemanSex" value="2" <%if("2".equals(saleManInfoMap.get("salemanSex"))) out.print("checked"); %> />女
					</div>
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">销售员生日：</label>
				<div class="formControls col-xs-5">
					<input type="date" class="input-text"  name="salemanBirthday" value="<%=saleManInfoMap.get("salemanBirthday") %>" readonly style="border:0px;">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">支付宝账号：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="alipayId" value="<%=saleManInfoMap.get("alipayId") %>"  style="">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">银行卡开户行：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="cardBank" value="<%=saleManInfoMap.get("cardBank") %>"  style="">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">银行卡开户名：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="cardName" value="<%=saleManInfoMap.get("cardName") %>"  style="">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">银行卡卡号：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="cardNumber" value="<%=saleManInfoMap.get("cardNumber") %>"  style="">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">撤销理由：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="revokeReason" value="<%=saleManInfoMap.get("revokeReason") %>"  style="">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">总消费额：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="revokeReason" value="<%=saleManInfoMap.get("sumOrderPrice") %>"  readonly style="border:0px;">
				</div>
				<div class="col-xs-5"> </div>
			</div>
		</form>
		<div class="row cl" style="margin-top:10px;">
			<div class="formControls col-xs-5">
				<input type="submit" name="revokeSaleMan" value="撤销销售员" class="btn btn-primary radius">
				<input type="submit" name="updateSaleMan" value="更新" class="btn btn-primary radius">
			</div>
			<div class="col-xs-5"> </div>
		</div>
		
			<%
			List<List<Map<String, Object>>> downLevelInfo = (List<List<Map<String, Object>>>)request.getAttribute("downLevelInfo");
			int i = 1;
			for(List<Map<String, Object>> tempList: downLevelInfo){
				if(tempList.size() > 0){
			%>
			<div class="rightOne">
			<label class="form-label col-xs-2" style="margin-top:10px;">第<%=i %>级</label>
				<table class="table table-border table-bg table-bordered table-hover" style="width:90%;margin-top:15px;">
					<tr>
						<th width="33%" class="text-c" style="background-color:#DFF0D8;">销售员ID</td>
						<th width="33%" class="text-c" style="background-color:#DFF0D8;">销售员姓名</td>
						<th width="33%" class="text-c" style="background-color:#DFF0D8;">本月销售额</td>
					</tr>
			<%
					for (Map<String, Object> tempMap : tempList) {
			%>
					<tr>
						<td><%=tempMap.get("saleManId") %></td>
						<td><%=tempMap.get("saleManName") %></td>
						<td><%=tempMap.get("mouthSale") %></td>
					</tr>
			<%
					}
			%>
				</table>
			</div>
			<%
				i++;
				}
			}
			%>

	</div>
</body>
</html>