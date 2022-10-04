package chuanfei.community.community.service;

import chuanfei.community.community.mapper.DealMapper;
import chuanfei.community.community.mapper.EntrustMapper;
import chuanfei.community.community.mapper.InstructionMapper;
import chuanfei.community.community.model.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InstructionService {

    @Autowired
    private InstructionMapper instructionMapper;

    @Autowired
    private EntrustMapper entrustMapper;

    @Autowired
    private DealMapper dealMapper;



    public void createOrder(Long orderId,
                            String objectId,
                            String market,
                            String objectAbbreviation,
                            Float objectPrice,
                            Integer objectNumber,
                            String direction){

        //单个指令下达
        Instruction instruction = new Instruction();
        Date date  = new Date();

        instruction.setOrderId(orderId);
        instruction.setObjectId(objectId);
        instruction.setMarket(market);
        instruction.setObjectAbbreviation(objectAbbreviation);
        instruction.setObjectPrice(objectPrice);
        instruction.setObjectNumber(objectNumber);
        instruction.setDirection(direction);
        instruction.setTransactionNumber(0);
        instruction.setOrderStatus("新建");
        instruction.setCreateTime(date.toString());
        instruction.setModifyTime(date.toString());
        instruction.setEntrustNumber(0);
        instructionMapper.create(instruction);

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
