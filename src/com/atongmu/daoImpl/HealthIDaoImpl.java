package com.atongmu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.atongmu.bean.Tbl_health_info;
import com.atongmu.bean.Tbl_health_investigation;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;

public class HealthIDaoImpl {
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;

	public Tbl_health_info getHealthInfo(String openId,String dateYM){
		Tbl_health_info  healthInfo = null;
		CommonUtil.logger.info("【mobile】"+"into HealthIDaoImpl,getHealthInfo");
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append("open_id, ");
			sql.append("reg_date, ");
			sql.append("height, ");
			sql.append("weight, ");
			sql.append("BMI, ");
			sql.append("waist, ");
			sql.append("hipline, ");
			sql.append("waist_to_hip_ratio, ");
			sql.append("blood_pressure, ");
			sql.append("fasting_blood_glucose, ");
			sql.append("triglyceride, ");
			sql.append("total_cholesterol, ");
			sql.append("other, ");
			sql.append("ask1, ");
			sql.append("ask2, ");
			sql.append("ask3, ");
			sql.append("ask4, ");
			sql.append("ask5, ");
			sql.append("ask6, ");
			sql.append("ask7, ");
			sql.append("ask8, ");
			sql.append("ask9, ");
			sql.append("ask10, ");
			sql.append("ask11, ");
			sql.append("ask12, ");
			sql.append("ask13, ");
			sql.append("ask14, ");
			sql.append("ask15, ");
			sql.append("ask16, ");
			sql.append("ask17, ");
			sql.append("ask18, ");
			sql.append("ask19, ");
			sql.append("ask20, ");
			sql.append("ask21, ");
			sql.append("ask22, ");
			sql.append("ask23, ");
			sql.append("ask24, ");
			sql.append("ask25, ");
			sql.append("ask26, ");
			sql.append("ask27, ");
			sql.append("ask28, ");
			sql.append("ask29, ");
			sql.append("ask30, ");
			sql.append("ask31, ");
			sql.append("ask32, ");
			sql.append("ask33, ");
			sql.append("ask34, ");
			sql.append("ask35, ");
			sql.append("ask36, ");
			sql.append("ask37, ");
			sql.append("ask38, ");
			sql.append("ask39, ");
			sql.append("ask40, ");
			sql.append("ask41, ");
			sql.append("ask42, ");
			sql.append("ask43, ");
			sql.append("ask44, ");
			sql.append("ask45, ");
			sql.append("ask46 ");
			sql.append(" FROM tbl_health_info ");
			sql.append(" WHERE ");
			sql.append(" open_id = ?");
			if(!"".equals(dateYM)){
				sql.append("  AND date_format(reg_date,'%Y-%m') = '"+dateYM+"'");
			}
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, openId);
			rs = ps.executeQuery();

