<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	File[] files = (File[])request.getAttribute("files");
%>
<html>
<head>
<meta charset="UTF-8">
<title>WEB-INF/views/model2SampleView.jsp</title>
<script>
	function moveFile(file){
		var form = document.fileForm;
		form.fileAddress.value = fileAddr;
		form.submit();
		
	}
</script>
</head>
<body>
	<form name = "fileForm" action="./">
	<% 
		for(File tmp : files){
			out.println(String.format("<li><a href = 'moveFile(%s)'>%s</li>",tmp.getAbsolutePath(),tmp.getName()));
		}
	%>
	<input value = "" name="fileAddress" type = "hidden"/>
	</form>
<pre>
	모델 2 구조를 이용하고 있고,
	컨트롤러를 거친 이후에 응답이 생성되는 영역 - view layer,(M,v,c)
	컨트롤러가 전달해준 모델 : <%=request.getAttribute("model") %>
</pre>
</body>
</html>

