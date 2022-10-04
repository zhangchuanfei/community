package chuanfei.community.community.mapper;

import chuanfei.community.community.model.Deal;
import chuanfei.community.community.model.Instruction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DealMapper {

    @Insert("insert into deal (entrust_id, order_id, deal_price, deal_number, deal_time) values (#{entrustId}, #{orderId}, #{dealPrice}, #{dealNumber}, #{dealTime})")
    void create(Deal deal);
}
