package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelSearchService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.controller.hotel.HotelController;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;
import com.example.hotel.vo.SearchBodyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: hx
 * @Date: 2020-05-24
 */

class Node{
    HotelVO hotel;
    int score;
    Node before = null;
    Node next = null;

    Node(HotelVO hotel, int score){
        this.hotel = hotel;
        this.score = score;
    }

}

@Service
public class HotelSearchServiceImpl implements HotelSearchService {

    @Autowired
    private HotelService hotelService;

    private String checkInDate = null;
    private String checkOutDate = null;
    private String address = null;
    private String bizRegion = null;
    private String[] hotelStar = null;
    private String[] keyWords = null;
    private double maxPrice = 999;
    private double minScore = 0;

    private Node head;


    @Override
    public List<HotelVO> searchHotel(SearchBodyVO searchBodyVO) {
        setInfo(searchBodyVO);
        return search();
    }



    private void setInfo(SearchBodyVO searchBodyVO){
        checkInDate = searchBodyVO.getCheckInDate();
        checkOutDate = searchBodyVO.getCheckOutDate();
        address = searchBodyVO.getAddress();
        bizRegion = searchBodyVO.getBizRegion();
        hotelStar = searchBodyVO.getHotelStar();
        keyWords = searchBodyVO.getKeyWords();
        maxPrice = searchBodyVO.getMaxPrice();
        minScore = searchBodyVO.getMinScore();
    }


    /**
     * 搜索，以 List<HotelVO> 格式返回符合要求的酒店
     * @return
     */
    private List<HotelVO> search(){
        List<HotelVO> hotelVOS = hotelService.retrieveHotels();

        List<HotelVO> targetHotels = new ArrayList<>();     //以关联度由高到底排序

        for(HotelVO hotel:hotelVOS){    //对所有的hotel，调取房间信息并检验
            hotel = hotelService.retrieveHotelDetails(hotel.getId());



            /*
             * judge为Boolean变量
             * 对表单中刚性条件进行判定，如果某项不符合则为false
             * 如果符合，则酒店进入候选池,并进行相关度评分
             * 候选池中酒店信息以双链表方式存储
             *
             * 检验内容包括：
             * 1.酒店地址是否符合
             * 2.酒店价格是否在可接受区间内
             * 3。酒店星级是否符合要求
             * 4.酒店评分是否符合要求
             *
             */
            boolean judge = checkAddress(hotel,address) && checkPrice(hotel,maxPrice)
                    && checkHotelStar(hotel,hotelStar) && checkHotelScore(hotel, minScore)&&checkBizRegion(hotel, bizRegion);
            if(judge){
                int score = 0;
                score += checkKeyWords(hotel,keyWords);
                Node node = new Node(hotel,score);
                insertHotel(node);
            }
        }
        if (head!=null) {
            Node ptr = head;
            while (ptr != null) {
                targetHotels.add(ptr.hotel);
                ptr = ptr.next;
            }
        }

        head = null;
        //if(targetHotels.size()==0){
            //targetHotels = secondarySearch();
       // }
        return targetHotels;
    }

//    private List<HotelVO> secondarySearch(){
//        return null;
//    }


    /**
     * 刚性条件
     * 检查价格是否符合要求
     *
     */
    private boolean checkPrice(HotelVO hotel, double maxPrice){
        double hotelPrice = hotel.getMinPrice();
        return maxPrice >= hotelPrice;
    }

    /**
     * 刚性条件
     * 检查地址是否符合要求
     *
     */
    private boolean checkAddress(HotelVO hotel,String address){
        return hotel.getAddress().contains(address);
    }


    /**
     * 刚性条件
     * 检查酒店的得分是否符合顾客要求
     * @param hotel
     * @param minScore
     * @return
     */
    private boolean checkHotelScore(HotelVO hotel, double minScore){
        double hotelScore = hotel.getRate();
        return hotelScore >= minScore;
    }


    /**
     * 刚性条件
     * 检查酒店星级,使用bitmap，从左到右三位分别表示“三星级”“四星级”“五星级”，0为false，1为true
     * ！如果顾客没有填写偏好，前端需传回 111 ！
     * @param hotel
     * @param hotelStar
     * @return
     */
    private boolean checkHotelStar(HotelVO hotel,String[] hotelStar){
        String star = hotel.getHotelStar();
        boolean judge = false;
        for (String s : hotelStar) {
            judge = judge || s.equals(star);
        }

        return judge;
    }


    /**
     * 刚性条件
     * 检查商圈是否符合要求，如果符合
     */
    private boolean checkBizRegion(HotelVO hotel,String bizRegion){
        return bizRegion.equals(hotel.getBizRegion());
    }


    /**
     * 非刚性条件
     * 检查关键字，每个关键字加2分
     * 如果没有对关键字进行检索，默认返回0分
     * @param hotel
     * @param keyWords
     * @return
     */
    private int checkKeyWords(HotelVO hotel,String[] keyWords){
        if(keyWords.length==0)
            return 0;
        else{
            String description = hotel.getDescription();
            int l = keyWords.length;
            int score = 0;
            for (String keyWord : keyWords) {
                if (description.contains(keyWord))
                    score += 2;
            }
            return score;
        }
    }


    /**
     * 在链表中插入酒店
     * 插入的同时按分数由高到低排序
     * @param hotel
     */
    private void insertHotel(Node hotel){
        if(head==null){
            head = hotel;
        }else {
            Node ptr = head;
            while (true) {
                if(ptr.score < hotel.score){
                    if(ptr==head){
                        hotel.next = ptr;
                        ptr.before = hotel;
                        head = hotel;
                        break;
                    }else{
                        ptr.before.next = hotel;
                        hotel.before = ptr.before;

                        ptr.before = hotel;
                        hotel.next = ptr;
                        break;
                    }
                }else if(ptr.next==null){
                    ptr.next = hotel;
                    break;
                }else{
                    ptr = ptr.next;
                }
            }
        }
    }


}