			while (rs.next()) {
				healthInfo = new Tbl_health_info();
				healthInfo.setOpen_id(StringUtil.nvl(rs.getString("open_id")));
				healthInfo.setReg_date(rs.getTimestamp("reg_date"));
				healthInfo.setHeight(StringUtil.nvl(rs.getString("height")));
				healthInfo.setWeight(StringUtil.nvl(rs.getString("weight")));
				healthInfo.setBMI(rs.getDouble("BMI"));
				healthInfo.setWaist(StringUtil.nvl(rs.getString("waist")));
				healthInfo.setHipline(StringUtil.nvl(rs.getString("hipline")));
				healthInfo.setWaist_to_hip_ratio(rs.getDouble("waist_to_hip_ratio"));
				healthInfo.setLow_blood_pressure(StringUtil.nvl(rs.getString("low_blood_pressure")));
				healthInfo.setHigh_blood_pressure(StringUtil.nvl(rs.getString("high_blood_pressure")));
				healthInfo.setFasting_blood_glucose(StringUtil.nvl(rs.getString("fasting_blood_glucose")));
				healthInfo.setTriglyceride(StringUtil.nvl(rs.getString("triglyceride")));
				healthInfo.setTotal_cholesterol(StringUtil.nvl(rs.getString("total_cholesterol")));
				healthInfo.setOther(StringUtil.nvl(rs.getString("other")));
				healthInfo.setAsk1(StringUtil.nvl(rs.getString("ask1")));
				healthInfo.setAsk2(StringUtil.nvl(rs.getString("ask2")));
				healthInfo.setAsk3(StringUtil.nvl(rs.getString("ask3")));
				healthInfo.setAsk4(StringUtil.nvl(rs.getString("ask4")));
				healthInfo.setAsk5(StringUtil.nvl(rs.getString("ask5")));
				healthInfo.setAsk6(StringUtil.nvl(rs.getString("ask6")));
				healthInfo.setAsk7(StringUtil.nvl(rs.getString("ask7")));
				healthInfo.setAsk8(StringUtil.nvl(rs.getString("ask8")));
				healthInfo.setAsk9(StringUtil.nvl(rs.getString("ask9")));
				healthInfo.setAsk10(StringUtil.nvl(rs.getString("ask10")));
				healthInfo.setAsk11(StringUtil.nvl(rs.getString("ask11")));
				healthInfo.setAsk12(StringUtil.nvl(rs.getString("ask12")));
				healthInfo.setAsk13(StringUtil.nvl(rs.getString("ask13")));
				healthInfo.setAsk14(StringUtil.nvl(rs.getString("ask14")));
				healthInfo.setAsk15(StringUtil.nvl(rs.getString("ask15")));
				healthInfo.setAsk16(StringUtil.nvl(rs.getString("ask16")));
				healthInfo.setAsk17(StringUtil.nvl(rs.getString("ask17")));
				healthInfo.setAsk18(StringUtil.nvl(rs.getString("ask18")));
				healthInfo.setAsk19(StringUtil.nvl(rs.getString("ask19")));
				healthInfo.setAsk20(StringUtil.nvl(rs.getString("ask20")));
				healthInfo.setAsk21(StringUtil.nvl(rs.getString("ask21")));
				healthInfo.setAsk22(StringUtil.nvl(rs.getString("ask22")));
				healthInfo.setAsk23(StringUtil.nvl(rs.getString("ask23")));
				healthInfo.setAsk24(StringUtil.nvl(rs.getString("ask24")));
				healthInfo.setAsk25(StringUtil.nvl(rs.getString("ask25")));
				healthInfo.setAsk26(StringUtil.nvl(rs.getString("ask26")));
				healthInfo.setAsk27(StringUtil.nvl(rs.getString("ask27")));
				healthInfo.setAsk28(StringUtil.nvl(rs.getString("ask28")));
				healthInfo.setAsk29(StringUtil.nvl(rs.getString("ask29")));
				healthInfo.setAsk30(StringUtil.nvl(rs.getString("ask30")));
				healthInfo.setAsk31(StringUtil.nvl(rs.getString("ask31")));
				healthInfo.setAsk32(StringUtil.nvl(rs.getString("ask32")));
				healthInfo.setAsk33(StringUtil.nvl(rs.getString("ask33")));
				healthInfo.setAsk34(StringUtil.nvl(rs.getString("ask34")));
				healthInfo.setAsk35(StringUtil.nvl(rs.getString("ask35")));
				healthInfo.setAsk36(StringUtil.nvl(rs.getString("ask36")));
				healthInfo.setAsk37(StringUtil.nvl(rs.getString("ask37")));
				healthInfo.setAsk38(StringUtil.nvl(rs.getString("ask38")));
				healthInfo.setAsk39(StringUtil.nvl(rs.getString("ask39")));
				healthInfo.setAsk40(StringUtil.nvl(rs.getString("ask40")));
				healthInfo.setAsk41(StringUtil.nvl(rs.getString("ask41")));
				healthInfo.setAsk42(StringUtil.nvl(rs.getString("ask42")));
				healthInfo.setAsk43(StringUtil.nvl(rs.getString("ask43")));
				healthInfo.setAsk44(StringUtil.nvl(rs.getString("ask44")));
				healthInfo.setAsk45(StringUtil.nvl(rs.getString("ask45")));
			}
		} catch (SQLException e) {
			CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return healthInfo;
	}
	public int insertHealthInfo(Tbl_health_info  healthInfo){
		int result = 0;
		CommonUtil.logger.info("【mobile】"+"into HealthIDaoImpl,insertHealthInfo");
		Configuration conf = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// 1.读取配置文件
			conf = new Configuration().configure();
			// 2.创建SessionFactory
			sessionFactory = conf.buildSessionFactory();
			// 3. 打开session
			session = sessionFactory.openSession();
			// 4. 开始一个事务
			tx = session.beginTransaction();
			// 5. 持久化操作
			session.save(healthInfo);
			// 6. 提交事务
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// 6. 回滚事务
			tx.rollback();
		} finally {
			// 7. 关闭session
			if (session != null)
				session.close();
		}

		return result;
	}
	//加载combobox用的数据，把所有的问卷抽出出来
	public List<Map> getQuestionList(){
		List<Map> questionList = new ArrayList<Map>();
		CommonUtil.logger.info("【mobile】"+"into HealthIDaoImpl,getQuestionList");
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append(" 	thi.goods_id,");
			sql.append(" 	tc.goods_name");
			sql.append(" FROM");
			sql.append(" 	tbl_health_investigation thi inner join tbl_commodity_category tc");
			sql.append(" WHERE thi.goods_id = tc.goods_id");
			sql.append(" group by thi.goods_id");
			ps = con.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            if(rs.next()){
            	Map questionMap = new HashMap();
            	questionMap.put("goods_id", StringUtil.nvl(rs.getString("goods_id")));
            	questionMap.put("goods_name", StringUtil.nvl(rs.getString("goods_name")));
            	questionList.add(questionMap);
            }
		} catch (Exception e) {
			CommonUtil.logger.error(e.getMessage());
		}
		finally{
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return questionList;
	}
	//根据商品ID，获取相应的问卷信息
	public List<Tbl_health_investigation> getTbl_health_investigation(int goods_id){
		CommonUtil.logger.info("【mobile】"+"into HealthIDaoImpl,getTbl_health_investigation");
		Configuration conf = null;
		SessionFactory sessionFactory = null;
		List<Tbl_health_investigation> healthInvestigationsList = null;
		Session session = null;
		try {
			// 1.读取配置文件
			conf = new Configuration().configure();
			// 2.创建SessionFactory
			sessionFactory = conf.buildSessionFactory();
			// 3. 打开session
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Tbl_health_investigation where goods_id = :goods_id");
			query.setParameter("goods_id",goods_id);
			healthInvestigationsList  = query.list();
			for(int i =0 ;i<healthInvestigationsList.size();i++){
				System.out.println(healthInvestigationsList.get(i).getQuestion());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			// 6. 回滚事务
		} finally {
			// 7. 关闭session
			if (session != null)
				session.close();
		}
		return healthInvestigationsList;
	}
}
