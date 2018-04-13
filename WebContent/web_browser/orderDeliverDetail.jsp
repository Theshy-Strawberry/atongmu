<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="lib/icheck/icheck.css" />
<script src="js/jquery-2.1.3.min.js"></script>
<title>订单发货</title>
</head>
<body style="padding-left:2%">
	<div class="titlebar">
		<h3>订单发货</h3>
	</div>
	<div class="line"></div>
					      <%
				String orderId = request.getParameter("orderId");
				List<Map<String, Object>> orderNotPayList = (List<Map<String, Object>>)request.getSession().getAttribute("orderNotPayList");
				int i = 0;
				int tempKey = 0;
				for(Map<String,Object> tempMap:orderNotPayList){
					if(orderId.equals(tempMap.get("orderId").toString())){
						tempKey = i;
					}
					i++;
				}
				%>
	<div class="body">
		<form id="wuliu" action="../OrderDeliveryDetailServlet" method="post" class="form form-horizontal responsive" style="margin-top:15px">

						<div class="row cl">
							<label class="form-label col-xs-2">收件人姓名：</label>
							<div class="formControls col-xs-5">
								<input type="text" class="input-text"  name="userName" id="userName"  readonly="readonly" value="<%=orderNotPayList.get(tempKey).get("userName") %>" style="width:320px;" >
							</div>
							<div class="col-xs-5"> </div>
						</div>

						<div class="row cl">
							<label class="form-label col-xs-2">收件人电话：</label>
							<div class="formControls col-xs-5">
								<input type="text" class="input-text"  name="userTel" id="userTel"  readonly="readonly" value="<%=orderNotPayList.get(tempKey).get("userTel") %>" style="width:320px;" >
							</div>
							<div class="col-xs-5"> </div>
						</div>

						<div class="row cl">
							<label class="form-label col-xs-2">收件人地址：</label>
							<div class="formControls col-xs-5">
								<input type="text" class="input-text"  name="userAddr" id="userAddr"  readonly="readonly" value="<%=orderNotPayList.get(tempKey).get("userAddr") %>" style="width:320px;" >
							</div>
							<div class="col-xs-5"> </div>
						</div>


					<div class="row cl">
						<label class="form-label col-xs-2">物流公司： </label>
						<div class="formControls col-xs-5">
						<span class="select-box" style="width:260px;">
								<select id="logisticsCom" name="logisticsCom" class="select"  style="width:250px;"  datatype="*">
												<option value="" selected="selected">请选择物流公司---</option>
												<option value="aae全球专递">aae全球专递</option>
												<option value="德邦物流">德邦物流</option>
												<option value="dhl">dhl</option>
												<option value="ems快递">ems快递</option>
												<option value="申通">申通</option>
												<option value="顺丰">顺丰</option>
												<option value="天天快递">天天快递</option>
												<option value="圆通速递">圆通速递</option>
												<option value="韵达快递">韵达快递</option>
												<option value="中通速递">中通速递</option>
								</select>
						</span>
						</div>
						<div class="col-xs-5"> </div>
					</div>


						<div class="row cl">
							<label class="form-label col-xs-2">物流编号：</label>
							<div class="formControls col-xs-5">
								<input type="text" class="input-text" name="logisticsNum" id="logisticsNum" datatype="*1-20" nullmsg="物流编号不能为空"  style="width:320px;" >
								<input type="hidden" name="logisticsPrice" value="0">
							</div>
							<div class="col-xs-5"> </div>
						</div>



				   <div class="row cl" style="margin-left:300px;margin-top:20px;">
				   	<input type="submit" value="确定发货" name="release" class="btn btn-primary radius"/>
			       </div>

			<input type="hidden" name="orderId" value="<%=orderId %>">
		</form>
	</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript" src="lib/bootstrap-Switch/bootstrapSwitch.js"></script>
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="lib/Validform/5.3.2/passwordStrength-min.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.js"></script>
<script>
//var navigation = responsiveNav("Hui-navbar", {customToggle: ".nav-toggle"});

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#wuliu").Validform({
		tiptype:2
	});
});
</script>
</body>
</html>