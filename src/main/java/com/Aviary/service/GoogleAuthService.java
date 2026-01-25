package com.Aviary.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GoogleAuthService {
    public static String appID = "29908543455-4tlaiuqtlgmv0ed0f92petpt8sf29ium.apps.googleusercontent.com";
    public static String appSecret = "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";
    public static String googleTokenLink = "https://accounts.google.com/o/oauth2/token";
    public static String googleUserInfoLink = "https://accounts.google.com/o/oauth2/token";
    public static String redirectIRL = "http://localhost:8080/Aviary-1.0/google_auth_callback";
    public static String ggGrantType = "authorization_code";

    public static String exchangeCodeForToken(String code){
        HttpPost post = new HttpPost(googleTokenLink);

        List<NameValuePair> params = new ArrayList<>();
        
        params.add(new BasicNameValuePair("client_id", appID));
        params.add(new BasicNameValuePair("client_secret", appSecret));
        params.add(new BasicNameValuePair("redirect_uri", redirectIRL));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("grant_type", ggGrantType));

        post.setEntity(new UrlEncodedFormEntity(params));

        try (CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(post)) {

            String body = EntityUtils.toString(response.getEntity());
            JsonObject jobj = new Gson().fromJson(body, JsonObject.class);
            String accessToken = jobj.get("access_token").toString().replace("\"", "");
            return accessToken;

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getUserEmail(String accessToken) {
        HttpPost post = new HttpPost(googleUserInfoLink);
        
        try(CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(post)) {
            
            String body = EntityUtils.toString(response.getEntity());
            JsonObject jobj = new Gson().fromJson(body, JsonObject.class);
            String email = jobj.get("email").toString();
            return email;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
