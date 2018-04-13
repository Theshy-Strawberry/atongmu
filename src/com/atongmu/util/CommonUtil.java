package com.atongmu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.atongmu.bean.Tbl_PayCallBackInfo;
import com.atongmu.bean.Tbl_PayInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONObject;

//
public class CommonUtil {
	public static Logger logger = Logger.getLogger(CommonUtil.class);
	//商户ID
	public static String mch_id="1323842901";
	//微信支付url
	public static String notify_url = "http://www.in-artoon.com/atongmu/WePayBackInfoServlet";
	//获取openId
	public static String oauth_url = "https://api.weixin.qq.com/sns/oauth/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	//微信ID
	public static String appid = "wx41d42d9b4c41ea95";
	//微信密钥
	public static String appsecret = "fb39eca74aab6b7e6aedd42543ff2db1";
	//微信支付key
	public static String key = "Z20160713X1301VATM75823765231NAB";
	//
	public static String oauth_url_static = "https://api.weixin.qq.com/sns/oauth/access_token?appid=wx41d42d9b4c41ea95&secret=SECRET&code=CODE&grant_type=authorization_code";

	/**
	 * 转JSON
	 *
	 * @param requestUrl，requestMethod，outputStr
	 * @return
	 */
	public static JSONObject httpsRequestToJsonObject(String requestUrl,
			String requestMethod, String outputStr) {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,httpsRequestToJsonObject");
		JSONObject jsonObject = null;
		try {
			StringBuffer buffer = httpsRequest(requestUrl, requestMethod,
					outputStr);
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			CommonUtil.logger.error("【mobile】"+ce.getStackTrace());
		} catch (Exception e) {
			CommonUtil.logger.error("【mobile】"+e.getStackTrace());
		}
		return jsonObject;
	}

	/**
	 * 生成随机数
	 *
	 * @param
	 * @return
	 */
	public static String create_nonce_str() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * HTTP请求
	 *
	 * @param requestUrl，requestMethod，output
	 * @return
	 */
	private static StringBuffer httpsRequest(String requestUrl,
			String requestMethod, String output)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			KeyManagementException, MalformedURLException, IOException,
			ProtocolException, UnsupportedEncodingException {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,httpsRequest");
		URL url = new URL(requestUrl);

		HttpsURLConnection connection = (HttpsURLConnection) url
				.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		connection.setRequestMethod(requestMethod);
		if (null != output) {
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(output.getBytes("UTF-8"));
			outputStream.close();
		}
		//
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		connection.disconnect();
		return buffer;
	}
	/**
	 * HTTP请求
	 *
	 * @param requestUrl，requestMethod，output
	 * @return
	 */
	private static StringBuffer httpsRequest2(String requestUrl,
			String requestMethod, String output)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			KeyManagementException, MalformedURLException, IOException,
			ProtocolException, UnsupportedEncodingException {
		URL url = new URL(requestUrl);

		HttpsURLConnection connection = (HttpsURLConnection) url
				.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("ContentType", "text/xml");
		connection.setRequestMethod(requestMethod);
		if (null != output) {
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(output.getBytes("UTF-8"));
			outputStream.close();
		}
		//
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		connection.disconnect();
		return buffer;
	}

