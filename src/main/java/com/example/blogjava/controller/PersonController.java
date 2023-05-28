package com.example.blogjava.controller;

import com.example.blogjava.entities.Person;
import com.example.blogjava.mapper.PersonMapper;
import com.example.blogjava.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Person getPerson(@PathVariable("id") Integer id) {
        return personSevice.getPersonId(id);
    }
}
