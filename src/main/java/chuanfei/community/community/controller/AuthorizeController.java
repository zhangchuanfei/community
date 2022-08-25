package chuanfei.community.community.controller;
import chuanfei.community.community.dto.AccessTokenDTO;
import chuanfei.community.community.dto.GithubUser;
import chuanfei.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id")
    private String clientId;
    @Value("${github.client.secret")
    private String clientSecret;
    @Value("${github.redirect.uri")
    private String redirectUri;

    @GetMapping("/callback")  //authorize后，Github会重定向回本站点，并返回code和state两个值
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){

        //设置好获取access_token所需要的AccessTokenDTO
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        //code为Github传回，感觉应该是动态的
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("e6766c1128a93a1e53ee");
        accessTokenDTO.setClient_secret("7000c202f0e098e1c913032f91c2a0e3ff770100");
        accessTokenDTO.setRedirect_uri("http://localhost:8888/callback");
        //根据Github提供的code获取accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        return "index";
    }

}
