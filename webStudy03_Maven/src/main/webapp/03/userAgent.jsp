<%@page import="kr.or.ddit.web.useragent.SystemType"%>
<%@page import="kr.or.ddit.web.useragent.BrowserType"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/userAgent.jsp</title>
</head>
<body>
<%
	String userAgent = request.getHeader("User-Agent");
	BrowserType browser = BrowserType.getBrowserType(userAgent);
	String browserMsg = "당신의 브라우저는 %s 입니다.";
	String name = browser.getBrowserName();
	String systemMsg = "당신의 시스템은 %s 입니다.";
	SystemType system = SystemType.getSystemType(userAgent);
	String systemName = system.getSystemName();
%>
<!-- 클라이언트 시스템과 브라우저에 대한 정보를 확인. -->
<!-- 클라이언트의 시스템이 -->
<!-- 데스크탑이라면, "당신의 시스템은 데스크탑 입니다." -->
<!-- 브라우저가 chrome이라면 " 당신의 브라우저는 크롬 입니다." -->
<!-- 브라우저가 firefox이라면 "당신의 브라우저는 파이어폭스 입니다." -->

<!-- 와 같은 형태의 메시지를 출력. -->
<div id = "msgArea">
	<%=String.format(browserMsg, name) %><br>
	<%=String.format(systemMsg, systemName) %><br>
	
<%-- <%
	String result = null;
	Enumeration<String> names = request.getHeaderNames();
	while(names.hasMoreElements()){
		String headerName = names.nextElement();
		String headerValue = request.getHeader(headerName);
		if(headerValue.contains("Chrome")&&!headerValue.contains("Android")){
			result = ("크롬입니다");
		}else if(headerValue.contains("Windows")){
			result=("윈도우 입니다");
		}else if(headerValue.contains("Android")){
			result=("모바일입니다.");
		}
}
%> --%>



</div>
</body>
</html>