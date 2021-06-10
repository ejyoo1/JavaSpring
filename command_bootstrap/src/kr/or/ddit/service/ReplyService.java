package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ReplyVO;

public interface ReplyService {
	Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException;
	
	// int 리스트 개수
	int getReplyListCount(int bno) throws SQLException;
	
	// 등록
	void registReply(ReplyVO reply)throws SQLException;
	
	// 수정
	void modifyReply(ReplyVO reply)throws SQLException;
	
	//삭제
	void removeReply(int rno)throws SQLException;
}
