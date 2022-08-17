package com.example.demo.encrypt;

import com.example.demo.encrypt.algorithm.EncryptAlgorithm;
import com.example.demo.encrypt.algorithm.Palindrome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AlgorithmFactory {
    private static Map<AlgorithmTypes, EncryptAlgorithm> encryptAlgorithmMap = new HashMap<>();
    static {
        encryptAlgorithmMap.put(AlgorithmTypes.Palindrome, new Palindrome());
    }

    public static EncryptAlgorithm choose(AlgorithmTypes algorithmTypes){
        return encryptAlgorithmMap.get(algorithmTypes);
    }

    public static EncryptAlgorithm choose(String algorithmTypes){
        if(Arrays.stream(AlgorithmTypes.values()).anyMatch(e -> e.name().equals(algorithmTypes))){
            return choose(AlgorithmTypes.valueOf(algorithmTypes));
        }
        throw new RuntimeException("no support algorithm");
    }
}
