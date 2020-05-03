package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种限时优惠策略
     *
     * @param orderVO 订单
     * @return 是否匹配
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        try {
            String couponStartTime = coupon.getStartTime().toString().split("T")[0];
            String couponEndTime = coupon.getEndTime().toString().split("T")[0];
            return (coupon.getHotelId() == -1 || coupon.getHotelId().equals(orderVO.getHotelId())) &&
                    coupon.getCouponType() == 4 &&
                    coupon.getStatus() == 1 &&
                    timeBefore(couponStartTime, orderVO.getCheckInDate()) &&
                    timeBefore(orderVO.getCheckOutDate(), couponEndTime);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean timeBefore(String src, String dest) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ds = formatter.parse(src);
            Date dd = formatter.parse(dest);
            return ds.before(dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
