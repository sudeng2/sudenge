<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 1.파라미터확보 -->
<!-- 2.검증(필수데이터 검증,유효데이터 검증) -->
<!-- 3.불통 -->
<!--   1)필수데이터 누락 : 400 -->
<!--   2)우리가 관리하지 않는 멤버를 요구한 경우 : 404 -->
<!-- 4.통과 -->
<!--     이동(맵에 있는 개인페이지, 클라이언트가 멤버 개인페이지의 주소를 모르도록.) -->
<!--     이동(맵에 있는 개인페이지, getBP에서 원본 요청을 모두 처리했을경우, UI페이지로 이동할때.) -->
<%!
	Map<String, String[]> singerMap = new LinkedHashMap<>();
{
	singerMap.put("BP001",new String[]{"jennie","/WEB-INF//bp/jennie.jsp"});
	singerMap.put("BP002",new String[]{"jisoo","/WEB-INF//bp/jisoo.jsp"});
	singerMap.put("BP003",new String[]{"lisa","/WEB-INF//bp/lisa.jsp"});
	singerMap.put("BP004",new String[]{"rose","/WEB-INF//bp/rose.jsp"});
}
%>
<%
	//post할때 UTF-8해줘야함
	//request.setCharacterEncoding("UTF-8");
	String singer = request.getParameter("member");
	boolean result = false;
	String goPage = null;
	
	int statusCode = 0;
	if(singer == null||singer.trim().length()==0){
		//goPage = "/05/bpForm.jsp";
		//response.sendError(400);
		statusCode = HttpServletResponse.SC_BAD_REQUEST;
	}else if(!singerMap.containsKey(singer)){
		statusCode = HttpServletResponse.SC_NOT_FOUND;
	}
	if(statusCode != 0){
		response.sendError(statusCode);
		return;
	}
	
	//선택한 정보랑 일치하는게 있는지 검증하는 부분
	String[] value = singerMap.get(singer);
	goPage = value[1];
	
	RequestDispatcher rd = request.getRequestDispatcher(goPage);
    rd.forward(request, response);
	
	//include방식
// 	RequestDispatcher rd = request.getRequestDispatcher(goPage);
//     rd.include(request, response);


	//redirect방식
	//response.sendRedirect(request.getContextPath()+goPage);

// 	for(String key:singerMap.keySet()){
// 		if(key.equals(singer)){
// 			goPage = singerMap.get(key)[1];
// 			result = true;
// 		}
// 	}
// 	if(result){
// 		RequestDispatcher rd = request.getRequestDispatcher(goPage);
// 		rd.include(request, response);
// 	}else{
// 		response.sendError(404);
// 	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>