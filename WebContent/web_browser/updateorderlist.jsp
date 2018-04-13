<%@page import="com.atongmu.bean.Tbl_saleman"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.atongmu.bean.Tbl_order"
	import="com.atongmu.bean.Tbl_user"
	import="com.atongmu.bean.Tbl_order_detail"
	import="com.atongmu.bean.Tbl_commodity"
	import="java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="web_browser/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="web_browser/lib/icheck/icheck.css" />
<title>修正订单</title>
<script src="web_mobile/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
	$(window).load(function() {
		var statusvalue = $("#hiddenstatus").val();
		if(statusvalue !="null"){
			$("#status").val(statusvalue);
		}

	});

	function backfrontpage() {
		if (!confirm("确认要返回吗？")) {
			window.event.returnValue = false;
		} else {
			history.back(-1);

		}
	}

	function confirmorder() {
		var orderid = document.getElementById("hiddenorderid").value;
		var orderuserid = document.getElementById("hiddenorderuserid").value;
		if (!confirm("确认修改吗？")) {
			window.event.returnValue = false;
		} else {
			window.location.href = "ConfirmUpdateOrderListShowServlet?orderid="
					+ orderid + "&orderuserid=" + orderuserid;
		}
	}

	function printorderlist() {
		var orderid = document.getElementById("hiddenorderid").value;
		if (!confirm("确认要打印发货单吗？")) {
			window.event.returnValue = false;
		} else {
			window.open("PrintOrderServlet?orderid=" + orderid);
		}
	}
	$(window).load(function(){
		var goodsclassified = $("#hdnlogisticscompany").val();
		if(goodsclassified !="null"){
			$("#logisticscompany").val(goodsclassified);
		}
	});

