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
    <script src="../plugin/jquery/jquery.min.js"></script>
	<script src="../plugin/seedsui/seedsui.min.js"></script>
	<!--Exmobi能力-->
	<script src="../plugin/exmobi/exmobi.js"></script>
	<link rel="stylesheet" href="css/seedsui.min.css">
<%

    Object userObj = (Object)request.getSession().getAttribute("loginuser");
    Object returncodeObj = request.getParameter("errorcode");
    int returncode = 1;
    Tbl_user userinfo = (Tbl_user)userObj;
    if(returncodeObj != null){
    	returncode = Integer.parseInt(returncodeObj.toString());%>
<%

if (returncode==-3){
	%>
	<title>修改资料</title>
	<%
}else{
	if(userinfo.getVip_flag() == 1){
		%>
			<title>修改资料</title>
		<%
	}else{
		%>
			<title>申请成为会员</title>
		<%
	}
	%>

	<%
}
%>
</head>
<body>
	<header>
		<div class="titlebar">
		<%
	  		Object roleObj = request.getSession().getAttribute("role");
			int role = -1;
	    	if(null != roleObj){
	    		role = (Integer)roleObj;
	            if(role == 1){
		%>
			<a href="userhome.jsp"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%}else if(role == 2){ %>
			<a href="userhome.jsp"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%} %>

<%

if (returncode==-3){
	%>
	<h1>修改资料</h1>
	<%
}else{
	if(userinfo.getVip_flag() == 1){
		%>
			<h1>修改资料</h1>
		<%
	}else{
		%>
			<h1>注册会员</h1>
		<%
	}
	%>

	<%
}
%>
		</div>
	</header>
	<article>

<%
    	if(returncode == 0 ){
    		//更新成功
    		%>
				<script>
					var at=new Alert("资料修改成功，由于您尚未填写健康顾问ID，所以无法享受会员的权利。");
					at.show();

				</script>
    		<%
    	}else if(returncode == 1){
			//登记会员成功
    		%>
				<script>
					var at=new Alert("成功注册会员，目前您可以享受会员的所有权利。");
					at.show();
				</script>
    		<%
    	}else if(returncode == 2){
			//登记会员成功
    		%>
				<script>
					var at=new Alert("资料修改成功。");
					at.show();
				</script>
    		<%
    	}else if(returncode == -1){
			//更新失败
    		%>
				<script>
					var at=new Alert("资料修改失败。");
					at.show();
				</script>
    		<%
    	}else if(returncode == -2){
			//更新失败
    		%>
				<script>
					var at=new Alert("您目前还未填写健康顾问ID，没有健康顾问的用户，请与客服联系。");
					at.show();
				</script>
    		<%
    	}else if(returncode == -3){
			//更新失败
    		%>
				<script>
					var at=new Alert("您还没有填写收货地址，请您更新收货地址。");
					at.show();
				</script>
    		<%
    	}else if(returncode == -4){
			//更新失败
    		%>
				<script>
					var at=new Alert("您输入的健康顾问不存在，请确认健康顾问ID是否正确");
					at.show();
				</script>
    		<%

    	}else if(returncode == 9){
			//更新失败
    		%>
				<script>
					var at=new Alert("填入健康顾问ID即可注册成为会员，若您没有健康顾问ID，请与客服联系");
					at.show();
				</script>
    		<%

    	}
    }
   }

        %>
            <form id="form1" style="padding: 10px;margin-top:-10px;" method="post" action = "../UpdateUserInfoServlet?updateKubun=1&returncode=<%=returncode%>">

			<div class="group" >
			    <div class="row">
				    <label class="row-left" style="width:100%;color:#333333;">用户ID为系统生成，无法进行修改</label>
			    </div>
				<div class="row">
					<label class="row-left">用户ID</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text"  name="user_id" value="<%= userinfo.getUser_id() %>"
							id="user_id" readonly="readonly" />
							<i class="icon icon-clear-fill color-placeholder"></i>
					</div>
				</div>
			<%
			    if(returncode!= -3 &&(null == userinfo.getSaleman_id() || "".equals(userinfo.getSaleman_id()))){
			%>
			    <div class="row">
				    <label class="row-left" style="width:100%;color:#333333;">①填入健康顾问ID，享受会员权益 <p/>②若没有健康顾问ID，请向客服提出申请</label>
			    </div>
				<div class="row">
					<label class="row-left">健康顾问ID</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text" placeholder="" name="saleman_id" value=""
							data-rule-field="健康顾问ID" id="saleman_id" data-rule="maxlength:20" />
							<i class="icon icon-clear-fill color-placeholder"></i>
					</div>
				</div>
			</div>
			<%}else{ %>

				<div class="row">
					<label class="row-left">健康顾问ID</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text"  name="saleman_id" value="<%= userinfo.getSaleman_id() %>"
							id="saleman_id" readonly="readonly" />
							<i class="icon icon-clear-fill color-placeholder"></i>

					</div>
				</div>
			</div>
			<%} %>
			<div class="group" >
				<div class="row">
				    <label class="row-left">微信号</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="微信号" name="wechat" id="wechat" value="<%=userinfo.getWeixin_id() %>"
							data-rule-field="微信号" data-rule="required wechat english maxlength:25" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left">真实姓名</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="真实姓名" name="user_name" id="user_name"
							data-rule-field="真实姓名" value="<%= userinfo.getUser_name() %>" data-rule="required user_name maxlength:5" />
					</div>
				</div>

                <div class="row">
                    <label class="row-left">出生日期</label>
                    <div class="row-right input-box">
                    <%
                        String userBirthday = null;
                        if(null != userinfo.getUser_birthday()){
                        	 userBirthday = userinfo.getUser_birthday().toString().substring(0, 10);
                        }
                    %>
                        <input type="date" name="birthday" id="birthday" value="<%=userBirthday %>" placeholder="出生日期" data-rule="required birthday"/>
                    </div>
                </div>
				<div class="row">
				    <label class="row-left">手机号码</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="手机号码" name="telnumber" value="<%=userinfo.getUser_tel_num() %>"
							data-rule-field="手机号码" data-rule="required telnumber phone maxlength:11" />
					</div>
				</div>
				<div class="row">
				    <label class="row-left">收货地址</label>
    				<div class="input-box margin8 nolrmargin" style="width:70%;">
	    				<input type="text" placeholder="收货地址" name="address" value="<%=userinfo.getUser_addr() %>"
							data-rule-field="收货地址" data-rule="required address maxlength:100" />
					</div>
				</div>
				<div class="row">
				    <label class="row-left">邮政编码</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="邮政编码" name="postNumber" value="<%=userinfo.getUser_post() %>"
							data-rule-field="邮政编码" data-rule="required postNumber number maxlength:6" />
					</div>
				</div>
			</div>
			<div class="group" >
				<div class="row">
				    <label class="row-left">居住城市</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="居住城市" name="user_form" value="<%=userinfo.getUserfrom() %>"
							data-rule-field="居住城市" data-rule="required user_form number maxlength:20" />
					</div>
				</div>

            <div class="row">
                <label class="row-left">性别</label>
                <div class="row-right input-box input-text" style="border:none;">
                <%if(userinfo.getUser_sex() == null || "".equals(userinfo.getUser_sex()) || Integer.parseInt(userinfo.getUser_sex())==0){ %>
                    <label><input type="radio" value="1" name="sex" /><span>男</span></label>
                    <label style="margin-left:12px;"><input type="radio" value="2" name="sex"/><span>女</span></label>
                <%}else if(Integer.parseInt(userinfo.getUser_sex())==1){ %>
                    <label><input type="radio" value="1" name="sex" checked/><span>男</span></label>
                    <label style="margin-left:12px;"><input type="radio" value="2" name="sex"/><span>女</span></label>
                <%}else if(Integer.parseInt(userinfo.getUser_sex())==2){ %>
                    <label><input type="radio" value="1" name="sex" /><span>男</span></label>
                    <label style="margin-left:12px;"><input type="radio" value="2" name="sex" checked/><span>女</span></label>
                <%} %>
                </div>
            </div>
             <div class="row">
                <label class="row-left">民族</label>
                <div class="input-box lrpadding8 row-right">
                    <select class="rl" id="user_nation" name="user_nation" style="padding-right:24px;">
                    <%if("汉族".equals(userinfo.getUser_nation()) ){ %>
						<option value="汉族" selected>汉族</option>
                    <%}else{ %>
						<option value="汉族">汉族</option>
                    <%} %>

                    <%if("壮族".equals(userinfo.getUser_nation()) ){ %>
						<option value="壮族" selected>壮族</option>
                    <%}else{ %>
						<option value="壮族">壮族</option>
                    <%} %>

                    <%if("满族".equals(userinfo.getUser_nation()) ){ %>
						<option value="满族" selected>满族</option>
                    <%}else{ %>
						<option value="满族">满族</option>
                    <%} %>

                    <%if("回族".equals(userinfo.getUser_nation()) ){ %>
						<option value="回族" selected>回族</option>
                    <%}else{ %>
						<option value="回族">回族</option>
                    <%} %>

                    <%if("苗族".equals(userinfo.getUser_nation()) ){ %>
						<option value="苗族" selected>苗族</option>
                    <%}else{ %>
						<option value="苗族">苗族</option>
                    <%} %>

                    <%if("维吾尔族".equals(userinfo.getUser_nation()) ){ %>
						<option value="维吾尔族" selected>维吾尔族</option>
                    <%}else{ %>
						<option value="维吾尔族">维吾尔族</option>
                    <%} %>

                    <%if("土家族".equals(userinfo.getUser_nation()) ){ %>
						<option value="土家族" selected>土家族</option>
                    <%}else{ %>
						<option value="土家族">土家族</option>
                    <%} %>

                    <%if("彝族".equals(userinfo.getUser_nation()) ){ %>
						<option value="彝族" selected>彝族</option>
                    <%}else{ %>
						<option value="彝族">彝族</option>
                    <%} %>

                    <%if("蒙古族".equals(userinfo.getUser_nation()) ){ %>
						<option value="蒙古族" selected>蒙古族</option>
                    <%}else{ %>
						<option value="蒙古族">蒙古族</option>
                    <%} %>

                    <%if("藏族".equals(userinfo.getUser_nation()) ){ %>
						<option value="藏族" selected>藏族</option>
                    <%}else{ %>
						<option value="藏族">藏族</option>
                    <%} %>

                    <%if("布依族".equals(userinfo.getUser_nation()) ){ %>
						<option value="布依族" selected>布依族</option>
                    <%}else{ %>
						<option value="布依族">布依族</option>
                    <%} %>

                    <%if("侗族".equals(userinfo.getUser_nation()) ){ %>
						<option value="侗族" selected>侗族</option>
                    <%}else{ %>
						<option value="侗族">侗族</option>
                    <%} %>

                    <%if("瑶族".equals(userinfo.getUser_nation()) ){ %>
						<option value="瑶族" selected>瑶族</option>
                    <%}else{ %>
						<option value="瑶族">瑶族</option>
                    <%} %>

                    <%if("朝鲜族".equals(userinfo.getUser_nation()) ){ %>
						<option value="朝鲜族" selected>朝鲜族</option>
                    <%}else{ %>
						<option value="朝鲜族">朝鲜族</option>
                    <%} %>

                    <%if("白族".equals(userinfo.getUser_nation()) ){ %>
						<option value="白族" selected>白族</option>
                    <%}else{ %>
						<option value="白族">白族</option>
                    <%} %>

                    <%if("哈尼族".equals(userinfo.getUser_nation()) ){ %>
						<option value="哈尼族" selected>哈尼族</option>
                    <%}else{ %>
						<option value="哈尼族">哈尼族</option>
                    <%} %>

                    <%if("哈萨克族".equals(userinfo.getUser_nation()) ){ %>
						<option value="哈萨克族" selected>哈萨克族</option>
                    <%}else{ %>
						<option value="哈萨克族">哈萨克族</option>
                    <%} %>

                    <%if("黎族".equals(userinfo.getUser_nation()) ){ %>
						<option value="黎族" selected>黎族</option>
                    <%}else{ %>
						<option value="黎族">黎族</option>
                    <%} %>

                    <%if("傣族".equals(userinfo.getUser_nation()) ){ %>
						<option value="傣族" selected>傣族</option>
                    <%}else{ %>
						<option value="傣族">傣族</option>
                    <%} %>

                    <%if("畲族".equals(userinfo.getUser_nation()) ){ %>
						<option value="畲族" selected>畲族</option>
                    <%}else{ %>
						<option value="畲族">畲族</option>
                    <%} %>

                    <%if("傈僳族".equals(userinfo.getUser_nation()) ){ %>
						<option value="傈僳族" selected>傈僳族</option>
                    <%}else{ %>
						<option value="傈僳族">傈僳族</option>
                    <%} %>

                    <%if("仡佬族".equals(userinfo.getUser_nation()) ){ %>
						<option value="仡佬族" selected>仡佬族</option>
                    <%}else{ %>
						<option value="仡佬族">仡佬族</option>
                    <%} %>

                    <%if("东乡族".equals(userinfo.getUser_nation()) ){ %>
						<option value="东乡族" selected>东乡族</option>
                    <%}else{ %>
						<option value="东乡族">东乡族</option>
                    <%} %>

                    <%if("高山族".equals(userinfo.getUser_nation()) ){ %>
						<option value="高山族" selected>高山族</option>
                    <%}else{ %>
						<option value="高山族">高山族</option>
                    <%} %>

                    <%if("拉祜族".equals(userinfo.getUser_nation()) ){ %>
						<option value="拉祜族" selected>拉祜族</option>
                    <%}else{ %>
						<option value="拉祜族">拉祜族</option>
                    <%} %>

                    <%if("水族".equals(userinfo.getUser_nation()) ){ %>
						<option value="水族" selected>水族</option>
                    <%}else{ %>
						<option value="水族">水族</option>
                    <%} %>

                    <%if("佤族".equals(userinfo.getUser_nation()) ){ %>
						<option value="佤族" selected>佤族</option>
                    <%}else{ %>
						<option value="佤族">佤族</option>
                    <%} %>

                    <%if("纳西族".equals(userinfo.getUser_nation()) ){ %>
						<option value="纳西族" selected>纳西族</option>
                    <%}else{ %>
						<option value="纳西族">纳西族</option>
                    <%} %>

                    <%if("羌族".equals(userinfo.getUser_nation()) ){ %>
						<option value="羌族" selected>羌族</option>
                    <%}else{ %>
						<option value="羌族">羌族</option>
                    <%} %>

                    <%if("土族".equals(userinfo.getUser_nation()) ){ %>
						<option value="土族" selected>土族</option>
                    <%}else{ %>
						<option value="土族">土族</option>
                    <%} %>

                    <%if("仫佬族".equals(userinfo.getUser_nation()) ){ %>
						<option value="仫佬族" selected>仫佬族</option>
                    <%}else{ %>
						<option value="仫佬族">仫佬族</option>
                    <%} %>

                    <%if("锡伯族".equals(userinfo.getUser_nation()) ){ %>
						<option value="锡伯族" selected>锡伯族</option>
                    <%}else{ %>
						<option value="锡伯族">锡伯族</option>
                    <%} %>

                    <%if("柯尔克孜族".equals(userinfo.getUser_nation()) ){ %>
						<option value="柯尔克孜族" selected>柯尔克孜族</option>
                    <%}else{ %>
						<option value="柯尔克孜族">柯尔克孜族</option>
                    <%} %>

                    <%if("达斡尔族".equals(userinfo.getUser_nation()) ){ %>
						<option value="达斡尔族" selected>达斡尔族</option>
                    <%}else{ %>
						<option value="达斡尔族">达斡尔族</option>
                    <%} %>

                    <%if("景颇族".equals(userinfo.getUser_nation()) ){ %>
						<option value="景颇族" selected>景颇族</option>
                    <%}else{ %>
						<option value="景颇族">景颇族</option>
                    <%} %>

                    <%if("毛南族".equals(userinfo.getUser_nation()) ){ %>
						<option value="毛南族" selected>毛南族</option>
                    <%}else{ %>
						<option value="毛南族">毛南族</option>
                    <%} %>

                    <%if("撒拉族".equals(userinfo.getUser_nation()) ){ %>
						<option value="撒拉族" selected>撒拉族</option>
                    <%}else{ %>
						<option value="撒拉族">撒拉族</option>
                    <%} %>

                    <%if("布朗族".equals(userinfo.getUser_nation()) ){ %>
						<option value="布朗族" selected>布朗族</option>
                    <%}else{ %>
						<option value="布朗族">布朗族</option>
                    <%} %>

                    <%if("塔吉克族".equals(userinfo.getUser_nation()) ){ %>
						<option value="塔吉克族" selected>塔吉克族</option>
                    <%}else{ %>
						<option value="塔吉克族">塔吉克族</option>
                    <%} %>

                    <%if("阿昌族".equals(userinfo.getUser_nation()) ){ %>
						<option value="阿昌族" selected>阿昌族</option>
                    <%}else{ %>
						<option value="阿昌族">阿昌族</option>
                    <%} %>

                    <%if("普米族".equals(userinfo.getUser_nation()) ){ %>
						<option value="普米族" selected>普米族</option>
                    <%}else{ %>
						<option value="普米族">普米族</option>
                    <%} %>

                    <%if("鄂温克族".equals(userinfo.getUser_nation()) ){ %>
						<option value="鄂温克族" selected>鄂温克族</option>
                    <%}else{ %>
						<option value="鄂温克族">鄂温克族</option>
                    <%} %>

                    <%if("怒族".equals(userinfo.getUser_nation()) ){ %>
						<option value="怒族" selected>怒族</option>
                    <%}else{ %>
						<option value="怒族">怒族</option>
                    <%} %>

                    <%if("京族".equals(userinfo.getUser_nation()) ){ %>
						<option value="京族" selected>京族</option>
                    <%}else{ %>
						<option value="京族">京族</option>
                    <%} %>

                    <%if("基诺族".equals(userinfo.getUser_nation()) ){ %>
						<option value="基诺族" selected>基诺族</option>
                    <%}else{ %>
						<option value="基诺族">基诺族</option>
                    <%} %>

                    <%if("德昂族".equals(userinfo.getUser_nation()) ){ %>
						<option value="德昂族" selected>德昂族</option>
                    <%}else{ %>
						<option value="德昂族">德昂族</option>
                    <%} %>

                    <%if("保安族".equals(userinfo.getUser_nation()) ){ %>
						<option value="保安族" selected>保安族</option>
                    <%}else{ %>
						<option value="保安族">保安族</option>
                    <%} %>

                    <%if("俄罗斯族".equals(userinfo.getUser_nation()) ){ %>
						<option value="俄罗斯族" selected>俄罗斯族</option>
                    <%}else{ %>
						<option value="俄罗斯族">俄罗斯族</option>
                    <%} %>

                    <%if("裕固族".equals(userinfo.getUser_nation()) ){ %>
						<option value="裕固族" selected>裕固族</option>
                    <%}else{ %>
						<option value="裕固族">裕固族</option>
                    <%} %>

                    <%if("乌孜别克族".equals(userinfo.getUser_nation()) ){ %>
						<option value="乌孜别克族" selected>乌孜别克族</option>
                    <%}else{ %>
						<option value="乌孜别克族">乌孜别克族</option>
                    <%} %>

                    <%if("门巴族".equals(userinfo.getUser_nation()) ){ %>
						<option value="门巴族" selected>门巴族</option>
                    <%}else{ %>
						<option value="门巴族">门巴族</option>
                    <%} %>

                    <%if("鄂伦春族".equals(userinfo.getUser_nation()) ){ %>
						<option value="鄂伦春族" selected>鄂伦春族</option>
                    <%}else{ %>
						<option value="鄂伦春族">鄂伦春族</option>
                    <%} %>

                    <%if("独龙族".equals(userinfo.getUser_nation()) ){ %>
						<option value="独龙族" selected>独龙族</option>
                    <%}else{ %>
						<option value="独龙族">独龙族</option>
                    <%} %>

                    <%if("塔塔尔族".equals(userinfo.getUser_nation()) ){ %>
						<option value="塔塔尔族" selected>塔塔尔族</option>
                    <%}else{ %>
						<option value="塔塔尔族">塔塔尔族</option>
                    <%} %>

                    <%if("赫哲族".equals(userinfo.getUser_nation()) ){ %>
						<option value="赫哲族" selected>赫哲族</option>
                    <%}else{ %>
						<option value="赫哲族">赫哲族</option>
                    <%} %>

                    <%if("珞巴族".equals(userinfo.getUser_nation()) ){ %>
						<option value="珞巴族" selected>珞巴族</option>
                    <%}else{ %>
						<option value="珞巴族">珞巴族</option>
                    <%} %>


                    </select>
                    <i class="icon icon-arrowright color-placeholder"></i>
                    </div>
                </div>
                <div class="row">
                    <label class="row-left">文化程度</label>
	                <div class="input-box lrpadding8 row-right">
	                    <select class="rl" style="padding-right:24px;"id="user_education" name="user_education" >
		                    <%if("初中".equals(userinfo.getUser_education()) ){ %>
								<option value="初中" selected>初中</option>
		                    <%}else{ %>
								<option value="初中">初中</option>
		                    <%} %>
		                    <%if("高中/技校/中专/中技".equals(userinfo.getUser_education()) ){ %>
								<option value="高中/技校/中专/中技" selected>高中/技校/中专/中技</option>
		                    <%}else{ %>
								<option value="高中/技校/中专/中技">高中/技校/中专/中技</option>
		                    <%} %>
		                    <%if("大专".equals(userinfo.getUser_education()) ){ %>
								<option value="大专" selected>大专</option>
		                    <%}else{ %>
								<option value="大专">大专</option>
		                    <%} %>
		                    <%if("大学".equals(userinfo.getUser_education()) ){ %>
								<option value="大学" selected>大学</option>
		                    <%}else{ %>
								<option value="大学">大学</option>
		                    <%} %>
		                    <%if("硕士及以上".equals(userinfo.getUser_education()) ){ %>
								<option value="硕士及以上" selected>硕士及以上</option>
		                    <%}else{ %>
								<option value="硕士及以上">硕士及以上</option>
		                    <%} %>
	                    </select>
	                    <i class="icon icon-arrowright color-placeholder"></i>
	                </div>
                </div>
                <div class="row">
                    <label class="row-left">职业</label>
	                <div class="input-box lrpadding8 row-right" >
	                    <select class="rl" style="padding-right:24px;" id="user_occupation" name="user_occupation">
		                    <%if("专业技术人员/医生/教师".equals(userinfo.getUser_occupation()) ){ %>
								<option value="专业技术人员/医生/教师" selected>专业技术人员/医生/教师</option>
		                    <%}else{ %>
								<option value="专业技术人员/医生/教师">专业技术人员/医生/教师</option>
		                    <%} %>
		                    <%if("行政管理干部".equals(userinfo.getUser_occupation()) ){ %>
								<option value="行政管理干部" selected>行政管理干部</option>
		                    <%}else{ %>
								<option value="行政管理干部">行政管理干部</option>
		                    <%} %>
		                    <%if("一般职员/服务人员/工人".equals(userinfo.getUser_occupation()) ){ %>
								<option value="一般职员/服务人员/工人" selected>一般职员/服务人员/工人</option>
		                    <%}else{ %>
								<option value="一般职员/服务人员/工人">一般职员/服务人员/工人</option>
		                    <%} %>
		                    <%if("自由职业者".equals(userinfo.getUser_occupation()) ){ %>
								<option value="自由职业者" selected>自由职业者</option>
		                    <%}else{ %>
								<option value="自由职业者">自由职业者</option>
		                    <%} %>
		                    <%if("农民".equals(userinfo.getUser_occupation()) ){ %>
								<option value="农民" selected>农民</option>
		                    <%}else{ %>
								<option value="农民">农民</option>
		                    <%} %>
		                    <%if("军人".equals(userinfo.getUser_occupation()) ){ %>
								<option value="军人" selected>军人</option>
		                    <%}else{ %>
								<option value="军人">军人</option>
		                    <%} %>
		                    <%if("无业人员".equals(userinfo.getUser_occupation()) ){ %>
								<option value="无业人员" selected>无业人员</option>
		                    <%}else{ %>
								<option value="无业人员">无业人员</option>
		                    <%} %>
		                    <%if("其它".equals(userinfo.getUser_occupation()) ){ %>
								<option value="其它" selected>其它</option>
		                    <%}else{ %>
								<option value="其它">其它</option>
		                    <%} %>
	                    </select>
	                    <i class="icon icon-arrowright color-placeholder"></i>
	                </div>
                </div>
            </div>
			<button class="button submit block" type="submit">提交</button>


		</form>
	</article>

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

