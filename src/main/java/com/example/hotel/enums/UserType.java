package com.example.hotel.enums;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */
public enum UserType {
    Client("1"),
    HotelManager("2"),
    Manager("3"),
    SalesPerson("4");

    private final String value;

    UserType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    } // 这里似乎并没有用上
}
