package com.atongmu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.daoImpl.BonusDao;
import com.atongmu.daoImpl.GetRoleDaoImpl;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.DateUtil;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * 支付 Servlet implementation class GoodsPayServlet
 */
@WebServlet("/GoodsPayServlet")
public class GoodsPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GetRoleDaoImpl getRoleDaoImpl = new GetRoleDaoImpl();

	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodsPayServlet() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 synchronized(this) {
			 
			 // TODO Auto-generated method stub
			 CommonUtil.logger.info("【mobile】" + "********************************");
			 CommonUtil.logger.info("【mobile】" + "into GoodsPayServlet,doGet");
			 CommonUtil.logger.info("【mobile】" + "支付成功，即将更新佣金和积分");
			 CommonUtil.logger.info("【mobile】" + "********************************");
			 // 设置接收和返回的编码格式
			 response.setCharacterEncoding("UTF-8");
			 request.setCharacterEncoding("UTF-8");
			 response.setHeader("Content-Type", "text/html;charset=UTF-8");
			 Object doTime = request.getSession().getAttribute("doTime");
			 System.out.println("doTime:"+doTime);
			 if(null == doTime || "" == StringUtil.nvl((String)doTime)){
				 // 查找数据库，显示list.jsp
				 // 用户id
				 // Tbl_user userinfo = (Tbl_user)
				 // request.getSession().getAttribute("loginuser");
				 String userId = (String) request.getSession()
						 .getAttribute("userid");
				 // 从画面获取订单id1
				 String orderId = request.getParameter("orderId");
				 CommonUtil.logger.info("【mobile】" + "即将处理订单"+orderId);
				 System.out.println(orderId);
				 double price = 0.0;
				 try {
					 String sqlGetOrderPri = "SELECT order_price FROM tbl_order WHERE order_id = '"
							 + orderId + "'";
					 con = MySQLUtil.getConnection();
					 statement = con.createStatement();
					 rs = statement.executeQuery(sqlGetOrderPri);
					 if (rs.next()) {
						 price = rs.getDouble("order_price");
					 }
				 } catch (SQLException e) {
					 CommonUtil.logger.error(e.getMessage());
				 } finally {
					 MySQLUtil.closeAll(rs, statement, con, null);
				 }
				 CommonUtil.logger.info("【mobile】" + "订单ID:" + orderId + " ，订单价格："
						 + price);
				 
				 // 普通用户积分
				 double userPoints = price * 0.05;
				 // 销售员业绩
				 double salerPoints = price * 0.15;
				 // 制度奖
				 double point5pst = price * 0.05;
				 CommonUtil.logger.info("【mobile】" + "该订单可获得积分" + userPoints);
				 CommonUtil.logger.info("【mobile】" + "该订单上级销售可得佣金" + salerPoints);
				 CommonUtil.logger.info("【mobile】" + "该订单上级销售可得制度奖金" + point5pst);
				 int userType = getRoleDaoImpl.getUserType(userId);
				 // 上级销售
				 String upsalemanId = "";
				 double userIntegral = 0.0;
				 // ********************************
				 // 更新用户积分先
				 // ********************************
				 System.out.println("now update user score, userid is " + userId);
				 if (userType == 1) {
					 try {
						 int vipFlag = 0;
						 con = MySQLUtil.getConnection();
						 // 查找用户积分和上级销售员id
						 String sqlUser = "SELECT user_integral,saleman_id,vip_flag FROM tbl_user WHERE user_id = '"
								 + userId + "'";
						 statement = con.createStatement();
						 rs = statement.executeQuery(sqlUser);
						 if (rs.next()) {
							 userIntegral = rs.getDouble("user_integral");
							 upsalemanId = rs.getString("saleman_id");
							 vipFlag = rs.getInt("vip_flag");
						 }
						 // 更新用户积分
						 if(vipFlag == 1){
							 double userIntegralNew = userIntegral + userPoints;
							 StringBuffer sql = new StringBuffer("UPDATE tbl_user ");
							 sql.append(" SET user_integral = ? ");
							 sql.append(" WHERE user_id = ? ");
							 ps = con.prepareStatement(sql.toString());
							 ps.setDouble(1, userIntegralNew);
							 ps.setString(2, userId);
							 ps.executeUpdate();
						 }
					 } catch (SQLException e) {
						 CommonUtil.logger.error(e.getMessage());
					 } finally {
						 MySQLUtil.closeAll(rs, statement, con, null);
					 }
					 // 积分处理完毕
					 CommonUtil.logger.info("【mobile】" + "用户积分更新完毕");
					 // ***********************************
					 // 开始处理用户佣金
					 // ***********************************
					 if (!"".equals(StringUtil.nvl(upsalemanId))) {
						 // 绑定的上级销售员可获得零售额15%
						 updateBonus(upsalemanId, userId, salerPoints, "K001");
//				// 绑定的上级销售员可获得制度奖5%
//				updateBonus(upsalemanId, userId, point5pst, "K003");
						 
						 // 插入更高三级的佣金记录
						 String hiSalerId = getUpSaleManId(upsalemanId);
						 if (!"".equals(hiSalerId)) {
							 updateBonus(hiSalerId, upsalemanId, userPoints, "K002");
							 String hiSalerId2 = getUpSaleManId(hiSalerId);
							 if(!"".equals(hiSalerId2)){
								 updateBonus(hiSalerId2, hiSalerId, userPoints, "K002");
								 String hiSalerId3 = getUpSaleManId(hiSalerId2);
								 if(!"".equals(hiSalerId3)){
									 updateBonus(hiSalerId3, hiSalerId2, userPoints, "K002");
								 }
							 }
						 }
						 
					 }
					 CommonUtil.logger.info("【mobile】" + "用户佣金数据作成完毕");
					 // 销售员
				 } else if (userType == 2) {
					 try {
						 con = MySQLUtil.getConnection();
						 // 查找销售员积分和销售员id
						 String sqlUser = "SELECT user_integral,up_saleman_id FROM tbl_saleman WHERE saleman_id = '"
								 + userId + "'";
						 statement = con.createStatement();
						 rs = statement.executeQuery(sqlUser);
						 if (rs.next()) {
							 userIntegral = rs.getDouble("user_integral");
							 upsalemanId = StringUtil.nvl(rs
									 .getString("up_saleman_id"));
						 }
						 // 更新用户积分
						 Double userIntegralNew = userIntegral + userPoints;
						 StringBuffer sql = new StringBuffer("UPDATE tbl_saleman ");
						 sql.append(" SET user_integral = ? ");
						 sql.append(" WHERE saleman_id = ? ");
						 ps = con.prepareStatement(sql.toString());
						 ps.setDouble(1, userIntegralNew);
						 ps.setString(2, userId);
						 ps.executeUpdate();
					 } catch (SQLException e) {
						 CommonUtil.logger.error(e.getMessage());
					 } finally {
						 MySQLUtil.closeAll(rs, statement, con, null);
					 }
					 
					 // 积分处理完毕
					 CommonUtil.logger.info("【mobile】" + "销售积分更新完毕");
					 // ***********************************
					 // 开始处理销售佣金
					 // ***********************************
					 // 自己可获得零售额15%
					 updateBonus(userId, userId, salerPoints, "K001");
//				// 绑定的上级销售员可获得制度奖5%
//				updateBonus(upsalemanId, userId, point5pst, "K003");
					 
					 // 插入更高二级的佣金记录
					 String hiSalerId = getUpSaleManId(userId);
					 if (!"".equals(hiSalerId)) {
						 updateBonus(hiSalerId, userId, userPoints, "K002");
						 String hiSalerId2 = getUpSaleManId(hiSalerId);
						 if(!"".equals(hiSalerId2)){
							 updateBonus(hiSalerId2, hiSalerId, userPoints, "K002");
						 }
					 }
					 
					 CommonUtil.logger.info("【mobile】" + "销售佣金数据作成完毕");
					 
				 }
				 // 销量增加处理
				 // 库存减少处理
				 try {
					 con = MySQLUtil.getConnection();
					 // 查找销售员积分和销售员id
					 String sqlSelStoSal = " SELECT TC.goods_id,TC.goods_stock,TC.goods_sales_volume,TOD.goods_number"
							 + " FROM tbl_commodity TC "
							 + " LEFT JOIN tbl_order_detail TOD "
							 + " ON TC.goods_id = TOD.goods_id "
							 + " WHERE TOD.order_id = '" + orderId + "'";
					 con = MySQLUtil.getConnection();
					 statement = con.createStatement();
					 rs = statement.executeQuery(sqlSelStoSal);
					 while (rs.next()) {
						 int goodsStock = rs.getInt("goods_stock");
						 int goodsSales = rs.getInt("goods_sales_volume");
						 int saleCount = rs.getInt("goods_number");
						 int goodsStockNew = goodsStock - saleCount;
						 int goodsSalesNew = goodsSales + saleCount;
						 con = MySQLUtil.getConnection();
						 StringBuffer sql = new StringBuffer(
								 " UPDATE tbl_commodity ");
						 sql.append(" SET goods_stock = ? ,");
						 sql.append(" goods_sales_volume = ? ");
						 sql.append(" WHERE goods_id = ? ");
						 ps = con.prepareStatement(sql.toString());
						 ps.setInt(1, goodsStockNew);
						 ps.setInt(2, goodsSalesNew);
						 ps.setString(3, rs.getString("goods_id"));
						 ps.executeUpdate();
					 }
					 
				 } catch (SQLException e) {
					 CommonUtil.logger.error(e.getMessage());
				 } finally {
					 MySQLUtil.closeAll(rs, statement, con, null);
				 }
				 
				 // 更新订单表成为已付款状态
				 try {
					 con = MySQLUtil.getConnection();
					 StringBuffer sqlUpdateOrder = new StringBuffer(
							 " UPDATE tbl_order ");
					 sqlUpdateOrder.append(" SET order_status = ? ");
					 sqlUpdateOrder.append(" WHERE order_id = ? ");
					 ps = con.prepareStatement(sqlUpdateOrder.toString());
					 ps.setString(1, "D002");
					 ps.setString(2, orderId);
					 System.out.println("更新订单为已付款 订单ID" + orderId);
					 ps.executeUpdate();
				 } catch (SQLException e) {
					 CommonUtil.logger.error(e.getMessage());
				 } finally {
					 MySQLUtil.closeAll(rs, statement, con, null);
				 }
			 }
			 CommonUtil.logger.info("【mobile】" + "订单状态已更新为已付款");
			 response.sendRedirect("OrderShowServlet");
			 // request.getRequestDispatcher("OrderShowServlet").forward(request,
			 // response);
			 request.getSession().setAttribute("doTime","success");
		 }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	public String getUpSaleManId(String saleManId){
		String upSaleManId = "";
		try {
			// 查找销售员积分和销售员id
			con = MySQLUtil.getConnection();
			String sqlHiSale = "SELECT up_saleman_id FROM tbl_saleman WHERE saleman_id = '"
					+ saleManId + "'";
			statement = con.createStatement();
			rs = statement.executeQuery(sqlHiSale);
			if (rs.next()) {
				upSaleManId = rs.getString("up_saleman_id");
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return upSaleManId;
	}
	/**
	 * 更新bonus_history表 接受ID，发生ID，发生金额，发生类型
	 *
	 * @throws SQLException
	 */
	public void updateBonus(String toUid, String fromUid, double salerPoints,
			String bonusType) {
		CommonUtil.logger.info("【mobile】" + "into GoodsPayServlet,updateBonus");
		Timestamp date = (Timestamp) DateUtil.getSysDate();
		Connection con = null;
		double bonusCount = 0.0;
		int breakFlag = 1;
		try {
			BonusDao bonusDao = new BonusDao();
			bonusCount = bonusDao.selectSaleSum(toUid);
			if (bonusCount >= 2500) {
				breakFlag = 0;
				String updateOccurBonus = "UPDATE tbl_bonus_history SET break_flag = 0 WHERE to_user_id = '"
						+ toUid
						+ "' AND date_format(get_date,'%Y-%m') = date_format(sysdate(),'%Y-%m')";
				con = MySQLUtil.getConnection();
				ps = con.prepareStatement(updateOccurBonus.toString());
				MySQLUtil.closeAll(rs, statement, con, null);
			}
			StringBuffer sql = new StringBuffer(
					"INSERT INTO tbl_bonus_history ");
			sql.append("( get_date, ");
			sql.append(" to_user_id, ");
			sql.append(" from_user_id, ");
			sql.append(" occur_bonus, ");
			sql.append(" break_flag, ");
			sql.append(" bonus_type) VALUES (");
			sql.append("?,?,?,?,?,?)");
			con = MySQLUtil.getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setTimestamp(1, date);
			ps.setString(2, toUid);
			ps.setString(3, fromUid);
			ps.setDouble(4, salerPoints);
			ps.setInt(5, breakFlag);
			ps.setString(6, bonusType);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			MySQLUtil.closeAll(rs, statement, con, null);
		}
	}
}
