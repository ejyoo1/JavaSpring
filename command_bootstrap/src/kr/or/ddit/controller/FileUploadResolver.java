package kr.or.ddit.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.util.MakeFileName;

public class FileUploadResolver { 
	// FileItem ==> MultipartHttpServletRequestParser 가져옴.
	public static List<AttachVO> fileUpload(FileItem[] items, String uploadPath) throws Exception{
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 생성을 실패했습니다.");
		}
		
		if(items != null) 
			for(FileItem item : items) {
				if(!item.isFormField()) {
					String fileName = new File(item.getName()).getName();
					fileName = MakeFileName.toUUIDFileName(fileName, "$$");
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);
					
					// local HDD 에 저장
					try {
						item.write(storeFile);
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
					
					// DB에 저장할 attach에 file 내용 추가.
					AttachVO attach = new AttachVO();
					attach.setFileName(fileName);
					attach.setUploadPath(uploadPath);
					attach.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
					
					attachList.add(attach);
					
					System.out.println("upload file : " + attach);
				}
			}
			return attachList;
	}
	
	// 저장하면서 그때그때 넣기 위해
	
	// 없으면 만들고 있으면 있는것 쓰고
	//mkdir : 상위디렉토리 없으면 에러 / mkdirs : 상위 디렉토리 하위디렉토리 모두 만들어줌(있으면 안만듬)
	// 만들면 true / 있으면 false (있으니까 안만듬)
	
	// 무조건 파일
	// 저 경로에 저 파일명으로 파일을 저장
	// 파일을 저장할 땐 output에
	// 파일 저장메서드는 item에 있으므로 write에 넣으면 알아서 output에 줌. (저장하다 터지면 Exception
	
	// 저장 제대로 되면 파일 하나씩 AttachVO 만듬
	
	
}
