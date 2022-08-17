package com.example.demo;

import com.example.demo.encrypt.algorithm.Palindrome;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class AlgorithmTest {

    @Test
    public void testPalindrome(){
        String password = "123456";
        Palindrome palindrome = new Palindrome();
        Assert.isTrue("654321".equals(palindrome.encrypt(password)));
    }
}
