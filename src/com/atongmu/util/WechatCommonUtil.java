package com.atongmu.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;

//
public class WechatCommonUtil {
	//
	 private static String appid = "wx41d42d9b4c41ea95";
	 //
	 private static String appsecret = "fb39eca74aab6b7e6aedd42543ff2db1";
	 //
	 private static String oauth_url_static = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	 private static String oauth_url_static_getUser = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	 private static String oauth_url_static_refush_User = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

	//private static Logger log = Logger.getLogger(CommonUtil.class);
	public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr) {
		CommonUtil.logger.info("【mobile】"+"into WechatCommonUtil,httpsRequestToJsonObject");
		JSONObject jsonObject = null;
		try {
			StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			CommonUtil.logger.error("【mobile】"+ce.getStackTrace());
		} catch (Exception e) {
			CommonUtil.logger.error("【mobile】"+e.getStackTrace());
		}
		return jsonObject;
	 }

	 private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)throws ConnectException{
			CommonUtil.logger.info("【mobile】"+"into WechatCommonUtil,httpsRequest");
		 StringBuffer buffer = new StringBuffer();
		 try{
			 URL url = new URL(requestUrl);
			 HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			 connection.setDoOutput(true);
			 connection.setDoInput(true);
			 connection.setUseCaches(false);
			 connection.setRequestMethod(requestMethod);
			  if (null != output) {
				  OutputStream outputStream = connection.getOutputStream();
				  outputStream.write(output.getBytes("UTF-8"));
				  outputStream.close();
			  }
			  //
			  InputStream inputStream = connection.getInputStream();
			  InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			  BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			  String str = null;
			  while ((str = bufferedReader.readLine()) != null) {
				  buffer.append(str);
			  }
			  bufferedReader.close();
			  inputStreamReader.close();
			  inputStream.close();
			  inputStream = null;
			  connection.disconnect();
		 }catch(Exception e){
			CommonUtil.logger.error("【mobile】"+e.getStackTrace());
		 }
		 return buffer;

	 }
	 //这里是获取openid的
	public static String[] getOpenID(String code) {
		CommonUtil.logger.info("【mobile】"+"into WechatCommonUtil,getOpenID");
		String[] userinfo =new String[3];
		String oauth_url = oauth_url_static.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE",String.valueOf(code));
		JSONObject jsonObject = WechatCommonUtil.httpsRequestToJsonObject(oauth_url, "POST", null);
		Object errorCode = jsonObject.get("errcode");
		if (errorCode != null) {
			CommonUtil.logger.error("【mobile】"+"get openid fail!errorCode:"+errorCode);
		} else {
			userinfo[0] = jsonObject.getString("openid");
			userinfo[1] = jsonObject.getString("access_token");
			userinfo[2] = jsonObject.getString("refresh_token");
		}
		CommonUtil.logger.info("【mobile】"+"成功获取openid");
		return userinfo;
	}
	//这里是通过openid和access_token来获取用户信息的
	public static String[] getWechatUserInfo(String access_token,String openid) {
		CommonUtil.logger.info("【mobile】"+"into WechatCommonUtil,getWechatUserInfo");
		String[] userinfo =new String[6];
		String oauth_url = oauth_url_static_getUser.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		JSONObject jsonObject = WechatCommonUtil.httpsRequestToJsonObject(oauth_url, "POST", null);
		Object errorCode = jsonObject.get("errcode");
		if (errorCode != null) {
			CommonUtil.logger.error("【mobile】"+"get userinfo fail!errorCode:"+errorCode);
		} else {
			userinfo[0] =jsonObject.getString("nickname");
			userinfo[1] =jsonObject.getString("sex");
			userinfo[2] =jsonObject.getString("province");
			userinfo[3] =jsonObject.getString("city");
			userinfo[4] =jsonObject.getString("country");
			userinfo[5] =jsonObject.getString("headimgurl");
		}
		CommonUtil.logger.info("【mobile】"+"成功获取"+userinfo[0]+"的资料");
		return userinfo;
	}
	//通过refresh_token刷新access_token
	public static String[] getRefushUser(String refresh_token) {
		CommonUtil.logger.info("【mobile】"+"into WechatCommonUtil,getRefushUser");
		String[] userinfo =new String[3];
		String oauth_url = oauth_url_static_refush_User.replace("APPID", appid).replace("REFRESH_TOKEN", refresh_token);
		JSONObject jsonObject = WechatCommonUtil.httpsRequestToJsonObject(oauth_url, "POST", null);
		Object errorCode = jsonObject.get("errcode");
		if (errorCode != null) {
			CommonUtil.logger.error("【mobile】"+"refush user token fail!errorCode:"+errorCode);
		} else {
			userinfo[0] = jsonObject.getString("openid");
			userinfo[1] = jsonObject.getString("access_token");
			userinfo[2] = jsonObject.getString("refresh_token");
		}
		CommonUtil.logger.info("【mobile】"+"成功刷新refrech_token");
		return userinfo;
	}
}
