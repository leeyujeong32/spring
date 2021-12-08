package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // spring 설정파일
public class AppContext {
	
	//MemberController bean으로 등록
	@Bean
	public MemberController memberController() {
		MemberController con = new MemberController();
		con.setService(memberService());
		return con;
	}
	
	//MemberDao bean으로 등록
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	//MemberService bean으로 등록
	@Bean
	public MemberService memberService() {
//		MemberService service = new MemberService();
//		//MemberDao 객체를 주입
//		service.setDao(memberDao());
//		return service;
		return new MemberService().setDao(memberDao());
	}
}
