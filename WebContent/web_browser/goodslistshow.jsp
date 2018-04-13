<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.atongmu.bean.Tbl_commodity"
	import="com.atongmu.bean.Tbl_commodity_category"
	import="java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no">
<title>商品一览</title>
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<script src="web_browser/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
$(window).load(function(){
	var goodsclassified = $("#hiddengoodsclassified").val();
	if(goodsclassified !="null"){
		$("#classified").val(goodsclassified);
	}
});

</script>
</head>

<body style="padding-left:2%">
		<div class="titlebar">
			<h3>商品一览</h3>
		</div>
		<div class="line"></div>
			<form id="goodslistform" action="ShowGoodsListServlet" method="post" class="form form-horizontal responsive" style="margin-top:15px">
				<%
				Object objectgoodsname = request.getAttribute("initgoodsname");
				String goodsname =  (String)objectgoodsname;
                         Object objectgoodsclassified =request.getAttribute("initgoodsclassified");
                         String goodsclassified = (String)objectgoodsclassified;
				%>
					<label for="goodsname" id="goodsname">商品名：<input type="text" name="goodsname" class="input-text" style="width:200px;"></label>
					<input type="hidden" name="hiddengoodsclassified" id="hiddengoodsclassified" value="<%=goodsclassified%>">
					<label for="status"  style="margin-left:10px;">商品分类：
					<span class="select-box" style="width:210px;">
						<select id="classified" name="classified" class="select"  style="width:200px;">
							<option value="" selected="selected">请选择商品分类----</option>
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
					</label> <input type="submit" name="search" value="搜索" class="btn btn-primary radius" style="margin-left:10px;">
			</form>
		<table class="table table-border table-bg table-bordered table-hover" style="width:100%;margin-top:15px;">
			<tr>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">ID</th>
				<th width="15%" class="text-c" style="background-color:#DFF0D8;">图片</th>
				<th width="15%" class="text-c" style="background-color:#DFF0D8;">商品名称</th>
				<th width="10%" class="text-c" style="background-color:#DFF0D8;">分类</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">状态</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">库存</th>
				<th width="5%" class="text-c" style="background-color:#DFF0D8;">销量</th>
				<th width="10%" class="text-c" style="background-color:#DFF0D8;">价格</th>
				<th width="10%"	class="text-c"  style="background-color:#DFF0D8;">编辑操作</th>
				<th width="10%" class="text-c"  style="background-color:#DFF0D8;">删除操作</th>
			</tr>

				<%
				Object objectcomoditieslist = request.getAttribute("comoditieslist");
				List<List<Object>> commoditieslist = (List<List<Object>>)objectcomoditieslist;
				for(int i=0;i<commoditieslist.size();i++){
					List<Object> listobject= commoditieslist.get(i);
					Tbl_commodity beancommodities = (Tbl_commodity)listobject.get(0);
					Tbl_commodity_category beancategories = (Tbl_commodity_category)listobject.get(1);
                    int goodsid = beancommodities.getGoods_id();
                    String classified = beancategories.getCategory_name();
                    String status = beancommodities.getGoods_status();
                    int stock = beancommodities.getGoods_stock();
                    int volume = beancommodities.getGoods_sales_volume();
                    double price = beancommodities.getGoods_price();
                    String goodname = beancommodities.getGoods_name();
				%>
				<tr>
					<td class="text-c"><%=goodsid %></td>
					<td class="text-c"><img
							height="150" width="180" class="img-responsive"
							src="http://www.in-artoon.com/goods_images/<%=goodsid %>_1.jpg"></td>
					<td class="text-c"><%=goodname %></td>
					<td class="text-c"><%=classified %></td>
					<td class="text-c"><%=status %></td>
					<td class="text-c"><%=stock %></td>
					<td class="text-c"><%=volume %></td>
					<td class="text-c"><%=price %></td>
					<td class="text-c"><input type="submit" name="update" id="update" value="修改商品"  class="btn btn-primary radius"
						onclick="javascript:window.location.href='ShowGoodsInfoServlet?goodsId=<%=goodsid %>'"></td>
					<td class="text-c"><input type="button" name="delete" id="delete"  class="btn btn-danger radius"
						value="删除商品" onclick="javascript:window.location.href=('GoodsDeleteServlet?goods_id=<%= goodsid%>')"></td>
				</tr>
				<%
				}
            %>
			</table>
</body>
</html>