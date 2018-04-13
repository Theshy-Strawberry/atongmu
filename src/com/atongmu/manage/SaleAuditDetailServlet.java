package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.daoImpl.BonusDao;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class SaleAuditDetailServlet
 * 销售审核详情
 */
@WebServlet("/SaleAuditDetailServlet")
public class SaleAuditDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BonusDao bonusDao = new BonusDao();

	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleAuditDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 设置接收和返回的编码格式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

		String reqNo = request.getParameter("reqNo");

		Map<String, Object> saleInfoDetailMap = getSaleInfoDetail(reqNo);

		request.setAttribute("saleInfoDetailMap", saleInfoDetailMap);
		request.getRequestDispatcher("web_browser/saleAuditDetail.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 设置接收和返回的编码格式
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

		String reqNo = request.getParameter("reqNo");
		String doPass = request.getParameter("doPass");
		String doBack = request.getParameter("doBack");

		if (null != doPass) {
			if(!pass(reqNo)){
				//报error
				System.out.println("error");
			}
		}else if (null != doBack) {
			if(!noPass(reqNo)){
				//报error
				System.out.println("error");
			}
		}
		request.getRequestDispatcher("SaleAuditListServlet").forward(
				request, response);
	}


	private boolean pass(String reqNo) {
		boolean pass = false;

		try {
			con = MySQLUtil.getOnlineConnection();
			StringBuffer passSaleSql = new StringBuffer("UPDATE tbl_sale_audit ");
			passSaleSql.append(" SET req_status = ? ");
			passSaleSql.append(" WHERE req_no = ? ");
			ps = con.prepareStatement(passSaleSql.toString());
			ps.setString(1, "H001");
			ps.setString(2, reqNo);
			ps.executeUpdate();

			StringBuffer passSaleUserSql = new StringBuffer("UPDATE tbl_user ");
			passSaleSql.append(" transfer_flag = '1' ");
			passSaleSql.append(" WHERE user_id = ( ");
			passSaleSql.append(" SELECT req_user_id ");
			passSaleSql.append(" FROM tbl_sale_audit ");
			passSaleSql.append(" WHERE req_no = '?') ");
			ps = con.prepareStatement(passSaleUserSql.toString());
			ps.setString(1, reqNo);
			ps.executeUpdate();

			pass = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return pass;
	}


	private boolean noPass(String reqNo) {
		boolean noPass = false;

		try {
			StringBuffer passSaleSql = new StringBuffer("UPDATE tbl_sale_audit ");
			passSaleSql.append(" SET req_status = ? ");
			passSaleSql.append(" WHERE req_no = ? ");
			con = MySQLUtil.getOnlineConnection();
			ps = con.prepareStatement(passSaleSql.toString());
			ps.setString(1, "H002");
			ps.setString(2, reqNo);
			ps.executeUpdate();

			noPass = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return noPass;
	}


	private Map<String, Object> getSaleInfoDetail(String reqNo) {
		Map<String, Object> saleInfoMap = new HashMap<String, Object>();

		try {
			String getSaleInfoDetailSql = ""
					+ " SELECT TBS.weixin_id, "
					+ " TSA.req_no, "
					+ " TBU.user_id, "
					+ " TBS.saleman_id, "
					+ " TBS.saleman_name, "
					+ " TBS.saleman_sex, "
					+ " TBS.alipay_id, "
					+ " TBS.card_bank, "
					+ " TBS.card_name, "
					+ " TBS.card_number "
					+ " FROM tbl_saleman TBS "
					+ " INNER JOIN tbl_user TBU "
					+ " ON TBU.open_id = TBS.open_id "
					+ " INNER JOIN tbl_sale_audit TSA "
					+ " ON TSA.req_user_id = TBU.user_id "
					+ " WHERE TSA.req_no = '"+reqNo+"'";
			con = MySQLUtil.getOnlineConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(getSaleInfoDetailSql);
			if (rs.next()) {
				double mouthSale = bonusDao.selectBuySum(rs.getString("saleman_id"));
				saleInfoMap.put("reqNo", StringUtil.nvl(rs.getString("req_no")));
				saleInfoMap.put("weixinId", StringUtil.nvl(rs.getString("weixin_id")));
				saleInfoMap.put("salemanName", StringUtil.nvl(rs.getString("saleman_name")));
				if("1".equals( StringUtil.nvl(rs.getString("saleman_sex")))){
					saleInfoMap.put("salemanSex","男");
				}else if("2".equals( StringUtil.nvl(rs.getString("saleman_sex")))){
					saleInfoMap.put("salemanSex", "女");
				}else{
					saleInfoMap.put("salemanSex",StringUtil.nvl(rs.getString("saleman_sex")));
				}
				saleInfoMap.put("alipayId", StringUtil.nvl(rs.getString("alipay_id")));
				saleInfoMap.put("cardBank", StringUtil.nvl(rs.getString("card_bank")));
				saleInfoMap.put("cardName", StringUtil.nvl(rs.getString("card_name")));
				saleInfoMap.put("cardNumber", StringUtil.nvl(rs.getString("card_number")));
				saleInfoMap.put("mouthSale", StringUtil.nvl(String.valueOf(mouthSale)));
			}
			saleInfoMap.put("userIdCard1", "http://www.in-artoon.com/userimage/" + rs.getString("user_id") + "_01.jpg");
			saleInfoMap.put("userIdCard2", "http://www.in-artoon.com/userimage/" + rs.getString("user_id") + "_02.jpg");

		} catch (Exception e) {
			// TODO: handle exception
		}
		return saleInfoMap;
	}


}
