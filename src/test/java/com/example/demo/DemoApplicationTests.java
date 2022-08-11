package com.example.demo;

import com.example.demo.mybatis.enums.UserNameEnum;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class DemoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(UserNameEnum.HUWENTAO.name());
		System.out.println(UserNameEnum.WUSHUYAN.name());
		Assert.isTrue(UserNameEnum.HUWENTAO.equals(UserNameEnum.valueOf("HUWENTAO")));
		Assert.isTrue(!UserNameEnum.HUWENTAO.equals(UserNameEnum.valueOf("huwentao")));
	}

}
