package com.example.blogjava.entities;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ConfigurationProperties(prefix = "author")
public class Author {
    private String age;

    private String[] getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(String[] hobbyList) {
        this.hobbyList = hobbyList;
    }

    private String[] hobbyList;

    public String getAge() {
        return age;
    }


    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Author{" +
                "age='" + age + '\'' +
                ", hobbyList=" + Arrays.toString(hobbyList) +
                '}';
    }
}
