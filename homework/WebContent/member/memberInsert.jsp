<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.ServiceResult"%>
<%@page import="kr.or.ddit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.ddit.member.service.IMemberService"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 1. 파라미터 확보(특수문자 고려) -->
<!-- 2. 검증(검증룰 : member 테이블의 스키마를 확인, 필수데이터 검증은 반드시!!) -->
<!-- 3. 검증통과 -->
<!--    1) 로직객체와의 의존관계 형셩 -->
<!--    2) 로직 선택(registMember) -->
<!--       PKDUPLICATED : memberFor.jsp 로 이동(메시지, 기존 입력데이터 공유 - dispatch 또는 forward) -->
<!--       OK : memberList.jsp 로 이동. redirect -->
<!--       FAILED : memberFor.jsp 로 이동(기존 입력데이터, 메시지 공유 - forward) -->
<!-- 4. 검증불통과 -->
<!--    memberFor.jsp 로 이동, (기존 입력데이터, 검증 결과 메시지 공유 - forward) -->
<%!//에러를 표시하는데 맵을 왜사용하는거야 도대체
   private boolean validate(MemberVO member, Map<String, String> errors) {
      boolean valid = true;
      //검증룰
      if (StringUtils.isBlank(member.getMem_id())) {
         valid = false;
         errors.put("mem_id", "회원아이디 누락");
      }
      if (StringUtils.isBlank(member.getMem_pass())) {
         valid = false;
         errors.put("mem_pass", "비밀번호 누락");
      }
      if (StringUtils.isBlank(member.getMem_name())) {
         valid = false;
         errors.put("mem_name", "회원명 누락");
      }
      if (StringUtils.isBlank(member.getMem_zip())) {
         valid = false;
         errors.put("mem_zip", "우편번호 누락");
      }
      if (StringUtils.isBlank(member.getMem_add1())) {
         valid = false;
         errors.put("mem_add1", "주소1 누락");
      }
      if (StringUtils.isBlank(member.getMem_add2())) {
         valid = false;
         errors.put("mem_add2", "주소2 누락");
      }
      if (StringUtils.isBlank(member.getMem_mail())) {
         valid = false;
         errors.put("mem_mail", "이메일 누락");
      }
      if(StringUtils.isNotBlank(member.getMem_bir())){
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         // formatting : 특정 타입의 데이터를 일정 형식의 문자열로 변환.
         // parsing : 일정한 형식의 문자열을 특정 타입의 데이터로 변환.
         try{
            formatter.parse(member.getMem_bir());
         }catch(ParseException e){
            valid = false;
            errors.put("mem_bir", "날짜 형식 확인");
         }
      }
      return valid;
   }%>

<%
   request.setCharacterEncoding("UTF-8"); //특수문자 고려하여 인코딩을 해준다.
%>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
<jsp:setProperty name="member" property="*" />
<%
   String goPage = null; //이동하는 페이지
   boolean redirect = false; // 전송방법
   String message = null; //에러메시지 
   Map<String, String> errors = new LinkedHashMap<>();
   request.setAttribute("errors", errors);
   boolean valid = validate(member, errors);
   System.err.println(errors.size());
   if (valid) {
      //로직 객체와의 의존관계 형성
      IMemberService service = new MemberServiceImpl();
      ServiceResult result = service.registMember(member);
      switch (result) {
      case PKDUPLICATED:
         goPage = "/member/memberForm.jsp";
         message = "아이디가 중복되셨슴다";
         break;
      case FAILED:
         goPage = "/member/memberForm.jsp";
         message = "서버 오류로 실패, 잠시 뒤 다시 시도해주세욤";
         break;
      case OK:
         goPage = "/member/memberList.jsp";
         redirect = true;
         break;
      }
      request.setAttribute("message", message);
   } else {
      goPage = "/member/memberForm.jsp";
   }

   if (redirect) {
      response.sendRedirect(request.getContextPath() + goPage);
   } else {
      RequestDispatcher rd = request.getRequestDispatcher(goPage);
      rd.forward(request, response);
   }
%>
