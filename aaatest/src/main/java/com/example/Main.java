package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @MyAnnotation
    public boolean test(Integer i){
        return i%2 ==0;
    }
}