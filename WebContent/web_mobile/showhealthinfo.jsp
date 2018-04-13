<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.atongmu.bean.*"
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
	<link rel="stylesheet" href="css/agile.layout.css">
<link rel="stylesheet" href="../plugin/seedsui/seedsui.min.css">
	<!--Exmobi能力-->
	<link rel="stylesheet" href="css/seedsui.min.css">
	<style>
	.row-left{width:40%;color:#000000}
	</style>
	<title>健康管理基础数据</title>
</head>
<body>
<%
    Object userObj = (Object)request.getSession().getAttribute("loginuser");
    Object returncodeObj = request.getParameter("errorcode");
    int returncode = 1;
    Tbl_user userinfo = null;
    Tbl_saleman salemaninfo = null;
    if(returncodeObj != null){
    	returncode = Integer.parseInt(returncodeObj.toString());%>
	<header>
		<div class="titlebar">
		<%
	  		Object roleObj = request.getSession().getAttribute("role");
			int role = -1;
	    	if(null != roleObj){
	    		role = (Integer)roleObj;
	            if(role == 1){
	            	userinfo = (Tbl_user)userObj;
		%>
			<a href="javascript:history.back();"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%}else if(role == 2){
		        salemaninfo  =(Tbl_saleman)userObj;
		%>
			<a href="javascript:history.back();"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%} %>
			<h1>我的身体状况</h1>
		</div>

	</header>

<%
    	if(returncode == 0 ){
    		//更新成功
    		%>
				<script>
					var at=new Alert("您已完成该月份的一般情况调查，下个月份记得再来填写噢！");
					at.show();
				</script>
    		<%

    	}
    }
}else{
	%>
				<script>
					var at=new Alert("请您回忆这个月的生活及饮食习惯，并填入或选择真实的数据噢！");
					at.show();
				</script>
	<%
}
        %>
<script>
function displayDiv(selectedValue,displayDivName){
	if(selectedValue == "0"){
		document.getElementById(displayDivName).style.display="block";
	}else{
		document.getElementById(displayDivName).style.display="none";
	}
}
function displayDiv2(selectedID,displayDivName){
	if(document.getElementById(selectedID).checked ==true){
		document.getElementById(displayDivName).style.display="block";
	}else{
		document.getElementById(displayDivName).style.display="none";
	}
}
<%Tbl_health_info healthinfo = (Tbl_health_info)request.getSession().getAttribute("health_info"); %>
</script>
			<div class="group" style="padding: 10px;margin-top:30px;"  >
				<div class="row">
					<label class="row-left" style="color:#333333;">身高</label>
						<div class="row-right input-box" >
							<input type="text"  name="height"  value="<%=healthinfo.getHeight() %>cm" readonly="readonly"
							id="height" data-rule-field="身高" data-rule="required height number" />
						</div>
					</div>

				<div class="row">
					<label class="row-left" style="color:#333333;">体重</label>
					<div class="row-right input-box">
							<input type="text"  name="weight" value="<%=healthinfo.getWeight() %>kg" readonly="readonly"
							id="weight" data-rule-field="体重" data-rule="required weight number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">腰围</label>
    				<div class="row-right input-box">
	    				<input type="text" name="waist" id="waist" value="<%=healthinfo.getWaist() %>cm" readonly="readonly"
							data-rule-field="腰围" data-rule="required waist number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">臀围</label>
    				<div class="row-right input-box">
	    				<input type="text" name="hipline" id="hipline" value="<%=healthinfo.getHipline()%>cm" readonly="readonly"
							data-rule-field="臀围" data-rule="required hipline number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">最低血压</label>
    				<div class="row-right input-box">
	    				<input type="text" name="low_blood_pressure" id="low_blood_pressure"
	    					value="<%=healthinfo.getLow_blood_pressure() %>mmHg" readonly="readonly"
							data-rule-field="最低血压" data-rule="required low_blood_pressure number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">最高血压</label>
    				<div class="row-right input-box">
	    				<input type="text" name="high_blood_pressure" id="high_blood_pressure"
	    					value="<%=healthinfo.getHigh_blood_pressure() %>mmHg" readonly="readonly"
							data-rule-field="最高血压" data-rule="required high_blood_pressure number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">空腹血糖(GLU)</label>
    				<div class="row-right input-box">
	    				<input type="text" name="fasting_blood_glucose" id="fasting_blood_glucose"
	    					value="<%=healthinfo.getFasting_blood_glucose() %>mmol/L" readonly="readonly"
							data-rule-field="空腹血糖" data-rule="required fasting_blood_glucose number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">甘油三酯(TG)</label>
    				<div class="row-right input-box">
	    				<input type="text" name="triglyceride" id="triglyceride"
	    					value="<%=healthinfo.getTriglyceride() %>mmol/L" readonly="readonly"
							data-rule-field="甘油三酯" data-rule="required triglyceride number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left">总胆固醇(TC)</label>
    				<div class="row-right input-box">
	    				<input type="text" name="total_cholesterol" id="total_cholesterol"
	    					value="<%=healthinfo.getTotal_cholesterol() %>mmol/L" readonly="readonly"
							data-rule-field="总胆固醇" data-rule="required total_cholesterol number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left">低密度脂蛋白(LDL)</label>
    				<div class="row-right input-box">
	    				<input type="text" name="ldl" id="ldl"
	    					value="<%=healthinfo.getLdl() %>mmol/L" readonly="readonly"
							data-rule-field="低密度脂蛋白" data-rule="required ldl number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left">高密度脂蛋白(HDL)</label>
    				<div class="row-right input-box">
	    				<input type="text" name="hdl" id="hdl"
	    					value="<%=healthinfo.getHdl() %>mmol/L" readonly="readonly"
							data-rule-field="高密度脂蛋白" data-rule="required hdl number" />
					</div>
				</div>

				<div class="row" style="border-bottom:none;">
				    <label class="row-left" style="width:100%;color:#333333;size:0.9em;">如有异常情况请注明 <p/>如转氨酶有问题、心电图、B超检测有脂肪肝等</label>
			    </div>
				<div class="row">
					<div class="input-box"  style="width:100%;">
	    				<input type="text" name="other" id="other" placeholder="请输入异常内容"
	    					value="<%=healthinfo.getOther() %>" readonly="readonly"
							data-rule-field="异常情况" data-rule="maxlength:50" />
					</div>
				</div>
			</div>
		<div class="group" >

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">是否有糖尿病 </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask1" name="ask1" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk1() %>" selected><%=healthinfo.getAsk1() %></option>
					</select>
				</div>
			</div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">是否有高血压 </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask30" name="ask30" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk30() %>" selected><%=healthinfo.getAsk30() %></option>
					</select>
				</div>
			</div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">是否有高血脂 </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask31" name="ask31" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk31() %>" selected><%=healthinfo.getAsk31() %></option>
					</select>
				</div>
			</div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">是否有高尿酸血症 </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask32" name="ask32" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk32() %>" selected><%=healthinfo.getAsk32() %></option>
					</select>
				</div>
			</div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">总的来说，您认为您的健康状况 </label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask2" name="ask2" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk2() %>" selected><%=healthinfo.getAsk2() %></option>
					</select>
				</div>
            </div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">最近一个月，您有健康问题吗？</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask3" name="ask3" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk3() %>" selected><%=healthinfo.getAsk3() %></option>
					</select>
				</div>
            </div>
            <div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您是否开心？</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask4" name="ask4" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk4() %>" selected><%=healthinfo.getAsk4() %></option>
					</select>
				</div>
            </div>
            <div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您的心态怎么样？是否遇到什么事都能想得开？</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask5" name="ask5" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk5() %>" selected><%=healthinfo.getAsk5() %></option>
					</select>
				</div>
            </div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您服用处方药吗？ </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask6" name="ask6" style="padding-right:24px;" onchange="displayDiv(this.value,'div_ask6_other')">
						<option value="<%=healthinfo.getAsk6() %>" selected><%=healthinfo.getAsk6() %></option>
					</select>
				</div>
			</div>

	        <div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">为了保持健康或防治疾病，您有以下哪些消费？</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;" >
						<label><span><%=healthinfo.getAsk6() %></span></label>
                        <br/>
				</div>
            </div>

            <div class="row" style="display:none;"id="div_ask44">
				    <label class="row-left">消费方向</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" name="ask44" id="ask44" value="" placeholder="请输入您的消费方向"
	    					readonly="readonly" value="<%=healthinfo.getAsk44() %>"
							data-rule-field="消费方向" data-rule="maxlenth:50" />
					</div>
			</div>


        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您服用保健品吗？ </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask7" name="ask7" style="padding-right:24px;" onchange="displayDiv(this.value,'div_ask7_other')">
						<option value="<%=healthinfo.getAsk7() %>" selected><%=healthinfo.getAsk7() %></option>
					</select>
				</div>
			</div>

		</div>
		<div class="group" >
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">粮谷类（主食馒头、米饭、包子、饼、面条、窝头等）干重摄入量： </label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask8" name="ask8" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk8() %>" selected><%=healthinfo.getAsk8() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">其中粗杂粮（大米白面之外的粮谷类包括绿豆等杂豆类）摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask9" name="ask9" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk9() %>" selected><%=healthinfo.getAsk9() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">薯类摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask10" name="ask10" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk10() %>" selected><%=healthinfo.getAsk10() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">蔬菜摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask11" name="ask11" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk11() %>" selected><%=healthinfo.getAsk11() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">水果摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask12" name="ask12" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk12() %>" selected><%=healthinfo.getAsk12() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">畜禽肉、鱼虾贝类摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask13" name="ask13" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk13() %>" selected><%=healthinfo.getAsk13() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">蛋类摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask14" name="ask14" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk14() %>" selected><%=healthinfo.getAsk14() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">豆腐等豆制品摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask15" name="ask15" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk15() %>" selected><%=healthinfo.getAsk15() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">奶和奶制品摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask16" name="ask16" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk16() %>" selected><%=healthinfo.getAsk16() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">坚果摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask17" name="ask17" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk17() %>" selected><%=healthinfo.getAsk17() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">饮水量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask18" name="ask18" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk18() %>" selected><%=healthinfo.getAsk18() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">每天吃几餐： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask19" name="ask19" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk19() %>" selected><%=healthinfo.getAsk19() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您每天或每周食用食物的种类： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask20" name="ask20" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk20() %>" selected><%=healthinfo.getAsk20() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">你的饮食口味（咸）： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask21" name="ask21" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk21() %>" selected><%=healthinfo.getAsk21() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">你的饮食口味（油）： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask22" name="ask22" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk22() %>" selected><%=healthinfo.getAsk22() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您近半年是否有被动吸烟（二手烟）的情况？ </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask23" name="ask23" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk23() %>" selected><%=healthinfo.getAsk23() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您是否吸烟？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask24" name="ask24" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk24() %>" selected><%=healthinfo.getAsk24() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您是否喝酒？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask25" name="ask25" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk25() %>" selected><%=healthinfo.getAsk25() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您每周参加几次体育锻炼？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask26" name="ask26" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk26() %>" selected><%=healthinfo.getAsk26() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您平均每次体育锻炼的时间？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask27" name="ask27" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk27() %>" selected><%=healthinfo.getAsk27() %></option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">您的体育锻炼形式？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask28" name="ask28" style="padding-right:24px;">
						<option value="<%=healthinfo.getAsk28() %>" selected><%=healthinfo.getAsk28() %></option>
					</select>
				</div>
			</div>
		</div>
		<div class="group" >
        	<div class="row">
				<label class="row-left" style="width:90%;color:#333333;">您在生活、工作中是否出现以下几种情况：</label>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">肩颈臂疼痛：疼痛不固定，会转移</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<%if("是".equals(healthinfo.getAsk33())){ %>
						<label><input type="radio" id="option1" name="ask33"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask33" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask33" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask33" readonly checked value="否"><span>否</span></label>
					<%} %>

				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">胃疼恶心：从没有胃病却胃疼</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<%if("是".equals(healthinfo.getAsk34())){ %>
						<label><input type="radio" id="option1" name="ask34"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask34" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask33" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask33" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">焦虑失眠：无症状惊醒</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right"style="margin-left:20px;" >
					<%if("是".equals(healthinfo.getAsk35())){ %>
						<label><input type="radio" id="option1" name="ask35"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask35" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask35" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask35" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">心跳加剧：不规则且无缘由</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<%if("是".equals(healthinfo.getAsk36())){ %>
						<label><input type="radio" id="option1" name="ask36"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask36" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask36" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask36" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">过度出汗：长期反复</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right"style="margin-left:20px;" >
					<%if("是".equals(healthinfo.getAsk37())){ %>
						<label><input type="radio" id="option1" name="ask37"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask37" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask37" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask37" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">呼吸短促：头晕，很难深呼吸</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<%if("是".equals(healthinfo.getAsk38())){ %>
						<label><input type="radio" id="option1" name="ask38"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask38" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask38" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask38" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">极度疲劳：没做什么就特别累，哈欠不断</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right"style="margin-left:20px;" >
					<%if("是".equals(healthinfo.getAsk39())){ %>
						<label><input type="radio" id="option1" name="ask39"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask39" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask39" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask39" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">四肢尤其双手突然冰冷</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<%if("是".equals(healthinfo.getAsk40())){ %>
						<label><input type="radio" id="option1" name="ask40"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask40" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask40" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask40" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">血压突然升高和降低，且有鼻出血</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<%if("是".equals(healthinfo.getAsk41())){ %>
						<label><input type="radio" id="option1" name="ask41"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask41" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask41" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask41" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">走路步态异常，腿部无力</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<%if("是".equals(healthinfo.getAsk42())){ %>
						<label><input type="radio" id="option1" name="ask42"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask42" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask42" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask42" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:90%;color:#333333;">突然剧烈头痛</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<%if("是".equals(healthinfo.getAsk43())){ %>
						<label><input type="radio" id="option1" name="ask43"  checked readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask43" readonly value="否"><span>否</span></label>
					<%}else{ %>
						<label><input type="radio" id="option1" name="ask43" readonly value="是"><span>是</span></label>&nbsp;&nbsp;
						<label><input type="radio" id="option1" name="ask43" readonly checked value="否"><span>否</span></label>
					<%} %>
				</div>
			</div>
		</div>
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

