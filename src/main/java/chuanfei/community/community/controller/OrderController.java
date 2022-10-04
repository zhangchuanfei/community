package chuanfei.community.community.controller;

import chuanfei.community.community.mapper.UserMapper;
import chuanfei.community.community.model.Question;
import chuanfei.community.community.model.User;
import chuanfei.community.community.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InstructionService instructionService;

    @GetMapping("/instruction")
    public String instruction(){
        return "instruction";
    }

    @PostMapping("/instruction")
    public String doInstruction(
            @RequestParam(value = "orderId", required = false) Long orderId,
            @RequestParam(value = "objectId", required = false) String objectId,
            @RequestParam(value = "market", required = false) String market,
            @RequestParam(value = "objectAbbreviation", required = false) String objectAbbreviation,
            @RequestParam(value = "objectPrice", required = false) Float objectPrice,
            @RequestParam(value = "objectNumber", required = false) Integer objectNumber,
            @RequestParam(value = "direction", required = false) String direction,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("orderId", orderId);
        model.addAttribute("objectId", objectId);
        model.addAttribute("market", market);
        model.addAttribute("objectAbbreviation", objectAbbreviation);
        model.addAttribute("objectPrice", objectPrice);
        model.addAttribute("objectNumber", objectNumber);
        model.addAttribute("direction", direction);


        //确保用户已登录
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user == null){
            model.addAttribute("tips", "错误：用户未登录");
            return "instruction";
        }

        //确保元素不能为空
        if (orderId == null){
            model.addAttribute("tips", "错误：指令编号不能为空");
            return "instruction";
        }
        if (objectId == null || objectId == ""){
            model.addAttribute("tips", "错误：标的编号不能为空");
            return "instruction";
        }
        if (market == null || market == ""){
            model.addAttribute("tips", "错误：交易市场不能为空");
            return "instruction";
        }
        if (objectAbbreviation == null || objectAbbreviation == ""){
            model.addAttribute("tips", "错误：标的简称不能为空");
            return "instruction";
        }
        if (objectPrice == null){
            model.addAttribute("tips", "错误：标的价格不能为空");
            return "instruction";
        }
        if (objectNumber == null){
            model.addAttribute("tips", "错误：标的数量不能为空");
            return "instruction";
        }
        if (direction == null || direction == ""){
            model.addAttribute("tips", "错误：交易方向不能为空");
            return "instruction";
        }
        if (instructionService.getInstruction(orderId) != null){
            model.addAttribute("tips", "错误：指令编号为主键，不能重复！");
            return "instruction";
        }else{
            instructionService.createOrder(orderId, objectId, market, objectAbbreviation, objectPrice, objectNumber, direction);
        }

        //return "redirect:/";
        model.addAttribute("tips", "下达单个指令成功！");
        return "instruction";

    }


}
