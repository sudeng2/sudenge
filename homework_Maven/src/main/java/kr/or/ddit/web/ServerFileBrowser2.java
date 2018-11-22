package kr.or.ddit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.web.model2.FileList2;

@WebServlet("/fileBrowser.do2")
public class ServerFileBrowser2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파일 목록을 보여주고 
		//그게 디렉토리라면 그폴더안에있는 목록보여주고
		//..링크 추가하면 다시 상위폴더로
		//파라미터로 넘겨줌
		String path= req.getParameter("path");//경로 파라미터 가져옴, 선택을하면 값이 담김 처음엔 null
		String name= req.getParameter("name");//폴더이름 파라미터 가져옴.
		String radio = req.getParameter("radio");
		/*String delete = req.getParameter("delete");
		String copy = req.getParameter("copy");*/
		
		String er = getServletContext().getRealPath("/");//현재 경로
		//path가 null이면 path에 현재경로가 담긴다.
		if(path==null) {
			path=getServletContext().getRealPath("/");
		}else {
			//폴더이름에 .이 있으면 path에 부모파일 절대경로를 가져온다.
			if(name.contains(".")) {
				File file=new File(path);
				path=file.getParentFile().getAbsolutePath();
				if(StringUtils.isNotBlank(radio) && "delete".equals(radio)) {
					file.delete();
				}
				//copy파라미터가 넘어오면 해당파일 복사
				if(StringUtils.isNotBlank(radio) && "copy".equals(radio)) {
						//파일이 복사될 경로
						File src = new File(file.getParentFile().getAbsolutePath());
						//파일이 복사될 경로, 이름명
					    File dist = new File(src,"copy"+file.getName());
					    FileInputStream fin = null;
						FileOutputStream fos = null;
				  
				      try {
				    	  	
				    	  	fin = new FileInputStream(file); //복사가 될 대상
							fos = new FileOutputStream(dist);

				         int c;
				         
				         while((c = fin.read())!=-1){
								fos.write(c);
							}
				         System.out.println("파일복사 성공");
				         fin.close();
						 fos.close();

				      }catch (IOException e) {
				         e.printStackTrace();
				         System.out.println("파일 복사 오류 발생~!~!~!");

				      }
				}
				if(StringUtils.isNotBlank(radio) && "move".equals(radio)) {
	                   File newFile = new File(er+file.getName());
	                   System.out.println(er);
	                   if(file.exists()) {
	                       file.renameTo(newFile);
	                       System.out.println("이동했냐");
	                   }
				}
			}else{
				//파라미터로 delete가 넘어오면 해당파일을 삭제.
				if(StringUtils.isNotBlank(radio) && "delete".equals(radio)) {
					
					File file=new File(path);
					path=file.getParentFile().getAbsolutePath();
					file.delete();
				}
			}
		}
		
		FileList2 fileList=new FileList2();
		
		//fileList를 리스트에 담아줌
		List<File> filefile=fileList.getFileList(path);
		
		if(filefile!=null) {
			//디스패치 형식으로 jsp로 전달하기때문에 원본 request값이 유지된다.
			//그래서 포워드 방식을 사용
			//setAttribute를 통해 파일리스트 전달
			req.setAttribute("file", filefile);
		}else {
			
		}
		
		// fileBrowser.jsp의 경로로  포워드방식으로  request와 response를 전달한다.
		// 포워드 방식이기 때문에 경로를 서버사이드 절대경로를 사용한다.
		String view="/WEB-INF/views/fileBrowser2.jsp";
		RequestDispatcher rd= req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
