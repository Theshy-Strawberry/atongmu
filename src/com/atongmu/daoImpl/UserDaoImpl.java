package com.atongmu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atongmu.bean.Tbl_user;
import com.atongmu.dao.UserDao;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MakeUnqID;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

public class UserDaoImpl implements UserDao {
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;

	@Override
	public boolean addUser(Tbl_user tbl_user) {
		CommonUtil.logger.info("【mobile】"+"into UserDaoImpl,addUser");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			boolean is = false;
			int tempUserId = MakeUnqID.randomNumber();
			do{
				String sql = "select count(1) as userCount from tbl_user where user_id = 'U"+tempUserId+"'";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()){
					int userCount = rs.getInt("userCount");
					if(userCount > 0){
						is = true;
						tempUserId = MakeUnqID.randomNumber();
					}else{
						is = false;
					}
				}
				statement.close();
				rs.close();
			}while(is);

			StringBuffer sql = new StringBuffer("INSERT INTO tbl_user ");
			sql.append("(user_id, ");
			sql.append(" reg_date,");
			sql.append(" open_id,");
			sql.append(" user_integral,");
			sql.append(" user_name,");
			sql.append(" user_sex,");
			sql.append(" user_form, ");
			sql.append(" transfer_flag,");
			sql.append(" vip_flag");
			sql.append(" ) VALUES ( ");
			sql.append(" ?,?,?,?,?,?,?,?,?) ");
			ps = con.prepareStatement(sql.toString());
			//销售员ID自动生成策略，S + 根据系统时间生成的随机数
			ps.setString(1, "U" + tempUserId);
			ps.setTimestamp(2, new Timestamp(new Date().getTime()));
			ps.setString(3, tbl_user.getOpen_id());
			ps.setDouble(4, 0);
			ps.setString(5, tbl_user.getUser_name());
			ps.setString(6, tbl_user.getUser_sex());
			ps.setString(7, tbl_user.getUserfrom());
			ps.setString(8, "0");
			ps.setString(9, "0");

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

