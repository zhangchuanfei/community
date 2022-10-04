package chuanfei.community.community.controller;

import chuanfei.community.community.service.DealService;
import chuanfei.community.community.service.EntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EntrustController {

    @Autowired
    private EntrustService entrustService;

    @Autowired
    private DealService dealService;

    @GetMapping("/entrust")
    public String entrust(){
        return "entrust";
    }

    @PostMapping("/entrust")
    public String doEntrust( @RequestParam(value = "orderId", required = false) Long orderId,
            Model model
    ){
        model.addAttribute("orderId", orderId);
        String result = entrustService.create(orderId);
        model.addAttribute("tips", result);
        if ("委托成功!".equals(result)){
            dealService.create(orderId);
        }
        return "entrust";
    }
}
