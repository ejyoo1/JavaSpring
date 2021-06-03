package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.util.MakeFileName;

public class FileDownloadResolver {
	
	public static void sendFile(String fileName, String savedPath,
								HttpServletRequest request, HttpServletResponse response)
								throws ServletException, IOException {
		
		String filePath = savedPath + File.separator + fileName;
		
		// 보낼 파일 설정
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		// 파일 포맷으로 MIME를 결정한다.
		ServletContext context = request.getServletContext();
		String mimeType = context.getMimeType(filePath);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		// response 수정.
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length()); // 회원 상세는 바이너리 값만 넘어가도 됨.
		
		// 다른 게시판에서도 사용할 것이기 때문에 아래 작업 수행.
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				MakeFileName.parseFileNameFromUUID(downloadFile.getName(), "\\$\\$"));// UUID 떼고 $$떼어 원본 파일 명만 가져옴
				
		response.setHeader(headerKey, headerValue);
		
		// 파일 내보내기
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096]; // 파일 내보내기 (4KB 단위)
		int bytesRead = -1;
		
		while((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		
		inStream.close();
		outStream.close();
	}
}