	@Override
	public boolean updateUser(Tbl_user tbl_user) {
		CommonUtil.logger.info("【mobile】"+"into UserDaoImpl,updateUser");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(Tbl_user tbl_user) {
		CommonUtil.logger.info("【mobile】"+"into UserDaoImpl,deleteUser");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tbl_user selectUser(Tbl_user tbl_user) {
		CommonUtil.logger.info("【mobile】"+"into UserDaoImpl,selectUser");
		Tbl_user userInfo = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append("     tbl_user.user_id as user_id,");
			sql.append("     tbl_user.user_pwd as user_pwd,");
			sql.append("     tbl_user.reg_date as reg_date,");
			sql.append("     tbl_user.open_id as open_id,");
			sql.append("     tbl_user.user_integral as user_integral,");
			sql.append("     tbl_user.user_grade as user_grade,");
			sql.append("     tbl_user.user_tel_num as user_tel_num,");
			sql.append("     tbl_user.user_name as user_name,");
			sql.append("     tbl_user.weixin_id as weixin_id,");
			sql.append("     tbl_user.user_sex as user_sex,");
			sql.append("     tbl_user.user_birthday as user_birthday,");
			sql.append("     tbl_user.saleman_id as saleman_id,");
			sql.append("     tbl_user.transfer_flag as transfer_flag,");
			sql.append("     tbl_user.user_addr as user_addr,");
			sql.append("     tbl_user.user_post as user_post,");
			sql.append("     tbl_user.vip_flag as vip_flag,");
			sql.append("     tbl_user.user_form as user_form,");
			sql.append("     tbl_user.user_occupation as user_occupation,");
			sql.append("     tbl_user.user_nation as user_nation,");
			sql.append("     tbl_user.user_education as user_education");
			sql.append(" FROM tbl_user");
			sql.append(" WHERE user_id = ?");
			sql.append("   AND open_id = ?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_user.getUser_id());
			ps.setString(2, tbl_user.getOpen_id());
			rs = ps.executeQuery();

			while (rs.next()) {
				userInfo = new Tbl_user();
				userInfo.setUser_id(StringUtil.nvl(rs.getString("user_id")));
				userInfo.setUser_pwd(StringUtil.nvl(rs.getString("user_pwd")));
				userInfo.setReg_date(rs.getTimestamp("reg_date"));
				userInfo.setOpen_id(StringUtil.nvl(rs.getString("open_id")));
				userInfo.setUser_integral(rs.getDouble("user_integral"));
				userInfo.setUser_grade(StringUtil.nvl(rs.getString("user_grade")));
				userInfo.setUser_tel_num(StringUtil.nvl(rs.getString("user_tel_num")));
				userInfo.setUser_name(StringUtil.nvl(rs.getString("user_name")));
				userInfo.setWeixin_id(StringUtil.nvl(rs.getString("weixin_id")));
				userInfo.setUser_sex(StringUtil.nvl(rs.getString("user_sex")));
				userInfo.setUser_birthday(rs.getTimestamp("user_birthday"));
				userInfo.setSaleman_id(StringUtil.nvl(rs.getString("saleman_id")));
				userInfo.setTransfer_flag(rs.getInt("transfer_flag"));
				userInfo.setUser_addr(StringUtil.nvl(rs.getString("user_addr")));
				userInfo.setUser_post(StringUtil.nvl(rs.getString("user_post")));
				userInfo.setVip_flag(rs.getInt("vip_flag"));
				userInfo.setUserfrom(StringUtil.nvl(rs.getString("user_form")));
				userInfo.setUser_occupation(StringUtil.nvl(rs.getString("user_occupation")));
				userInfo.setUser_nation(StringUtil.nvl(rs.getString("user_nation")));
				userInfo.setUser_education(StringUtil.nvl(rs.getString("user_education")));
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return userInfo;
	}

	@Override
	public List<Tbl_user> selectUser() {
		CommonUtil.logger.info("【mobile】"+"into UserDaoImpl,selectUser");
		List<Tbl_user> user_list = new ArrayList<Tbl_user>();
		Tbl_user userInfo = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append("     tbl_user.user_id as user_id,");
			sql.append("     tbl_user.user_pwd as user_pwd,");
			sql.append("     tbl_user.reg_date as reg_date,");
			sql.append("     tbl_user.open_id as open_id,");
			sql.append("     tbl_user.user_integral as user_integral,");
			sql.append("     tbl_user.user_grade as user_grade,");
			sql.append("     tbl_user.user_tel_num as user_tel_num,");
			sql.append("     tbl_user.user_name as user_name,");
			sql.append("     tbl_user.weixin_id as weixin_id,");
			sql.append("     tbl_user.user_sex as user_sex,");
			sql.append("     tbl_user.user_birthday as user_birthday,");
			sql.append("     tbl_user.saleman_id as saleman_id,");
			sql.append("     tbl_user.transfer_flag as transfer_flag,");
			sql.append("     tbl_user.user_addr as user_addr,");
			sql.append("     tbl_user.user_post as user_post,");
			sql.append("     tbl_user.vip_flag as vip_flag");
			sql.append(" FROM tbl_user");
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				userInfo = new Tbl_user();
				userInfo.setUser_id(StringUtil.nvl(rs.getString("user_id")));
				userInfo.setUser_pwd(StringUtil.nvl(rs.getString("user_pwd")));
				userInfo.setReg_date(rs.getTimestamp("reg_date"));
				userInfo.setOpen_id(StringUtil.nvl(rs.getString("open_id")));
				userInfo.setUser_integral(rs.getDouble("user_integral"));
				userInfo.setUser_grade(StringUtil.nvl(rs.getString("user_grade")));
				userInfo.setUser_tel_num(StringUtil.nvl(rs.getString("user_tel_num")));
				userInfo.setUser_name(StringUtil.nvl(rs.getString("user_name")));
				userInfo.setWeixin_id(StringUtil.nvl(rs.getString("weixin_id")));
				userInfo.setUser_sex(StringUtil.nvl(rs.getString("user_sex")));
				userInfo.setUser_birthday(rs.getTimestamp("user_birthday"));
				userInfo.setSaleman_id(StringUtil.nvl(rs.getString("saleman_id")));
				userInfo.setTransfer_flag(rs.getInt("transfer_flag"));
				userInfo.setUser_addr(StringUtil.nvl(rs.getString("user_addr")));
				userInfo.setUser_post(StringUtil.nvl(rs.getString("user_post")));
				userInfo.setVip_flag(rs.getInt("vip_flag"));
				user_list.add(userInfo);
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return user_list;
	}

}
