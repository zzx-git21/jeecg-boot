package org.jeecg.modules.huanxin;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.jeecg.common.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Date;

@Component
@PropertySource({"classpath:application.yml"})
@Configuration
public class HuanXinUtil {



    @Value("${huanxin.uri}")
    private String uri;
    @Value("${huanxin.client_id}")
    private String hx_client_id;
    @Value("${huanxin.client_secret}")
    private String hx_client_Secret;
    private static String hx_access_token = "";
    private static long expire_tiem = 0l;
    private static Date token_start_tiem = new Date();

    private void checkToken() {
        long nowTime = System.currentTimeMillis();
        long expireTime = token_start_tiem.getTime() + expire_tiem;
        if (nowTime >= expireTime){
            getAccessToken();
        }
    }

    public String getAccessToken(){
        String token="";
        JSONObject param = new JSONObject();
        param.put("grant_type", "client_credentials");
        param.put("client_id", hx_client_id);
        param.put("client_secret", hx_client_Secret);

        JSONObject json = HttpClientUtils.httpPost(uri + "token", param, null);
        token = json.getString("access_token");
        expire_tiem = json.getLong("expires_in");
        hx_access_token = "Bearer " + token;
        token_start_tiem = new Date();

        return token;
    }

    /**
     * create huanxin user  0 success 1.UserName repetition  2. other error,please retry
     * @param username
     * @param password
     * @param nickname
     * @return
     */
    public int createUser(String username,String password,String nickname){
        int  i =0;
        checkToken();
        JSONObject param = new JSONObject();
        param.put("username", username);
        param.put("password", password);
        param.put("nickname", nickname);
        JSONObject header = new JSONObject();
        header.put("Authorization", hx_access_token);
        JSONObject json = HttpClientUtils.httpPost(uri + "users", param, header);
        if (json.getInteger("resultCode").equals(HttpStatus.SC_OK)){
            return 0;
        } else if (json.get("resultCode").equals(HttpStatus.SC_UNAUTHORIZED)){
            getAccessToken();
            header = new JSONObject();
            header.put("Authorization", hx_access_token);
            json = HttpClientUtils.httpPost(uri + "users", param, header);
            if (json.getInteger("resultCode").equals(HttpStatus.SC_OK)){
                return 0;
            }
        } else  if (json.get("resultCode").equals(HttpStatus.SC_BAD_REQUEST)){
            return 1;
        } else {
            return 2;
        }
        return i;
    }

    /**
     *  addContacts  0 success 1.此IM用户或被添加的好友不存在  2. other error,please retry
     * @param ownUserName
     * @param friendUserName
     * @return
     */
    public int addContacts(String ownUserName, String friendUserName){
        int  i =0;
        checkToken();

        String url = uri + "users/" + ownUserName + "/contacts/users/" + friendUserName;
        JSONObject header = new JSONObject();
        header.put("Authorization", hx_access_token);

        JSONObject json = HttpClientUtils.httpPost(url, null, header);
        if (json.getInteger("resultCode").equals(HttpStatus.SC_OK)){
            return 0;
        } else if (json.get("resultCode").equals(HttpStatus.SC_UNAUTHORIZED)){
            getAccessToken();
            header = new JSONObject();
            header.put("Authorization", hx_access_token);
            json = HttpClientUtils.httpPost(url, null, header);
            if (json.getInteger("resultCode").equals(HttpStatus.SC_OK)){
                return 0;
            }
        } else  if (json.get("resultCode").equals(HttpStatus.SC_NOT_FOUND)){
            return 1;
        } else {
            return 2;
        }
        return i;
    }


}
