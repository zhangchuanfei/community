package chuanfei.community.community.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller是指允许Controller接收前端请求
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "Index";
    }
}

