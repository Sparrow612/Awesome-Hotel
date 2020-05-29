package com.example.hotel.vo;

/**
 * @author huwen
 * @date 2019/3/23
 */
public class UserForm {
    /**
     * 用户邮箱，不可重复
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;

    /**
     * 酒店Id
     */
    private Integer hotelID;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getHotelID() {
        return hotelID;
    }
}
