<%@page import="com.atongmu.bean.Tbl_code_master"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" 
	import="com.atongmu.daoImpl.ManagerDaoImpl"
	import="java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
	<%-- <%@ include file="roleCheck.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="js/jquery-2.1.3.min.js"></script>
<script>
 $().ready(function() {
    alert('hello');
    var drrole = $("#hiderole").val(); 
    alert(drrole)
    var drha = $("#hideha").val();
    alert(drha);
    var hiflag= $("#flag").val();
    alert(hiflag);
    $("#role").attr("value",drrole);
    $("#homearea").attr("value",drha);
    if(hiflag=='exist'){
    	alert("您的微信号已存在");
    }
}); 

</script>

<title>添加管理员</title>
</head>
<body style="padding-left:2%">
    <%
    Object objweixinid = request.getAttribute("weixinid");
    String weixinid = String.valueOf(objweixinid);
    Object objpassword = request.getAttribute("password");
    String password = String.valueOf(objpassword);
    Object objusername = request.getAttribute("username");
    String username = String.valueOf(objusername);
    Object objteleno = request.getAttribute("telphone");
    String teleno = String.valueOf(objteleno);
    Object objrole = request.getAttribute("role");
    String role = String.valueOf(objrole);
    Object objhomearea = request.getAttribute("homearea");
    String homearea = String.valueOf(objhomearea);
    Object objflag = request.getAttribute("flag");
    String flag = String.valueOf(objflag);
    %>
	
	<div class="line"></div>
	<form action="../AddAdminServlet" method="post" id="addadminform"  class="form form-horizontal responsive" style="margin-top:15px">
		<div class="row cl">
			<label class="form-label col-xs-2">登陆id：</label>
			<div class="formControls col-xs-5">
				<input type="text" class="input-text" value="<%=objweixinid==null?"":weixinid%>" placeholder="请输入您的微信号" name="weixinid" id="weixinid" datatype="*1-20" nullmsg="不能为空"   style="width:320px;" >
			</div>
			<div class="col-xs-5"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-2">登陆密码：</label>
			<div class="formControls col-xs-5">
				<input type="text" class="input-text" value="<%=objpassword==null?"":password%>" placeholder="请输入您的登陆密码" name="password" id="password" datatype="*1-20" nullmsg="不能为空"   style="width:320px;" >
			</div>
			<div class="col-xs-5"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-2">用户名称：</label>
			<div class="formControls col-xs-5">
				<input type="text" class="input-text" value="<%=objusername==null?"":username%>" placeholder="请输入您的用户名称" name="username" id="username"  nullmsg="不能为空"   style="width:320px;" >
			</div>
			<div class="col-xs-5"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-2">手机号：</label>
			<div class="formControls col-xs-5">
				<input type="text" class="input-text" value="<%=objteleno==null?"":teleno%>" placeholder="请输入您的手机号" name="telphone" id="telphone"  nullmsg="不能为空"   style="width:320px;" >
			</div>
			<div class="col-xs-5"> </div>
		</div>
		<input type="hidden"  value="<%=flag==null?"":flag%>"  name="flag" id="flag">
		<input type="hidden"  value="<%=role==null?"":role%>"  name="hiderole" id="hiderole">
		<input type="hidden"  value="<%=homearea==null?"":homearea%>"  name="hideha" id="hideha">
		<div class="row cl">
			<label class="form-label col-xs-2">管理者角色： </label>
			<div class="formControls col-xs-5">
			<span class="select-box" style="width:210px;">
				<select id="role" name="role" class="select"  style="width:200px;"  datatype="*" nullmsg="请选择">
					<option value="" selected>---请选择----</option>
					<%
					ManagerDaoImpl managerdao = new ManagerDaoImpl();
						List<Tbl_code_master> droprolelist = managerdao.dropdownroleList();
						for(Tbl_code_master codemaster:droprolelist){
							String code = codemaster.getCode();
							String value = codemaster.getCode_value();
						%>
					<option value="<%=code %>"><%=value %></option>
					<%
					}
					%>
				</select>
			</span>
			</div>
			<div class="col-xs-5"> </div>
		</div>
		
		 <div class="row cl">
			<label class="form-label col-xs-2">管理者管辖区域： </label>
			<div class="formControls col-xs-5">
			<span class="select-box" style="width:210px;">
				<select id="homearea" name="homearea" class="select"  style="width:200px;"  datatype="*" nullmsg="请选择">
					<option value="" selected>---请选择----</option>
					<%
					ManagerDaoImpl managerhadao = new ManagerDaoImpl();
						List<Tbl_code_master> drophalist = managerhadao.dropdownhomeareaList();
						for(Tbl_code_master codemaster:drophalist){
							String code = codemaster.getCode();
							String value = codemaster.getCode_value();
						%>
					<option value="<%=code %>"><%=value %></option>
					<%
					}
					%>
				</select>
			</span>
			</div>
			<div class="col-xs-5"> </div>
		</div>
		
	   <div class="row cl" style="margin-left:300px;margin-top:20px;">
	   	<input type="submit" value="添加" name="add" class="btn btn-primary radius"/>
       </div>
	</form>


</body>
</html>