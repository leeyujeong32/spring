package chap07;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //bean에 등록하기 위해서
public class BoardDao {
	
	@Autowired //자동 주입 : MvcConfig 파일에서 scan 후 동일한 이름 찾아서주입
	SqlSessionTemplate sqlSessionTemplate;//필드 - 타입이 SqlSessionTemplate일 경우 자동 주입
	
	public int count(BoardVo vo) {
		return sqlSessionTemplate.selectOne("board.count",vo); //1가지로 나오기 때문에 selectOne을 이용
	}
	
	public List<BoardVo> selectList(BoardVo vo) { //board.xml 결과를 받아오기-리스트타입
		return sqlSessionTemplate.selectList("board.selectList", vo); //map 사용시 - 검색어를 controller에서 받음
	}
	
	public int insert(BoardVo vo) {
		int r = -1;
		try {
			r = sqlSessionTemplate.insert("board.insert", vo); //namespace.id, 넘겨줄 객체(vo)
		}catch (Exception e) {
			r = 0;
			System.out.println(e.getMessage());
		}
		return r;
	}
	
	/*
	 * 메서드 생성
	 * sqlSessionTemplate.selectOne("board.selectOne", PK)
	 */
	public BoardVo selectOne(int boardno) {
		return sqlSessionTemplate.selectOne("board.selectOne",boardno); //int타입의 boardno가 autobox?, 그래서 object화
	}
	public BoardVo2 selectOne2(int boardno) {
		return sqlSessionTemplate.selectOne("board.selectOne2",boardno); //int타입의 boardno가 autobox?, 그래서 object화
	}
	
	public int update(BoardVo vo) {
		return sqlSessionTemplate.update("board.update", vo);
	}
	
	public int delete(BoardVo vo) { //delete 개수를 반환 -> int 
		return sqlSessionTemplate.delete("board.delete",vo.getBoardno());
	}
}