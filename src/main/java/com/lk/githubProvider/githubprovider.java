package com.lk.githubProvider;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lk.dto.githubDto;
import com.lk.dto.accessToken;
import com.lk.dto.user;
@Component
public class githubprovider {
	public String accessToken(githubDto githubdto){
		MediaType mediaType = MediaType.get("application/json; charset=utf-8");
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(githubdto));
		Request request = new Request.Builder()
		      .url("https://gitee.com/oauth/token")
		      .post(body)
		      .build();
		  try (Response response = client.newCall(request).execute()) {
		    String string = response.body().string();
		    accessToken as=JSON.parseObject(string, accessToken.class);
		    return as.getAccess_token();
		  }catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public user getUser(String access_token){
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
	      .url("https://gitee.com/api/v5/user?access_token="+access_token)
	      .build();
		try {
			Response response = client.newCall(request).execute();
			String string = response.body().string();
		    user as=JSON.parseObject(string, user.class);
		    return as;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
