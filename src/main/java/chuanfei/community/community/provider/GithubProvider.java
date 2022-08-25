package chuanfei.community.community.provider;
import chuanfei.community.community.dto.AccessTokenDTO;
import chuanfei.community.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        //利用accessTokenDTO（包含Github返回的code）请求access_token
        Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
//经调试，发现access_token为：
//“access_token=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&scope=user&token_type=bearer"
//我们需要的只是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&scope=user
//先根据&切分后根据=切分即可
            //获取access_token
            String Token = string.split("&")[0].split("=")[1];
            return Token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        //利用获取的accessToken请求Github的user信息
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //获取user信息
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch(Exception e){
        }
        return null;
    }
}