	/**
	 * 将字节转换为十六进制字符串
	 *
	 * @param btyes
	 * @return
	 */
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "a", "b", "c", "d", "e", "f"};
    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
    	CommonUtil.logger.info("【mobile】"+"into CommonUtil,byteArrayToHexString");
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }
    /**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
    	CommonUtil.logger.info("【mobile】"+"into CommonUtil,byteToHexString");
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

	/**
	 * 获取ip地址
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,getIpAddr");
		  String ip = request.getHeader("x-forwarded-for");
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		      ip = request.getHeader("Proxy-Client-IP");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		      ip = request.getHeader("WL-Proxy-Client-IP");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		      ip = request.getHeader("HTTP_CLIENT_IP");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		      ip = request.getRemoteAddr();
		    }
		    return ip;
//		return "140.75.25.15";
	}

	/**
	 * 创建统一下单的xml的java对象
	 *
	 * @param bizOrder
	 *            系统中的业务单号
	 * @param ip
	 *            用户的ip地址
	 * @param openId
	 *            用户的openId
	 * @return
	 */
	public static Tbl_PayInfo createPayInfo(Map<String, String> tempMap, String ip) {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,createPayInfo");
		Tbl_PayInfo payInfo = new Tbl_PayInfo();
		payInfo.setAppid(appid);
//		订单名称
		payInfo.setBody(tempMap.get("order_contents"));
		payInfo.setDevice_info("WEB");
		payInfo.setMch_id(mch_id);
		payInfo.setNonce_str(CommonUtil.create_nonce_str().replace("-", ""));
		payInfo.setNotify_url(notify_url);
		payInfo.setOpenid(tempMap.get("open_id"));
//		附加数据
//		payInfo.setAttach(bizOrder.get("").t);
//		用户订单号
		payInfo.setOut_trade_no(tempMap.get("order_id"));
//		订单金额
		payInfo.setSpbill_create_ip(ip);
		payInfo.setTotal_fee(Integer.parseInt(tempMap.get("order_price")));
		payInfo.setTrade_type("JSAPI");
//		非必须
//		payInfo.setOpenid(openId);
		return payInfo;
	}

	/**
	 * 获取签名
	 *
	 * @param payInfo
	 * @return
	 * @throws Exception
	 */
	public static String getSign(Tbl_PayInfo payInfo) {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,getSign");
		String signTemp = "appid=" + payInfo.getAppid()
				+ "&body=" + payInfo.getBody()
				+ "&device_info=" + payInfo.getDevice_info()
				+ "&mch_id=" + payInfo.getMch_id()
				+ "&nonce_str=" + payInfo.getNonce_str()
				+ "&notify_url=" + payInfo.getNotify_url()
				+ "&openid=" + payInfo.getOpenid()
				+ "&out_trade_no=" + payInfo.getOut_trade_no()
				+ "&spbill_create_ip=" + payInfo.getSpbill_create_ip()
				+ "&total_fee=" + payInfo.getTotal_fee()
				+ "&trade_type=" + payInfo.getTrade_type()
				+ "&key=" + key;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(signTemp.getBytes());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String sign = byteArrayToHexString(md.digest()).toUpperCase();
		System.out.println(signTemp);
		return sign;
	}
	/**
	 * 获取jd签名
	 *
	 * @param payInfo
	 * @return
	 * @throws Exception
	 */
	public static String getSignForJs(Map<String, Object> tempMap) {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,getSignForJs");
		String signTemp = "appId=" + tempMap.get("appId").toString()
				+ "&nonceStr=" + tempMap.get("nonceStr").toString()
				+ "&package=" + tempMap.get("package").toString()
				+ "&signType=" + tempMap.get("signType").toString()
				+ "&timeStamp=" + tempMap.get("timeStamp").toString()
				+ "&key=" + key;
				;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(signTemp.getBytes());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String sign = byteArrayToHexString(md.digest()).toUpperCase();
		System.out.println(signTemp);
		System.out.println(sign);
		return sign;
	}
	/**
	 * 扩展xstream使其支持CDATA
	 *
	 * @param payInfo
	 * @return
	 * @throws Exception
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			CommonUtil.logger.info("【mobile】"+"into CommonUtil,createWriter");
			return new PrettyPrintWriter(out) {
				// 增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	/**
	 * 支付信息转为XML
	 *
	 * @param payInfo
	 * @return
	 * @throws Exception
	 */
	public static String payInfoToXML(Tbl_PayInfo pi) {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,payInfoToXML");
		xstream.alias("xml", pi.getClass());
		return xstream.toXML(pi);
	}

	/**
	 * 支付信息转为XML
	 *
	 * @param payInfo
	 * @return
	 * @throws Exception
	 */
	public static String payCallBackToXML(Tbl_PayCallBackInfo pi) {
		xstream.alias("xml", pi.getClass());
		return xstream.toXML(pi);
	}
	/**
	 * XML转map
	 *
	 * @param payInfo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(String xml) throws Exception {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,parseXml");
		Map<String, String> map = new HashMap<String, String>();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();
		for (Element e : elementList)
			map.put(e.getName(), e.getText());
		return map;
	}

	/**
	 * http请求转xml
	 *
	 * @param payInfo
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> httpsRequestToXML(String requestUrl,
			String requestMethod, String outputStr) {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,httpsRequestToXML");
		Map<String, String> result = new HashMap<>();
		try {
			StringBuffer buffer = httpsRequest(requestUrl, requestMethod,
					outputStr);
			result = CommonUtil.parseXml(buffer.toString());
		} catch (ConnectException ce) {
			CommonUtil.logger.error("【mobile】httpsRequestToXML fail!"+ce.getStackTrace());
		} catch (Exception e) {
			CommonUtil.logger.error("【mobile】httpsRequestToXML fail!"+e.getStackTrace());
		}
		return result;
	}
	/**
	 * http请求转xml
	 *
	 * @param payInfo
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> httpsRequestToXML2(String requestUrl,
			String requestMethod, String outputStr) {
		Map<String, String> result = new HashMap<>();
		try {
			StringBuffer buffer = httpsRequest2(requestUrl, requestMethod,
					outputStr);
			result = CommonUtil.parseXml(buffer.toString());
		} catch (ConnectException ce) {
			System.out.print("连接超时：" + ce.getMessage());
		} catch (Exception e) {
			System.out.print("https请求异常：" + e.getMessage());
		}
		return result;
	}

	/**
	 * 获取用户openID
	 *
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String getOpenID(String code) {
		CommonUtil.logger.info("【mobile】"+"into CommonUtil,getOpenID");
		String openID = null;
		String oauth_url = oauth_url_static.replace("APPID", appid)
				.replace("SECRET", appsecret)
				.replace("CODE", String.valueOf(code));
		JSONObject jsonObject = CommonUtil.httpsRequestToJsonObject(oauth_url,
				"POST", null);
		Object errorCode = jsonObject.get("errcode");
		if (errorCode != null) {
			CommonUtil.logger.info("【mobile】"+"openId get error!");
		} else {
			String openId = jsonObject.getString("openid");
			openID = openId;
		}
		return openID;
	}

}
