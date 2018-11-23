package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

public class MemberListController implements ICommandHandler{
	
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//서블릿 작성
//		1.요청과의 매핑설정
//		2.요청 분석(주소, 파라미터, 메소드, 헤더들...)
		int currentPage = 1;
		String page = req.getParameter("page");
//		3.B.L.L와의 의존관계 형성(BusinessLogicLayer)
		IMemberService service = new MemberServiceImpl();
		if(StringUtils.isNumeric(page)) {//정규식 쓰지않더라도 문자가 숫자로 구성되는지 파악
			currentPage = Integer.parseInt(page);
		}
		PagingInfoVO pagingVO = new PagingInfoVO();
		pagingVO.setCurrentPage(currentPage);
//		4.로직선택
//		5.컨텐츠(Model) 확보
		long totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
//		6.V.L를 선택(ViewLayer)
		String view = "member/memberList";
//		7.Scope를 통해 Model공유
		req.setAttribute("pagingVO", pagingVO);
//		8.이동 방식을 결정하고, V.L로 이동
//		RequestDispatcher rd = req.getRequestDispatcher(view);
//		rd.forward(req, resp);
//		frontController쪽에서 이미 짬.
		return view;
		
	}
}
