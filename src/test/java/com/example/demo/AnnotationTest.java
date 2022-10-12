package com.example.demo;

import com.example.demo.spel.SpelStudy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class AnnotationTest {

    @Test
    public void test(){
        System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        Configuration configuration = SpelStudy.class.getAnnotation(Configuration.class);

    }
}
