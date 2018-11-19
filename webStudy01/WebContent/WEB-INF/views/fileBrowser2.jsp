<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
	    var fileForm = document.fileForm;
	    //li들을 더블클릭하면 경로와 이름이 온다.
		$("#fileList>li").on("dblclick", function() {
			alert($(this).text());
			fileForm.path.value=$(this).attr('value');
			fileForm.name.value=$(this).text();
			fileForm.submit();

		});
	})
	function name() {
		
	}
</script>
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="file" class="java.util.ArrayList" scope="request" />
	<ul id="fileList">
		<%
		
			String pattern = "<li value=%s >%s</li>";
			for (Object name : file) {//file의 갯수만큼 for문을 돌려서 name에 담아준다. li를 만듬.
				File ss = (File) name;//name이 Object인데 File로 다운캐스팅을 해준다.
				out.println(String.format(pattern, ss.getAbsolutePath(), ss.getName()));
				//value=%s에는 경로를 넣어주고, 그뒤 %s에는 폴더명이다.
			}
		%>
	</ul>
	<form method="get" name="fileForm" >
	<input name="path" value="" type="hidden" />
	<input name="name" value="" type="hidden" />
	<input type = "radio"  name="radio" value = "delete"/> 삭제
	<input type = "radio" name = "radio" value = "copy"/> 복사
	<input type = "radio" name = "radio" value = "move"/> 이동
	</form>
</body>
</html>