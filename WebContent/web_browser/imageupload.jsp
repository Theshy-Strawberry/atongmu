<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
	pageEncoding="UTF-8"%>
	<%@ include file="roleCheck.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function checkFileType(inputFile) { //file onchange时，验证文件的格式是否为正确格式
		var patrn = new RegExp("([.]jpg)|([.]JPG)|([.]gif)|([.]GIF)$");//限制文件类型
		if (!patrn.test(inputFile.value)) {
			inputFile.outerHTML = inputFile.outerHTML;//清空input file
			alert("请选择JPG或GIF图片！");
		}
	}
	function uploadPic() {
		var imgSrcValue = document.getElementByIdx_x("image").value;
		if (imgSrcValue == "") {
			alert("请选择文件");
		} else {
			form1.submit();
		}
	}
</script>
<body>
	<form method="post" action="../atongmu/PicUploadServlet" onsubmit="" >
		<input type="file" name="image" id="image" onchange="checkFileType(this);"/><br />
		<br /> <input type="submit" onclick="uploadPic()"/>
	</form>
	<div id="imgDiv">
		<font color="blue">图片预览</font>
		<table border="1" bordercolor="red">
		<%
		Object obj = request.getAttribute("image_id_list");
		if(obj != null){
			List<String> image_id_list = (List<String>)obj;
			for(int i = 0;i<image_id_list.size();i++){

		%>
			<tr>
				<td><img id="imgSrc" width="100px" height="100px"
				src="../atongmu/PictureShowServlet?id=<%=image_id_list.get(i)%>"
				/></td>
			</tr>
	    <%
			}
		} %>
		</table>
	</div>
</body>
</html>