<%@page import="kr.or.ddit.utils.CookieUtil"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String failedId = request.getParameter("mem_id");
	String message = (String)session.getAttribute("message");
	String id = new CookieUtil(request).getCookieValue("login");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
<script type = "text/javascript">
	<%
		if(StringUtils.isNotBlank(message)){
	%>
		alert("<%=message%>");
	<%
			session.removeAttribute("message");
		}
	%>
</script>
<script src = "<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<form action = "<%=request.getContextPath() %>/login/loginCheck.jsp" method = "post">
	<ul>
		<li>
			아이디:<input type = "text" name = "mem_id" value = "<%=Objects.toString(id,"") %>"/>
			<label>
			<input type = "checkbox" name="idChecked" value = "idSaved" <%=StringUtils.isNotBlank(id)?"CHECKED":"" %>/>아이디 기억하기
			</label>
		</li>
		<li>
			비밀번호 : <input type = "password" name = "mem_pass"/>
			<input type = "submit" value="로그인"/>
		</li>
	</ul>
</form>
</body>
</html>