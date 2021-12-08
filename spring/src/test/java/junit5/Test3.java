package junit5;


import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import chap07.BoardDao;
import chap07.BoardVo;
import chap07.user.UserVo;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = chap07.MvcConfig.class)
public class Test3 {

	@Autowired
	BoardDao dao;
	
	@Autowired
	WebApplicationContext ctx;
	MockMvc mockMvc;
	
	//세션 객체
	MockHttpSession session;
	
	@BeforeEach
	void init() {
		//목업객체
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); //Mvc 목업 객체가 빌드
		
		//로그인 세션 객체 생성
		UserVo uv = new UserVo();
		uv.setUserno(2);
		session = new MockHttpSession(); //session 객체 생성
		session.setAttribute("loginInfo", uv);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		request.setSession(session); //위에서 만든 session객체를 넣어준것
	}
	
	// /board/index.do 테스트
	@Test
	void boardIndex() throws Exception{
		RequestBuilder req = MockMvcRequestBuilders.get("/board/index.do").param("page", "2");
		mockMvc.perform(req);
	}
	
	@Test
	void boardWrite() throws Exception{
		RequestBuilder req = MockMvcRequestBuilders.get("/board/write.do");
		mockMvc.perform(req);
	}
	@Test
	void mypage() throws Exception{
		RequestBuilder req = MockMvcRequestBuilders.get("/user/mypage.do").session(session);
		mockMvc.perform(req);
	}
	
	@Test
	void boardInsert() throws Exception{
		File f = new File("C:\\kdigital\\web\\img\\logo.png");
		FileInputStream fis = new FileInputStream(f);//읽어들이는거니까 inputstream
		MockMultipartFile file = new MockMultipartFile("file", f.getName(),"multipart/form-data",fis);//업로드된 파일명,업로드한 파일 객체
		RequestBuilder req = MockMvcRequestBuilders
				.multipart("/board/insert.do")
				.file(file)
				.param("title", "제목테스트")
				.param("content", "내용테스트")
				.param("writer", "작성자테스트")
				.session(session);
		mockMvc.perform(req);
	}
	
	@Test
	public void boardDaoTest() {
		int cnt = dao.count(new BoardVo());
		System.out.println(cnt);
		assumeTrue(cnt>0);

	}
}
