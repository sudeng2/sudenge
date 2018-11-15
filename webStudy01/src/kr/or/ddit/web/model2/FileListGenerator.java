package kr.or.ddit.web.model2;

import java.io.File;

public class FileListGenerator {
	
	//url 을 매개변수로 받아 그에 해당하는 파일을 생성해주고
	//그 폴더 경로에 잇는 파일리스트를 가져오는 메서드
	public File[] getFileList(String url) {
		File folder = new File(url);
		return folder.listFiles();
	}
}
