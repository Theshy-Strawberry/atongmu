<%@page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.atongmu.util.*"
	import="com.atongmu.bean.*" import="java.sql.*"
	import="com.atongmu.daoImpl.*"
	import="java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
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
<title>信息汇总</title>
</head>
<body style="padding-left:2%">
<%
	int order_count = 0;
	int order_count_today = 0;
	int normal_user_count = 0;
	int vip_user_count = 0;
	int saleman_acc_count_today = 0;
	int saleman_acc_count = 0;
	double order_sale_sum = 0.0;
	StringBuffer sql = new StringBuffer();

	 Connection con = null;
	 PreparedStatement ps = null;
	 Statement sm = null;
	 ResultSet rs = null;
try{
	con = MySQLUtil.getConnection();
	sql = new StringBuffer("");
	sql.append(" select count(1) as order_count from tbl_order where order_status = 'D002'");
	System.out.println(sql);
	ps = con.prepareStatement(sql.toString());
    rs = ps.executeQuery();
    if(rs.next()){
    	order_count = rs.getInt("order_count");
    }
    MySQLUtil.closeAll(rs, sm, con, ps);
    con = MySQLUtil.getConnection();
    sql = new StringBuffer("");
    sql.append("select count(1) as order_count_today, ifnull(sum(order_price),0) as order_sale_sum from tbl_order");
    sql.append(" where order_status = 'D002' and order_date = CURDATE()");
    System.out.println(sql);
	ps = con.prepareStatement(sql.toString());
    rs = ps.executeQuery();
    if(rs.next()){
    	order_count_today = rs.getInt("order_count_today");
    	order_sale_sum = rs.getDouble("order_sale_sum");
    }
    MySQLUtil.closeAll(rs, sm, con, ps);
    con = MySQLUtil.getConnection();
    sql = new StringBuffer("");
    sql.append("select count(1) as normal_user_count from tbl_user where reg_date = CURDATE() and vip_flag = 0");
    System.out.println(sql);
	ps = con.prepareStatement(sql.toString());
    rs = ps.executeQuery();
    if(rs.next()){
    	normal_user_count = rs.getInt("normal_user_count");
    }
    MySQLUtil.closeAll(rs, sm, con, ps);
    con = MySQLUtil.getConnection();
    sql = new StringBuffer("");
    sql.append("select count(1) as vip_user_count from tbl_user where reg_date = CURDATE()  and vip_flag = 1");
    System.out.println(sql);
	ps = con.prepareStatement(sql.toString());
    rs = ps.executeQuery();
    if(rs.next()){
    	vip_user_count = rs.getInt("vip_user_count");
    }
    MySQLUtil.closeAll(rs, sm, con, ps);
    con = MySQLUtil.getConnection();
    sql = new StringBuffer("");
    sql.append("select count(1) as saleman_acc_count_today from tbl_sale_audit where req_date = CURDATE() and req_status = 'H003'");
    System.out.println(sql);
	ps = con.prepareStatement(sql.toString());
    rs = ps.executeQuery();
    if(rs.next()){
    	saleman_acc_count_today = rs.getInt("saleman_acc_count_today");
    }
    MySQLUtil.closeAll(rs, sm, con, ps);
    con = MySQLUtil.getConnection();
    sql = new StringBuffer("");
    sql.append("select count(1) as saleman_acc_count from tbl_sale_audit where req_status='H003'");
    System.out.println(sql);
	ps = con.prepareStatement(sql.toString());
    rs = ps.executeQuery();
    if(rs.next()){
    	saleman_acc_count = rs.getInt("saleman_acc_count");
    }
    MySQLUtil.closeAll(rs, sm, con, ps);
} catch (Exception e) {
	CommonUtil.logger.error(e.getMessage());
}
finally{
	MySQLUtil.closeAll(rs, sm, con, ps);
}
								%>	
	<div class="titlebar">
		<h3>阿同木在线商城后台主页</h3>
	</div>
	<div class="line"></div>
		<div class="row cl"style="margin-top:40px;">
			<label class="form-label col-xs-2">待发货订单数：</label>
			<div class="formControls col-xs-5">
				<label class="form-label col-xs-2"><%=order_count %></label>
				<a href="../ShowUsersListServlet?type=1" class="btn btn-success radius" href="javascript:;">立刻处理</a>
			</div>
			<div class="formControls col-xs-5">
			</div>

			<div class="col-xs-5"> </div>
		</div>
		<div class="row cl"style="margin-top:40px;">
			<label class="form-label col-xs-2">今日新增未发货订单数：</label>
			<div class="formControls col-xs-5">
				<label class="form-label col-xs-2"><%=order_count_today %></label>
				<a href="../ShowUsersListServlet?type=1" class="btn btn-success radius" href="javascript:;">立刻处理</a>
			</div>
			<div class="formControls col-xs-5">
			</div>

		</div>
		<div class="row cl"style="margin-top:40px;">
			<label class="form-label col-xs-2">今日总零售额：</label>
			<div class="formControls col-xs-5">
				<label class="form-label col-xs-2"><%=order_sale_sum %></label>
			</div>
			<div class="col-xs-5"> </div>
		</div>
		<div class="row cl"style="margin-top:40px;">
			<label class="form-label col-xs-2">今日新增游客数：</label>
			<div class="formControls col-xs-5">
				<label class="form-label col-xs-2"><%=normal_user_count %></label>
				<a href="../ShowUsersListServlet?type=1" class="btn btn-success radius" href="javascript:;">立刻查看</a>
			</div>
			<div class="formControls col-xs-5">
			</div>
		</div>
		<div class="row cl"style="margin-top:40px;">
			<label class="form-label col-xs-2">今日新增会员数：</label>
			<div class="formControls col-xs-5">
				<label class="form-label col-xs-2"><%=vip_user_count %></label>
				<a href="../ShowUsersListServlet?type=2" class="btn btn-success radius" href="javascript:;">立刻查看</a>
			</div>
			<div class="formControls col-xs-5">
			</div>

		</div>
		<div class="row cl"style="margin-top:40px;">
			<label class="form-label col-xs-2">待审核销售数：</label>
			<div class="formControls col-xs-5">
				<label class="form-label col-xs-2"><%=saleman_acc_count_today %></label>
				<a href="../SaleAuditListServlet" class="btn btn-success radius" href="javascript:;">立刻处理</a>
			</div>
			<div class="formControls col-xs-5">
			</div>

		</div>
		<div class="row cl"style="margin-top:40px;">
			<label class="form-label col-xs-2">今日新增待审核销售数：</label>
			<div class="formControls col-xs-5">
				<label class="form-label col-xs-2"><%=saleman_acc_count_today %></label>
				<a href="../SaleAuditListServlet" class="btn btn-success radius" href="javascript:;">立刻处理</a>
			</div>
			<div class="formControls col-xs-5">
			</div>

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
	$("#goodsform").Validform({
		tiptype:2
	});
  	var select=$("#select").val();
	$("#goodsCategory option[value='"+select+"']").attr("selected", true);
});
</script>
</body>
</html>