package chuanfei.community.community.controller;

import chuanfei.community.community.model.Entrust;
import chuanfei.community.community.model.Instruction;
import chuanfei.community.community.service.EntrustService;
import chuanfei.community.community.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryEntrustController {
    @Autowired
    private EntrustService entrustService;

    @RequestMapping("/queryEntrust")
    public List<Entrust> queryEntrust(@RequestParam("orderId") Long orderId){
        return entrustService.getEntrust(orderId);
    }

}
