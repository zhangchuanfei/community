package chuanfei.community.community.controller;

import chuanfei.community.community.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructionController {

    @Autowired
    private InstructionService instructionService;

    @RequestMapping(value ="/order")
    public String order (@RequestParam("orderId") Long orderId,
                         @RequestParam("objectId") String objectId,
                         @RequestParam("market") String market,
                         @RequestParam("orderAbbreviation") String orderAbbreviation,
                         @RequestParam("objectPrice") Float objectPrice   ,
                         @RequestParam("objectNumber") Integer objectNumber,
                         @RequestParam("direction") String direction
                         ){

        instructionService.createOrder(orderId, objectId, market, orderAbbreviation, objectPrice, objectNumber, direction);
        System.out.println("市场是" + market);
        return "成功啦!";
    }

}
