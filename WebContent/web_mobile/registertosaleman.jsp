<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.atongmu.bean.Tbl_saleman,com.atongmu.bean.Tbl_user,com.atongmu.daoImpl.UpdateSomeInfo"
	pageEncoding="UTF-8"%>
<%@ include file="roleCheck.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title>申请成为销售员</title>
<link rel="stylesheet" href="css/seedsui.min.css">
<script src="../plugin/jquery/jquery.min.js"></script>
<script src="../plugin/seedsui/seedsui.min.js"></script>
<!--Exmobi能力-->
<script src="../plugin/exmobi/exmobi.js"></script>
</head>

<body>
	<header>
		<div class="titlebar">
			<a href="userhome.jsp"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
			<h1>申请成为销售员</h1>
		</div>
	</header>

	<article style="width:100%;margin-top:-20px;">

	<%
	Object errorcodeObj = request.getParameter("errcode");
	if (errorcodeObj != null){
		int errorcode = Integer.parseInt(errorcodeObj.toString());
		if(errorcode == -1){
			%>
				<script>
					var at=new Alert("申请失败，请检查输入的数据是否正确。");
					at.show();
				</script>
			<%
		}else if(errorcode == -2){
			%>
				<script>
					var at=new Alert("您已经提交过销售资料，请您耐心等待。");
					at.show();
				</script>
			<%
		}else if(errorcode == -3){
			%>
				<script>
					var at=new Alert("您输入的推荐人ID不存在，请确认后再次填入。");
					at.show();
				</script>
			<%
		}
	}
    %><%
	UpdateSomeInfo updateSomeInfo = new UpdateSomeInfo();
	if(updateSomeInfo.selectAuditSaleManStatus((String)request.getSession().getAttribute("userid"))!=null){
		//如果当前已经申请过的话，则返回页面，提示当前用户已申请销售
		%>
	 <label class="row-left" style="width:200px	;color:red;margin-top:30px;">您已经提交销售资料<p></p>请耐心等待审核</label>
		<%
	}else{

	%>
	<form id="form1" style="padding: 10px;" onsubmit=" formCheck();" method="post" action = "../UpdateUserInfoServlet?updateKubun=5"
	 enctype="multipart/form-data" >
	<script>
	function formCheck(){
	    var upSalemanID = document.getElementByID("user_id").Value;
	    if(upSalemanID == ""){
	    	alert("请您填写推荐人ID");
	    	return false;
	    }
	    return true;
	}
	</script>
<%
    Object roleObj = request.getSession().getAttribute("role");
    Object userObj = (Object)request.getSession().getAttribute("loginuser");

    if(null != roleObj){
        int role = (Integer)roleObj;
        if(role == 1){
            Tbl_user userinfo = (Tbl_user)userObj;
        %>

			<div class="group" >
			<%
			    if(null == userinfo.getSaleman_id() || "".equals(userinfo.getSaleman_id())){
			%>
			    <div class="row">
				    <label class="row-left" style="width:100%;color:red;">推荐人ID填入并提交后，将无法修改<br/>若没有推荐人ID，请向管理员提出申请</label>
			    </div>
				<div class="row">
					<label class="row-left">推荐人ID</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text" placeholder="<%= userinfo.getSaleman_id() %>" name="saleman_id" value="<%= userinfo.getSaleman_id() %>"
							data-rule-field="推荐人ID" id="saleman_id" data-rule="required saleman_id" />
							<i class="icon icon-clear-fill color-placeholder"></i>
					</div>
				</div>
			</div>
			<%}else{ %>
			    <div class="row">
				    <label class="row-left" style="width:100%;color:red;">您已绑定推荐人，无法修改此项</label>
			    </div>
				<div class="row">
					<label class="row-left">推荐人ID</label>
					<div class="row-right input-box" data-input="clear">
						<input type="text" placeholder="S102010001" name="saleman_id_dis" value="<%= userinfo.getSaleman_id() %>"
							   id="saleman_id_dis" readonly="readonly"  />
							<i class="icon icon-clear-fill color-placeholder"></i>
						<input type="hidden" name="saleman_id" id="saleman_id" value="<%= userinfo.getSaleman_id() %>"/>
					</div>
				</div>
			<%} %>
            <div class="group">
                <div class="row">
				    <label class="row-left" style="width:100%">请上传身份证正面照片</label>
				</div>
                <div class="row">
    				<div class="input-box margin8 nolrmargin">
	    				<input type="file" name="idcard_fore" id="idcard_fore" accept="image/*"
	    				       data-rule-field="身份证正面照片" data-rule="required idcard_fore"  />
					</div>
				</div>
                <div class="row">
				    <label class="row-left" style="width:100%">请上传身份证反面照片</label>
				</div>
                <div class="row">
    				<div class="input-box margin8 nolrmargin">
	    				<input type="file" name="idcard_back" id="idcard_back" accept="image/*"
	    				       data-rule-field="身份证反面照片" data-rule="required idcard_back"  />
					</div>
				</div>
            </div>
			<div class="group" >
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
                        <input type="date" name="birthday" id="birthday" value="<%=userBirthday %>" placeholder="出生日期"
                         data-rule-field="出生日期" data-rule="required birthday"/>
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
    				<div class="input-box margin8 nolrmargin">
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
				<div class="row">
				    <label class="row-left">微信号</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="微信号" name="wechat" value="<%=userinfo.getWeixin_id() %>" id="wechat"
							data-rule-field="微信号" data-rule="required wechat maxlength:25" />
					</div>
				</div>
				<div class="row">
				    <label class="row-left">支付宝账号</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="支付宝账号" name="alipay_id"
							data-rule-field="支付宝账号" data-rule="required alipay_id english maxlength:25" />
					</div>
				</div>
				<div class="row">
				    <label class="row-left">银行卡开户行</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="例：招商银行华龙路支行" name="bankName"
							data-rule-field="银行卡开户行" data-rule="required bankName chinese maxlength:50" />
					</div>
				</div>
				<div class="row">
				    <label class="row-left">银行卡开户名</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="例：张三" name="bankCreateName"
							data-rule-field="银行卡开户名" data-rule="required bankCreateName chinese maxlength:50" />
					</div>
				</div>
				<div class="row">
				    <label class="row-left">银行卡号</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" placeholder="银行卡号" name="bankCardNo"
							data-rule-field="银行卡号" data-rule="required bankCardNo number 20" />
				    </div>
			   </div>
			</div>

			<input type="hidden" value="<%=userinfo.getUser_integral() %>" id="user_integral" name="user_integral"/>
			<%
			}else if(role == 2){
	            Tbl_saleman userinfo = (Tbl_saleman)userObj;
			%>
             <h3>您已经是销售了哦！</h3>
			<%
			        }
			    }
			%>
			<div class="group" >
				<div class="row">
					<label><input type="checkbox" id="ckrole"><span>我已阅读<a href="salemanRole.jsp">销售员守则</a></span></label>
				</div>
			</div>
			<button class="button submit block" id="submitForm" type="submit">确定提交</button>
		</form>
	</article>
<%} %>

	<script>
		window.addEventListener("load", function() {
			var f = new Form("#form1");
			f.container.addEventListener("submit", function(e) {
				e.preventDefault();
				var returnflg;
				if(!document.getElementById("ckrole").checked){
					var at=new Alert("您还没有同意销售员协议");
					at.show();
					returnflg = false;
					return;
				}else{
					returnflg = true;
				}
				returnflg = f.validate();
				if(returnflg == true){
					document.getElementById("submitForm").disabled=true;
					var at=new Alert("正在提交，请勿关闭页面...");
					at.show();
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

