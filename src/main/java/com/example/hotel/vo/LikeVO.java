package com.example.hotel.vo;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/5/24 9:30
 */
public class LikeVO {

    private Integer id;
    private Integer userID;
    private Integer hotelID;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return this.userID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getHotelID() {
        return this.hotelID;
    }
}
