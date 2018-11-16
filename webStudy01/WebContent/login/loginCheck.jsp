<%@page import="kr.or.ddit.utils.CookieUtil.TextType"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.utils.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	//1.파라미터확보

	String id = request.getParameter("mem_id");
	String pass = request.getParameter("mem_pass");
	String goPage = null;
	boolean redirect = false;

	//2.검증(필수 데이터)누락
	if (id == null || id.trim().length() == 0||pass == null || pass.trim().length() == 0) {
// 		goPage = "/login/loginForm.jsp?error=1";
		goPage = "/login/loginForm.jsp";
		redirect = true;
		session.setAttribute("message","아이디나 비번 누락");
	}else{
		//4-1.인증(아이디==비번)
		if(id.equals(pass)){
			goPage = "/";
			session.setAttribute("authMember",id);
			redirect = true;
			String idchecked = request.getParameter("idChecked");
			if(StringUtils.isNotBlank(idchecked)){
				Cookie loginCookie = CookieUtil.createCookie("login",id,request.getContextPath(),TextType.PATH,60*60*24*7);
				response.addCookie(loginCookie);
			}else{
				Cookie loginCookie = CookieUtil.createCookie("login",id,request.getContextPath(),TextType.PATH,0);
				response.addCookie(loginCookie);
				
			}
		}else{
			//인증실패
			goPage = "/login/loginForm.jsp";
			redirect = true;
			session.setAttribute("message", "비번 오류로 인증 실패");
		}
	}
	if(redirect){
		response.sendRedirect(request.getContextPath()+goPage);
	}else{
		RequestDispatcher rd = request.getRequestDispatcher(goPage);
		rd.forward(request, response);//request안에는 파라미터가 들어있고 그 파라미터를 도착지쪽으로 넘겨준다.
	}
	
	
	
	
	//4-2.인증 성공과:웰컴페이지로 이동(원본요청을 제거하고 이동) 
%>
