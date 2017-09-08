package com.andrew.JunitTest.test;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class MathTest {

	// 依赖关系  依赖 MathImpl 对象
	// IOP
	private IMath math = new MathImpl();

	@Test
	public void testGetSum() {
		int sum = math.getSum(2,3);
		Assert.assertEquals("断言失败", 5, sum);
//		Assert.assertSame();	// 比较对象是否相等
	}

	// 断言该方法报 ArithmeticException 异常
	// 报错 断言成功，不报错，断言失败
	@Test(expected = ArithmeticException.class)
	public void testDivide() {
		int result = math.divide(12, 0);
	}

	// 断言该方法在 2000 ms 内完成
	@Test(timeout = 2000)
	public void timeOutTest() {
		long begin = System.currentTimeMillis();
		String str = new String();
		for (int i = 0; i < 30000; i ++) {
			str += i;
		}
		System.out.println(System.currentTimeMillis() - begin);
	}
}
