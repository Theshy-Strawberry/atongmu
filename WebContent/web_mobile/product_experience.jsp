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
	<title>阿同木产品食用情况</title>
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
			<a href="javascript:history.back(-1);"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%}else if(role == 2){
		        salemaninfo  =(Tbl_saleman)userObj;
		%>
			<a href="javascript:history.back(-1);"><i class="icon icon-arrowleft" style="margin-left:5px;"></i></a>
		<%}

		}%>
			<h1>阿同木产品食用情况</h1>
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
}else{
	%>
				<script>
					var at=new Alert("请您回忆这个月的生活及饮食习惯，并填入或选择真实的数据噢！");
					at.show();
				</script>
	<%
		}
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
</script>
            <form id="form1" style="padding: 10px;margin-top:10px;" method="post" action = "../HealthInfoCommitServlet">

			<div class="group" >
				<div class="row">
					<label class="row-left" style="color:#333333;">身高</label>
						<div class="row-right input-box" data-input="clear">
							<input type="text"  name="height" value="" placeholder="单位：cm"
							id="height" data-rule-field="身高" data-rule="required height number" />
							<i class="icon icon-clear-fill color-placeholder"></i>
						</div>
					</div>

				<div class="row">
					<label class="row-left" style="color:#333333;">体重</label>
					<div class="row-right input-box" data-input="clear">
							<input type="text"  name="weight" value=""  placeholder="单位：kg"
							id="weight" data-rule-field="体重" data-rule="required weight number" />
							<i class="icon icon-clear-fill color-placeholder"></i>
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">腰围</label>
    				<div class="row-right input-box">
	    				<input type="text" name="waist" id="waist" value="" placeholder="单位：cm"
							data-rule-field="腰围" data-rule="required waist number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">臀围</label>
    				<div class="row-right input-box">
	    				<input type="text" name="hipline" id="hipline" value="" placeholder="单位：cm"
							data-rule-field="臀围" data-rule="required hipline number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">最低血压</label>
    				<div class="row-right input-box">
	    				<input type="text" name="low_blood_pressure" id="low_blood_pressure" value="" placeholder="单位：mmHg"
							data-rule-field="最低血压" data-rule="required low_blood_pressure number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">最高血压</label>
    				<div class="row-right input-box">
	    				<input type="text" name="high_blood_pressure" id="high_blood_pressure" value="" placeholder="单位：mmHg"
							data-rule-field="最高血压" data-rule="required high_blood_pressure number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">空腹血糖(GLU)</label>
    				<div class="row-right input-box" >
	    				<input type="text" name="fasting_blood_glucose" id="fasting_blood_glucose" value="" placeholder="单位：mmol/L"
							data-rule-field="空腹血糖" data-rule="required fasting_blood_glucose number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left" style="color:#333333;">甘油三酯(TG)</label>
    				<div class="row-right input-box">
	    				<input type="text" name="triglyceride" id="triglyceride" value="" placeholder="单位：mmol/L"
							data-rule-field="甘油三酯" data-rule="required triglyceride number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left">总胆固醇(TC)</label>
    				<div class="row-right input-box">
	    				<input type="text" name="total_cholesterol" id="total_cholesterol" value="" placeholder="单位：mmol/L"
							data-rule-field="总胆固醇" data-rule="required total_cholesterol number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left">低密度脂蛋白(LDL)</label>
    				<div class="row-right input-box">
	    				<input type="text" name="ldl" id="ldl" value="" placeholder="单位：mmol/L"
							data-rule-field="低密度脂蛋白" data-rule="required ldl number" />
					</div>
				</div>

				<div class="row">
				    <label class="row-left">高密度脂蛋白(HDL)</label>
    				<div class="row-right input-box">
	    				<input type="text" name="hdl" id="hdl" value="" placeholder="单位：mmol/L"
							data-rule-field="高密度脂蛋白" data-rule="required hdl number" />
					</div>
				</div>

				<div class="row" style="border-bottom:none;">
				    <label class="row-left" style="width:100%;color:#333333;">如有异常情况请注明 <p/>如转氨酶有问题、心电图、B超检测有脂肪肝等</label>
			    </div>
				<div class="row">
					<div class="input-box" data-input="clear" style="width:100%;">
	    				<input type="text" name="other" id="other" value="" placeholder="请输入异常内容"
							data-rule-field="异常情况" data-rule="maxlength:50" />
					</div>
				</div>
			</div>
		<div class="group" >

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">是否有糖尿病 </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask1" name="ask1" style="padding-right:24px;">
						<option value="无" selected>无</option>
						<option value="有" >有</option>
					</select>
				</div>
			</div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">是否有高血压 </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask30" name="ask30" style="padding-right:24px;">
						<option value="无" selected>无</option>
						<option value="有" >有</option>
					</select>
				</div>
			</div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">是否有高血脂 </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask31" name="ask31" style="padding-right:24px;">
						<option value="无" selected>无</option>
						<option value="有" >有</option>
					</select>
				</div>
			</div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">是否有高尿酸血症 </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask32" name="ask32" style="padding-right:24px;">
						<option value="无" selected>无</option>
						<option value="有" >有</option>
					</select>
				</div>
			</div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">总的来说，您认为您的健康状况 </label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask2" name="ask2" style="padding-right:24px;">
						<option value="极好" selected>极好</option>
						<option value="很好" >很好</option>
						<option value="好" >好</option>
						<option value="一般" >一般</option>
						<option value="差" >差</option>
					</select>
				</div>
            </div>

			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">最近一个月，您有健康问题吗？</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask3" name="ask3" style="padding-right:24px;">
						<option value="完全没有" selected>完全没有</option>
						<option value="有一点" >有一点</option>
						<option value="中度" >中度</option>
						<option value="较大" >较大</option>
						<option value="非常大" >非常大</option>
					</select>
				</div>
            </div>
            <div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您是否开心？</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask4" name="ask4" style="padding-right:24px;">
						<option value="没有不开心" selected>没有不开心</option>
						<option value="不开心" >不开心</option>
						<option value="非常不开心" >非常不开心</option>
					</select>
				</div>
            </div>
            <div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您的心态怎么样？是否遇到什么事都能想得开？</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask5" name="ask5" style="padding-right:24px;">
						<option value="很想得开" selected>很想得开</option>
						<option value="想得开" >想得开</option>
						<option value="一般" >一般</option>
						<option value="一般" >一般</option>
						<option value="一般" >想不开</option>
						<option value="很想不开" >很想不开</option>
					</select>
				</div>
            </div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您吃处方药吗？ </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask6" name="ask6" style="padding-right:24px;" onchange="displayDiv(this.value,'div_ask6_other')">
						<option value="否" selected>否</option>
						<option value="0" >是</option>
					</select>
				</div>
			</div>

            <div class="row" style="display:none;"id="div_ask6_other">
				    <label class="row-left">处方药信息</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" name="ask6_other" id="ask6_other" value="" placeholder="服用处方药的剂量和名称"
							data-rule-field="处方药名称" data-rule="maxlenth:50" />
					</div>
			</div>

	        <div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">为了保持健康或防治疾病，您有以下哪些消费？</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;" >
						<label><input type="checkbox" id="option1" name="ask43" value="去医院看病、开药或去药店买药"><span>去医院看病、开药或去药店买药</span></label>
                        <br/>
						<label><input type="checkbox" id="option2" name="ask43" value="购买保健品或医疗器械"><span>购买保健品或医疗器械</span></label>
                        <br/>
						<label><input type="checkbox" id="option3" name="ask43" value="去健身场所锻炼"><span>去健身场所锻炼</span></label>
                        <br/>
						<label><input type="checkbox" id="option4" name="ask43" value="体检"><span>体检</span></label>
                        <br/>
						<label><input type="checkbox" id="option5" name="ask43" value="购买健康保险"><span>购买健康保险</span></label>
                        <br/>
						<label><input type="checkbox" id="option6" name="ask43" value="0" onchange="displayDiv2('option6','div_ask44')"><span>其他，请注明</span></label>
                        <br/>
				</div>
            </div>

            <div class="row" style="display:none;"id="div_ask44">
				    <label class="row-left">消费方向</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" name="ask44" id="ask44" value="" placeholder="请输入您的消费方向"
							data-rule-field="消费方向" data-rule="maxlenth:50" />
					</div>
			</div>


        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您服用保健品吗？ </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask7" name="ask7" style="padding-right:24px;" onchange="displayDiv(this.value,'div_ask7_other')">
						<option value="否" selected>否</option>
						<option value="服用钙、铁、锌等矿物质类" >服用钙、铁、锌等矿物质类</option>
						<option value="维生素类蛋白质类" >维生素类蛋白质类</option>
						<option value="0" >其它</option>
					</select>
				</div>
			</div>
            <div class="row" style="display:none;"id="div_ask7_other">
				    <label class="row-left">保健品名称</label>
    				<div class="input-box margin8 nolrmargin">
	    				<input type="text" name="ask7_other" id="ask7_other" value="" placeholder="经常服用的保健品名"
							data-rule-field="保健品名称" data-rule="maxlenth:50" />
					</div>
			</div>
		</div>
		<div class="group" >
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">粮谷类（主食馒头、米饭、包子、饼、面条、窝头等）干重摄入量： </label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask8" name="ask8" style="padding-right:24px;">
						<option value="1-2两" selected>1-2两</option>
						<option value="3-5两" >3-5两</option>
						<option value="6-7两" >6-7两</option>
						<option value="7-8两" >7-8两</option>
						<option value="9两及以上" >9两及以上</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">其中粗杂粮（大米白面之外的粮谷类包括绿豆等杂豆类）摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask9" name="ask9" style="padding-right:24px;">
						<option value="很少吃" selected>很少吃</option>
						<option value="1两-2两" >1两-2两</option>
						<option value="3两-4两" >3两-4两</option>
						<option value="5两及以上" >5两及以上</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">薯类摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask10" name="ask10" style="padding-right:24px;">
						<option value="很少吃" selected>很少吃</option>
						<option value="1两-2两" >1两-2两</option>
						<option value="3两-4两" >3两-4两</option>
						<option value="5两及以上" >5两及以上</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">蔬菜摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask11" name="ask11" style="padding-right:24px;">
						<option value="小于2两/每天" selected>小于2两/每天</option>
						<option value="3-5两/天" >3-5两/天</option>
						<option value="6-9两/天" >6-9两/天</option>
						<option value="大于10两" >大于10两</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">水果摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask12" name="ask12" style="padding-right:24px;">
						<option value="小于1两/每天" selected>小于1两/每天</option>
						<option value="2-4两/天" >2-4两/天</option>
						<option value="5-7两/天" >5-7两/天</option>
						<option value="大于8两" >大于8两</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">畜禽肉、鱼虾贝类摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask13" name="ask13" style="padding-right:24px;">
						<option value="很少吃" selected>很少吃</option>
						<option value="2-3两/天" >2-3两/天</option>
						<option value="4-4.9两/天" >4-4.9两/天</option>
						<option value="大于5两/天" >大于5两/天</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">蛋类摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask14" name="ask14" style="padding-right:24px;">
						<option value="很少吃" selected>很少吃</option>
						<option value="1-2个/周" >1-2个/周</option>
						<option value="3-6个/周" >3-6个/周</option>
						<option value="大于1个 /每天" >大于1个 /每天</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">豆腐等豆制品摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask15" name="ask15" style="padding-right:24px;">
						<option value="很少吃" selected>很少吃</option>
						<option value="1-2两/天" >1-2两/天</option>
						<option value="3-4两/天" >3-4两/天</option>
						<option value="5-6两/天" >5-6两/天</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">奶和奶制品摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask16" name="ask16" style="padding-right:24px;">
						<option value="不喝" selected>不喝</option>
						<option value="一天250 mL" >一天250 mL左右</option>
						<option value="一周251-500mL" >一周251-500mL左右</option>
						<option value="一天大于501mL" >一天大于501mL</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">坚果摄入量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask17" name="ask17" style="padding-right:24px;">
						<option value="很少吃" selected>很少吃</option>
						<option value="一天大于1两" >一天大于1两</option>
						<option value="一天0.8-0.9两" >一天0.8-0.9两</option>
						<option value="一天0.5-0.7两" >一天0.5-0.7两</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">饮水量： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask18" name="ask18" style="padding-right:24px;">
						<option value="一天小于1000 mL" selected>一天小于1000 mL</option>
						<option value="一天在1100-1400 mL" >一天在1100-1400 mL</option>
						<option value="一天在1500-1700mL" >一天在1500-1700mL</option>
						<option value="一天大于1800mL" >一天大于1800mL</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">每天吃几餐： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask19" name="ask19" style="padding-right:24px;">
						<option value="3餐" selected>3餐</option>
						<option value="2餐" >2餐</option>
						<option value="1餐" >1餐</option>
						<option value="节食减肥" >节食减肥</option>
						<option value="4餐" >4餐</option>
						<option value="5餐及以上" >5餐及以上</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您每天或每周食用食物的种类： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask20" name="ask20" style="padding-right:24px;">
						<option value="每天小于12种，每周小于25种" selected>每天小于12种，每周小于25种</option>
						<option value="每天小于12种，每周25种左右" >每天小于12种，每周25种左右</option>
						<option value="每天12种左右，每周小于25种" >每天12种左右，每周小于25种</option>
						<option value="每天大于12种，每周大于25种" >每天大于12种，每周大于25种</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">你的饮食口味（咸）： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask21" name="ask21" style="padding-right:24px;">
						<option value="清淡" selected>清淡</option>
						<option value="一般" >一般</option>
						<option value="偏咸" >偏咸</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">你的饮食口味（油）： </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask22" name="ask22" style="padding-right:24px;">
						<option value="清淡" selected>清淡</option>
						<option value="正常" >正常</option>
						<option value="油腻" >油腻</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您近半年是否有被动吸烟（二手烟）的情况？ </label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask23" name="ask23" style="padding-right:24px;">
						<option value="无" selected>无</option>
						<option value="有，但吸入的较少" >有，但吸入的较少</option>
						<option value="有大量吸入" >有大量吸入</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您是否吸烟？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask24" name="ask24" style="padding-right:24px;">
						<option value="否" selected>否</option>
						<option value="偶尔（一周一盒）" >偶尔，一周一盒</option>
						<option value="经常（一天一盒）" >经常，一天一盒</option>
						<option value="一天大于一盒" >一天大于一盒</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您是否喝酒？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask25" name="ask25" style="padding-right:24px;">
						<option value="否" selected>否</option>
						<option value="偶尔（一周3次左右）" >偶尔，一周3次左右</option>
						<option value="经常（几乎每天都喝）" >经常，几乎每天都喝</option>
						<option value="每天都喝，酒局不断" >每天都喝，酒局不断</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您每周参加几次体育锻炼？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask26" name="ask26" style="padding-right:24px;">
						<option value="小于1次" selected>小于1次</option>
						<option value="1~2次" >1~2次</option>
						<option value="3~4次" >3~4次</option>
						<option value="5~7次" >5~7次</option>
						<option value="大于8次" >大于8次</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您平均每次体育锻炼的时间？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask27" name="ask27" style="padding-right:24px;">
						<option value="小于15分钟" selected>小于15分钟</option>
						<option value="15min~0.5h" >15min~0.5h</option>
						<option value="0.5h~1h" >0.5h~1h</option>
						<option value="1h~1.5h" >1h~1.5h</option>
						<option value="大于1.5h" >大于1.5h</option>
					</select>
				</div>
			</div>
        	<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">您的体育锻炼形式？</label>
			    </div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" >
					<select class="rl" id="ask28" name="ask28" style="padding-right:24px;">
						<option value="慢跑或快走" selected>慢跑或快走</option>
						<option value="瑜伽" >瑜伽</option>
						<option value="骑自行车、跳舞或打网球" >骑自行车、跳舞或打网球</option>
						<option value="篮球或足球" >篮球或足球</option>
						<option value="健身房器械锻炼" >健身房器械锻炼</option>
						<option value="不参加锻炼" >不参加锻炼</option>
						<option value="其他" >其他</option>
					</select>
				</div>
			</div>
		</div>
		<div class="group" >
        	<div class="row">
				<label class="row-left" style="width:100%;color:#333333;">您在生活、工作中是否出现以下几种情况：</label>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">肩颈臂疼痛：疼痛不固定，会转移</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<label><input type="radio" id="option1" name="ask33"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask33" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">胃疼恶心：从没有胃病却胃疼</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<label><input type="radio" id="option1" name="ask34"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask34" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">焦虑失眠：无症状惊醒</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right"style="margin-left:20px;" >
					<label><input type="radio" id="option1" name="ask35"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask35" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">心跳加剧：不规则且无缘由</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<label><input type="radio" id="option1" name="ask36"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask36" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">过度出汗：长期反复</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right"style="margin-left:20px;" >
					<label><input type="radio" id="option1" name="ask37"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask37" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">呼吸短促：头晕，很难深呼吸</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<label><input type="radio" id="option1" name="ask38"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask38" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">极度疲劳：没做什么就特别累，哈欠不断</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right"style="margin-left:20px;" >
					<label><input type="radio" id="option1" name="ask39"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask39" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">四肢尤其双手突然冰冷</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<label><input type="radio" id="option1" name="ask40"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask40" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">血压突然升高和降低，且有鼻出血</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<label><input type="radio" id="option1" name="ask41"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask41" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">走路步态异常，腿部无力</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<label><input type="radio" id="option1" name="ask42"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask42" checked value="否"><span>否</span></label>
				</div>
			</div>
			<div class="row" style="border-bottom:none;">
				<label class="row-left" style="width:100%;color:#333333;">突然剧烈头痛</label>
			</div>
			<div class="row">
				<div class="input-box lrpadding0 row-right" style="margin-left:20px;">
					<label><input type="radio" id="option1" name="ask43"  value="是"><span>是</span></label>&nbsp;&nbsp;
					<label><input type="radio" id="option1" name="ask43" checked value="否"><span>否</span></label>
				</div>
			</div>
		</div>
		<button class="button submit block" type="submit">提交</button>


		</form>

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

