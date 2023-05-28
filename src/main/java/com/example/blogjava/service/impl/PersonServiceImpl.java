package com.example.blogjava.service.impl;

import com.example.blogjava.entities.Person;
import com.example.blogjava.mapper.PersonMapper;
import com.example.blogjava.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Autowired
    public RedisTemplate redisTemplate;

    @Resource
    private PersonMapper personMapper;

    @Override
    public void add(Person person) {
        personMapper.insert(person);

    }

    @Override
    public Object getPersonId(Integer id) {
        String key = "perosn_id_" + id;

        Object personObj = redisTemplate.opsForValue().get(key);
        if (personObj == null) {

            synchronized (this.getClass()) {
                personObj = redisTemplate.opsForValue().get(key);
                if (personObj == null) {
                    log.debug("查询数据库");
                    Person person = personMapper.selectByPrimaryKey(id);
                    redisTemplate.opsForValue().set(key, person);
                    return person;
                }
                log.debug("查询redis(同步代码块)--------------");
                return personObj;
            }
        } else {
            log.debug("查询redis--------------");
        }
        return personObj;
    }
}
