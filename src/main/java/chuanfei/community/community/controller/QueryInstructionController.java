package chuanfei.community.community.controller;

import chuanfei.community.community.dto.QuestionDTO;
import chuanfei.community.community.model.Instruction;
import chuanfei.community.community.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryInstructionController {

    @Autowired
    private InstructionService instructionService;

    @RequestMapping("/queryOneInstruction")
    public Instruction queryOneInstruction(@RequestParam("orderId") Long orderId){
        return instructionService.getInstruction(orderId);
    }

    @GetMapping("/queryAllInstruction")
    public List<Instruction> queryAllInstruction(){
        List<Instruction> instruction = instructionService.getInstruction();
        return instruction;
    }


}
