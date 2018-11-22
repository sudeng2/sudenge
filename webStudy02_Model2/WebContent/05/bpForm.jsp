<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public Map<String, String[]> singerMap = new LinkedHashMap<>();
{
	singerMap.put("BP001",new String[]{"jennie","/WEB-INF/bp/jennie.jsp"});
	singerMap.put("BP002",new String[]{"jisoo","/WEB-INF/bp/jisoo.jsp"});
	singerMap.put("BP003",new String[]{"lisa","/WEB-INF//bp/lisa.jsp"});
	singerMap.put("BP004",new String[]{"rose","/WEB-INF//bp/rose.jsp"});
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/bpForm.jsp</title>
<script type = "text/javascript">
	function singerChangeHandler(){
		document.singerForm.submit();
	}
</script>
</head>
<body>
<form name = "singerForm" action = "<%=request.getContextPath() %>/05/getBP.jsp" method = "get">
	<select name = "member" onchange="singerChangeHandler()">
		<option value = "">멤버 선택</option>
		<%
			String pattern = "<option value = %s>%s</option>";
			for(Entry<String, String[]>mem : singerMap.entrySet()){
			String key = mem.getKey();
			String [] value = mem.getValue();
			out.println(String.format(pattern,key,value[0]));
		}
		%>
	</select>
</form>
</body>
</html>