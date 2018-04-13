package com.atongmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class OrderPayInfoServlet
 */
@WebServlet("/OrderPayInfoServlet")
public class OrderPayInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPayInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 设置接收和返回的编码格式
		synchronized(this) {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Type", "text/html;charset=UTF-8");
			Connection con = null;
			PreparedStatement ps = null;
			Statement statement = null;
			ResultSet rs  = null;
			String userId  = "";
			int userType = Integer.parseInt(request.getSession().getAttribute("role").toString());
			if(userType == 1 || userType == 2){
				//会员的场合
				userId = (String)request.getSession().getAttribute("userid");
			}else{
				//无权限进行该项操作
				response.sendRedirect("web_mobile/error.jsp");
			}
//		订单价格
//		double orderPrice = 0.0;
//		从画面获取订单id
			String orderId = request.getParameter("orderId");
			String prepayId = "";
			try {
//			查找用户积分和销售员id
				String sqlUser = "SELECT TWS.prepay_id,TBO.order_price "
						+ " FROM tbl_wechat_save TWS "
						+ " LEFT JOIN tbl_order TBO "
						+ " ON TWS.order_id = TBO.order_id "
						+ " WHERE TWS.order_id = '" + orderId + "'"
						+ " AND TWS.user_id = '" + userId + "'";
				con = MySQLUtil.getConnection();
				statement = con.createStatement();
				rs = statement.executeQuery(sqlUser);
				if (rs.next()) {
					prepayId = rs.getString("prepay_id");
//				orderPrice = rs.getDouble("order_price");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				MySQLUtil.closeAll(rs, statement, con, null);
			}
			
//		String orderPriceString = String.valueOf(orderPrice).replace(".", "");
//		Map<String, String> tempMap = new HashMap<String, String>();
//		tempMap.put("order_id", orderId);
//		tempMap.put("open_id", openId);
//		tempMap.put("order_price", orderPriceString);
//		tempMap.put("order_contents", "test");
//		当前系统时间戳
			String systemTime = String.valueOf(System.currentTimeMillis());
			String timeStamp = systemTime.substring(0, systemTime.length()-3);
//		获取ip
			Map<String, Object> reqMap = new HashMap<String, Object>();
			Map<String, Object> getSignMap = new HashMap<String, Object>();
//		公众号id
			reqMap.put("appId", CommonUtil.appid);
			getSignMap.put("appId", CommonUtil.appid);
//		时间戳
			reqMap.put("timeStamp", timeStamp);
			getSignMap.put("timeStamp", timeStamp);
//		随机字符串
			reqMap.put("nonceStr", CommonUtil.create_nonce_str());
			getSignMap.put("nonceStr", CommonUtil.create_nonce_str());
//		订单详情扩展字符串 prepay_id
			reqMap.put("package", "prepay_id=" + prepayId);
			getSignMap.put("package", "prepay_id=" + prepayId);
//		签名方式
			reqMap.put("signType", "MD5");
			getSignMap.put("signType", "MD5");
//		签名
			String sign = CommonUtil.getSignForJs(reqMap);
			reqMap.put("paySign", sign);
			
			JSONArray json = JSONArray.fromObject(reqMap);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.close();
		}
	}

}
