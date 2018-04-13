package com.atongmu.manage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atongmu.daoImpl.BonusDao;
import com.atongmu.util.DateUtil;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

/**
 * Servlet implementation class SalemanInfoUpdateServlet
 * 销售员信息更新
 */
@WebServlet("/SalemanInfoUpdateServlet")
public class SalemanInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BonusDao bonusDao = new BonusDao();

	Connection con = null;
	PreparedStatement ps = null;
	Statement statement = null;
	ResultSet rs = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalemanInfoUpdateServlet() {
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
		String saleManId = request.getParameter("saleManId");

		Map<String, Object> saleManInfoMap = getSaleManInfo(saleManId);
		String saleManLevel = saleManInfoMap.get("salemanLevel").toString();

		List<List<Map<String, Object>>> downLevelInfo = getDownSaleList(saleManId, saleManLevel);

		request.setAttribute("saleManMap", saleManInfoMap);
		request.setAttribute("downLevelInfo", downLevelInfo);

		request.getRequestDispatcher("web_browser/saleManInfoUpdate.jsp").forward(
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
		String saleManId = request.getParameter("saleManId");
		String revokeSaleMan = request.getParameter("revokeSaleMan");
		String updateSaleMan = request.getParameter("updateSaleMan");
		if(null != revokeSaleMan){
			if(!revokeSaleMan(request)){
				//報error
				System.out.println("error");
			}
		}else if (null != updateSaleMan) {
			if(!updateSaleMan(request)){
				//報error
				System.out.println("error");
			}
		}
		request.getRequestDispatcher("SalemanInfoShowServlet").forward(
				request, response);
	}

	protected Map<String, Object> getSaleManInfo(String saleManId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
		String sqlSaleManInfo =
				 " SELECT "
				+ " 	TBS.saleman_id, "
				+ " 	TBS.reg_date, "
				+ " 	TBS.user_integral, "
				+ " 	TBS.up_saleman_id, "
				+ " 	TBS.saleman_tel_num, "
				+ " 	TBS.saleman_name, "
				+ " 	TBS.saleman_level, "
				+ " 	TCM.code_value, "
				+ " 	TBS.weixin_id, "
				+ " 	TBS.saleman_sex, "
				+ " 	TBS.saleman_birthday, "
				+ " 	TBS.alipay_id, "
				+ " 	TBS.card_bank, "
				+ " 	TBS.card_name, "
				+ " 	TBS.card_number, "
				+ " 	TBS.revoke_reason, "
				+ " 	SOP.sumOrderPrice "
				+ " FROM	tbl_saleman TBS "
				+ " LEFT JOIN tbl_code_master TCM "
				+ " ON TBS.saleman_level = TCM.code "
				+ " JOIN "
				+ " 	(SELECT  "
				+ " 		SUM(tbl_order.order_price) sumOrderPrice "
				+ " 	FROM tbl_order "
				+ " 	WHERE tbl_order.order_status = 'D005' "
				+ " 	AND tbl_order.order_user  "
				+ " 	IN ( "
				+ " 		SELECT tbl_user.user_id "
				+ " 		FROM tbl_user "
				+ " 		WHERE tbl_user.saleman_id = '" + saleManId + "' "
				+ " 		UNION ALL  "
				+ " 		SELECT sub_saleman.saleman_id "
				+ " 		FROM tbl_saleman sub_saleman "
				+ " 		WHERE sub_saleman.up_saleman_id = '" + saleManId + "' "
				+ " 		) "
				+ " 	) SOP "
				+ " WHERE TBS.saleman_id = '" + saleManId + "' ";
		con = MySQLUtil.getOnlineConnection();
		statement = con.createStatement();
		rs = statement.executeQuery(sqlSaleManInfo);
		if(rs.next()){
			retMap.put("salemanId", StringUtil.nvl(rs.getString("saleman_id")));
			retMap.put("regDate", StringUtil.nvl(rs.getString("reg_date")));
			retMap.put("userIntegral", StringUtil.nvl(rs.getString("user_integral")));
			retMap.put("upSalemanId", StringUtil.nvl(rs.getString("up_saleman_id")));
			retMap.put("salemanTelNum", StringUtil.nvl(rs.getString("saleman_tel_num")));
			retMap.put("salemanName", StringUtil.nvl(rs.getString("saleman_name")));
			retMap.put("salemanLevel", StringUtil.nvl(rs.getString("saleman_level")));
			retMap.put("codeValue", StringUtil.nvl(rs.getString("code_value")));
			retMap.put("weixinId", StringUtil.nvl(rs.getString("weixin_id")));
			retMap.put("salemanSex", StringUtil.nvl(rs.getString("saleman_sex")));
			retMap.put("salemanBirthday", StringUtil.nvl(rs.getString("saleman_birthday")));
			retMap.put("alipayId", StringUtil.nvl(rs.getString("alipay_id")));
			retMap.put("cardBank", StringUtil.nvl(rs.getString("card_bank")));
			retMap.put("cardName", StringUtil.nvl(rs.getString("card_name")));
			retMap.put("cardNumber", StringUtil.nvl(rs.getString("card_number")));
			retMap.put("revokeReason", StringUtil.nvl(rs.getString("revoke_reason")));
			retMap.put("sumOrderPrice", StringUtil.nvl(rs.getString("sumOrderPrice")));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLUtil.closeAll(rs, statement, con, null);
		}
		return retMap;
	}

	protected boolean revokeSaleMan(HttpServletRequest request) {
		boolean revokeFlag = false;
		Timestamp date = (Timestamp) DateUtil.getSysDate();

		String saleManId = request.getParameter("saleManId");
		String revokeReason = request.getParameter("revokeReason");

		try {
			StringBuffer sqlRevokeSaleMan = new StringBuffer(" UPDATE  tbl_saleman ");
			sqlRevokeSaleMan.append(" SET revoke_date = ? ");
			sqlRevokeSaleMan.append(" , revoke_reason = ? ");
			sqlRevokeSaleMan.append(" WHERE saleman_id = ? ");
			con = MySQLUtil.getOnlineConnection();
			ps = con.prepareStatement(sqlRevokeSaleMan.toString());
			ps.setTimestamp(1, date);
			ps.setString(2, revokeReason);
			ps.setString(3, saleManId);
			ps.executeUpdate();
			revokeFlag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MySQLUtil.closeAll(rs, statement, con, null);
		}

		return revokeFlag;
	}

	protected boolean updateSaleMan(HttpServletRequest request) {
		boolean updateFlag = false;
		String saleManId = request.getParameter("saleManId");
//		用户积分
		String userIntegral = request.getParameter("userIntegral");
//		用户手机号
		String salemanTelNum = request.getParameter("salemanTelNum");
//		真实姓名
		String salemanName = request.getParameter("salemanName");
//		当前级别
//		String codeValue = request.getParameter("codeValue");
//		微信号
		String weixinId = request.getParameter("weixinId");
//		销售员性别
		String salemanSex = request.getParameter("salemanSex");
//		销售员生日
		String salemanBirthday = request.getParameter("salemanBirthday");
//		支付宝账号
		String alipayId = request.getParameter("alipayId");
//		银行卡开户行
		String cardBank = request.getParameter("cardBank");
//		银行卡开户名
		String cardName = request.getParameter("cardName");
//		银行卡号
		String cardNumber = request.getParameter("cardNumber");
//		撤销理由
//		String revokeReason = request.getParameter("revokeReason");
//		总销售额
//		String sumOrderPrice = request.getParameter("sumOrderPrice");

		try {
			StringBuffer sqlRevokeSaleMan = new StringBuffer(" UPDATE  tbl_saleman ");
			sqlRevokeSaleMan.append(" SET user_integral = ? ");
			sqlRevokeSaleMan.append(" , saleman_tel_num = ? ");
			sqlRevokeSaleMan.append(" , saleman_name = ? ");
			sqlRevokeSaleMan.append(" , weixin_id = ? ");
			sqlRevokeSaleMan.append(" , saleman_sex = ? ");
			sqlRevokeSaleMan.append(" , saleman_birthday = ? ");
			sqlRevokeSaleMan.append(" , alipay_id = ? ");
			sqlRevokeSaleMan.append(" , card_bank = ? ");
			sqlRevokeSaleMan.append(" , card_name = ? ");
			sqlRevokeSaleMan.append(" , card_number = ? ");
			sqlRevokeSaleMan.append(" WHERE saleman_id = ? ");
			con = MySQLUtil.getOnlineConnection();
			ps = con.prepareStatement(sqlRevokeSaleMan.toString());
			ps.setString(1, userIntegral);
			ps.setString(2, salemanTelNum);
			ps.setString(3, salemanName);
			ps.setString(4, weixinId);
			ps.setString(5, salemanSex);
			ps.setString(6, salemanBirthday);
			ps.setString(7, alipayId);
			ps.setString(8, cardBank);
			ps.setString(9, cardName);
			ps.setString(10, cardNumber);
			ps.setString(11, saleManId);
			ps.executeUpdate();
			updateFlag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MySQLUtil.closeAll(rs, statement, con, null);
		}

		return updateFlag;
	}
	protected List<List<Map<String, Object>>> getDownSaleList(String saleManId,String saleManLevel) {
		List<List<Map<String, Object>>>  downSaleManList = new ArrayList<List<Map<String,Object>>>();

		int saleManLevelInt = Integer.parseInt(saleManLevel.substring(saleManLevel.length()-1, saleManLevel.length()));

		for(int i = saleManLevelInt - 1;i > 0;i--){
			List<Map<String, Object>> childSaleManList = new ArrayList<Map<String,Object>>();
			try{
				String getDownSaleMan = ""
						+ " SELECT TBS.saleman_id,TBS.saleman_name "
						+ " FROM tbl_saleman TBS "
						+ " WHERE TBS.up_saleman_id = '"+ saleManId +"' "
						+ " AND TBS.saleman_level = 'I00"+ i +"' ";
				con = MySQLUtil.getOnlineConnection();
				statement = con.createStatement();
				rs = statement.executeQuery(getDownSaleMan);
				while (rs.next()) {
					Map<String, Object> saleManMap = new HashMap<String, Object>();
					double mouthSale = bonusDao.selectSaleSum(rs.getString("saleman_id"));
					saleManMap.put("saleManId", rs.getString("saleman_id"));
					saleManMap.put("saleManName", rs.getString("saleman_name"));
					saleManMap.put("mouthSale", mouthSale);
					childSaleManList.add(saleManMap);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				MySQLUtil.closeAll(rs, statement, con, null);
			}
			downSaleManList.add(childSaleManList);
		}
		return downSaleManList;
	}
}
