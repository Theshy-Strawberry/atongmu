package com.atongmu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DateUtil {

	public static Date getSysDate() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date sysdate = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT SYSDATE() as dbdate ");
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				sysdate = rs.getTimestamp("dbdate");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.closeAll(rs, null, con, ps);
		}
		return sysdate;
	}
	public static int getSysYear(){
		int sysyear = 0;
		sysyear = Integer.parseInt(getSysDate().toString().substring(0, 4));
		return sysyear;
	}
	public static int getSysMonth(){
		int sysmonth = 0;
		sysmonth = Integer.parseInt(getSysDate().toString().substring(5, 7));
		return sysmonth;
	}
	public static int getSysDay(){
		int sysmonth = 0;
		sysmonth = Integer.parseInt(getSysDate().toString().substring(8, 10));
		return sysmonth;
	}
}
