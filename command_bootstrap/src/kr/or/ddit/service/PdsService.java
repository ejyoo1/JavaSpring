package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.dto.PdsVO;

public interface PdsService {
	// 자료실 목록 조회
	Map<String, Object> getPdsList(SearchCriteria cri) throws SQLException;
		
	// 상세보기
	PdsVO getPds(int pno) throws SQLException;
	
	// 수정화면 상세
	PdsVO getPdsForModify(int pno) throws SQLException;
	
	// 등록
	void regist(PdsVO pds) throws SQLException;
	
	// 수정
	void modify(PdsVO pds) throws SQLException;
	
	//삭제
	void remove(int pno) throws SQLException;
}
