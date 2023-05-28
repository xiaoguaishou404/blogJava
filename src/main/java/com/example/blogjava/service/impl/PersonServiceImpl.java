package com.example.blogjava.service.impl;

import com.example.blogjava.entities.Person;
import com.example.blogjava.mapper.PersonMapper;
import com.example.blogjava.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersonMapper personMapper;

    @Override
    public void add(Person person) {
        personMapper.insert(person);

    }

    @Override
    public Person getPersonId(Integer id) {
        return personMapper.selectByPrimaryKey(id);
    }
}
