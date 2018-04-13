<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.atongmu.bean.Tbl_saleman,com.atongmu.bean.Tbl_user"
    import="com.atongmu.dao.SaleManDao,com.atongmu.daoImpl.SaleManDaoImpl,com.atongmu.bean.Tbl_code_master"
	pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title>健康顾问</title>
<link rel="stylesheet" href="css/seedsui.min.css">
</head>

<body>
	<header>
		<div class="titlebar">
			<a href="javascript:history.back(-1);"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
			<h1>我的健康顾问</h1>
		</div>
	</header>
	<article style="margin-top:-20px;">
	<form id="form1" style="padding:10px;" method="post" action = "../UpdateUserInfoServlet?updateKubun=4">
<%
    Object roleObj = request.getSession().getAttribute("role");
    Object userObj = (Object)request.getSession().getAttribute("loginuser");
	SaleManDao salemanDao = new SaleManDaoImpl();
    Tbl_user userinfo = (Tbl_user)userObj;
	Tbl_saleman salemaninfo = salemanDao.selectSaleManByID(userinfo.getSaleman_id());
	Object errorcodeObj = request.getParameter("errorcode");
	if (errorcodeObj != null){
		int errorcode = Integer.parseInt(errorcodeObj.toString());
		if(errorcode == 0){
			%>
			<div class="group" >
				<div class="row">
					<label class="row-left" style="width:100%;color:red;">绑定成功！</label>
				</div>
			</div>
			<%
		}else if(errorcode == -1){
			%>
			<div class="group" >
				<div class="row">
					<label class="row-left" style="width:100%;color:red;">您输入的推荐人ID不存在<p/>请确认推荐人ID是否正确</label>
				</div>
			</div>
			<%
		}
	}
        %>

			<div class="group" >
			<%
			    if(null != userinfo.getSaleman_id() || !"".equals(userinfo.getSaleman_id())){
			%>
			    <div class="row">
				    <label class="row-left" style="width:100%;color:red;">您的健康顾问资料</label>
			    </div>
				<div class="row">
					<label class="row-left">顾问ID</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text" name="saleman_id" value="<%= salemaninfo.getSaleman_id() %>"
							data-rule-field="" id="saleman_id" readonly="readonly"  />
							<i class="icon icon-clear-fill color-placeholder"></i>
					</div>
				</div>
				<div class="row">
					<label class="row-left">顾问姓名</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text" name="salemanname" value="<%= salemaninfo.getSaleman_name() %>"
							data-rule-field="" id="salemanname" readonly="readonly"  />
							<i class="icon icon-clear-fill color-placeholder"></i>
					</div>
				</div>
				<div class="row">
					<label class="row-left">顾问微信号</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text" name="wechat" value="<%= salemaninfo.getWeixin_id() %>"
							data-rule-field="" id="wechat" readonly="readonly"  />
							<i class="icon icon-clear-fill color-placeholder"></i>
					</div>
				</div>
				<div class="row">
					<label class="row-left">顾问手机号</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text"  name="telno" value="<%= salemaninfo.getSaleman_tel_num() %>"
							data-rule-field="" id="telno" readonly="readonly"  />
							<i class="icon icon-clear-fill color-placeholder"></i>
					</div>
				</div>
				<div class="row">
					<label class="row-left">顾问地区</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text" name="area" value="<%= salemaninfo.getHome_area_info().getCode_value() %>"
							data-rule-field="" id="area" readonly="readonly"  />
							<i class="icon icon-clear-fill color-placeholder"></i>
					</div>
				</div>
			<%}

			%>

			</div>
		</form>
	</article>

	<script src="../plugin/jquery/jquery.min.js"></script>
	<script src="../plugin/seedsui/seedsui.min.js"></script>
	<!--Exmobi能力-->
	<script src="../plugin/exmobi/exmobi.js"></script>
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

