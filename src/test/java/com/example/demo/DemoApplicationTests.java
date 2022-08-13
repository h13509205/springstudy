package com.example.demo;

import com.example.demo.mybatis.enums.UserNameEnum;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.sound.midi.Soundbank;
import java.util.Random;

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
	@Test
	void randomTest(){
		System.out.println(new Random().nextInt(100));
		System.out.println(new Random().nextInt(100));
		System.out.println(new Random().nextInt(100));
		System.out.println(new Random().nextInt(100));
	}

}
