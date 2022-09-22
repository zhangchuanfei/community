package chuanfei.community.community.mapper;

import chuanfei.community.community.model.Instruction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InstructionMapper {

        @Insert("insert into instruction (order_id, object_id, market, object_abbreviation, object_price, object_number, direction, order_status, transaction_number, create_time, modify_time) values (#{orderId}, #{objectId}, #{market}, #{objectAbbreviation}, #{objectPrice}, #{objectNumber}, #{direction}, #{orderStatus}, #{transactionNumber}, #{createTime}, #{modifyTime})")
        void create(Instruction instruction);

        @Select("select * from instruction where order_id = #{orderId}")
        Instruction findByOrderId(@Param("orderId") Long orderId);

        @Select("select * from instruction")
        List<Instruction> find();

}
