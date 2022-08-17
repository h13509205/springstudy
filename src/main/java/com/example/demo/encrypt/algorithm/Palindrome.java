package com.example.demo.encrypt.algorithm;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class Palindrome implements EncryptAlgorithm{
    @Override
    public String encrypt(String original) {
        Assert.isTrue(StringUtils.hasText(original));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < original.length(); i++) {
            stringBuilder.append(original.charAt(original.length()-i-1));
        }
        return stringBuilder.toString();
    }

    @Override
    public String decrypt(String encrypted) {
        Assert.isTrue(StringUtils.hasText(encrypted));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            stringBuilder.append(encrypted.charAt(encrypted.length()-i-1));
        }
        return stringBuilder.toString();
    }

}
