package com.andrew.JunitTest.Junit3;

import junit.framework.TestCase;


// 简单搭建 junit3 的测试环境
public class Junit3Demo extends TestCase{

	public void testSave() {
		System.out.println("testSave()");
	}
	
	public void testDestroy() {
		System.out.println("testDestroy");
	}
	
	protected void setUp() throws Exception {
		System.out.println("init");
	}
	
	protected void tearDown() throws Exception {
		System.out.println("dealloc");
	}
	
	
    
}
