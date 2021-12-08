package chap07;

import java.io.IOException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //spring 설정 파일이라고 표시
@ComponentScan(basePackages = {"chap07"}) //chap06 패키지 안에 있는 모든 코드를 스캔, Component가 있나없나 - controller, service 등등
@EnableWebMvc // spring mvc 활성화
@EnableTransactionManagement //트랜잭션 활성화
public class MvcConfig implements WebMvcConfigurer{

	//html, css...처리
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer cnf) {
		cnf.enable();
	} //그외의 
	//web
	//was : web application
	
	
	//view 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry reg) {
		reg.jsp("/WEB-INF/view/", ".jsp");
	} //viewResolve를 configure(설정) : view를 설정
	//jsp를 포워딩할려고 하는 것
	
	//DataSource 객체 등록
	//DB 접속정보 설정
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");//driver set
		ds.setUrl("jdbc:mysql://localhost:3306/board"); //db url
		ds.setUsername("root"); //id
		ds.setPassword("root1234"); //password
		
		
		return ds;
	}
	
	//SqlSessionFactory를 주입받기위해서 빈등록
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource());
		
		//sql이 들어있는 xml 경로 설정
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssfb.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml")); //classpath의 mapper폴더 밑의 모든 xml
		
		return ssfb.getObject();
	}
	
	//SqlSessionTemplate :Dao에서 호출하는 객체
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory()); //sqlSessionFactory 주입받고 있음
	} //sqlSessionTemplate을 Dao에서 주입받음
	
	//파일 업로드
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding("UTF-8");
		cmr.setMaxUploadSize(1024*1024*10); //파일업로드할때 최대 업로드 사이즈(바이트)
		return cmr;
	}
	//인터셉터
	@Bean LoginInterceptor  loginInterceptor() {
		return new LoginInterceptor();
	}
	
	//인터셉터 설정
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(loginInterceptor())
				.addPathPatterns("/board/write.do")
				.addPathPatterns("/board/insert.do")
				.addPathPatterns("/user/mypage.do");
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
}
