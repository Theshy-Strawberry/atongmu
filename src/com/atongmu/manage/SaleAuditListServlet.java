package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.daoImpl.BonusDao;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class SaleAuditListServlet
 * 销售审核一览
 */
@WebServlet("/SaleAuditListServlet")
public class SaleAuditListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BonusDao bonusDao = new BonusDao();

	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleAuditListServlet() {
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

		List<Map<String, Object>> saleInfoList = getSaleInfo("","");

		request.setAttribute("saleInfoList", saleInfoList);

		request.getRequestDispatcher("web_browser/saleAuditList.jsp").forward(
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

		String status = request.getParameter("auditStatus");
		String userId = request.getParameter("orderIdSearch");

		List<Map<String, Object>> saleInfoList = getSaleInfo(status,userId);

		request.setAttribute("saleInfoList", saleInfoList);

		request.getRequestDispatcher("web_browser/saleAuditList.jsp").forward(
				request, response);

	}



	private List<Map<String, Object>> getSaleInfo(String status, String userId) {
		List<Map<String, Object>> saleInfoList = new ArrayList<Map<String,Object>>();
		try{
			String getSaleInfoSql =
				" SELECT TSA.req_user_id, "
					+ " TSA.req_no, "
					+ " TBU.user_name, "
					+ " TBU.saleman_id, "
					+ " TSA.req_status, "
					+ " TCM1.code_value "
					+ " FROM tbl_sale_audit TSA "
					+ " INNER JOIN tbl_user TBU "
					+ " ON TSA.req_user_id = TBU.user_id "
					+ " LEFT JOIN tbl_code_master TCM1 "
					+ " ON TSA.req_status = TCM1.code "
					+ " WHERE ''='' ";
			if(!"".equals(StringUtil.nvl(status))){
				getSaleInfoSql += " AND TSA.req_status = '"+ status +"'";
			}
			if (!"".equals(StringUtil.nvl(userId))) {
				getSaleInfoSql += " AND TBU.user_id = '"+ userId +"'";
			}
			con = MySQLUtil.getOnlineConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(getSaleInfoSql);
			while (rs.next()) {
				Map<String, Object> saleManMap = new HashMap<String, Object>();
				double mouthSale = bonusDao.selectSaleSum(rs.getString("req_user_id"));
				saleManMap.put("reqNo", rs.getString("req_no"));
				saleManMap.put("reqUserId", rs.getString("req_user_id"));
				saleManMap.put("userName", rs.getString("user_name"));
				saleManMap.put("salemanId", rs.getString("saleman_id"));
				saleManMap.put("reqStatus", rs.getString("req_status"));
				saleManMap.put("codeValue", rs.getString("code_value"));
				saleManMap.put("mouthSale", mouthSale);
				saleInfoList.add(saleManMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MySQLUtil.closeAll(rs, statement, con, null);
		}

		return saleInfoList;
	}

}
