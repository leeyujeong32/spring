package chap01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // spring 설정파일
public class AppContext {
	
	//Greeter 클래스에 객체(bean)를 생성해서 컨테이너에 등록
	//bean 이름이 greeter인 리턴값이 객체로 등록
	@Bean
	public Greeter greeter() { //메서드명이 빈이름
		Greeter g = new Greeter();
		g.setName("홍길동");
		return g;
	}
}