</script>
</head>
<style>
html { overflow-y: hidden; width:100%;}
</style>
<body style="padding-left:2%">
	<div class="titlebar">
		<h3>修改订单信息</h3>
	</div>
	<div class="line"></div>

	<div class="body">
			<form id="orderlistform" action="ConfirmUpdateOrderListShowServlet" id="orderform" class="form form-horizontal responsive" style="margin-top:15px;width:90%"
				method="post">
						<%
							Object objectdetaillist = request.getAttribute("orderdetail");
							List<Object> orderdetail = (List<Object>) objectdetaillist;
							Object userType=request.getAttribute("userType");
							String usertype=(String)userType;
							String username ="";
							String usertelnum ="";
							String useraddr ="";
							if("1".equals(usertype)){
								Tbl_saleman user=(Tbl_saleman)orderdetail.get(0);
								 username = user.getSaleman_name();
								 usertelnum = user.getSaleman_tel_num();
								 useraddr = user.getSaleman_addr();
							}else{
								Tbl_user user = (Tbl_user) orderdetail.get(0);
								 username = user.getUser_name();
								 usertelnum = user.getUser_tel_num();
								 useraddr = user.getUser_addr();
							}
						
							Tbl_order order = (Tbl_order) orderdetail.get(1);
							String orderid = order.getOrder_id();
							Date date = order.getOrder_date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							String orderdate = sdf.format(date);
							String orderuserid = order.getOrder_user();
							String status = order.getOrder_status();
							double totalprice = order.getOrder_price();
							String logisticscompany = order.getLogistics_company();
							String logisticsnumber = order.getLogistics_number();
						
						%>

								 <span style="margin-left:20px;" class="f-16">订单Id： <%=orderid%></span>
								 <input type="hidden" id="hiddenorderid" name="hiddenorderid"value="<%=orderid%>">
								 <input type="hidden" id="hiddenorderuserid" name="hiddenorderuserid" value="<%=orderuserid%>">
								 <span style="margin-left:20px;" class="f-16">订单时间： <%=orderdate%></span>
								 <span style="margin-left:20px;" class="f-16">用户ID:&nbsp;<%=orderuserid%></span>
								 <span style="margin-left:20px;" class="f-16">用户名: <%=username%></span>

								<div class="row cl">
									<label class="form-label col-xs-2">订单状态： </label>
									<div class="formControls col-xs-5">
									<span class="select-box" style="width:260px;">
											<select id="status" name="status" class="select"  style="width:250px;"  datatype="*" nullmsg="请选择商品分类">
															<option value="D001">待付款</option>
															<option value="D002">待发货</option>
															<option value="D003">已发货</option>
															<option value="D004">已取消</option>
															<option value="D005">已完成</option>
														</select>
									</span>
									</div>
									<div class="col-xs-5"> </div>
								</div>
								<div class="row cl">
									<label class="form-label col-xs-2">订单总金额：</label>
									<div class="formControls col-xs-5">
										<input type="text" class="input-text"  value="<%=totalprice%>" placeholder="订单总金额可以填入数字" name="totalmoney" id="totalmoney" datatype="n1-8" nullmsg="订单总金额不能为空"  style="width:260px;" >
									</div>
									<div class="col-xs-5"> </div>
								</div>
								<input type="hidden" id="hiddenstatus" name="hiddenstatus" value="<%=status%>">
								<input type="hidden" name="hdnlogisticscompany" id="hdnlogisticscompany" value="<%=logisticscompany%>">
								<div class="row cl">
									<label class="form-label col-xs-2">物流公司： </label>
									<div class="formControls col-xs-5">
									<span class="select-box" style="width:260px;">
											<select id="logisticscompany" name="logisticscompany" class="select"  style="width:250px;"  datatype="*">
															<option value="" selected="selected">未发货</option>
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
									<label class="form-label col-xs-2">物流编号:</label>
									<div class="formControls col-xs-5">
										<input type="text" class="input-text"  value="<%=logisticsnumber%>" name="logisticsno" id="logisticsno" style="width:260px;"  >
									</div>
									<div class="col-xs-5"> </div>
								</div>
								<div class="row cl">
									<label class="form-label col-xs-2">收货人姓名:</label>
									<div class="formControls col-xs-5">
										<input type="text" class="input-text"  value="<%=username%>" name="consignee" id="consignee" style="width:260px;"  >
									</div>
									<div class="col-xs-5"> </div>
								</div>
								<div class="row cl">
									<label class="form-label col-xs-2">收货人电话:</label>
									<div class="formControls col-xs-5">
										<input type="text" class="input-text"  value="<%=usertelnum%>" name="tel" id="tel" style="width:260px;"  >
									</div>
									<div class="col-xs-5"> </div>
								</div>
								<div class="row cl">
									<label class="form-label col-xs-2">收货人地址:</label>
									<div class="formControls col-xs-5">
										<input type="text" class="input-text"  value="<%=useraddr%>" name="address" id="address" style="width:260px;"  >
									</div>
									<div class="col-xs-5"> </div>
								</div>
								<div class="row cl"  style="margin-left:200px;margin-top:20px;">
									<input type="button" name="back" value="返回" onclick="backfrontpage()"  class="btn btn-primary radius" />
									<input type="button" name="printOrderList"	value="打印发货单" onclick="printorderlist()"  class="btn btn-primary radius" style="margin-left:10px;"/>
									<input type="submit" name="confirmUpdate" value="确定修改" onclick="confirmorder()"  class="btn btn-primary radius" style="margin-left:10px;"/>
								</div>



				</form>
				
				<lable></lable>
							</div>
			<div class="titlebar">
				<h3>订单明细</h3>
			</div>
			<div class="line"></div>
			<table class="table table-border table-bg table-bordered table-hover" style="width:90%;margin-top:15px;">
				<tr>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">商品名ID</td>
					<th width="10%" class="text-c" style="background-color:#DFF0D8;">商品名</td>
					<th width="10%" class="text-c" style="background-color:#DFF0D8;">图片</td>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">单价</td>
					<th width="5%" class="text-c" style="background-color:#DFF0D8;">数量</td>
					<th width="10%" class="text-c" style="background-color:#DFF0D8;">总价</td>
				</tr>
				<%
					Object objectordergoodsdetaillist = request
							.getAttribute("ordergoodsdetail");
					List<List<Object>> list = (List<List<Object>>) objectordergoodsdetaillist;
					for (int i = 0; i < list.size(); i++) {
						List<Object> list2 = (List<Object>) list.get(i);
						Tbl_commodity commodity = (Tbl_commodity) list2.get(0);
						Tbl_order_detail detail = (Tbl_order_detail) list2.get(1);
						String goodsid = detail.getGoods_id();
						String goodsname = commodity.getGoods_name();
						double singleprice = commodity.getGoods_price();
						int count = detail.getGoods_number();
						double goodstotalprice = singleprice * count;
				%>
				<tr>
					<td><%=goodsid%></td>
					<td><%=goodsname%></td>
					<td width="10%"><a href="#"><img height="100" width="100"
							class="img-responsive"
							src="http://www.in-artoon.com/goods_images/<%=goodsid %>_1.jpg"></a></td>
					<td><%=singleprice%></td>
					<td><%=count%></td>
					<td><%=goodstotalprice%></td>
				</tr>
			</table>
			<%
				}
			%>
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
	$("#orderform").Validform({
		tiptype:2
	});
});
</script>
</body>
</html>