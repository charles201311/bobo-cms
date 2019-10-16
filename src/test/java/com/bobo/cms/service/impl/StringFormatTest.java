package com.bobo.cms.service.impl;

import org.junit.Test;

public class StringFormatTest {
	
	
	@Test
	public void test1() {
		
		String url ="/admin/users?username=123";
		
		 String string = String.format("%1$s%2$s", "abc","123");
		 System.out.println(string);
		
	}

}
