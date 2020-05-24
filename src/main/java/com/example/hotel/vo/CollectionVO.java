package com.example.hotel.vo;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/5/24 9:30
 */
public class CollectionVO {
    private Integer id;
    private Integer userID;
    private Integer hotelID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getHotelID() {
        return this.hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }
}
