package com.imooc.entity;

import com.sun.xml.internal.bind.v2.runtime.XMLSerializer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.ApplicationTests;
import com.imooc.repository.UserRepository;

/**
 * Created by 瓦力.
 */
public class UserRepositoryTest extends ApplicationTests {
    @Autowired
    private UserRepository userRepository;
        @Test
    public void testOne(){
            Iterable<User> all = userRepository.findAll();
            String ss = "22";
        }

}
