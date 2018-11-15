<%@page import="kr.or.ddit.web.modulize.ServiceType"%>
<%@page import="kr.or.ddit.web.url.URL"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
      String mem_id = (String)session.getAttribute("authMember");
	  String  urlVal = request.getParameter("command");
	  String urlAddress = "";
	  
	  int statusCode = 0;
	   if(StringUtils.isNotBlank(urlVal)){
		   try{
		    URL url = URL.getUrlType(urlVal);
		    urlAddress = url.getUrlAddress();
		   		//ServiceType sType = ServiceType.valueOf(urlVal.toUpperCase());
		   }catch(IllegalArgumentException e){
			   statusCode = HttpServletResponse.SC_NOT_FOUND;
		   }
	   }
	   if(statusCode!=0){
		   response.sendError(statusCode);
		   return;
	   }
   
   
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>
<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet">
<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
</head>
<body>
	<div id = "top">
<jsp:include page = "/includee/header.jsp"/>
<%-- 	<iframe src = "<%=request.getContextPath() %>/includee/header.jsp"></iframe> --%>
	</div>
	<div id = "left">
<jsp:include page = "/includee/left.jsp"/>
<%-- 		<iframe src = "<%=request.getContextPath() %>/includee/left.jsp"></iframe> --%>
	</div>
	<div id = "body" style = "overflow-y:auto">
		<%
			if(StringUtils.isNotBlank(urlAddress)){
		%>
			<jsp:include page="<%=urlAddress %>"></jsp:include>
		<%
			}else{
		%>
		<h4>웰컴 페이지</h4>
<pre>
	처음부터 웰컴페이지로 접속하거나,
	로그인에 성공해서 웰컴 페이지로 접속하는 경우의 수가 있음.
	aaa님 로그인상태, <a>로그아웃</a>
	
	로그인하러가기
   <%
			if(StringUtils.isNotBlank(mem_id)){
		%>
			<%=mem_id%>님 로그인 상태
			<a href="<%=request.getContextPath()%>/login/logout.jsp">로그아웃</a>	
<%-- 			<jsp:include page = "/login/logout.jsp"/> --%>
		<%				
			}else{
				
		%>
 			<a href="<%=request.getContextPath()%>/login/loginForm.jsp">로그인하러 가기</a>
<%-- 			<jsp:include page = "/login/loginForm.jsp"/> --%>
			
		<%
			}
		%>      

</pre>
		<%
			}
		
		%>	
<!-- 	parameter 있을때, 없을때 , 동적제공, 동적제공할수없는 경우 -->
	
	

</div>
	<div id = "footer">
 		
 			<jsp:include page = "/includee/footer.jsp"/>
 		 
<%-- 	<iframe src = "<%=request.getContextPath() %>/includee/footer.jsp"></iframe> --%>
	</div>
</body>
</html>