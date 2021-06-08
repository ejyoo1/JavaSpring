package kr.or.ddit.handler.summernote;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.command.SummernoteDeleteImgCommand;
import kr.or.ddit.handler.Handler;
import kr.or.ddit.util.GetUploadPath;

public class SummernoteDeleteImgHandler implements Handler {

   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String url = null;
      
      ObjectMapper mapper = new ObjectMapper();
      
      SummernoteDeleteImgCommand delReq = mapper.readValue(request.getReader(), SummernoteDeleteImgCommand.class);
      
      String savePath = GetUploadPath.getUploadPath("summernote.img");// 리플렉션 ( 변수명 일치해야 함.)
      savePath = URLDecoder.decode(savePath, "UTF-8");
      String fileName = delReq.getFileName();
      fileName = URLDecoder.decode(fileName, "UTF-8");
      
      File target = new File(savePath + File.separator + fileName);
      
      response.setContentType("text/plain;charset=utf-8");
      
      PrintWriter out = response.getWriter();
      
      if (!target.exists()) {
         response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 없는 파일
      } else {
         target.delete();
         out.print(fileName + " 이미지를 삭제했습니다.");
      }
      
      return url;
   }

}