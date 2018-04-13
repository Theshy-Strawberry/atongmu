package com.atongmu.servlet;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.util.SHA1;
 
@WebServlet("/WechatCallbackApi")
public class WechatCallbackApi extends HttpServlet {
    private String TOKEN = "ATONGMU20160621";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	synchronized(this) {
            String signature = request.getParameter("signature");
            String echostr = request.getParameter("echostr");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");

            String[] str = { TOKEN, timestamp, nonce };
            Arrays.sort(str); 
            String bigStr = str[0] + str[1] + str[2];
            String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
            if (digest.equals(signature)) {
                response.getWriter().print(echostr);
            }
    	}

    }
}