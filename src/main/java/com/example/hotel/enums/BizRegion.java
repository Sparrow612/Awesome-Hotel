package com.example.hotel.enums;

public enum BizRegion {
    XiDan("西单"),
    XianLin("仙林"),
    GuLou("鼓楼"),
    XinJie("新街口");

    private final String value;

    BizRegion(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
