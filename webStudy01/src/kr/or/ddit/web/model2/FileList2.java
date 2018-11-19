package kr.or.ddit.web.model2;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileList2 {
	public List<File> getFileList(String fileNmae) {
		//file객체 생성 folder라는 이름에 
		File folder= new File(fileNmae);
		
		//파일 배열안에 folder파일리스트를 담아줌
		File[] folderList=folder.listFiles();
		//파일리스트의 갯수
		System.out.println(folderList.length);
		//arraylist 객체(담아줄것을 만듬)
		List<File> filist=new ArrayList<>();
		//파일이름에 webStudy01이 포함되어있지않으면  filist에 부모 파일명을 담아준다.
		if(!folder.getName().equals("webStudy01")) {
			filist.add(folder.getParentFile());
		}
		//만약 folder(=파일) 가 폴더인지 아닌지 판별
		if (folder.isDirectory()) {
			//tmp에 파일을 하나씩 담아준다.
			for(File tmp:folderList) {
				//그걸다시 filist에 추가
				filist.add(tmp);
			}
			
		}
		
		return filist;
		}
}
