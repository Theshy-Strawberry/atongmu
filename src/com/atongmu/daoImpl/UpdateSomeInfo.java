package com.atongmu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import com.atongmu.bean.Tbl_sale_audit;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MakeUnqID;
import com.atongmu.util.MySQLUtil;

public class UpdateSomeInfo {
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;
	/**
	 * ��������Ա����
	 */
	public boolean updateSaleMan(Tbl_saleman tbl_SaleMan) {
		CommonUtil.logger.info("【mobile】"+"into UpdateSomeInfo,updateSaleMan");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("UPDATE tbl_saleman SET");
			sql.append(" saleman_tel_num = ?, ");
			sql.append(" saleman_addr = ?, ");
			sql.append(" saleman_post = ?, ");
			sql.append(" saleman_name = ?, ");
			sql.append(" alipay_id = ?, ");
			sql.append(" weixin_id = ?, ");
			sql.append(" card_bank = ?, ");
			sql.append(" card_name = ?, ");
			sql.append(" card_number = ?, ");
			sql.append(" saleman_birthday = ?, ");
			sql.append(" user_nation = ?, ");
			sql.append(" user_education = ?, ");
			sql.append(" user_occupation = ?, ");
			sql.append(" saleman_sex = ?, ");
			sql.append(" user_form = ? ");
			sql.append(" WHERE saleman_id = ?");
			sql.append("   AND open_id = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_SaleMan.getSaleman_tel_num());
			ps.setString(2, tbl_SaleMan.getSaleman_addr());
			ps.setString(3, tbl_SaleMan.getSaleman_post());
			ps.setString(4, tbl_SaleMan.getSaleman_name());
			ps.setString(5, tbl_SaleMan.getAlipay_id());
			ps.setString(6, tbl_SaleMan.getWeixin_id());
			ps.setString(7, tbl_SaleMan.getCard_bank());
			ps.setString(8, tbl_SaleMan.getCard_name());
			ps.setString(9, tbl_SaleMan.getCard_number());
			ps.setTimestamp(10, new Timestamp(tbl_SaleMan.getSaleman_birthday().getTime()));
			ps.setString(11, tbl_SaleMan.getUser_nation());
			ps.setString(12, tbl_SaleMan.getUser_education());
			ps.setString(13, tbl_SaleMan.getUser_occupation());
			ps.setString(14, tbl_SaleMan.getSaleman_sex());
			ps.setString(15, tbl_SaleMan.getUserfrom());
			ps.setString(16, tbl_SaleMan.getSaleman_id());
			ps.setString(17, tbl_SaleMan.getOpen_id());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return result > 0 ? true : false;
	}
	/**
	 * ��������Աopenid
	 */
	public boolean updateSaleManOpenID(Tbl_saleman tbl_SaleMan) {
		CommonUtil.logger.info("【mobile】"+"into UpdateSomeInfo,updateSaleManOpenID");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("UPDATE tbl_saleman SET");
			sql.append(" open_id = ? ");
			sql.append(" WHERE saleman_id = ?");
			sql.append("   AND weixin_id = ? ");
			sql.append("   AND saleman_tel_num = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_SaleMan.getOpen_id());
			ps.setString(2, tbl_SaleMan.getSaleman_id());
			ps.setString(3, tbl_SaleMan.getWeixin_id());
			ps.setString(4, tbl_SaleMan.getSaleman_tel_num());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return result > 0 ? true : false;
	}
	/**
	 * �����û�����
	 */
	public boolean updateUser(Tbl_user tbl_user) {
		CommonUtil.logger.info("【mobile】"+"into UpdateSomeInfo,updateUser");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("UPDATE tbl_user SET");
			sql.append(" user_tel_num = ?, ");
			sql.append(" user_addr = ?, ");
			sql.append(" user_post = ?, ");
			sql.append(" user_name = ?, ");
			sql.append(" weixin_id = ?, ");
			sql.append(" user_birthday = ?, ");
			sql.append(" user_nation = ?, ");
			sql.append(" user_education = ?, ");
			sql.append(" user_occupation = ?, ");
			sql.append(" user_sex = ?, ");
			sql.append(" user_form = ? ");
			sql.append(" WHERE user_id = ?");
			sql.append("   AND open_id = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_user.getUser_tel_num());
			ps.setString(2, tbl_user.getUser_addr());
			ps.setString(3, tbl_user.getUser_post());
			ps.setString(4, tbl_user.getUser_name());
			ps.setString(5, tbl_user.getWeixin_id());
			ps.setTimestamp(6, new Timestamp(tbl_user.getUser_birthday().getTime()));
			ps.setString(7, tbl_user.getUser_nation());
			ps.setString(8, tbl_user.getUser_education());
			ps.setString(9, tbl_user.getUser_occupation());
			ps.setString(10, tbl_user.getUser_sex());
			ps.setString(11, tbl_user.getUserfrom());
			ps.setString(12, tbl_user.getUser_id());
			ps.setString(13, tbl_user.getOpen_id());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return result > 0 ? true : false;
	}
	/**
	 * ���ϼ�����Ա����
	 */
	public boolean buildUpSaleman(Tbl_user tbl_user) {
		CommonUtil.logger.info("【mobile】"+"into UpdateSomeInfo,buildUpSaleman");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("UPDATE tbl_user SET");
			sql.append(" saleman_id = ?,vip_flag = 1");
			sql.append(" WHERE user_id = ?");
			sql.append("   AND open_id = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_user.getSaleman_id());
			ps.setString(2, tbl_user.getUser_id());
			ps.setString(3, tbl_user.getOpen_id());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return result > 0 ? true : false;
	}
	/**
	 * 根据微信ID，销售员姓名，销售员手机号抽出销售员ID
	 */
	public String selectSaleMan(Tbl_saleman tbl_SaleMan) {
		CommonUtil.logger.info("【mobile】"+"into UpdateSomeInfo,selectSaleMan");
		String saleman_id = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append("   saleman_id as saleman_id");
			sql.append(" FROM tbl_saleman");
			sql.append(" WHERE saleman_name = ?");
			sql.append("   AND weixin_id = ? ");
			sql.append("   AND saleman_tel_num = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_SaleMan.getSaleman_name());
			ps.setString(2, tbl_SaleMan.getWeixin_id());
			ps.setString(3, tbl_SaleMan.getSaleman_tel_num());
			rs = ps.executeQuery();

			while (rs.next()) {
				saleman_id = rs.getString("saleman_id");
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return saleman_id;
	}
	/**
	 * 申请提取
	 */
	public boolean addAuditSaleMan(Tbl_sale_audit tbl_sale_audit) {
		CommonUtil.logger.info("【mobile】"+"into UpdateSomeInfo,addAuditSaleMan");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("INSERT INTO tbl_sale_audit ");
			sql.append("(req_date, ");
			sql.append(" req_user_id, ");
			sql.append(" req_status");
			sql.append(" ) VALUES (");
			sql.append(" ?,?,?");
			sql.append(" )");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_sale_audit.getReq_date().toString());
			ps.setString(2, tbl_sale_audit.getReq_user_id());
			ps.setString(3, tbl_sale_audit.getReq_status());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return result > 0 ? true : false;
	}
	public String selectAuditSaleManStatus(String userid) {
		CommonUtil.logger.info("【mobile】"+"into UpdateSomeInfo,selectAuditSaleManStatus");
		String req_no = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append("   req_no as req_no");
			sql.append(" FROM tbl_sale_audit");
			sql.append(" WHERE req_user_id = ?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1,userid);
			rs = ps.executeQuery();

			while (rs.next()) {
				req_no = rs.getString("req_no");
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return req_no;
	}

}
