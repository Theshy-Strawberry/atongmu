<%@page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.atongmu.bean.Tbl_commodity"
	import="com.atongmu.bean.Tbl_commodity_category"
	import="com.atongmu.daoImpl.GoodsDaoImpl"
	import="java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="web_browser/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="web_browser/lib/icheck/icheck.css" />

<script src="js/jquery-2.1.3.min.js"></script>
<title>修改商品</title>
</head>
<body style="padding-left:2%">
<% 
Object obj = request.getAttribute("goodInfo");
Tbl_commodity tbl_commodity = (Tbl_commodity)obj;
Object obj2 = request.getAttribute("pathList");
List pathList = (List)obj2;

								%>	
<input type="hidden" id="select" name="select" value="<%=tbl_commodity.getGoods_category_id() %>"/> 
	<div class="titlebar">
		<h3>商品信息</h3>
	</div>
	<div class="line"></div>
	<form action="../ReleaseGoodsServlet" method="post" id="goodsform" enctype="multipart/form-data" class="form form-horizontal responsive" style="margin-top:15px">
		<div class="row cl">
			<label class="form-label col-xs-2">商品名称：</label>
			<div class="formControls col-xs-5">
				<input type="text" class="input-text" placeholder="1~20个字符，字母/中文/数字/下划线" name="goodsName" id="goodsName" datatype="*1-20" nullmsg="商品名称不能为空"   style="width:320px;" value="<%=tbl_commodity.getGoods_name() %>" >
			</div>
			<div class="col-xs-5"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-2">商品分类： </label>
			<div class="formControls col-xs-5">
			<span class="select-box" style="width:210px;">
				<select id="goodsCategory" name="goodsCategory" class="select"  style="width:200px;"  datatype="*" nullmsg="请选择商品分类">
				
					<option value="" selected>请选择商品分类----</option>
					<%

					Object objectcommodity_categorieslist = request.getAttribute("commodity_categories");
					List<Tbl_commodity_category> commodity_categorieslist = (List<Tbl_commodity_category>)objectcommodity_categorieslist;
					for(Tbl_commodity_category category:commodity_categorieslist){
						int code = category.getCategory_id();
						String value = category.getCategory_name();
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
			<label class="form-label col-xs-2">商品价格：</label>
			<div class="formControls col-xs-5">
				<input type="text" value="<%=tbl_commodity.getGoods_price() %>" class="input-text" placeholder="商品价格只可以填入数字" name="goodsPrice" id="goodsPrice" datatype="n1-8" nullmsg="商品价格不能为空"  style="width:320px;" >
			</div>
			<div class="col-xs-5"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-2">商品库存：</label>
			<div class="formControls col-xs-5">
				<input type="text" value="<%=tbl_commodity.getGoods_stock() %>" class="input-text" placeholder="商品库存只可以填入数字" name="goodsStock" id="goodsStock" datatype="n1-8" nullmsg="商品库存不能为空"  style="width:320px;" >
			</div>
			<div class="col-xs-5"> </div>
		</div>
		<input type="hidden" name="goodsDiscount" value="0">

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品规格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea rows="4" cols="50" name="goodSpec"><%=tbl_commodity.getGoods_spec() %></textarea>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品简述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea rows="4" cols="50" name="goodsDescribe"><%=tbl_commodity.getGoods_describe() %></textarea>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品图片1：</label>
			<div class="formControls col-xs-8 col-sm-9"><img  alt="图片未上传"  src="http://www.in-artoon.com/goods_images/<%=tbl_commodity.getGoods_id()%>_1.jpg" height="150" width="180" > 
				<span class="btn-upload form-group">
				
				<input class="input-text upload-url" type="text" name="uploadfile-2" id="uploadfile-2" readonly  datatype="*" nullmsg="请添加附件！" style="width:200px" name="goodsimage1"
			accept="image/gif, image/jpeg">
			
				<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 更换图片</a>
				<input type="file" multiple name="file-2" class="input-file">
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品图片2：</label>
			<div class="formControls col-xs-8 col-sm-9"><img alt="图片未上传" src="http://www.in-artoon.com/goods_images/<%=tbl_commodity.getGoods_id()%>_2.jpg" height="150" width="180" > 
				<span class="btn-upload form-group">
				<input class="input-text upload-url" type="text" name="uploadfile-2" id="uploadfile-2" readonly  datatype="*" nullmsg="请添加附件！" style="width:200px" name="goodsimage2"
			accept="image/gif, image/jpeg">
				<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 更换图片</a>
				<input type="file" multiple name="file-2" class="input-file" name="goodsimage1"	accept="image/gif, image/jpeg">
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品图片3：</label>
			<div class="formControls col-xs-8 col-sm-9"><img alt="图片未上传" src="http://www.in-artoon.com/goods_images/<%=tbl_commodity.getGoods_id()%>_3.jpg" height="150" width="180" > 
				<span class="btn-upload form-group">
				<input class="input-text upload-url" type="text" name="uploadfile-2" id="uploadfile-2" readonly  datatype="*" nullmsg="请添加附件！" style="width:200px" name="goodsimage3"
			accept="image/gif, image/jpeg">
				<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 更换图片</a>
				<input type="file" multiple name="file-2" class="input-file">
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品图片4：</label>
			<div class="formControls col-xs-8 col-sm-9"><img alt="图片未上传" src="http://www.in-artoon.com/goods_images/<%=tbl_commodity.getGoods_id()%>_4.jpg" height="150" width="180" > 
				<span class="btn-upload form-group">
				<input class="input-text upload-url" type="text" name="uploadfile-2" id="uploadfile-2" readonly  datatype="*" nullmsg="请添加附件！" style="width:200px" name="goodsimage4"
			accept="image/gif, image/jpeg">
				<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 更换图片</a>
				<input type="file" multiple name="file-2" class="input-file">
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">商品图片5：</label>
			<div class="formControls col-xs-8 col-sm-9"><img alt="图片未上传" src="http://www.in-artoon.com/goods_images/<%=tbl_commodity.getGoods_id()%>_5.jpg" height="150" width="180" > 
				<span class="btn-upload form-group">
				<input class="input-text upload-url" type="text" name="uploadfile-2" id="uploadfile-2" readonly  datatype="*" nullmsg="请添加附件！" style="width:200px" name="goodsimage5"
			accept="image/gif, image/jpeg">
				<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 更换图片</a>
				<input type="file" multiple name="file-2" class="input-file">
				</span>
			</div>
		</div>
	   <div class="row cl" style="margin-left:300px;margin-top:20px;">
	   	<input type="submit" value="修改" name="release" class="btn btn-primary radius"/>
       </div>
	</form>
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
	$("#goodsform").Validform({
		tiptype:2
	});
  	var select=$("#select").val();
	$("#goodsCategory option[value='"+select+"']").attr("selected", true);
});
</script>
</body>
</html>