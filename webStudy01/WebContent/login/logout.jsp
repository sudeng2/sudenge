<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	session.removeAttribute("authMember");
	session.invalidate();//유효하지않게 만들어버림 unbinds상태로 만든다 binding되어있는 객체를.(세션만료)
	//이동(index.jsp, redirect)
	response.sendRedirect(request.getContextPath()+"/");
%>
