package com.example.hotel.blImpl.user;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.controller.hotel.HotelController;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: hx
 * @Date: 2020-05-24
 */

public class SearchHotel {
    private final static String FIND_FAIL = "未找到匹配的酒店";

    @Autowired
    private HotelService hotelService;
    private HotelController hotelController;

    private String chechInDate;
    private String checkOutDate;
    private String address;
    private String bizRegion;
    private String hotelStar;  //酒店星级种类为长度为3的bitmap , 类型为String
    private String keyWords;
    private Integer maxPrice = 1000;
    private Integer minPrice = 0;
    private double minScore = 2.5;

List<HotelVO> search(){
    List<HotelVO> hotelVOS = hotelService.retrieveHotels();
    List<HotelVO> targetHotels = null;     //关联度高的hotel
    List<HotelVO> others = null;   //关联度较小的hotel

    for(HotelVO hotel:hotelVOS){    //对所有的hotel，调取房间信息并检验
        ResponseVO response = hotelController.getAvailableRoom(hotel.getId(),chechInDate,checkOutDate);
        hotel = (HotelVO)response.getContent();

        //数据锁定的遍历，得到时间和地址都符合的hotel
        List<RoomVO> rooms = hotel.getRooms();
        if(!rooms.isEmpty())
            if(checkAddress(hotel,address))
                others.add(hotel);
    }

    for(HotelVO hotel:others){
        int score = 0;
        score += checkBizRegion(hotel,bizRegion);
        score += checkKeyWords(hotel,keyWords);
        if(score>6)
            targetHotels.add(hotel);
    }

    return targetHotels;
}

boolean checkAddress(HotelVO hotel,String address){
    if(hotel.getAddress().equals(address))
        return true;
    else
        return false;
}


int checkBizRegion(HotelVO hotel,String bizRegion){
    if(bizRegion == hotel.getBizRegion())
        return 5;
    else
        return 0;
}

int checkKeyWords(HotelVO hotel,String keyWords){
    String[] keyWord = keyWords.split(" ");
    String description = hotel.getDescription();
    int l = keyWord.length;
    int score = 0;
    for(int i=0;i<l;i++){
        if(description.contains(keyWord[i]))
            score++;
    }
    return score;
}

}
