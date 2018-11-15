package kr.or.ddit.web;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.web.model2.FileListGenerator;

@WebServlet("/fileBrowser.do")
public class ServerFileBrowser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//브라우저 상에 폴더들의 목록을 그대로 보여준다.
		// 보여줬는데 그게 디렉토리라면 두번클릭했을때 그아래에 있는 파일들로 화면을 바꾼다.
		// .. 링크를 누르면 상위로 갈 수 있는 서버의 파일 브라우저 만들기
		
		//파일 리스트를 뽑아올 메서드를 만들어준 객체 생성
		FileListGenerator fg = new FileListGenerator();
		
		//제일 초기 url을 세팅
		String url = "d:/A_TeachingMaterial/gitRepo/webStudy01/WebContent/";
		
		//request에서 fileAddress라는 이름을 가진 input tag의 value 값 가져오기
		if(req.getParameter("fileAddress")!=null) {
			url = req.getParameter("fileAddress");
		}
		
		//FileListGenerator에서 정의해준 메서드를 이용해 파일 리스트 생성
		File [] files = fg.getFileList(url);
		
		//디스패치 형식으로 jsp로 전달하기때문에 원본 request값이 유지된다.
		//그래서 포워드 방식을 사용
		
		//request에 setattribute를 이용해 파일리스트를 전달
		req.setAttribute("files", files);
		
		// fileBrowser.jsp의 경로로  포워드방식으로  request와 response를 전달한다.
		// 포워드 방식이기 때문에 경로를 서버사이드 절대경로를 사용한다.
		String view = "/WEB-INF/views/fileBrowser.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
		
	}
}
