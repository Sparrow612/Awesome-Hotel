package com.example.hotel.data.collection;

import com.example.hotel.po.Collection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 庄子元 181830266@smail.nju.edu.cn
 * @date 2020/5/24 10:57
 */

@Repository
@Mapper
public interface CollectionMapper {
    int addCollection(Collection collection);

    int annualCollection(@Param("id") int id);

    int getUserCollection(@Param("userId") int userId);

    int getHotelCollection(@Param("hotelId") int hotelId);
}
