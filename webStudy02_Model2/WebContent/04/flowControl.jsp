<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/flowControl.jsp</title>
</head>
<body>
<h4>흐름제어(페이지 이동)</h4>
<pre>
	1.Request Dispatch : 원본 요청에 대한 정보를 페이지를 이동하는 과정에서 전달해주는 방식
		1)Forward : 페이지를 이동한 이후 최종 도착지에서 응답데이터가 전송
		<%
			RequestDispatcher rd = request.getRequestDispatcher("/05/destination.jsp");
// 			rd.forward(request, response);//destination.jsp에 있는내용, 마치 flowControl.jsp의 내용마냥
		%>
		2)Include : 페이지를 이동한 후 도착지에서 생성된 결과데이터(버퍼의내용)가 출발지(시작페이지)로
					다시 돌아오는 구조.
					최종응답데이터는 시작페이지에서 전송.
		<%
// 			rd.include(request, response);//destination.jsp에있는내용이랑 합쳐짐 마치 flowControl.jsp가 다한거처럼
		%>
					
	2.Redirect : 
		1)원본요청이 시작페이지로 전송
		2)해당페이지에서 Body가 없고, 상태코드(302/307), Location헤더를 가지고 응답 전송
		  Stateless특성에 따라 서버에서는 최초의 요청 정보가 삭제.
		3)클라이언트 쪽에서 Location방향으로 새로운 요청이 발생.
		4)최종 응답데이터는 3)번의 새로운 요청에 대한 응답으로 전송.
		
		<%
			response.sendRedirect(request.getContextPath()+"/05/destination.jsp");//응답데이터가 destination에서만 나오는데 주소도 destination으로 바뀌어있음
																				  //응답데이터가 b에서 나왔구나를 알수있음.
		%>
</pre>
</body>
</html>