package kr.or.ddit.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import kr.or.ddit.exception.NotMultipartFormDataException;

public class ServletFileUploadBuilder {
	//그냥 파일을 만드는것.
	public static ServletFileUpload build(int MEMORY_THRESHOLD, int MAX_FILE_SIZE, int MAX_REQUEST_SIZE) {
		//파일사이즈와 리퀘스트 파일 사이즈 차이점
		//ex ) 10MB 파일이 4개 : 파일사이즈 : 10MB , 리퀘스트 파일 사이즈 : 40MB
		// JVM 이 사용하는 임시 디렉토리(system)
		
		// 업로드를 위한 upload 환경설정 적용.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 저장을 위한 threshold memory 적용.
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 임시 저장 위치 결정
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 업로드 파일의 크기 적용
		upload.setFileSizeMax(MAX_FILE_SIZE);
		// 업로드 request 크기 적용
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		return upload;
	}
	
	//(기능 확장) request를 보고 멀티파트가 아니면 안만들어주고 멀티파트면 만듦. 기준이 있음.
	public static ServletFileUpload build(HttpServletRequest request,
										int MEMORY_THRESHOLD, int MAX_FILE_SIZE, int MAX_REQUEST_SIZE)
										throws NotMultipartFormDataException{
		// request 파일 첨부 여부 확인
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new NotMultipartFormDataException();
		}
		
		return build(MEMORY_THRESHOLD,MAX_FILE_SIZE,MAX_REQUEST_SIZE);
	}
}
