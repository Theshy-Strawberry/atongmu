package com.atongmu.daoImpl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.atongmu.dao.GetRoleDao;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;

public class GetRoleDaoImpl implements GetRoleDao {
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;
    /**
     * getManagerRole
     * 获取管理员权限
     * return code
     *  -1：无管理员权限
     *   1：最高管理员
     *   2：区域管理员
     *   3：订单管理员
     *   4: 商品管理员
     */
	@Override
	public int getManagerRole(String managerID) {
		//TODO
		int return_code = -1;
		return return_code;	}
	/**
	 * getUserType
	 * 获取登录用户类型
	 * return code
	 * -1： 无权限登录
	 *  1： 会员
	 *  2： 销售员
	 */
	@Override
	public int getUserType(String userID) {
		CommonUtil.logger.info("【mobile】"+"into GetRoleDaoImpl,getUserType");
		int return_code = -1;
		int select_count = 0;
		try {
			String sql = "select count(user_id) as user_count from tbl_user where user_id = '" + userID +"'";
			sql = sql + " and transfer_flag = 0 ";//设置转移flag=0的条件，代表当前用户是普通用户。
			con = MySQLUtil.getConnection();
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
			if (rs.next()){
				select_count = rs.getInt("user_count");
			}
			if(select_count > 0){
				return_code = 1;//若用户表中有数据，则表示当前登录的用户权限为会员
			}else{
				sql = "";//重置sql
				sql = "select count(saleman_id) as saleman_count from tbl_saleman where saleman_id = '" + userID +"'";
				sql = sql + " and (revoke_date is null or sysdate() > revoke_date)";//当前没有被废止的管理员
				sm = con.createStatement();
				rs = sm.executeQuery(sql);
				if (rs.next()){
					select_count = rs.getInt("saleman_count");
					if(select_count > 0){
						return_code = 2;
					}
				}
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, null);
		}
		return return_code;
	}

	/**
	 * getUserIDByOpenID
	 * 根据openID获取用户ID
	 * return
	 * null：获取失败
	 * UserID：用户ID
	 */
	@Override
	public String getUserIDByOpenID(String openID) {
		CommonUtil.logger.info("【mobile】"+"into GetRoleDaoImpl,getUserIDByOpenID");
		String userID = null;
		try {
			String sql = "select user_id from tbl_user where open_id = '" + openID + "'";
			sql = sql + " and transfer_flag = 0 ";//设置转移flag=0的条件，代表当前用户是普通用户。
			con = MySQLUtil.getConnection();
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
			if (rs.next()){
				userID = rs.getString("user_id");
			}
			if(userID == null){
				sql = "";//重置sql
				sql = "select saleman_id from tbl_saleman where open_id = '" + openID +"'";
				sm = con.createStatement();
				rs = sm.executeQuery(sql);
				if (rs.next()){
					userID = rs.getString("saleman_id");
				}
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, null);
		}
		return userID;
	}

}
