<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="kr.or.ddit.buyer.service.BuyerServiceImpl"%>
<%@page import="kr.or.ddit.buyer.service.IBuyerService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	IBuyerService service = new BuyerServiceImpl();
	List<BuyerVO> buyerList = service.selectBuyerList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>buyer/buyerList.jsp</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
<h4>거래처 목록</h4>
<table class = "table">
	<thead class = "thead-dark">
	<tr>
		<th>거래처아이디</th>
		<th>거래처명</th>
		<th>거래은행</th>
		<th>거래처 계좌번호</th>
		<th>거래처 예금주</th>
		<th>거래처 주소</th>
	</tr>
	</thead>
	<tbody>
	<%
	if(buyerList.size()>0){
		for(BuyerVO vo : buyerList){
	%>
	<tr>
		<td><%=vo.getBuyer_id() %></td>
		<td><%=vo.getBuyer_name() %></td>
		<td><%=vo.getBuyer_bank() %></td>
		<td><%=vo.getBuyer_bankno() %></td>
		<td><%=vo.getBuyer_bankname() %></td>
		<td><%=vo.getBuyer_add1() %></td>
	</tr>
	<%
		}
	}else{
	%>
	<%
	}
	%>
</table>
</body>
</html>