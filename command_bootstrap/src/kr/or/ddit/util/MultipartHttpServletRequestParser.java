package kr.or.ddit.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import kr.or.ddit.exception.NotMultipartFormDataException;

public class MultipartHttpServletRequestParser {
	private static final Logger INFO_LOGGER = Logger.getLogger(MultipartHttpServletRequestParser.class);
	
	// 업로드 파일 환경 설정 (고정값. 처음 할당받고 값을 변화시키지 않음 , 인스턴스마다 다를 순 있음.)
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB // 고정수. 상수취급
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB
	
	Map<String, String[]> paramString = new HashMap<String, String[]>();
	Map<String, List<FileItem>> paramFile = new HashMap<String, List<FileItem>>();
	
	// 파일 사이즈 기본값
	public MultipartHttpServletRequestParser(HttpServletRequest request)
											throws NotMultipartFormDataException,
												UnsupportedEncodingException,
												FileUploadException {
		this(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
	}
	
	// 파일 사이즈 변경할 수 있도록 만듬
	public MultipartHttpServletRequestParser(HttpServletRequest request, 
										int memory_threshold, int max_file_size, int max_request_size)
										throws NotMultipartFormDataException,
											UnsupportedEncodingException,
											FileUploadException {
		ServletFileUpload upload = 
				ServletFileUploadBuilder.build(request, memory_threshold, max_file_size,
											max_request_size); 
		
		// 코어부분 굉장히 중요 --> item형식 리스트로 줌
		// 파일이 몇개올지 모르므로 List로
		List<FileItem> formItems = upload.parseRequest(request);
		// 파일 아이템인지 구분
		for (FileItem item : formItems) {
			// 필드
			String paramName = item.getFieldName();
			
			// 일반 파라미터이면 K,V 삽입(Multipart는 key하나에 value가 ','를 구분자로 가지고 있음.)
			if(item.isFormField()) {
				INFO_LOGGER.info("일반 파라미터 형식");
				String[] paramValues = item.getString("utf-8").split(",");
				this.paramString.put(paramName, paramValues);
			} else { // 파일이면
				// 파일을 리스트 형태로 변환하여 넣어야 함.
				INFO_LOGGER.info("파일 형식");
				List<FileItem> files = this.paramFile.get(paramName); // 해당 키로 리스트를 가져옴
				
				if(files == null) { // 리스트가 없다면
					files = new ArrayList<FileItem>(); // 만듦
					this.paramFile.put(paramName, files); // Map에 ArrayList 삽입
				} // 준비 완료
				
				files.add(item); // 아이템 하나 하나 찍기
			}
		}
	}
	
	// 파라미터 하나만 주는 것
	public String getParameter(String paramName) {
		String[] param = this.paramString.get(paramName);
		String result = null;
		if(param != null) {
			result = param[0];
		}
		return result;
	}
	
	// 파라미터 여러개 주는것
	public String[] getParameterValues(String paramName) {
		return this.paramString.get(paramName);
	}
	
	public Enumeration<String> getParameterName(){ // ServletRequest 의 getParameterName 의 타입을 맞춤, 일반 Request처럼 사용할 수 있도록 만듬.
		List<String> paramNames = new ArrayList<String>();
		
		if(paramString.size() > 0) {
			for(String paramName : paramString.keySet()) {
				paramNames.add(paramName);
			}
		}
		if(paramFile.size() > 0) {
			for(String paramName : paramString.keySet()) {
				paramNames.add(paramName);
			}
		}
		
		Enumeration<String> result = Collections.enumeration(paramNames);
		
		return result;
	}
	
	public FileItem getFileItem(String paramName) {
		List<FileItem> itemList = paramFile.get(paramName);
		FileItem result = null;
		
		if(itemList != null) result = itemList.get(0);
		
		return result;
	}
	
	public FileItem[] getFileItems(String paramName) { // 파일 목록을 줄때는 변하지 않을것이므로 배열로 줌. ArrayList는 길이를 모를때 사용함.
		List<FileItem> items = paramFile.get(paramName);
	      FileItem[] files = null;
	      if(items != null) {
	         files = new FileItem[items.size()];
	         items.toArray(files);
	      }
	      return files;
	}
}
