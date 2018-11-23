package kr.or.ddit.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/song")
public class MusicLyricsServlet2 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String song = req.getParameter("song");
		int status = 0;
		String message = null;
		if(song == null || song.trim().length()==0) {
			status = HttpServletResponse.SC_BAD_REQUEST;
			message = "가사파일을 선택해주세요";
		}
		
		/*String contentFolder = getServletContext().getInitParameter("contentFolder");
		File folder = new File(contentFolder);*/
		 File folder = (File)getServletContext().getAttribute("contentFolder");
		File songFile = new File(folder, song);
		if(!songFile.exists()) {
			status = HttpServletResponse.SC_NOT_FOUND;
			message = "해당곡은 가사가 없습니다 메롱";
		}
		if(status != 0) {
			resp.sendError(status, message);
			return;
		}
		resp.setContentType("text/html;charset=UTF-8");
		
		try(
				//Closable객체를 할당
				FileInputStream fis = new FileInputStream(songFile);//바이트스트림으로밖에 못읽음, 스트림열기
				InputStreamReader isr = new InputStreamReader(fis,"MS949");//바이트 단위로 읽기, 다시 isr 값을 받아서 버퍼에 작성을 하게 됩니다
				BufferedReader reader = new BufferedReader(isr);//BufferedReader는 인자로 취한 Reader 
															//스트림에 버퍼링기능을 추가한 입력스트림 클래스 입니다
															//그리고, 사용자가 사용하기 편리한, readLine()과 같이 한줄씩읽어오는 메소드를 제공합니다
				PrintWriter out = resp.getWriter();
				) {
			
		String temp = null;
		StringBuffer html = new StringBuffer();
		while((temp = reader.readLine())!=null) {
			html.append("<p>"+temp+"</p>");
			System.out.println(temp);
		}
		out.println(html);
		}/*finally {
			if(reader!=null)reader.close();
			if(out!=null) out.close();
			out.close();
		}*/
		
	}
}
