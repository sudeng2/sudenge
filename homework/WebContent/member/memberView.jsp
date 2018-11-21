<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.ddit.member.service.IMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String who = request.getParameter("who");
	System.out.println(who);
	IMemberService service = new MemberServiceImpl();
	MemberVO member	= service.retrieveMember(who);
	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>아이디:</th>
		<td><%=member.getMem_id() %></td>
	</tr>
	<tr>
		<th>비밀번호:</th>
		<td><%=member.getMem_pass() %></td>
	</tr>
	<tr>
		<th>회원명:</th>
		<td><%=member.getMem_name() %></td>
	</tr>
	<tr>
		<th>우편번호:</th>
		<td><%=member.getMem_zip() %></td>
	</tr>
	<tr>
		<th>주소:</th>
		<td><%=member.getAddress() %></td>
	</tr>
	<tr>
		<th>이메일:</th>
		<td><%=member.getMem_mail() %></td>
	</tr>
</table>
</body>
</html>