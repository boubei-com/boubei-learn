package com.boubei.learn.ysy;

import junit.framework.Assert;

import org.junit.Test;

public class UserTest {
	
	@Test
	public void test1() {
		
		User user = new User();
		user.setName("YangSY");
		user.setAge(25);
		
		Assert.assertEquals("YangSY", user.getName());
		Assert.assertEquals(24, user.getAge());
	}

}
