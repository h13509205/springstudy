package com.example.demo;

import com.example.demo.mybatis.enums.UserNameEnum;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class DemoApplicationTests {

	@Test
	void contextLoads() {

	}
	@Test
	void enumTest(){
		System.out.println(UserNameEnum.HUWENTAO.name());
		System.out.println(UserNameEnum.WUSHUYAN.name());
		Assert.isTrue(UserNameEnum.HUWENTAO.equals(UserNameEnum.valueOf("HUWENTAO")));
		Assert.isTrue(UserNameEnum.HUWENTAO.equals(UserNameEnum.getEnum("HUWENTAO")));
		Assert.isTrue(UserNameEnum.HUWENTAO.equals(UserNameEnum.getEnum("huwentao")));
	}

}
