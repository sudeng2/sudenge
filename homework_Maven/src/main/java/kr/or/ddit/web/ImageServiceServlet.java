package kr.or.ddit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;

public class ImageServiceServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 파라미터 확보 : 파라미터명(image)
		//이미지 스트리밍...
		String selectImg = req.getParameter("image");
		if(selectImg == null || selectImg.trim().length()==0) {
			resp.sendError(400);
			return;
		}
		//폴더명가지고 파일가져오는방법
		//File folder = new File("d:/contents");
		//File imgFile = new File(folder, selectImg);
//		String contentFolder = getServletContext().getInitParameter("contentFolder");
		 File folder = (File)getServletContext().getAttribute("contentFolder");
		File imgFile = new File("d:/contents/"+selectImg);
		if(!imgFile.exists()) {
			resp.sendError(404);
			return;
		}
		//쿠키값 : A,B
		String imgCookieValue = new CookieUtil(req).getCookieValue("imageCookie");
		String[] cookieValues = null;
		ObjectMapper mapper = new ObjectMapper();
		if(StringUtils.isBlank(imgCookieValue)) {
			cookieValues = new String[] {imgFile.getName()};
		}else {
			String[] cValues = mapper.readValue(imgCookieValue, String[].class);
			cookieValues = new String[cValues.length+1];
			System.arraycopy(cValues, 0, cookieValues, 0, cValues.length);
			cookieValues[cookieValues.length - 1] = imgFile.getName();	
		}
//		imgCookieValue = Arrays.toString(cookieValues);
//		imgCookieValue = imgCookieValue.replaceAll("[\\[\\]\\s]","");
		//마샬링 marshalling
		//오브젝트를받아 문자열로 반환해준
		imgCookieValue = mapper.writeValueAsString(cookieValues);
		Cookie imageCookie = CookieUtil.createCookie("imageCookie",imgCookieValue, req.getContextPath(),TextType.PATH,60*60*24*3);
		System.out.println(imgCookieValue);
		resp.addCookie(imageCookie);
		
		//검증
				ServletContext context = req.getServletContext();
				resp.setContentType(context.getMimeType(selectImg));		
		
		//이미지스트리밍
		FileInputStream fis = new FileInputStream(imgFile);
		OutputStream out = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while((pointer = fis.read(buffer)) != -1){
			out.write(buffer,0,pointer);//읽을만큼만 기록
		}
		fis.close();
		out.close();
	}
}
