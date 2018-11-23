package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.controller.MemberListController;
import kr.or.ddit.member.controller.MemberUpdateController;
import kr.or.ddit.member.controller.MemberViewController;
import kr.or.ddit.member.controller.MyPageController;

public class FrontController extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String,ICommandHandler> handlerMap;
	private String mappingInfo;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		handlerMap = new HashMap<>();
		mappingInfo=config.getInitParameter("mappingInfo");
		ResourceBundle bundle = ResourceBundle.getBundle(mappingInfo);//프로퍼티스 파일 읽어오기
		Set<String> keySet = bundle.keySet();//프로퍼티스 파일에서 읽어온값 셋팅
		for(String uri : keySet) {
			String qualifiedName = bundle.getString(uri);
			try {
				Class<ICommandHandler> handlerClz = (Class<ICommandHandler>)Class.forName(qualifiedName.trim());//클래스를 리턴받음
				ICommandHandler handler = handlerClz.newInstance();
				handlerMap.put(uri.trim(),handler);
				logger.info("{} 에 대한 핸들러 {} 등록", uri, qualifiedName);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				logger.error("{} 에 대한 핸들러 : {} 에서 문제 발생 \n", uri, qualifiedName, e.getMessage());
				continue;
			}
		}
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		1.요청과의 매핑 설정-web.xml에서 설정 해줫음
//		2.요청 분석(주소, 파라미터, 메소드, 헤더...)
		String uri = req.getRequestURI();//주소분석까지만 frontcontroller에서함
		System.out.println(uri);
//		/webStudy03_Maven/*.do
		int cpLength = req.getContextPath().length();
		System.out.println(cpLength);
		uri = uri.substring(cpLength).split(";")[0];
		uri.substring(cpLength);
		System.out.println(uri);
		ICommandHandler handler = handlerMap.get(uri);
		
		if(handler==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,"해당 서비스는 제공하지 않습니다.");
			return;
		}
		
		
//		3.의존관계 형성()
//		4.로직 선택
//		5.모델 확보
//		6.뷰 선택
//		7.모델 공유 2~7번은 그뒤에서 해줌
//		8.선택한 뷰로 이동
		String view = handler.process(req, resp);
		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";
		if(view!=null) {
			boolean redirect = view.startsWith("redirect:");
			if(redirect) {
				view = view.substring("redirect:".length());
				resp.sendRedirect(req.getContextPath()+view);
			}else {
				RequestDispatcher rd = req.getRequestDispatcher(prefix+view+suffix);
				rd.forward(req,resp);
			}
			
		}else {
			if(!resp.isCommitted()) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"커맨드 핸들러에서 뷰가 선택되지 않았습니다.");
			}
		}
	}
}
