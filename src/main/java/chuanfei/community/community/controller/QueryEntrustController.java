package chuanfei.community.community.controller;

import chuanfei.community.community.model.Entrust;
import chuanfei.community.community.service.EntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryEntrustController {
    @Autowired
    private EntrustService entrustService;

    @RequestMapping("/queryEntrustByOrderId")
    public List<Entrust> queryEntrustByOrderId(@RequestParam("orderId") Long orderId){
        return entrustService.getEntrust(orderId);
    }

    @RequestMapping("/queryEntrustByEntrustId")
    public Entrust queryEntrustByEntrustId(@RequestParam("entrustId") Integer entrustId){
        return entrustService.getEntrust(entrustId);
    }

}
