package chap07;

import java.util.List;

public interface BoardService {

	List<BoardVo> selectList(BoardVo vo);
	int count(BoardVo vo);
	int insert(BoardVo vo); //추상메서드
	BoardVo selectOne(int boardno);
	BoardVo2 selectOne2(int boardno);
	int update(BoardVo vo);
	int delete(BoardVo vo);
	
}
