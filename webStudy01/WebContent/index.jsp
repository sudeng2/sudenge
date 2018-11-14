<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
      String mem_id = (String)session.getAttribute("authMember");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>
</head>
<body>
<h4>웰컴 페이지</h4>
<pre>
	처음부터 웰컴페이지로 접속하거나,
	로그인에 성공해서 웰컴 페이지로 접속하는 경우의 수가 있음.
	aaa님 로그인상태, <a>로그아웃</a>
	
	로그인하러가기
	 <%
      if(StringUtils.isNotBlank(mem_id)){
   		 out.print(session.getAttribute("authMember") + "님 로그인 상태");
   %>	
         
   <%
      }else{
         
   %>
<%--     	  <a href = "<%=request.getContextPath()%>/login/loginForm.jsp">로그인하러 가기</a> --%>
         
   <%
      }
   %>   
         <a href = "<%=request.getContextPath()%>/login/logout.jsp">로그아웃하러 가기</a>

</pre>
</body>
</html>