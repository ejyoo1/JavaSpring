package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.dto.PdsVO;

public interface PdsDAO {
	// 자료실 조회
	List<PdsVO> selectSearchPdsList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 자료실 글 수 조회
	int selectSearchPdsListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// pno 기준 글 조회
	PdsVO selectPdsByPno(SqlSession session, int pno) throws SQLException;
	
	// 조회수 증가
	void increaseViewCount(SqlSession session, int pno) throws SQLException;
	
	// 글 다음 번호 가져오기
	int selectPdsSequenceNextValue(SqlSession session) throws SQLException;
	
	// 자료실 등록
	void insertPds(SqlSession session, PdsVO pds) throws SQLException;
	
	// 자료실 수정
	void updatePds(SqlSession session, PdsVO pds) throws SQLException;
	
	// 자료실 삭제
	void deletePds(SqlSession session, int pno) throws SQLException;
}
