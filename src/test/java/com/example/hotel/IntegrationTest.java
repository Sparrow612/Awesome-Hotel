package com.example.hotel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/6/25 15:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

    @Test
    @Transactional
    public void orderTest() {

    }

}
