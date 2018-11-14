<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/resourceIdentify.jsp</title>
</head>
<body>
<h4>자원의 식별</h4>
<pre>
1.파일 시스템 자원 : 파일 시스템 경로를 통한 식별
2.클래스패스(classpath) 자원 : classpath 기준 경로를 통한 식별
3.웹 리소스 식별
	URI(Uniform Resource Identifier)
		URL(Uniform Resource Locator)
		URN(Uniform Resource Name)
		URC(Uniform Resource Content) 
	URL
	scheme://domain:port/context/depth1/depth2...../resource_name
	
	절대경로 : http://localhost:80/webStudy01/images/Jellyfish.jpg
		client-side : /webstudy01/images/Jellyfish.jpg ->webstudy01은 컨텍스트 path 컨텍스트 path부터 나와야함, 자원을 식별하고싶으면 이거
		server-side : /desc (contextPath를 제외한 이후 경로 표기)자바나 jsp에서 자원을 식별하고싶으면
	상대경로 : 경로를 판단할 기준 위치 : 현재 브라우저의 주소값.
		../images/Koala.jpg
</pre>
	<img src = "<%=request.getContextPath()%>/images/Jellyfish.jpg"/>
	<img src = "http://localhost:80/webStudy01/images/Jellyfish.jpg"/>
	<img src = "../images/Jellyfish.jpg"/>
</body>
</html>