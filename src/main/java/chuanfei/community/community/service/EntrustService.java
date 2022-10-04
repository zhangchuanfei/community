package chuanfei.community.community.service;

import chuanfei.community.community.mapper.EntrustMapper;
import chuanfei.community.community.mapper.InstructionMapper;
import chuanfei.community.community.model.Entrust;
import chuanfei.community.community.model.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EntrustService {

    @Autowired
    private InstructionMapper instructionMapper;

    @Autowired
    private EntrustMapper entrustMapper;

    public String create(Long orderId
    ){

        Instruction instruction = instructionMapper.findByOrderId(orderId);
        if (instruction == null){
            return "委托失败，没有对应的单个指令！";
        }else if (!instruction.getOrderStatus().equals("新建")){
            return "委托失败，对应的单个指令已经委托！";
        }
        //下达委托指令
        Entrust entrust = new Entrust();
        Date date = new Date();
        entrust.setOrderId(orderId);
        entrust.setSingleEntrustPrice(instruction.getObjectPrice());     //因为是限价交易，所以价格和单个指令一样
        entrust.setSingleEntrustNumber(instruction.getObjectNumber());
        entrust.setEntrustTime(date.toString());
        entrustMapper.create(entrust);
        //修改指令状态为已报
        instructionMapper.changeStatus("已报", orderId);
        instructionMapper.changeEntrustNumber(instruction.getObjectNumber(), orderId);

        return "委托成功!";
    }

    public List<Entrust> getEntrust(Long orderId){

        List<Entrust> entrust = entrustMapper.findByOrderId(orderId);
        return entrust;
    }

    public Entrust getEntrust(Integer entrustId){

        Entrust entrust = entrustMapper.findByEntrustId(entrustId);
        return entrust;
    }
}
