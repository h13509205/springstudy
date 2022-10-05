package com.example.demo;

import com.example.demo.mybatis.enums.UserNameEnum;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;

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

	@Test
	void expressionTest(){
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("new java.util.Random().nextInt(100)");
		System.out.println(expression.getValue());
		Expression expression2 = parser.parseExpression("0 > null");
		System.out.println(expression2.getValue(Integer.class));
	}

}
