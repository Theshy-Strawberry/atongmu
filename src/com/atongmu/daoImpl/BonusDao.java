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

import com.atongmu.bean.Bonus_bean;
import com.atongmu.bean.Tbl_bonus_audit;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;

public class BonusDao {
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;

	//查询销售员该月营业额\
	public double selectSaleSum(String saleman_id){
		CommonUtil.logger.info("【mobile】"+"into BonusDao,selectSaleSum");
		con = MySQLUtil.getConnection();
		double saleSum =0.00;
		StringBuffer strSQL = new StringBuffer("");
		try{
			strSQL.append(" SELECT");
			strSQL.append(" 	sum(tbl_order.order_price) sum_order_price ");
			strSQL.append(" FROM");
			strSQL.append(" 	tbl_order");
			strSQL.append(" WHERE");
			strSQL.append(" 	tbl_order.order_status = 'D005'");
			strSQL.append(" AND tbl_order.order_user IN (");
			strSQL.append(" 		SELECT");
			strSQL.append(" 			tbl_user.user_id");
			strSQL.append(" 		FROM");
			strSQL.append(" 			tbl_user");
			strSQL.append(" 		WHERE");
			strSQL.append(" 			tbl_user.saleman_id = '"+saleman_id+"'");
			strSQL.append(" 		UNION ALL");
			strSQL.append(" 			SELECT");
			strSQL.append(" 				sub_saleman.saleman_id");
			strSQL.append(" 			FROM ");
			strSQL.append(" 				tbl_saleman sub_saleman");
			strSQL.append(" 			WHERE");
			strSQL.append(" 				sub_saleman.saleman_id = '"+saleman_id+"'");
			strSQL.append(" 	)");
			strSQL.append(" AND date_format(");
			strSQL.append(" 	tbl_order.order_date,'%Y-%m') = date_format(now(), '%Y-%m')");

			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				saleSum = rs.getDouble("sum_order_price");//当月销售额
			}
		}catch(Exception e){
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}

