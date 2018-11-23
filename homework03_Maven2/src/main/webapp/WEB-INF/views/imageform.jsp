<%@page contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
<script type = "text/javascript">
	$(function(){
		var imgArea = $("#imgArea");
		var pattern = '<img src="imageService?image=%v" />';
		$("[name = 'image']").on("change", function(){
			var filename = $(this).val();
			imgArea.append(pattern.replace("%v", filename));
		});
	});

</script>
	
</head>
<body>
	<form name = "imgForm" action = "imageService" method="get">
		<select name = "image">
<!-- 			@imageform -->
				<%=request.getAttribute("optionsAttr") %>
		</select>
	</form>
	<div id = "imgArea">
		<%=request.getAttribute("imgTags") %>
		
	</div>
	<script type = "text/javascript">
		/* 	var imgArea = document.getElementById("imgArea");
		function changeHandler(event){
			//document.imgForm.submit();
			var filename = event.target.value;
			var pattern = '<img src="imageService?image=%v " />';
			imgArea.innerHTML = pattern.replace("%v",filename);
		} */
		
	</script>
</body>
</html>