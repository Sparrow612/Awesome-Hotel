package com.example.hotel.vo;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/4/29 9:55
 */
public class BirthdayCouponVO extends CouponVO {
    private Integer hotelId;
    private Integer discountMoney;

    public Integer getHotelId() {
        return this.hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getDiscountMoney() {
        return this.discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }
}
