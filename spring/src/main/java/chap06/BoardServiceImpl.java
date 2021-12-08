package chap06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chap05.MemberVo;

/*
 * Service의 역할 : 비즈니스 로직 담당 - 연산, 등등
 * 
 */
@Service //annotation 사용해서 빈으로 사용? 등록?
public class BoardServiceImpl implements BoardService { //interface 만든후 생성

	@Autowired
	BoardDao boardDao; //BoardDao 주입
	
	@Override
	public int count(BoardVo vo) {

		return boardDao.count(vo);
	}
	
	@Override
	public List<BoardVo> selectList(BoardVo vo) { 
//		Map map = new HashMap(); 
//		map.put("searchType", searchType);
//		map.put("searchWord", searchWord);
		return boardDao.selectList(vo); //Controller로 전달
	}
	
	@Override
	public int insert(BoardVo vo) { //재정의
		
		return boardDao.insert(vo);
	}

	@Override
	public BoardVo selectOne(int boardno) {

		return boardDao.selectOne(boardno);
	}

	@Override
	public int update(BoardVo vo) {
		return boardDao.update(vo);
	}

	@Override
	public int delete(BoardVo vo) { //추상메서드
		return boardDao.delete(vo); //실행 vo안에는 boardno만 들어있?
		
	}
	
	@Override //필드가 오타났을 떄 에러가 발생할 수 있도록 어노테이션을 지정, 안할경우 부모(boardservice) 안에 없는 경우 controller에서 못찾음
	public BoardVo2 selectOne2(int boardno) {
		return boardDao.selectOne2(boardno);
	}


}
