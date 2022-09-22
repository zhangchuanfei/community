package chuanfei.community.community.service;

import chuanfei.community.community.mapper.EntrustMapper;
import chuanfei.community.community.mapper.InstructionMapper;
import chuanfei.community.community.model.Entrust;
import chuanfei.community.community.model.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructionService {

    @Autowired
    private InstructionMapper instructionMapper;

    @Autowired
    private EntrustMapper entrustMapper;

    public void createOrder(Long orderId,
                            String objectId,
                            String market,
                            String orderAbbreviation,
                            Float objectPrice,
                            Integer objectNumber,
                            String direction){

        Instruction instruction = new Instruction();
        instruction.setOrderId(orderId);
        instruction.setObjectId(objectId);
        instruction.setMarket(market);
        instruction.setObjectAbbreviation(orderAbbreviation);
        instruction.setObjectPrice(objectPrice);
        instruction.setObjectNumber(objectNumber);
        instruction.setDirection(direction);
        instruction.setTransactionNumber(0);
        instruction.setOrderStatus("未成交");
        instruction.setCreateTime(System.currentTimeMillis());
        instruction.setModifyTime(instruction.getCreateTime());
        instructionMapper.create(instruction);

        Entrust entrust = new Entrust();
        for (int i = 0; i < 2; i++){
            entrust.setOrderId(orderId);
            entrust.setSingleEntrustPrice(objectPrice);
            entrust.setSingleEntrustNumber(objectNumber / 2);
            entrust.setEntrustTime(System.currentTimeMillis());
            entrustMapper.create(entrust);
        }





    }

    public Instruction getInstruction(Long orderId){

        Instruction instruction = instructionMapper.findByOrderId(orderId);
        return instruction;
    }

    public List<Instruction> getInstruction() {

        List<Instruction> instructions = instructionMapper.find();
        return instructions;



    }
}
