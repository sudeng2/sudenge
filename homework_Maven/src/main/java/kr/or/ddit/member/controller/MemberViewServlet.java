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
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String who = req.getParameter("who");
		//who에 값이 넘어 왔나 안왔나 확인하기위한 이프문
		if(StringUtils.isBlank(who)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return; //이 조건에 걸리면 who에 값이 없다는 의미로 더이상 이 메소드를 실행할 필요가 없으므로 리턴
		}
		IMemberService service = new MemberServiceImpl();
		MemberVO member = service.retrieveMember(who);//서비스에서 값 받아오기
		String view = "/WEB-INF/views/member/memberView.jsp";//디스패처는 서버사이드 경로이므로 /WEB-INF 로시작....
		
		req.setAttribute("member", member);//리퀘스트 스코프 영역에 멤버vo를 담아줌
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
		
		
	}
}
