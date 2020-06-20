package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.VIP.VIPMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.CorpVIP;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/5/30 16:13
 */
@Service
public class CorporateCouponStrategyImpl implements CouponMatchStrategy {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    VIPMapper vipMapper;

    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        try {
            User user = accountMapper.getAccountById(orderVO.getUserId());
            if (user.getCorporation() == null) {
                return false;
            }
            CorpVIP corpVIP = vipMapper.getVIPbyCorpName(user.getCorporation());
            return coupon.getStatus() == 1 &&
                    coupon.getCorporateName().equals(user.getCorporation()) &&
                    corpVIP.getStatus() == 1 &&
                    (coupon.getSrcId() == WEBSITE || coupon.getSrcId().equals(orderVO.getHotelId()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
