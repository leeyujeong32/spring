package chap01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainForXml {

	public static void main(String[] args) {
		//xml 설정파일을 읽어오기
		//classpath : 클래스가 있는 경로
		//(서버(운영환경)에서는 /WEB-INF/classes, 로컬(개발환경)에서는 target/classes)
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("chap01/beans.xml");
		
		Greeter g = ctx.getBean("greeter", Greeter.class);
		System.out.println(g.greet());
	}

}
