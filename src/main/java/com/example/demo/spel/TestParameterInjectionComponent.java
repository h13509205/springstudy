package com.example.demo.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.example.demo.spel")
public class TestParameterInjectionComponent {

    @Autowired
    public TestParameterInjectionComponent(@Value("#{systemProperties['user.language']}") String language){
        this.language = language;
    }

    private String language;

    @Value("#{systemProperties['user.language']}")
    private String locale;

    private String name;

    @Value("#{systemProperties['user.name']}")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestParameterInjectionComponent{" +
                "language='" + language + '\'' +
                ", locale='" + locale + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
