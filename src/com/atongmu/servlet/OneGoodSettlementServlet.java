package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_PayInfo;
import com.atongmu.bean.Tbl_commodity;
import com.atongmu.bean.Tbl_commodity_commont;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.daoImpl.GetRoleDaoImpl;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.DateUtil;
import com.atongmu.util.MakeUnqID;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class OneGoodSettlementServlet
 */
@WebServlet("/OneGoodSettlementServlet")
public class OneGoodSettlementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GetRoleDaoImpl getRoleDaoImpl = new GetRoleDaoImpl();

	// 数据库定值
	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneGoodSettlementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommonUtil.logger.info("【mobile】"+"into OneGoodSettlementServlet,doGet");
		// 当前系统时间
		Timestamp date = (Timestamp) DateUtil.getSysDate();

		// 设置接收和返回的编码格式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

//		用户id
		String userId = (String) request.getSession().getAttribute("userid");
//		商品id
		String goodsId = request.getParameter("goodsId");

		CommonUtil.logger.info("【mobile】"+"****************************************");
		CommonUtil.logger.info("【mobile】"+"用户"+request.getSession().getAttribute("userid")+"准备购买商品"+goodsId);
		CommonUtil.logger.info("【mobile】"+"****************************************");

		int goodsCount = 1;
		if(null !=  request.getParameter("count")){
			goodsCount = Integer.parseInt(request.getParameter("count"));
		}

		// 用户收货地址check
		int userType = getRoleDaoImpl.getUserType(userId);;
		if (userType == 1) {
			Tbl_user sessionUser = (Tbl_user) request.getSession()
					.getAttribute("loginuser");
			if (sessionUser.getUser_addr() == null
					|| "".equals(sessionUser.getUser_addr())) {
				CommonUtil.logger.info("【mobile】"+"用户收货地址不完整，即将进入修改用户资料页面");
				response.sendRedirect("web_mobile/updateInfo.jsp?errorcode=-3");
				return;
			}
		} else if (userType == 2) {
			Tbl_saleman sessionSaleman = (Tbl_saleman) request.getSession()
					.getAttribute("loginuser");
			if (sessionSaleman.getSaleman_addr() == null
					|| "".equals(sessionSaleman.getSaleman_addr())) {
				CommonUtil.logger.info("【mobile】"+"用户收货地址不完整，即将进入修改用户资料页面");
				response.sendRedirect("web_mobile/updateInfo.jsp?errorcode=-3");
				return;
			}
		}
//		获取商品信息
		double orderPrice = 0.0;
		List<Tbl_commodity> goodsList = new ArrayList<Tbl_commodity>();
		try {

			String sqlGetOrderPri = "SELECT goods_name, "
					+ " goods_price ,goods_id "
					+ " FROM tbl_commodity WHERE goods_id = '" + goodsId + "'";
			con = MySQLUtil.getConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(sqlGetOrderPri);
			while(rs.next()){
				orderPrice = rs.getDouble("goods_price") * goodsCount;
				Tbl_commodity commodity =new Tbl_commodity();
				commodity.setGoods_name(rs.getString("goods_name"));
				commodity.setGoods_price(rs.getDouble("goods_price"));
				commodity.setGoods_id(rs.getInt("goods_id"));
				commodity.setGoods_count(goodsCount);
				goodsList.add(commodity);
			}
		} catch (Exception e) {
			CommonUtil.logger.info("【mobile】"+e);
			MySQLUtil.closeAll(rs, statement, con, ps);
		}
		// 订单ID，用户id后两位+当前时间戳
		String orderId = userId.substring(userId.length() - 2, userId.length()) + MakeUnqID.nextId();
		// 微信统一下单
		String openId = (String) request.getSession().getAttribute("openid");
		String orderPriceString = String.valueOf(orderPrice);
		String orderPriceTemp = orderPriceString.substring(orderPriceString.indexOf(".") + 1);
		if(orderPriceTemp.length() == 1){
			orderPriceString = orderPriceString + "0";
		}
		String orderPriceWechat = String.valueOf(orderPriceString).replace(".", "");
		Map<String, String> weChatMap = new HashMap<String, String>();
		weChatMap.put("order_id", orderId);
		weChatMap.put("user_id", userId);
		weChatMap.put("open_id", openId);
		weChatMap.put("order_price", orderPriceWechat);
		weChatMap.put("order_contents", "artoon store");
		boolean payStatus = payStatus(weChatMap, request);
		// 下单失败
		if (!payStatus) {
			request.getRequestDispatcher("error.jsp").forward(
					request, response);
			CommonUtil.logger.info("【mobile】"+"统一下单失败，支付state:"+payStatus);
			return;
		}
