package com.atongmu.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.atongmu.bean.Tbl_PayCallBackInfo;

/**
 * Servlet implementation class WePayBackInfoServlet
 */
@WebServlet("/WePayBackInfoServlet")
public class WePayBackInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WePayBackInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("WePayBackInfoServlet G");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	synchronized(this) {
    		System.out.println("WePayBackInfoServlet P");

    		Tbl_PayCallBackInfo payInfo = new Tbl_PayCallBackInfo();
    		payInfo.setReturnCode("SUCCESS");
    		payInfo.setReturnMsg("OK");

    		Map<String,String> resultMap = null;
    		try {
    			resultMap = parseXml(request)
    					;
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		System.out.println("return_code:"+resultMap.get("return_code"));
    		System.out.println("appid:"+resultMap.get("appid"));
    		String return_code = resultMap.get("return_code");
    		String return_msg = resultMap.get("return_msg");
    		System.out.println(return_code);

    		if(return_code.equals("SUCCESS")){
    	            response.getWriter().write(setXML("SUCCESS", ""));   //告诉微信服务器，我收到信息了，不要在调用回调action了
    	            System.out.println("-------------"+setXML("SUCCESS", ""));
//    	        }
    		}else{
    			System.out.println(return_msg);
    		}
    	}

	}
	private String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";

}


	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
			    // 解析结果存储在HashMap
			    Map<String, String> map = new HashMap<String, String>();
			    InputStream inputStream = request.getInputStream();
			    // 读取输入流
			    SAXReader reader = new SAXReader();
			    Document document = reader.read(inputStream);
			    // 得到xml根元素
			    Element root = document.getRootElement();
			    // 得到根元素的所有子节点
			    List<Element> elementList = root.elements();

			    // 遍历所有子节点
			    for (Element e : elementList)
			        map.put(e.getName(), e.getText());

			    // 释放资源
			    inputStream.close();
			    inputStream = null;

			    return map;
			}

}
