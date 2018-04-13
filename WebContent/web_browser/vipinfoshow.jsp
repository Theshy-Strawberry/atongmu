<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List" pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VipInfoshow</title>
<script src="web_mobile/js/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="web_browser/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="web_browser/lib/icheck/icheck.css" />

</head>
<body style="padding-left:2%">
<script type="text/javascript">
	$(function() {
		$("#update").click(function() {
			$("#formid").submit();
		});
		$("#back").click(function() {
			window.location.href="ShowUsersListServlet?type="+$("#pagetype").val();
		});
		$("#change").click(function (){
			window.location.href="SalemanTransfer?userId="+$("#userid").val();
		});
	});
</script>
	<div class="titlebar">
		<h3>修改用户信息</h3>
	</div>
	<div class="line"></div>
	<%
		Object obj = request.getAttribute("vipInfo");
		if (obj != null) {
			List<String> vipInfo = (List<String>) obj;
	%>
	<div class="body">
		<form action="VipInfoUpdateServlet" method="post" id="formid" class="form form-horizontal responsive"  style="margin-top:15px;width:90%" >
			<div class="row cl">
				<label class="form-label col-xs-2">用户ID:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userid" id="userid" value="<%=vipInfo.get(0)%>" style="border:0px;" readonly="readonly">
					<input type="hidden" name="pagetype" id="pagetype" value="<%=request.getParameter("type")%>">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">加入日期:</label>
				<div class="formControls col-xs-5">
					<input type="date" class="input-text"  name="addDate" value="<%=vipInfo.get(1)%>" style="border:0px;" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2"> 会员积分：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="userintegral" value="<%=vipInfo.get(3)%>" style="width:260px;">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2"> 上级销售ID：</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="saleman" style="width:260px;" value="<%=vipInfo.get(4)%>">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">会员手机号:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text"  name="usertelnum" style="width:260px;" value="<%=vipInfo.get(5)%>">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">会员姓名:</label>
				<div class="formControls col-xs-5">
					<input type="text" class="input-text" name="username" style="width:260px;" value="<%=vipInfo.get(6)%>">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2">会员所属区域:</label>
				<div class="formControls col-xs-5">
					<%=vipInfo.get(9)%>
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2"> 会员微信号:</label>
				<div class="formControls col-xs-5">
					<input type="text"  class="input-text"  name="weinxin" style="width:260px;" value="<%=vipInfo.get(7)%>">
				</div>
				<div class="col-xs-5"> </div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2"> 月消费额:</label>
				<div class="formControls col-xs-5">
					<input type="text"  class="input-text"  name="userpwd" style="width:260px;" value="<%=vipInfo.get(8)%>" readonly="readonly">
				</div>
				<div class="col-xs-5"> </div>
			</div>
		</form>
	<%
		}
	%>
	</div>
	<br />
	<%
		Object obj1 = request.getAttribute("upInfo");
		if (obj1 != null) {
			List<String> upInfo = (List<String>) obj1;
	%>
	<div class="titlebar">
		<h3>上级销售信息:</h3>
	</div>
	<div class="line"></div>
	<form action="SalemanInfoUpdateServlet" method="get">
		<table class="table table-border table-bg table-bordered table-hover" style="width:90%;margin-top:15px;">
			<tr>
				<th width="20%" class="text-c" style="background-color:#DFF0D8;">销售员姓名：</th>
				<th width="20%" class="text-c" style="background-color:#DFF0D8;">销售员等级：</th>
				<th width="20%" class="text-c" style="background-color:#DFF0D8;">销售员月销售额：</th>
				<th width="20%" class="text-c" style="background-color:#DFF0D8;">销售员微信号:</th>
				<th width="20%" class="text-c" style="background-color:#DFF0D8;">详情</th>
			</tr>
			<tr>
				<td><label><%=upInfo.get(0)%></label></td>
				<td><label><%=upInfo.get(1)%></label></td>
				<td><label><%=upInfo.get(4)%></label></td>
				<td><label><%=upInfo.get(2)%></label></td>
				<td><input type="hidden" name="saleManId" value="<%=upInfo.get(3)%>" ></td>
				<td> <input type="submit" value="详情" class="btn btn-primary radius" ></td>
			</tr>
		</table>
	</form>
	<%
		}
	%>
	<br />
	<div class="row cl" style="margin-top:10px;margin-left:200px;">
		<button type="button" id="change" class="btn btn-primary radius" style="margin-left:15px;">转为销售员</button>
		<button type="button" id="update" class="btn btn-primary radius" style="margin-left:50px;">更新</button>
		<button type="button" id="back" class="btn btn-primary radius" style="margin-left:50px;">返回</button>
	</div>

</body>
</html>