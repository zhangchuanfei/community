package chuanfei.community.community.controller;

import chuanfei.community.community.model.Instruction;
import chuanfei.community.community.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class QueryOneInstructionController {

    @Autowired
    private InstructionService instructionService;

    @GetMapping("/queryOneInstruction")
    public String instruction(){

        return "queryInstruction";
    }

    @PostMapping("/queryOneInstruction")
    public String queryOneInstruction(
            @RequestParam(value = "orderId", required = false) Long orderId,
            Model model
    ){
        Instruction instruction = instructionService.getInstruction(orderId);
        if (instruction == null){
            model.addAttribute("error", "查询不到对应的单个指令");
        }else{
            model.addAttribute("orderId", orderId);
            model.addAttribute("objectId", instruction.getObjectId());
            model.addAttribute("market", instruction.getMarket());
            model.addAttribute("objectAbbreviation", instruction.getObjectAbbreviation());
            model.addAttribute("objectPrice", instruction.getObjectPrice());
            model.addAttribute("objectNumber", instruction.getObjectNumber());
            model.addAttribute("direction", instruction.getDirection());
            model.addAttribute("orderStatus", instruction.getOrderStatus());
            model.addAttribute("transactionNumber", instruction.getTransactionNumber());
            model.addAttribute("createTime", instruction.getCreateTime());
            model.addAttribute("modifyTime", instruction.getModifyTime());
            model.addAttribute("entrustNumber", instruction.getEntrustNumber());
        }
        return "queryInstruction";
    }
}
