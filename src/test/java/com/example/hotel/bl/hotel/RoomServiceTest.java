package com.example.hotel.bl.hotel;

import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/6/7 10:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;


    @Test
    @Transactional
    public void retrieveHotelRoomInfo() {
        List<HotelRoom> hotelRooms = roomService.retrieveHotelRoomInfo(1);
        Assert.assertThat(hotelRooms.size(),is(3));
    }

    @Test
    @Transactional
    public void retrieveHotelRoomInfoByType() {

    }

    @Test
    @Transactional
    public void insertRoomInfo() {
    }

    @Test
    @Transactional
    public void updateRoomInfo() {
    }

    @Test
    @Transactional
    public void getRoomCurNum() {
    }

    @Test
    @Transactional
    public void  getRoomCurNumByTime() {
        int curNum = roomService.getRoomCurNumByTime(1,"2020-06-01","2020-06-02","大床房");
        Assert.assertThat(curNum, is(19));
    }
}