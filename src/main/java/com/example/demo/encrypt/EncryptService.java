package com.example.demo.encrypt;

import com.example.demo.encrypt.algorithm.EncryptAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

    public String encrypt(String original, AlgorithmTypes algorithmTypes){
        EncryptAlgorithm algorithm = AlgorithmFactory.choose(algorithmTypes);
        return algorithm.encrypt(original);
    }
}