		return saleSum;
	}
	//查询用户或销售月消费额
	public double selectBuySum(String userid){
		CommonUtil.logger.info("【mobile】"+"into BonusDao,selectBuySum");
		con = MySQLUtil.getConnection();
		double buySum =0.00;
		StringBuffer strSQL = new StringBuffer("");
		try{
			strSQL.append(" select SUM(order_price) as buySum from tbl_order");
			strSQL.append(" where order_status not in ('D001','D004') ");
			strSQL.append(" and order_user = ''");
			strSQL.append(" AND date_format(");
			strSQL.append(" 	tbl_order.order_date,'%Y-%m') = date_format(now(), '%Y-%m')");

			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				buySum = rs.getDouble("buySum");//当月消费额
			}
		}catch(Exception e){
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}

		return buySum;
	}

	// 佣金所有信息查询
	public Bonus_bean select_bouns(String saleman_id, String dateYM) {
		CommonUtil.logger.info("【mobile】"+"into BonusDao,select_bouns");
		con = MySQLUtil.getConnection();
		Bonus_bean bonus_info = new Bonus_bean();
		try {
			StringBuffer strSQL = null;
			// 查询可提取佣金（非冻结佣金)和已冻结佣金
			strSQL = new StringBuffer("");
			strSQL.append(" select un_breakBonus.occur_bonus as unbreak_occur_bonus,");
			strSQL.append("        breakBonus.occur_bonus as break_occur_bonus");
			strSQL.append(" from");
			strSQL.append(" (select sum(occur_bonus) as occur_bonus from tbl_bonus_history");
			strSQL.append(" where to_user_id = ?");
			strSQL.append("   and break_flag = '0') un_breakBonus,");
			strSQL.append(" (select sum(occur_bonus) as occur_bonus from tbl_bonus_history");
			strSQL.append(" where to_user_id = ?");
			strSQL.append("   and break_flag = '1') breakBonus");
			ps = con.prepareStatement(strSQL.toString());
			ps.setString(1, saleman_id);
			ps.setString(2, saleman_id);
			rs = ps.executeQuery();
			double activeBouns = 0.00;
			while (rs.next()) {
				// 设置该销售的非冻结佣金和冻结佣金
				activeBouns = rs.getDouble("unbreak_occur_bonus");// 非冻结佣金
				bonus_info.setLockedBouns(rs.getDouble("break_occur_bonus"));// 已冻结佣金
			}
			//查询已经提取出的佣金
			strSQL = new StringBuffer("");
			strSQL.append(" SELECT SUM(req_bouns) AS req_bouns from tbl_bonus_audit where req_user_id = '"+ saleman_id +"'");
			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			if(rs.next()) {
				// 设置已经提取的佣金总额
				double get_bonus_sum = rs.getDouble("req_bouns");
				bonus_info.setSelfGetBonus(get_bonus_sum);
				bonus_info.setActiveBouns(activeBouns - get_bonus_sum);
			}else{
				bonus_info.setSelfGetBonus(0);
			}
			strSQL = new StringBuffer("");
			strSQL.append("   SELECT");
			strSQL.append(" 	A.my_sale_price as my_sum_sale,");
			strSQL.append(" 	B.sale_price as level1_sum_sale,");
			strSQL.append(" 	C.sale_price as level2_sum_sale,");
			strSQL.append(" 	D.sale_price as level3_sum_sale");
			strSQL.append(" FROM");
			strSQL.append(" 	(");
			strSQL.append(" 		SELECT");
			strSQL.append(" 			sum(order_price) AS my_sale_price");
			strSQL.append(" 		FROM");
			strSQL.append(" 			tbl_order,");
			strSQL.append(" 			tbl_saleman");
			strSQL.append(" 		WHERE");
			strSQL.append(" 			tbl_order.order_user IN (");
			strSQL.append(" 				SELECT");
			strSQL.append(" 					tbl_user.user_id");
			strSQL.append(" 				FROM");
			strSQL.append(" 					tbl_user");
			strSQL.append(" 				WHERE");
			strSQL.append(" 					tbl_user.saleman_id = '" + saleman_id + "'");
			strSQL.append(" 				UNION ALL");
			strSQL.append(" 					SELECT");
			strSQL.append(" 						sub_saleman.saleman_id");
			strSQL.append(" 					FROM");
			strSQL.append(" 						tbl_saleman sub_saleman");
			strSQL.append(" 					WHERE");
			strSQL.append(" 						sub_saleman.up_saleman_id = '" + saleman_id + "' ");
			strSQL.append(" 			)");
			strSQL.append(" 		AND tbl_saleman.saleman_id = '" + saleman_id + "' ");
			strSQL.append(" 		AND tbl_order.order_status = 'D005'");
			strSQL.append(" 		AND date_format(tbl_order.order_date,'%Y-%m') = '" + dateYM + "' ");
			strSQL.append(" 	) A,");
			strSQL.append(" 	(");
			strSQL.append(" 		SELECT");
			strSQL.append(" 			sum(order_price) AS sale_price");
			strSQL.append(" 		FROM");
			strSQL.append(" 			tbl_order");
			strSQL.append(" 		WHERE");
			strSQL.append(" 			tbl_order.order_user IN (");
			strSQL.append(" 				SELECT");
			strSQL.append(" 					tbl_user.user_id");
			strSQL.append(" 				FROM");
			strSQL.append(" 					tbl_user");
			strSQL.append(" 				WHERE");
			strSQL.append(" 					tbl_user.saleman_id IN (");
			strSQL.append(" 						SELECT");
			strSQL.append(" 							saleman_id");
			strSQL.append(" 						FROM");
			strSQL.append(" 							tbl_saleman");
			strSQL.append(" 						WHERE");
			strSQL.append(" 							up_saleman_id = '" + saleman_id + "' ");
			strSQL.append(" 						AND saleman_level = 'I002'");
			strSQL.append(" 						AND revoke_date IS NULL");
			strSQL.append(" 					)");
			strSQL.append(" 				UNION ALL");
			strSQL.append(" 					SELECT");
			strSQL.append(" 						sub_saleman.saleman_id");
			strSQL.append(" 					FROM");
			strSQL.append(" 						tbl_saleman sub_saleman");
			strSQL.append(" 					WHERE");
			strSQL.append(" 						sub_saleman.up_saleman_id IN (");
			strSQL.append(" 							SELECT");
			strSQL.append(" 								saleman_id");
			strSQL.append(" 							FROM");
			strSQL.append(" 								tbl_saleman");
			strSQL.append(" 							WHERE");
			strSQL.append(" 								up_saleman_id = '" + saleman_id + "' ");
			strSQL.append(" 							AND saleman_level = 'I002'");
			strSQL.append(" 							AND revoke_date IS NULL");
			strSQL.append(" 						)");
			strSQL.append(" 			)");
			strSQL.append(" 		AND tbl_order.order_status = 'D005'");
			strSQL.append(" 		AND date_format(tbl_order.order_date,'%Y-%m') = '" + dateYM + "'");
			strSQL.append(" 		GROUP BY order_id");
			strSQL.append(" 	) B,");
			strSQL.append(" 	(");
			strSQL.append(" 		SELECT");
			strSQL.append(" 			sum(order_price) AS sale_price");
			strSQL.append(" 		FROM");
			strSQL.append(" 			tbl_order");
			strSQL.append(" 		WHERE");
			strSQL.append(" 			tbl_order.order_user IN (");
			strSQL.append(" 				SELECT");
			strSQL.append(" 					tbl_user.user_id");
			strSQL.append(" 				FROM");
			strSQL.append(" 					tbl_user");
			strSQL.append(" 				WHERE");
			strSQL.append(" 					tbl_user.saleman_id IN (");
			strSQL.append(" 						SELECT");
			strSQL.append(" 							saleman_id");
			strSQL.append(" 						FROM");
			strSQL.append(" 							tbl_saleman");
			strSQL.append(" 						WHERE");
			strSQL.append(" 							up_saleman_id = '" + saleman_id + "' ");
			strSQL.append(" 						AND saleman_level = 'I003'");
			strSQL.append(" 						AND revoke_date IS NULL");
			strSQL.append(" 					)");
			strSQL.append(" 				UNION ALL");
			strSQL.append(" 					SELECT");
			strSQL.append(" 						sub_saleman.saleman_id");
			strSQL.append(" 					FROM");
			strSQL.append(" 						tbl_saleman sub_saleman");
			strSQL.append(" 					WHERE");
			strSQL.append(" 						sub_saleman.up_saleman_id IN (");
			strSQL.append(" 							SELECT");
			strSQL.append(" 								saleman_id");
			strSQL.append(" 							FROM");
			strSQL.append(" 								tbl_saleman");
			strSQL.append(" 							WHERE");
			strSQL.append(" 								up_saleman_id = '" + saleman_id + "' ");
			strSQL.append(" 							AND saleman_level = 'I003'");
			strSQL.append(" 							AND revoke_date IS NULL");
			strSQL.append(" 						)");
			strSQL.append(" 			)");
			strSQL.append(" 		AND tbl_order.order_status = 'D005'");
			strSQL.append(" 		AND date_format(tbl_order.order_date,'%Y-%m') = '" + dateYM + "'");
			strSQL.append(" 		GROUP BY order_id");
			strSQL.append(" 	) C,");
			strSQL.append(" 	(");
			strSQL.append(" 		SELECT");
			strSQL.append(" 			sum(order_price) AS sale_price");
			strSQL.append(" 		FROM");
			strSQL.append(" 			tbl_order");
			strSQL.append(" 		WHERE");
			strSQL.append(" 			tbl_order.order_user IN (");
			strSQL.append(" 				SELECT");
			strSQL.append(" 					tbl_user.user_id");
			strSQL.append(" 				FROM");
			strSQL.append(" 					tbl_user");
			strSQL.append(" 				WHERE");
			strSQL.append(" 					tbl_user.saleman_id IN (");
			strSQL.append(" 						SELECT");
			strSQL.append(" 							saleman_id");
			strSQL.append(" 						FROM");
			strSQL.append(" 							tbl_saleman");
			strSQL.append(" 						WHERE");
			strSQL.append(" 							up_saleman_id = '" + saleman_id + "' ");
			strSQL.append(" 						AND saleman_level = 'I004'");
			strSQL.append(" 						AND revoke_date IS NULL");
			strSQL.append(" 					)");
			strSQL.append(" 				UNION ALL");
			strSQL.append(" 					SELECT");
			strSQL.append(" 						sub_saleman.saleman_id");
			strSQL.append(" 					FROM");
			strSQL.append(" 						tbl_saleman sub_saleman");
			strSQL.append(" 					WHERE");
			strSQL.append(" 						sub_saleman.up_saleman_id IN (");
			strSQL.append(" 							SELECT");
			strSQL.append(" 								saleman_id");
			strSQL.append(" 							FROM");
			strSQL.append(" 								tbl_saleman");
			strSQL.append(" 							WHERE");
			strSQL.append(" 								up_saleman_id = '" + saleman_id + "' ");
			strSQL.append(" 							AND saleman_level = 'I004'");
			strSQL.append(" 							AND revoke_date IS NULL");
			strSQL.append(" 						)");
			strSQL.append(" 			)");
			strSQL.append(" 		AND tbl_order.order_status = 'D005'");
			strSQL.append(" 		AND date_format(tbl_order.order_date,'%Y-%m') = '" + dateYM + "'");
			strSQL.append(" 		GROUP BY order_id");
			strSQL.append(" 	) D");
			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				// 此处设置已经查询到的销售额
				bonus_info.setOwn_turnover(rs.getDouble("my_sum_sale"));
				bonus_info.setSeniorSalesManagerTurnover(rs
						.getDouble("level3_sum_sale"));
				bonus_info.setSalesManagerTurnover(rs
						.getDouble("level2_sum_sale"));
				bonus_info.setSalesturnover(rs.getDouble("level3_sum_sale"));
			}
			strSQL = new StringBuffer("");

			strSQL.append("SELECT");
			strSQL.append("	A.self_bonus_sum,");
			strSQL.append("	B.systeAward,");
			strSQL.append("	C.seniorSalesManagerBonus,");
			strSQL.append("	D.bonusSalesManager,");
			strSQL.append("  E.salesBonus");
			strSQL.append(" FROM ");
			strSQL.append("	(");
			strSQL.append("		SELECT");
			strSQL.append("			sum(occur_bonus) AS self_bonus_sum");
			strSQL.append("		FROM");
			strSQL.append("			tbl_bonus_history");
			strSQL.append("		WHERE");
			strSQL.append("			break_flag = 0");
			strSQL.append("		AND bonus_type = 'K001'");
			strSQL.append("		AND to_user_id = '" + saleman_id + "'");
			strSQL.append("     AND date_format(get_date,'%Y-%m') = '" + dateYM + "'");
			strSQL.append("	) A,");
			strSQL.append("	(");
			strSQL.append("		SELECT");
			strSQL.append("			sum(occur_bonus) AS systeAward");
			strSQL.append("		FROM");
			strSQL.append("			tbl_bonus_history");
			strSQL.append("		WHERE");
			strSQL.append("			break_flag = 0");
			strSQL.append("		AND bonus_type = 'K003'");
			strSQL.append("		AND to_user_id = '" + saleman_id + "'");
			strSQL.append("     AND date_format(get_date,'%Y-%m') = '" + dateYM + "'");
			strSQL.append("	) B,");
			strSQL.append("	(");
			strSQL.append("		SELECT");
			strSQL.append("			sum(occur_bonus) AS seniorSalesManagerBonus");
			strSQL.append("		FROM");
			strSQL.append("			tbl_bonus_history");
			strSQL.append("		WHERE");
			strSQL.append("			break_flag = 0");
			strSQL.append("		AND bonus_type = 'K002'");
			strSQL.append("		AND to_user_id = '" + saleman_id + "'");
			strSQL.append("     AND date_format(get_date,'%Y-%m') = '" + dateYM + "'");
			strSQL.append("		AND from_user_id IN(");
			strSQL.append("			SELECT");
			strSQL.append("				saleman_id");
			strSQL.append("			FROM");
			strSQL.append("				tbl_saleman");
			strSQL.append("			WHERE");
			strSQL.append("				up_saleman_id = '" + saleman_id + "'");
			strSQL.append("			AND saleman_level = 'I004'");
			strSQL.append("			AND revoke_date IS NULL");
			strSQL.append("		)");
			strSQL.append("	) C,");
			strSQL.append("	(");
			strSQL.append("		SELECT");
			strSQL.append("			sum(occur_bonus) AS bonusSalesManager");
			strSQL.append("		FROM");
			strSQL.append("			tbl_bonus_history");
			strSQL.append("		WHERE");
			strSQL.append("			break_flag = 0");
			strSQL.append("		AND bonus_type = 'K002'");
			strSQL.append("     AND date_format(get_date,'%Y-%m') = '" + dateYM + "'");
			strSQL.append("		AND to_user_id IN (");
			strSQL.append("			SELECT");
			strSQL.append("				saleman_id");
			strSQL.append("			FROM");
			strSQL.append("				tbl_saleman");
			strSQL.append("			WHERE");
			strSQL.append("		  up_saleman_id = '" + saleman_id + "'");
			strSQL.append("			AND saleman_level = 'I003'");
			strSQL.append("			AND revoke_date IS NULL");
			strSQL.append("		)");
			strSQL.append("	) D,");
			strSQL.append("	(");
			strSQL.append("		SELECT");
			strSQL.append("			sum(occur_bonus) AS salesBonus");
			strSQL.append("		FROM");
			strSQL.append("			tbl_bonus_history");
			strSQL.append("		WHERE");
			strSQL.append("			break_flag = 0");
			strSQL.append("		AND bonus_type = 'K002'");
			strSQL.append("		AND to_user_id = '" + saleman_id + "'");
			strSQL.append("     AND date_format(get_date,'%Y-%m') = '" + dateYM + "'");
			strSQL.append("		AND from_user_id");
			strSQL.append("		IN (");
			strSQL.append("			SELECT");
			strSQL.append("				saleman_id");
			strSQL.append("			FROM");
			strSQL.append("				tbl_saleman");
			strSQL.append("			WHERE");
			strSQL.append("				up_saleman_id = '" + saleman_id + "'");
			strSQL.append("			AND saleman_level = 'I002'");
			strSQL.append("			AND revoke_date IS NULL");
			strSQL.append("		)");
			strSQL.append("	) E");

			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				// 此处设置已经查询到的销售额
				bonus_info.setTalPrizeMoney(rs.getDouble("self_bonus_sum"));
				bonus_info.setSysteAward(rs.getDouble("systeAward"));
				bonus_info.setSeniorSalesManagerBonus(rs
						.getDouble("seniorSalesManagerBonus"));
				bonus_info.setBonusSalesManager(rs
						.getDouble("bonusSalesManager"));
				bonus_info.setSalesBonus(rs.getDouble("salesBonus"));
			}

		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return bonus_info;
	}

	// 提取佣金
	public int get_bonus(Tbl_bonus_audit bonus_info) {
		CommonUtil.logger.info("【mobile】"+"into BonusDao,get_bonus");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("INSERT INTO tbl_bonus_audit ");
			sql.append("(req_date, ");
			sql.append(" req_user_id, ");
			sql.append(" req_bouns,");
			sql.append(" req_status");
			sql.append(" ) VALUES (");
			sql.append("?,?,?,?)");
			ps = con.prepareStatement(sql.toString());
			ps.setTimestamp(1, new Timestamp(new Date().getTime()));
			ps.setString(2, bonus_info.getReq_user_id());
			ps.setDouble(3, bonus_info.getReq_bouns());
			ps.setString(4, bonus_info.getReq_status());
			result = ps.executeUpdate();
		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return result;
	}
	//显示佣金提取记录
    public List<Tbl_bonus_audit> showBonusExtractRecords(String salesmanId){
    	CommonUtil.logger.info("【mobile】"+"into BonusDao,showBonusExtractRecords");
        List<Tbl_bonus_audit> auditslist = new ArrayList<Tbl_bonus_audit>();
        con = MySQLUtil.getConnection();
        try {
            StringBuffer strSQL = null;
            //查询佣金提取记录
            strSQL = new StringBuffer("");
            strSQL.append(" SELECT                                                       ");
            strSQL.append(" tba.req_no,                                                  ");
            strSQL.append(" tba.req_date,                                                ");
            strSQL.append(" tba.req_bouns,                                               ");
            strSQL.append(" tcm.code_value as req_status                                 ");
            strSQL.append(" FROM tbl_bonus_audit tba INNER JOIN tbl_code_master tcm ON   ");
            strSQL.append(" tba.req_status = tcm.code                                    ");
            strSQL.append(" WHERE req_user_id= ?                                         ");
            strSQL.append(" ORDER BY tba.req_date DESC                                   ");
            ps = con.prepareStatement(strSQL.toString());
            ps.setString(1,salesmanId );
            rs = ps.executeQuery();
            while(rs.next()){
                Tbl_bonus_audit audit = new Tbl_bonus_audit();
                audit.setReq_no(rs.getInt("req_no"));
                audit.setReq_date(rs.getDate("req_date"));
                audit.setReq_bouns(rs.getDouble("req_bouns"));
                audit.setReq_status(rs.getString("req_status"));
                auditslist.add(audit);
            }

        } catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
        } finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
        return auditslist;
    }
}
