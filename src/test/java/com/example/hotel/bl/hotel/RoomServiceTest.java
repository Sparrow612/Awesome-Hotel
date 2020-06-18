package com.example.hotel.bl.hotel;

import com.example.hotel.enums.BedType;
import com.example.hotel.enums.BreakFast;
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
        List<HotelRoom> hotelRooms = roomService.retrieveHotelRoomInfoByType(1,RoomType.BigBed);
        HotelRoom hotelRoom = hotelRooms.get(0);
        Assert.assertThat(20,is(hotelRoom.getCurNum()));
        Assert.assertThat("大床房",is(hotelRoom.getRoomType().toString()));
        Assert.assertThat(2,is(hotelRoom.getId()));
        hotelRooms = roomService.retrieveHotelRoomInfoByType(1,RoomType.DoubleBed);
        hotelRoom = hotelRooms.get(0);
        Assert.assertThat(3,is(hotelRoom.getId()));
        Assert.assertThat(30,is(hotelRoom.getCurNum()));
        Assert.assertThat("双床房",is(hotelRoom.getRoomType().toString()));
    }

    private static final HotelRoom hotelRoom = new HotelRoom(){{
        setHotelId(3);
        setCurNum(10);
        setRoomType(RoomType.Luxury);
        setBedType(BedType.Luxury);
        setBreakfast(BreakFast.Porridge);
        setPeopleNum(10);
        setPrice(999);
        setTotal(10);
    }};
    @Test
    @Transactional
    public void insertRoomInfo() {

        roomService.insertRoomInfo(hotelRoom);
        List<HotelRoom> hotelRooms = roomService.retrieveHotelRoomInfoByType(3,RoomType.Luxury);
        HotelRoom hotelRoom = hotelRooms.get(0);
        Assert.assertThat(10,is(hotelRoom.getCurNum()));
        Assert.assertThat("豪华套房",is(hotelRoom.getRoomType().toString()));
    }

    @Test
    @Transactional
    public void updateRoomInfo() {
        roomService.updateRoomInfo(1,"BigBed",10);
        int num = roomService.getRoomCurNum(1,"BigBed");
        Assert.assertThat(10,is(num));
    }

    @Test
    @Transactional
    public void getRoomCurNum() {
        int num = roomService.getRoomCurNum(1,"BigBed");
        Assert.assertThat(20,is(num));
    }

    @Test
    @Transactional
    public void  getRoomCurNumByTime() {
        int curNum = roomService.getRoomCurNumByTime(1,"2020-06-01","2020-06-02",RoomType.BigBed.toString());
        Assert.assertThat(curNum, is(19));
    }
}