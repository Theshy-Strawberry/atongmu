package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.bean.Tbl_user;
import com.atongmu.util.MySQLUtil;

/**
 * Servlet implementation class OrderShowServlet
 */
@WebServlet("/OrderShowServlet")
public class OrderShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		System.out.println("OrderShowServlet");
//		 设置接收和返回的编码格式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		// 清除session
		request.getSession().removeAttribute("orderGoodsList");
//		数据库定值
		Connection con = null;
		PreparedStatement ps = null;
		Statement statement = null;
		ResultSet rs  = null;
		ResultSet rs2  = null;
		int status = 0;
		if(null != request.getParameter("status")){
			status = Integer.parseInt(request.getParameter("status"));
		}
//		页面接收订单id
//		获取用户id
		String userId = (String) request.getSession().getAttribute("userid");
		List<Map<String, Object>> orderGoodsList = new ArrayList<Map<String,Object>>();
		try {
			String sqlOrderSel = "SELECT order_id,order_date,order_status,code_value,"
					+ "order_price,logistics_company,logistics_number"
					+ " FROM tbl_order TBO "
					+ " LEFT JOIN tbl_code_master TCM "
					+ " ON TBO.order_status = TCM.code "
					+ " WHERE order_user = '"+ userId
					+ "' AND delete_flag = 'C002'";
			if(status == 1){
				sqlOrderSel += " AND  order_status NOT IN('D004','D005') ";
			}else if (status == 2) {
				sqlOrderSel += " AND order_status = 'D005'";
			}
			sqlOrderSel += " Order By order_date DESC";
			con = MySQLUtil.getConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(sqlOrderSel);
			while (rs.next()) {
				Map<String, Object> orderGoodsMap = new HashMap<String, Object>();
				ArrayList<Map<String,Object>> goodsList = new ArrayList<Map<String, Object>>();
				String sqlGoodsByOrderIdString = "SELECT TOD.order_id,TOD.goods_id,"
						+ "TOD.goods_number,TC.goods_name,"
						+ "TC.goods_price "
						+ "FROM tbl_order_detail TOD "
						+ "LEFT JOIN tbl_commodity TC "
						+ "on TOD.goods_id = TC.goods_id "
						+ "WHERE TOD.order_id = '"
						+ rs.getString("order_id") + "'";
				statement = con.createStatement();
				rs2 = statement.executeQuery(sqlGoodsByOrderIdString);
				while (rs2.next()) {
					Map<String, Object> goodsMap = new HashMap<String, Object>();
					goodsMap.put("order_id", rs2.getString("order_id"));
					goodsMap.put("goods_id", rs2.getString("goods_id"));
					goodsMap.put("goods_number", rs2.getInt("goods_number"));
					goodsMap.put("goods_name", rs2.getString("goods_name"));
					goodsMap.put("goods_price", rs2.getDouble("goods_price"));
					//商品总价
					double goodsPriceCount = rs2.getDouble("goods_price") * rs2.getInt("goods_number");
					goodsMap.put("goods_price_count", goodsPriceCount);
					goodsList.add(goodsMap);
				}
				rs2.close();
				orderGoodsMap.put("order_id", rs.getString("order_id"));
				orderGoodsMap.put("order_date", rs.getString("order_date"));
				orderGoodsMap.put("order_status_num", rs.getString("order_status").substring(3));
				orderGoodsMap.put("order_status", rs.getString("code_value"));
				orderGoodsMap.put("order_price", rs.getString("order_price"));
				orderGoodsMap.put("logistics_company", rs.getString("logistics_company"));
				orderGoodsMap.put("logistics_number", rs.getString("logistics_number"));
				orderGoodsMap.put("goodsList", goodsList);
				orderGoodsList.add(orderGoodsMap);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{

			MySQLUtil.closeAll(rs, null, con, ps);
		}
//		快递接口
//		http://m.kuaidi100.com/index_all.html?type=[快递公司编码]&postid=[快递单号]&callbackurl=[点击"返回"跳转的地址]
//		request.getRequestDispatcher("web_mobile/ordersShow.jsp").forward(request, response);
//		response.sendRedirect("web_mobile/ordersShow.jsp");
		request.setAttribute("orderGoodsList", orderGoodsList);
		request.setAttribute("status", status);
		request.getRequestDispatcher("web_mobile/ordersShow.jsp").forward(request, response);

	}

}
