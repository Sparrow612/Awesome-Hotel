package com.example.hotel.bl.VIP;

import com.example.hotel.vo.ResponseVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/6/9 9:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LevelServiceTest {

    @Autowired
    private LevelService levelService;

    @Test
    public void formulateVIPLevel() {
        levelService.formulateVIPLevel(8, 200);
        ResponseVO responseVO = levelService.getTheRequestOfLevel(8);
        Assert.assertEquals(responseVO.getContent(), 200);
    }

    @Test
    public void getTheRequestOfLevel() {
        // same as formulate VIP level
    }
}