package com.atongmu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.apache.tomcat.jdbc.pool.DataSource;

public class MySQLUtil {
	/**
	 * 获取数据库连接
	 *
	 * @param rs
	 *            ResultSet对象
	 * @param sm
	 *            Statement对象
	 * @param con
	 *            Connection对象
	 * @param ps
	 *            PreparedStatement对象
	 */
	public static Connection getConnection() {

		Connection connection = null;
		try {
			/***** 填写数据库相关信息(请查找数据库详情页) *****/
			String databaseName = "atongmu";
			String host = "120.26.77.96";
			String port = "19800";
			String username = "atongmu"; // 用户AK
			String password = "Atongmu@db"; // 用户SK
			String driverName = "com.mysql.jdbc.Driver";
			String dbUrl = "jdbc:mysql://";
			String serverName = host + ":" + port + "/";
			String connName = dbUrl + serverName + databaseName;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			/****** 接着连接并选择数据库名为databaseName的服务器 ******/
			Class.forName(driverName);
			connection = (Connection) DriverManager.getConnection(connName, username, password);
		} catch (Exception e) {
			CommonUtil.logger.info(e.getStackTrace());
		}
		return connection;
	}
	/**
	 * 获取数据库连接
	 *
	 * @param rs
	 *            ResultSet对象
	 * @param sm
	 *            Statement对象
	 * @param con
	 *            Connection对象
	 * @param ps
	 *            PreparedStatement对象
	 */
	public static Connection getOnlineConnection() {

		Connection connection = null;
		try {
			/***** 填写数据库相关信息(请查找数据库详情页) *****/
			String databaseName = "atongmu";
			String host = "120.26.77.96";
			String port = "19800";
			String username = "atongmu"; // 用户AK
			String password = "Atongmu@db"; // 用户SK
			String driverName = "com.mysql.jdbc.Driver";
			String dbUrl = "jdbc:mysql://";
			String serverName = host + ":" + port + "/";
			String connName = dbUrl + serverName + databaseName;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			/****** 接着连接并选择数据库名为databaseName的服务器 ******/
			Class.forName(driverName);
			connection = (Connection) DriverManager.getConnection(connName, username, password);
		} catch (Exception e) {
			CommonUtil.logger.info(e.getStackTrace());
		}
		return connection;
	}

	/**
	 * 关闭所有连接
	 *
	 * @param rs
	 *            ResultSet对象
	 * @param sm
	 *            Statement对象
	 * @param con
	 *            Connection对象
	 * @param ps
	 *            PreparedStatement对象
	 */
	public static void closeAll(ResultSet rs, Statement sm, Connection con, PreparedStatement ps) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (sm != null)
				sm.close();
			if (con != null)
				con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 本方法用来执行更新语句，并返回影响了多少行（insert,update,delete）
	 *
	 * @param sql
	 *            传入的sql语句，等待执行
	 * @return 返回执行sql语句后的结果集对象
	 */
	public static int update(Connection conn, String sql) {
		// 执行sql语句前先连接到数据库
		Statement stmt = null;
		int i = 0;
		try {
			// 通过连接对象创建一个语句对象stmt，用来执行sql语句
			stmt = conn.createStatement();
			// 执行更新语句，并返回影响了多少行
			i = stmt.executeUpdate(sql); 
		} catch (Exception e) { // 错误处理，暂时不用理会
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}

	/**
	 * 本函数用来执行用户传入的sql语句(仅限于select语句)
	 *
	 * @param sql
	 *            传入的sql语句，等待执行
	 * @return 返回执行sql语句后的结果集对象
	 */
	public static ResultSet query(Connection conn, String sql) {
		ResultSet rs = null;
		try {
			// 3、通过连接对象创建一个语句对象stmt，用来执行sql语句
			Statement stmt = conn.createStatement();
			// 4、执行sql语句，得到一个rs（结果集对象）
			rs = stmt.executeQuery(sql);
		} catch (Exception e) { // 错误处理，暂时不用理会
			e.printStackTrace();
		}
		return rs; // 将查询得到的结果集对象返回
	}

}