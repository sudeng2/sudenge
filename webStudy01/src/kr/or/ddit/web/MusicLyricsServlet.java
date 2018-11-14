package kr.or.ddit.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MusicLyricsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = req.getServletContext();
		File folder = new File("d:/contents");
		String[] filenames = folder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = context.getMimeType(name);
				return  mime.startsWith("text/");
			}
		});
		
		resp.setContentType("text/html;charset=UTF-8");
		
		InputStream in = this.getClass().getResourceAsStream("musicForm.html");
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuffer html = new StringBuffer();
		String temp = null;
		while((temp = br.readLine()) != null){
			html.append(temp + "\n");
		}
		
		StringBuffer sb = new StringBuffer();
	      for (int i = 0; i < filenames.length; i++) {
	    	  sb.append("<option>");
	    	  sb.append(filenames[i]);
	    	  sb.append("</option>\n");
			
		}
	     
	      int start = html.indexOf("@music");
		  int end = start + "@music".length();
		  String replacetext = sb.toString();
		  
		  html.replace(start, end, replacetext);
		  
	     
	      PrintWriter out = resp.getWriter();
	      out.println(html.toString());
	      out.close();
	     
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String music = req.getParameter("music");
		//화이트 스페이스 일수도 있어서
		if(music == null || music.trim().length()==0) {
			resp.sendError(400);
			return;
		}
		
		//폴더명가지고 파일가져오는방법
		File folder = new File("d:/contents");
		File musicFile = new File(folder, music);
		
		//이 파일이 실제로 존재하는지
		if(!musicFile.exists()) {
			resp.sendError(404);
			return;
		}

		//파일을 읽어온다
		FileInputStream fis = new FileInputStream(musicFile);
		InputStreamReader ins = new InputStreamReader(fis,"EUC-KR");
		BufferedReader br = new BufferedReader(ins);
		
		StringBuffer html = new StringBuffer();
		String gasa = null;
		while((gasa = br.readLine()) !=  null) {
			html.append(gasa+"<br>");
		}
		//-----------------------------------------------------------------
		//템플릿을 읽어온다
		InputStream in = this.getClass().getResourceAsStream("musicContent.html");
		InputStreamReader isr2 = new InputStreamReader(in, "UTF-8");
		BufferedReader br2 = new BufferedReader(isr2);
		StringBuffer html2 = new StringBuffer();
		String temp = null;
		while((temp = br2.readLine()) != null){
			html2.append(temp + "\n");
		}
		//@musicc의 부분을 html로 대체해준다.
		int start = html2.indexOf("@musicc");
		int end = start + "@musicc".length();
		String replacetext = html.toString();
		html2.replace(start, end, replacetext);
		
		PrintWriter out = resp.getWriter();
		out.println(html2.toString());
		out.close();
		
	}
	
}
