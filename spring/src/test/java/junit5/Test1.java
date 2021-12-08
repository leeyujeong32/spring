package junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test1 {
	@BeforeAll //모든 테스트 전 실행
	static void beforeAll() {
		System.out.println("beforeAll");
	}
	
	@BeforeEach //각 테스트 전 실행
	void beforeEach() {
		System.out.println("BeforeEach");
	}
	
	@AfterAll // 모든 테스트 후 실행
	static void afterAll() {
		System.out.println("AfterAll");
	}
	
	@AfterEach //각 테스트 후 실행
	void afterEach() {
		System.out.println("AfterEach");
	}
	
	@Test
	void test() {
		System.out.println("test");
	}
	@Test
	void test2() {
		System.out.println("test2");
	}
}
