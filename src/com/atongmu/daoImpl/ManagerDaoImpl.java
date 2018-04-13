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

import com.atongmu.bean.Tbl_code_master;
import com.atongmu.bean.Tbl_manager;
import com.atongmu.dao.ManagerDao;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;
/**
 * ����Աdao
 * @author Ning
 *
 */
public class ManagerDaoImpl implements ManagerDao {
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;

	@Override
	/**
	 * ��ӹ���Ա
	 */
	public boolean addManager(Tbl_manager tbl_manager) {
		CommonUtil.logger.info("【mobile】"+"into ManagerDaoImpl,addManager");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("INSERT INTO tbl_manager ");
			sql.append("(manager_username, ");
			sql.append(" manager_password, ");
			sql.append(" manager_name,");
			sql.append(" manager_telno,");
			sql.append(" manager_role,");
			sql.append(" manager_home_area,");
			sql.append(" join_date) VALUES (");
			sql.append("?,?,?,?,?,?,?)");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_manager.getManager_username());
			ps.setString(2, tbl_manager.getManager_password());
			ps.setString(3, tbl_manager.getManager_name());
			ps.setString(4, tbl_manager.getManager_telno());
			ps.setString(5, tbl_manager.getManager_role());
			ps.setString(6, tbl_manager.getManager_home_area());
			ps.setTimestamp(7, new Timestamp(new Date().getTime()));
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
	 * ���¹���Ա����
	 */
	@Override
	public boolean updateManager(Tbl_manager tbl_manager) {
		CommonUtil.logger.info("【mobile】"+"into ManagerDaoImpl,updateManager");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("UPDATE tbl_manager SET");
			sql.append(" manager_password = ?, ");
			sql.append(" manager_name = ?,");
			sql.append(" manager_telno = ?,");
			sql.append(" manager_role = ?,");
			sql.append(" manager_home_area = ? ");
			sql.append(" WHERE manager_username = ?");
			sql.append("   AND manager_ID = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_manager.getManager_password());
			ps.setString(2, tbl_manager.getManager_name());
			ps.setString(3, tbl_manager.getManager_telno());
			ps.setString(4, tbl_manager.getManager_role());
			ps.setString(5, tbl_manager.getManager_home_area());
			ps.setString(6, tbl_manager.getManager_username());
			ps.setInt(7, tbl_manager.getManager_ID());
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
	 * ɾ������Ա
	 */
	@Override
	public boolean deleteManager(Tbl_manager tbl_manager) {
		CommonUtil.logger.info("【mobile】"+"into ManagerDaoImpl,deleteManager");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("DELETE FROM tbl_manager ");
			sql.append(" WHERE manager_username = ?");
			sql.append("   AND manager_ID = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_manager.getManager_username());
			ps.setInt(2, tbl_manager.getManager_ID());
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
	 * ����Ա��½
	 */
	@Override
	public Tbl_manager selectManager(Tbl_manager tbl_manager) {
		CommonUtil.logger.info("【mobile】"+"into ManagerDaoImpl,selectManager");
		Tbl_manager manager = null;
		Tbl_code_master manager_role_info = null;
		Tbl_code_master manager_home_area_info = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append(" 	tm.manager_ID as manager_ID,");
			sql.append(" 	tm.manager_username as manager_username,");
			sql.append(" 	tm.manager_password as manager_password,");
			sql.append(" 	tm.manager_name as manager_name,");
			sql.append(" 	tm.manager_telno as manager_telno,");
			sql.append(" 	tm.manager_role as manager_role,");
			sql.append(" 	tm.manager_home_area as manager_home_area,");
			sql.append(" 	tm.join_date as join_date,");
			sql.append(" 	tcm1.code_value as manager_role_info,");
			sql.append(" 	tcm2.code_value as manager_home_area_info");
			sql.append(" FROM");
			sql.append(" 	tbl_manager tm");
			sql.append(" LEFT OUTER JOIN tbl_code_master tcm1 ON tcm1. CODE = tm.manager_role");
			sql.append(" LEFT OUTER JOIN tbl_code_master tcm2 ON tcm2. CODE = tm.manager_home_area");
			sql.append(" WHERE tm.manager_username = ?");
			sql.append("   AND tm.manager_password = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_manager.getManager_username());
			ps.setString(2, tbl_manager.getManager_password());
			rs = ps.executeQuery();

			while (rs.next()) {
				manager = new Tbl_manager();
				manager_role_info = new Tbl_code_master();
				manager_home_area_info = new Tbl_code_master();
				// ����Ȩ����
				manager_role_info.setCode_value(rs.getString("manager_role_info"));
				manager.setManager_role_info(manager_role_info);
				// ���ù�������
				manager_home_area_info.setCode_value("manager_home_area_info");
				manager.setManager_home_area_info(manager_home_area_info);

				manager.setManager_ID(rs.getInt("manager_ID"));
				manager.setManager_name(rs.getString("manager_username"));
				manager.setManager_password(rs.getString("manager_password"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setManager_telno(rs.getString("manager_telno"));
				manager.setManager_role(rs.getString("manager_role"));
				manager.setManager_home_area(rs.getString("manager_home_area_info"));
				manager.setJoin_date(rs.getTimestamp("join_date"));
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return manager;
	}

	/**
	 * ��ѯ����Աһ��
	 */
	@Override
	public List<Tbl_manager> selectManager() {
		CommonUtil.logger.info("【mobile】"+"into ManagerDaoImpl,selectManager");
		Tbl_manager manager = null;
		List<Tbl_manager> manager_list = new ArrayList<Tbl_manager>();
		Tbl_code_master manager_role_info = null;
		Tbl_code_master manager_home_area_info = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append(" 	tm.manager_ID as manager_ID,");
			sql.append(" 	tm.manager_username as manager_username,");
			sql.append(" 	tm.manager_password as manager_password,");
			sql.append(" 	tm.manager_name as manager_name,");
			sql.append(" 	tm.manager_telno as manager_telno,");
			sql.append(" 	tm.manager_role as manager_role,");
			sql.append(" 	tm.manager_home_area as manager_home_area,");
			sql.append(" 	tm.join_date as join_date,");
			sql.append(" 	tcm1.code_value as manager_role_info,");
			sql.append(" 	tcm2.code_value as manager_home_area_info");
			sql.append(" FROM");
			sql.append(" 	tbl_manager tm");
			sql.append(" LEFT OUTER JOIN tbl_code_master tcm1 ON tcm1. CODE = tm.manager_role");
			sql.append(" LEFT OUTER JOIN tbl_code_master tcm2 ON tcm2. CODE = tm.manager_home_area");
			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				manager = new Tbl_manager();
				manager_role_info = new Tbl_code_master();
				manager_home_area_info = new Tbl_code_master();
				// ����Ȩ����
				manager_role_info.setCode_value(rs.getString("manager_role_info"));
				manager.setManager_role_info(manager_role_info);
				// ���ù�������
				manager_home_area_info.setCode_value("manager_home_area_info");
				manager.setManager_home_area_info(manager_home_area_info);

				manager.setManager_ID(rs.getInt("manager_ID"));
				manager.setManager_name(rs.getString("manager_username"));
				manager.setManager_password(rs.getString("manager_password"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setManager_telno(rs.getString("manager_telno"));
				manager.setManager_role(rs.getString("manager_role"));
				manager.setManager_home_area(rs.getString("manager_home_area_info"));
				manager.setJoin_date(rs.getTimestamp("join_date"));
				manager_list.add(manager);
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return manager_list;
	}

	@Override
	public List<Tbl_manager> selectAdminManager() {

		Tbl_manager manager = null;
		List<Tbl_manager> manager_list = new ArrayList<Tbl_manager>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append(" 	tm.manager_ID as manager_ID,");
			sql.append(" 	tm.manager_username as manager_username,");
			sql.append(" 	tm.manager_password as manager_password,");
			sql.append(" 	tm.manager_name as manager_name,");
			sql.append(" 	tm.manager_telno as manager_telno,");
			sql.append(" 	tm.manager_role as manager_role,");
			sql.append(" 	tm.manager_home_area as manager_home_area,");
			sql.append(" 	tm.join_date as join_date,");
			sql.append(" 	tcm1.code_value as manager_role_info,");
			sql.append(" 	tcm2.code_value as manager_home_area_info");
			sql.append(" FROM");
			sql.append(" 	tbl_manager tm");
			sql.append(" LEFT OUTER JOIN tbl_code_master tcm1 ON tcm1. CODE = tm.manager_role");
			sql.append(" LEFT OUTER JOIN tbl_code_master tcm2 ON tcm2. CODE = tm.manager_home_area");
			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				manager = new Tbl_manager();

				manager.setManager_ID(rs.getInt("manager_ID"));
				manager.setManager_username(rs.getString("manager_username"));
				manager.setManager_password(rs.getString("manager_password"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setManager_telno(rs.getString("manager_telno"));
				manager.setManager_role(rs.getString("manager_role"));
				manager.setManager_home_area(rs.getString("manager_home_area"));
				manager.setJoin_date(rs.getTimestamp("join_date"));
				manager_list.add(manager);
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return manager_list;
	
	}

	@Override
	public List<Tbl_code_master> dropdownroleList() {

		
		List<Tbl_code_master> manager_list = new ArrayList<Tbl_code_master>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append("   SELECT                          ");
			sql.append("	code,code_value                ");
			sql.append("  FROM                             ");
			sql.append("	tbl_code_master                ");
			sql.append("   WHERE                           ");
			sql.append("	code_no IN (81, 82, 83, 84)    ");

			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				Tbl_code_master master = new Tbl_code_master();
				master.setCode(rs.getString("code"));
				master.setCode_value(rs.getString("code_value"));
				manager_list.add(master);
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return manager_list;
	
	}

	@Override
	public List<Tbl_code_master> dropdownhomeareaList() {

		
		List<Tbl_code_master> manager_list = new ArrayList<Tbl_code_master>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append("   SELECT                          ");
			sql.append("	code,code_value                ");
			sql.append("  FROM                             ");
			sql.append("	tbl_code_master                ");
			sql.append("   WHERE                           ");
			sql.append("	code_no IN (85,86,87,88,89)    ");

			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				Tbl_code_master master = new Tbl_code_master();
				master.setCode(rs.getString("code"));
				master.setCode_value(rs.getString("code_value"));
				manager_list.add(master);
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return manager_list;
	
	}

}