//		插入订单详情表
		for (Tbl_commodity goodsAddMap : goodsList) {
			try {
				con = MySQLUtil.getConnection();
				StringBuffer sql = new StringBuffer(
						"INSERT INTO tbl_order_detail ");
				sql.append("(order_id, ");
				sql.append(" goods_id, ");
				sql.append(" goods_number ");
				sql.append(" ) VALUES (");
				sql.append("?,?,?)");
				ps = con.prepareStatement(sql.toString());
				ps.setString(1, orderId);
				ps.setLong(2, goodsAddMap.getGoods_id());
				ps.setInt(3, goodsAddMap.getGoods_count());
				ps.executeUpdate();
			} catch (SQLException e) {
				CommonUtil.logger.info("【mobile】"+e);
			} catch (Exception e) {
				CommonUtil.logger.info("【mobile】"+e);
			} finally {
				MySQLUtil.closeAll(rs, statement, con, null);
			}
		}
		CommonUtil.logger.info("【mobile】"+"订单详情表数据作成成功");
		// 订单表
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("INSERT INTO tbl_order ");
			sql.append("(order_id, ");
			sql.append(" order_user, ");
			sql.append(" order_date, ");
			sql.append(" order_status, ");
			sql.append(" order_price, ");
			sql.append(" delete_flag) VALUES (");
			sql.append("?,?,?,?,?,?)");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, orderId);
			ps.setString(2, userId);
			ps.setTimestamp(3, date);
			ps.setString(4, "D001");
			ps.setDouble(5, orderPrice);
			ps.setString(6, "C002");
			ps.executeUpdate();
		} catch (Exception e) {
			CommonUtil.logger.info("【mobile】"+e);
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		CommonUtil.logger.info("【mobile】"+"订单表数据作成成功，订单ID:"+orderId);
		// 把订单内容及收货信息展示在页面
		// 把用户信息提取
		Map<String, Object> userMap = new HashMap<String, Object>();
		if (userType == 1) {
			Tbl_user sessionUser = (Tbl_user) request.getSession()
					.getAttribute("loginuser");
			userMap.put("userName", sessionUser.getUser_name());
			userMap.put("userAddr", sessionUser.getUser_addr());
			userMap.put("userTel", sessionUser.getUser_tel_num());

		} else if (userType == 2) {
			Tbl_saleman sessionSaleman = (Tbl_saleman) request.getSession()
					.getAttribute("loginuser");
			userMap.put("userName", sessionSaleman.getSaleman_name());
			userMap.put("userAddr", sessionSaleman.getSaleman_addr());
			userMap.put("userTel", sessionSaleman.getSaleman_tel_num());
		}
		// 把订单id放在session里面
		if (goodsList.size() != 0) {
			request.getSession().setAttribute("goodsList", goodsList);
		}
		request.setAttribute("userInfo", userMap);
		request.setAttribute("orderId", orderId);
		request.setAttribute("orderPrice", orderPrice);
		request.getRequestDispatcher("web_mobile/registerorder.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private boolean payStatus(Map<String, String> tempMap,
			HttpServletRequest request) {
		String ip = CommonUtil.getIpAddr(request);
		System.out.println(ip);
		boolean payStatus = false;
		Tbl_PayInfo pi = CommonUtil.createPayInfo(tempMap, ip);
		String sign = CommonUtil.getSign(pi);
		pi.setSign(sign);
		Map<String, String> retMap = CommonUtil.httpsRequestToXML(
				"https://api.mch.weixin.qq.com/pay/unifiedorder",
				"POST",
				CommonUtil.payInfoToXML(pi).replace("__", "_")
						.replace("<![CDATA[", "").replace("]]>", ""));

//		System.out.println(CommonUtil.payInfoToXML(pi).replace("__", "_").replace("<![CDATA[", "").replace("]]>", ""));

		String return_code = retMap.get("return_code");
		String return_msg = retMap.get("return_msg");
		if (return_code.equals("SUCCESS")) {
			if (return_msg.equals("")) {
				CommonUtil.logger.info("【mobile】"+"统一下单失败，return_msg:"+return_code);
			} else {
				payStatus = true;
				retMap.put("user_id", tempMap.get("user_id"));
				retMap.put("order_id", tempMap.get("order_id"));
				saveWeChatInfo(retMap);
				CommonUtil.logger.info("【mobile】"+"统一下单成功。");
			}
		} else {
			System.out.println(return_msg);
		}

		return payStatus;
	}

	private void saveWeChatInfo(Map<String, String> tempMap) {

		// 当前系统时间
		Timestamp date = (Timestamp) DateUtil.getSysDate();

		// 预支付id
		String prepay_id = tempMap.get("prepay_id");
//		// 支付二维码链接
//		String code_url = tempMap.get("code_url");

		String userId = tempMap.get("user_id");
		String orderId = tempMap.get("order_id");

		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("INSERT INTO tbl_wechat_save ");
			sql.append("(order_id, ");
			sql.append(" user_id, ");
			sql.append(" save_date, ");
			sql.append(" prepay_id) VALUES (");
			sql.append("?,?,?,?)");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, orderId);
			ps.setString(2, userId);
			ps.setTimestamp(3, date);
			ps.setString(4, prepay_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			CommonUtil.logger.info("【mobile】"+e);
		} catch (Exception e) {
			CommonUtil.logger.info("【mobile】"+e);
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}

	}
}
