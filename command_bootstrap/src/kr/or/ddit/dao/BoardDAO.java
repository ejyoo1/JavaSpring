package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BoardVO;
import kr.or.ddit.dto.PdsVO;

public interface BoardDAO {
	// 자료실 조회
	List<BoardVO> selectSearchBoardList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// 자료실 글 수 조회
	int selectSearchBoardListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	// bno 기준 글 조회
	BoardVO selectBoardByBno(SqlSession session, int bno) throws SQLException;
	
	// 조회수 증가
	void increaseViewCount(SqlSession session, int bno) throws SQLException;
	
	// 글 다음 번호 가져오기
	int selectBoardSequenceNextValue(SqlSession session) throws SQLException;
	
	// 게시판 등록
	void insertBoard(SqlSession session, BoardVO board) throws SQLException;
	
	// 게시판 수정
	void updateBoard(SqlSession session, BoardVO board) throws SQLException;
	
	// 게시판 삭제
	void deleteBoard(SqlSession session, int bno) throws SQLException;
}
