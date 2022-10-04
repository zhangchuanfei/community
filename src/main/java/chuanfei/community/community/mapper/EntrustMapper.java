package chuanfei.community.community.mapper;

import chuanfei.community.community.model.Entrust;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EntrustMapper {

    @Insert("insert into entrust (order_id, single_entrust_price, single_entrust_number, entrust_time) values (#{orderId}, #{singleEntrustPrice}, #{singleEntrustNumber}, #{entrustTime})")
    void create(Entrust entrust);

    @Select("select * from entrust where order_id = #{orderId}")
    List<Entrust> findByOrderId(@Param("orderId") Long orderId);

    @Select("select * from entrust where order_id = #{orderId}")
    Entrust findEntrust(@Param("orderId") Long orderId);

    @Select("select * from entrust where entrust_id = #{entrustId}")
    Entrust findByEntrustId(@Param("entrustId") Integer entrustId);

    @Select("select entrust_id from entrust where order_id = #{orderId}")
    Integer findEntrustId(@Param("orderId") Long orderId);
}
