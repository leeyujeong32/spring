package junit5;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test2 {

	/*
	 * Assertions : 성공/실패 검증
	 * assertEquals(a,b) : a와 b가 같은지 검증
	 * assertTrue(a) : a가 true인지 검증
	 * assertFalse(a) : a가 false인지 검증
	 * assertNull(a) : a가 null인지 검증
	 * assertNotNull(a) : a가 null이 아닌지 검증
	 * assertArrayEquals(a,b) : a와 b 배열이 같은지 검증
	 * 
	 */
	
	@Test
	void test() {
		String a = "홍길동";
		String b = "홍길동1";
		assertEquals(a,b);
	}
	
	/*
	 * Assumptions : 성공한 경우에만 테스트 실행
	 * assemeTrue() : true인지
	 * assumeEqulas() : 같은지
	 */
	@Test
	void test2() {
		assumeTrue(1 == 2);
		assertEquals("a", "a"); //assumeTrue가 false이므로 미실행
	}

}
