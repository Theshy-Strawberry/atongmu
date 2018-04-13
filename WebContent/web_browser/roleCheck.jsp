<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Object objUser = (Object)session.getAttribute("loginuser");
if(objUser == null){
	request.setAttribute("loginFail", "2");
	request.getRequestDispatcher("managerlogin.jsp").forward(request,response);
}
%>

