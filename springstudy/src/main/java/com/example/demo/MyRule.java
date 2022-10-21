package com.example.demo;

import com.example.MyAnnotation;

public class MyRule {
    @MyAnnotation
    public boolean test(Integer i){
        return i%2 ==0;
    }
}
