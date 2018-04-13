<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.atongmu.bean.Tbl_commodity_category"
	import="java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<meta name="format-detection" content="telephone=no">
<title>商品分类管理</title>
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="web_browser/static/h-ui.admin/css/H-ui.admin.css" />
<script src="web_browser/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
function changeclassifiedgoods(type,id){
	if(type=='1'){
		if(confirm("确定要删除该分类吗？")){
			$("input[name='hiddenflag']").val("delete");
			window.location.href="GetGoodsClassifiedListServlet?hiddenid="+id+"&hiddenflag=delete";
		}

	}else if(type=='2'){
		$("input[name='hiddenflag']").val("update");
		var category = "categoryname"+id;
		var categoryname = document.getElementById(category).value;
		window.location.href="GetGoodsClassifiedListServlet?hiddenid="+id+"&hiddenflag=update&categoryname="+categoryname;
	}
}
function addedclassifiedgoods(){
	 $("#hiddenflagadd").val("added");
	 document.getElementById("addform").submit();
}

$(window).load(function(){
	var uflag = $("#updateflag").val();
	var dflag = $("#deleteflag").val();
	if(uflag=="update"){
		alert("更新完成");
	}
	if(dflag=="delete"){
		alert("删除完成");
	}
});
</script>

</head>

<body style="padding-left:2%">
		<div class="titlebar">
			<h3>商品分类管理</h3>
		</div>
		<div class="line"></div>
	<div class="body" style="margin-top:15px">
			<table class="table table-border table-bg table-bordered table-hover" style="width:600px;">
				<tr>
					<th width="20%" class="text-c" style="background-color:#DFF0D8;">分类名称</th>
					<th width="5%" class="text-c"  style="background-color:#DFF0D8;">更新操作</th>
					<th width="5%"	class="text-c"  style="background-color:#DFF0D8;">删除操作</th>
				</tr>
		<%
			    Object objectlist = request.getAttribute("goodsclassifiedlist");
			    List<Tbl_commodity_category> categorieslist = (List<Tbl_commodity_category>)objectlist;
			    for(int i=0;i<categorieslist.size();i++){
			    	int categoryid = categorieslist.get(i).getCategory_id();
			    	String categoryname = categorieslist.get(i).getCategory_name();
			    %>
				<tr>
					<td width="20%"class="text-c">
					    <input type="text" id="categoryname<%=categoryid%>" name="categoryname<%=categoryid%>" class="input-text" value="<%=categoryname%>">
						<input type="hidden" id="hiddenid" name="hiddenid"
						value="<%=categoryid%>">
						<input type="hidden" id="hiddenflag1" name="hiddenflag" value=""> <%
                 Object objuflag = request.getAttribute("updateflag");
                 Object objdflag = request.getAttribute("deleteflag");
                 String uflag = (String)objuflag;
                 String dflag = (String)objdflag;
                %> <input type="hidden" id="updateflag"
						name="updateflag" value="<%=uflag%>"> <input type="hidden"
						id="deleteflag" name="deleteflag" value="<%=dflag%>"></td>
					<td width="5%"class="text-c"><input type="submit" name="update" id="update" class="btn btn-primary radius"
						value="更新分类" onclick="changeclassifiedgoods(2,<%=categoryid%>);"></td>
					<td width="5%"class="text-c"><input type="submit" name="delete" id="delete" class="btn btn-danger radius"
						value="删除分类" onclick="changeclassifiedgoods(1,<%=categoryid%>)"></td>

				</tr>
		<%
			      }
              %>
			</table>
		<p/>
		<div class="titlebar">
			<h3>新增产品分类 </h3>
		</div>
		<form action="GetGoodsClassifiedListServlet" id="addform"
			method="post">
			<input type="hidden" id="hiddenflagadd" name="hiddenflag" value="" >
			<input id="newadded" name="newadded" type="text" class="input-text" style="width:200px;"> <input
				type="button" name="insert" id="insert" value="添加分类" class="btn btn-primary radius"
				onclick="addedclassifiedgoods()">
		</form>
	</div>
</body>
</html>