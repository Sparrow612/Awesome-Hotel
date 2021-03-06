package com.example.hotel.enums;

public enum VIPType {

    Normal("非会员"),
    Client("普通会员"),
    Corporation("企业会员");

    private final String value;

    VIPType (String value) { this.value = value; }

    @Override
    public String toString() {
        return value;
    }

}
