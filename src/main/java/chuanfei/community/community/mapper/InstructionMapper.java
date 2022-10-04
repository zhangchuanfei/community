package chuanfei.community.community.mapper;

import chuanfei.community.community.model.Instruction;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructionMapper {

        @Insert("insert into instruction (order_id, object_id, market, object_abbreviation, object_price, object_number, direction, order_status, transaction_number, create_time, modify_time, entrust_number) values (#{orderId}, #{objectId}, #{market}, #{objectAbbreviation}, #{objectPrice}, #{objectNumber}, #{direction}, #{orderStatus}, #{transactionNumber}, #{createTime}, #{modifyTime}, #{entrustNumber})")
        void create(Instruction instruction);

        @Select("select * from instruction where order_id = #{orderId}")
        Instruction findByOrderId(@Param("orderId") Long orderId);

        @Select("select * from instruction")
        List<Instruction> find();

        @Update("update instruction set order_status = #{orderStatus} where order_id = #{orderId}")
        void changeStatus(@Param("orderStatus") String orderStatus, @Param("orderId") Long orderId);

        @Update("update instruction set entrust_number = #{objectNumber} where order_id = #{orderId}")
        void changeEntrustNumber(@Param("objectNumber") Integer objectNumber, @Param("orderId") Long orderId);

        @Update("update instruction set transaction_number = #{transactionNumber} where order_id = #{orderId}")
        void changeNumber(@Param("transactionNumber") Integer transactionNumber, @Param("orderId") Long orderId);

        @Select("select transaction_number from instruction where order_id = #{orderId}")
        Integer findNumber(@Param("orderId") Long orderId);

        @Update("update instruction set modify_time = #{modifyTime} where order_id = #{orderId}")
        void changeModifyTime(@Param("modifyTime") String modifyTime, @Param("orderId") Long orderId);
}
