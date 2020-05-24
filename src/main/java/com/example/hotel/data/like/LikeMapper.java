package com.example.hotel.data.like;

import com.example.hotel.po.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/5/24 10:58
 */
@Mapper
@Repository
public interface LikeMapper {
    int addLike(Like like);

    int annualLike(@Param("id") int likeId);

    List<Like> getHotelLike(@Param("hotelId") int hotelId);

    List<Like> getUserLike(@Param("userId") int userId);


}
