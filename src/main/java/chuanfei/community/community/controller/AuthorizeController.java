package chuanfei.community.community.controller;
import chuanfei.community.community.dto.AccessTokenDTO;
import chuanfei.community.community.dto.GithubUser;
import chuanfei.community.community.mapper.UserMapper;
import chuanfei.community.community.model.User;
import chuanfei.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")  //authorize后，Github会重定向回本站点，并返回code和state两个值
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        //设置好获取access_token所需要的AccessTokenDTO
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        //code为Github传回，感觉应该是动态的
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        //根据Github提供的code获取accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //根据access Token再次访问Github，获取user信息
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        if (githubUser != null && githubUser.getId() != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userMapper.insert(user);
            //登录成功，写cookie和session;
            response.addCookie(new Cookie("token", token));

            return "redirect:/";
        }else{
            //重新登录
            return "redirect:/";
        }
    }

}
