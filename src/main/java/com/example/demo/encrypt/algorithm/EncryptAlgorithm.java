package com.example.demo.encrypt.algorithm;

public interface EncryptAlgorithm {
    String encrypt(String original);

    String decrypt(String encrypted);
}
