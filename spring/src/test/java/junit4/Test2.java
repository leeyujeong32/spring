package junit4;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chap07.BoardDao;
import chap07.BoardVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {chap07.MvcConfig.class})//java annotation 방식이기 때문
public class Test2 {
	
	@Autowired
	BoardDao boardDao;
	
	@Test
	public void boardDaoTest() {
		int cnt = boardDao.count(new BoardVo());
		System.out.println(cnt);
	}
}
