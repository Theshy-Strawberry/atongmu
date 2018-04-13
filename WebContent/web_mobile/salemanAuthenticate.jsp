<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.atongmu.bean.Tbl_saleman,com.atongmu.bean.Tbl_user"
	pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title>销售员认证</title>
<link rel="stylesheet" href="css/seedsui.min.css">
<!--Exmobi能力-->
   <script src="../plugin/jquery/jquery.min.js"></script>
<script src="../plugin/seedsui/seedsui.min.js"></script>
<!--Exmobi能力-->
<script src="../plugin/exmobi/exmobi.js"></script>
</head>

<body>
	<header>
		<div class="titlebar">
			<a href="javascript:back()"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
			<h1>销售员认证</h1>
		</div>
	</header>
	<%
    Object returncodeObj = request.getParameter("errorcode");
    if(returncodeObj != null){
    	int returncode = Integer.parseInt(returncodeObj.toString());
    	if(returncode == 0 ){
    		//更新成功
    		%>
				<script>
					var at=new Alert("销售员认证成功！下次进入商场时开始生效。");
					at.show();
				</script>
    		<%
    	}else if(returncode == -1){
			//更新失败
    		%>
				<script>
					var at=new Alert("数据验证失败，数据库未登记您的资料。");
					at.show();
				</script>
    		<%

    	}
    }
	%>
	<article>
	<form id="form1" style="padding: 10px;" action="../SalemanAuthenticateServlet" method="post">

			<div class="group" >
				<div class="row">
				    <label class="row-left">真实姓名</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="真实姓名" name="user_name" id="user_name"
							data-rule-field="真实姓名"  data-rule="required user_name maxlength:5" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left">手机号码</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="手机号码" name="telnumber" id="telnumber"
							data-rule-field="手机号码" data-rule="required telnumber phone maxlength:11" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left">微信号</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="微信号" name="wechat" id="wechat"
							data-rule-field="微信号" data-rule="required wechat english maxlength:25" />
					</div>
				</div>
			</div>

			<button class="button submit block"  value=""  type="submit">进行认证</button>
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

