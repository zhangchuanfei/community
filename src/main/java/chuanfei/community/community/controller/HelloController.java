package chuanfei.community.community.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller是指允许Controller接收前端请求
@Controller
public class HelloController {
    @GetMapping("/helloo")
    public String hello(@RequestParam(name = "name") String name,  Model model){
        model.addAttribute("name", name);
        return "hello";
    }
}
