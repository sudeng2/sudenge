package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.ICommandHandler;
import kr.or.ddit.vo.BuyerVO;

public class BuyerViewController implements ICommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String who = req.getParameter("who");
		//who에 값이 넘어 왔나 안왔나 확인하기위한 이프문
		if(StringUtils.isBlank(who)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null; //이 조건에 걸리면 who에 값이 없다는 의미로 더이상 이 메소드를 실행할 필요가 없으므로 리턴
		}
		IBuyerService service = new BuyerServiceImpl();
		BuyerVO buyer = service.retrieveBuyer(who);//서비스에서 값 받아오기
		String view = "buyer/buyerView";//디스패처는 서버사이드 경로이므로 /WEB-INF 로시작....
		
		req.setAttribute("buyer", buyer);//리퀘스트 스코프 영역에 멤버vo를 담아줌
		return view;
	}

}
