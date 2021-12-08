package junit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test1 {
	/*
	 * 어노테이션 기반으로 동작
	 * 
	 * junit4 테스트 순서
	 Before -> Test -> After
	 */
	
	@Before
	public void init() {
		System.out.println("before");
	}
	
	@Test
	public void test() {
		System.out.println("test");
		
	}
	@Test
	public void test2() {
		System.out.println("test2");
	}
	
	@After
	public void after() {
		System.out.println("after");
	}
}
