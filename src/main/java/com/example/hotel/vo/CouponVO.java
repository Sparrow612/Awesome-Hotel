package com.example.hotel.vo;

public class CouponVO {
    private Integer id;
    private String description;
    private Integer status;
    private String name;
    private Integer type;
    private Integer hotelId;
    private double discount;
    private String startTime;
    private String endTime;
    private Integer discountMoney;
    private Integer targetRoomNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    /**
     * 1生日特惠 2多间特惠 3满减优惠 4限时优惠
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer id) {
        this.hotelId = id;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String start_time) {
        this.startTime = start_time;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String end_time) {
        this.endTime = end_time;
    }

    public Integer getDiscountMoney() {
        return this.discountMoney;
    }

    public void setDiscountMoney(int discount) {
        this.discountMoney = discount;
    }

    public Integer getTargetRoomNum() {
        return this.targetRoomNum;
    }

    public void setTargetRoomNum(Integer targetRoomNum) {
        this.targetRoomNum = targetRoomNum;
    }
}
