package com.example.blogjava.controller;

import com.example.blogjava.entities.Person;
import com.example.blogjava.mapper.PersonMapper;
import com.example.blogjava.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class PersonController {
    @Autowired
    private PersonService personSevice;

    @PostMapping("/person")
    public String addPerson(Person person) {
        personSevice.add(person);
        return "add success";
    }

    @GetMapping("/person/{id}")
    public Object getPerson(@PathVariable("id") Integer id) {

        ExecutorService es = Executors.newFixedThreadPool(200);
        for (int i = 0; i <= 500; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    personSevice.getPersonId(id);
                }
            });
        }
        return personSevice.getPersonId(id);
    }
}
