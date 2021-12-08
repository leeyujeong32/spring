package chap04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // spring 설정파일
@ComponentScan(basePackages = {"chap04"}) //basePackages의 배열 값 (패키지)를 스캔해서 빈을 등록
public class AppContext {
	/*
	//MemberController bean으로 등록
	@Bean //AutoWided를 쓰면 필요 없음
	public MemberController memberController() {
		MemberController con = new MemberController();
		//con.setService(memberService());
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
		//return new MemberService().setDao(memberDao());
		return new MemberService();
	}
	*/
}
