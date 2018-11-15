package kr.or.ddit.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImagesFormServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = req.getServletContext();
		/*String contentFolder = context.getInitParameter("contentFolder");
		//File folder = new File("d:/contents");
		File folder = new File(contentFolder);*/
		 File folder = (File)context.getAttribute("contentFolder");
		String[] filenames = folder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = context.getMimeType(name);
				return  mime.startsWith("image/");
			}
		});
		
		//action속성의 값은 context/imageService, method = "get"
		resp.setContentType("text/html;charset=UTF-8");
		InputStream in = this.getClass().getResourceAsStream("imageform.html");
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
	     
	      int start = html.indexOf("@imageform");
		  int end = start + "@imageform".length();
		  String replacetext = sb.toString();
		  
		  html.replace(start, end, replacetext);
		  
	     
	      PrintWriter out = resp.getWriter();
	      out.println(html.toString());
	      
	     
	}

}
