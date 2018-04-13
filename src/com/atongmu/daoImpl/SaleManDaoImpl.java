package com.atongmu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atongmu.bean.Tbl_code_master;
import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;
import com.atongmu.dao.SaleManDao;
import com.atongmu.util.CommonUtil;
import com.atongmu.util.MakeUnqID;
import com.atongmu.util.MySQLUtil;
import com.atongmu.util.StringUtil;
/**
 * 销售员Dao
 * @author Ning
 *
 */
public class SaleManDaoImpl implements SaleManDao {
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement sm = null;
	private ResultSet rs = null;

	@Override
	/**
	 * 添加销售员
	 */
	public boolean addSaleMan(Tbl_saleman tbl_SaleMan) {
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,addSaleMan");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			boolean is = false;
			int tempUserId = MakeUnqID.randomNumber();
			do{
				String sql = "select count(1) as userCount from tbl_saleman where saleman_id = 'S"+tempUserId+"'";
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

			StringBuffer sql = new StringBuffer("INSERT INTO tbl_saleman ");
			sql.append("(saleman_id, ");
			sql.append(" saleman_pwd, ");
			sql.append(" reg_date,");
			sql.append(" open_id,");
			sql.append(" user_integral,");
			sql.append(" up_saleman_id,");
			sql.append(" saleman_tel_num,");
			sql.append(" saleman_addr,");
			sql.append(" saleman_post,");
			sql.append(" saleman_name,");
			sql.append(" weixin_id,");
			sql.append(" saleman_sex,");
			sql.append(" saleman_birthday,");
			sql.append(" alipay_id,");
			sql.append(" tenpay_id,");
			sql.append(" card_bank,");
			sql.append(" card_name,");
			sql.append(" card_number,");
			sql.append(" saleman_level");
			sql.append(" ) VALUES ( ");
			sql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			ps = con.prepareStatement(sql.toString());
			//销售员ID自动生成策略，S + 根据系统时间生成的随机数
			ps.setString(1, "S" + tempUserId);
			ps.setString(2, tbl_SaleMan.getSaleman_pwd());
			ps.setTimestamp(3, new Timestamp(new Date().getTime()));
			//TODO OpenID在Servlet取得
			ps.setString(4, tbl_SaleMan.getOpen_id());
			//用户积分
			ps.setDouble(5, tbl_SaleMan.getUser_integral());
			//上级销售员ID
			ps.setString(6, tbl_SaleMan.getUp_saleman_id());
			ps.setString(7, tbl_SaleMan.getSaleman_tel_num());
			ps.setString(8, tbl_SaleMan.getSaleman_addr());
			ps.setString(9, tbl_SaleMan.getSaleman_post());
			ps.setString(10, tbl_SaleMan.getSaleman_name());
			ps.setString(11, tbl_SaleMan.getWeixin_id());
			ps.setString(12, tbl_SaleMan.getSaleman_sex());
			ps.setTimestamp(13, new Timestamp(tbl_SaleMan.getSaleman_birthday().getTime()));
			ps.setString(14, tbl_SaleMan.getAlipay_id());
			ps.setString(15, tbl_SaleMan.getTenpay_id());
			ps.setString(16, tbl_SaleMan.getCard_bank());
			ps.setString(17, tbl_SaleMan.getCard_name());
			ps.setString(18, tbl_SaleMan.getCard_number());
			ps.setString(19, "I002");
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
	 * 更新销售员资料
	 */
	@Override
	public boolean updateSaleMan(Tbl_saleman tbl_SaleMan) {
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,updateSaleMan");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("UPDATE tbl_saleman SET");
			sql.append(" saleman_pwd = ?, ");
			sql.append(" user_integral = ?, ");
			sql.append(" up_saleman_id = ?, ");
			sql.append(" saleman_tel_num = ?, ");
			sql.append(" saleman_addr = ?, ");
			sql.append(" saleman_post = ?, ");
			sql.append(" saleman_name = ?, ");
			sql.append(" alipay_id = ?, ");
			sql.append(" card_bank = ?, ");
			sql.append(" card_name = ?, ");
			sql.append(" card_number = ?, ");
			sql.append(" wages = ?, ");
			sql.append(" revoke_date = ?, ");
			sql.append(" revoke_reason = ?, ");
			sql.append(" home_area = ? ");
			sql.append(" WHERE saleman_id = ?");
			sql.append("   AND weixin_id = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_SaleMan.getSaleman_pwd());
			ps.setDouble(2, tbl_SaleMan.getUser_integral());
			ps.setString(3, tbl_SaleMan.getUp_saleman_id());
			ps.setString(4, tbl_SaleMan.getSaleman_tel_num());
			ps.setString(5, tbl_SaleMan.getSaleman_addr());
			ps.setString(6, tbl_SaleMan.getSaleman_post());
			ps.setString(7, tbl_SaleMan.getSaleman_name());
			ps.setString(8, tbl_SaleMan.getAlipay_id());
			ps.setString(9, tbl_SaleMan.getCard_bank());
			ps.setString(10, tbl_SaleMan.getCard_name());
			ps.setString(11, tbl_SaleMan.getCard_number());
			ps.setDouble(12, tbl_SaleMan.getWages());
			ps.setTimestamp(13, new Timestamp(tbl_SaleMan.getRevoke_date().getTime()));
			ps.setString(14, tbl_SaleMan.getRevoke_reason());
			ps.setString(15, tbl_SaleMan.getHome_area());
			ps.setString(16, tbl_SaleMan.getSaleman_id());
			ps.setString(17, tbl_SaleMan.getWeixin_id());
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
	 * 删除销售员
	 */
	@Override
	public boolean deleteSaleMan(Tbl_saleman tbl_SaleMan) {
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,deleteSaleMan");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("DELETE FROM tbl_SaleMan ");
			sql.append(" WHERE saleman_id = ?");
			sql.append("   AND weixin_id = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_SaleMan.getSaleman_id());
			ps.setString(2, tbl_SaleMan.getWeixin_id());
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
	 * 销售员登陆
	 */
	@Override
	public Tbl_saleman selectSaleMan(Tbl_saleman tbl_SaleMan) {
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,selectSaleMan");
		Tbl_saleman saleMan = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append("     tbl_saleman.saleman_id as saleman_id,");
			sql.append("     tbl_saleman.saleman_pwd as saleman_pwd,");
			sql.append("     tbl_saleman.reg_date as reg_date,");
			sql.append("     tbl_saleman.open_id as open_id,");
			sql.append("     tbl_saleman.user_integral as user_integral,");
			sql.append("     tbl_saleman.up_saleman_id as up_saleman_id,");
			sql.append("     tbl_saleman.saleman_tel_num as saleman_tel_num,");
			sql.append("     tbl_saleman.saleman_addr as saleman_addr,");
			sql.append("     tbl_saleman.saleman_post as saleman_post,");
			sql.append("     tbl_saleman.saleman_name as saleman_name,");
			sql.append("     tbl_saleman.weixin_id as weixin_id,");
			sql.append("     tbl_saleman.saleman_sex as saleman_sex,");
			sql.append("     tbl_saleman.saleman_birthday as saleman_birthday,");
			sql.append("     tbl_saleman.alipay_id as alipay_id,");
			sql.append("     tbl_saleman.tenpay_id as tenpay_id,");
			sql.append("     tbl_saleman.card_bank as card_bank,");
			sql.append("     tbl_saleman.card_name as card_name,");
			sql.append("     tbl_saleman.card_number as card_number,");
			sql.append("     tbl_saleman.wages as wages,");
			sql.append("     tbl_saleman.revoke_date as revoke_date,");
			sql.append("     tbl_saleman.revoke_reason as revoke_reason,");
			sql.append("     tbl_saleman.home_area as home_area_code,");
			sql.append("     tbl_saleman.saleman_level, ");
			sql.append("     tbl_saleman.user_form, ");
			sql.append("     tbl_saleman.user_occupation, ");
			sql.append("     tbl_saleman.user_nation, ");
			sql.append("     tbl_saleman.user_education ");
			sql.append(" FROM tbl_saleman");
			sql.append(" WHERE saleman_id = ?");
			sql.append("   AND open_id = ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tbl_SaleMan.getSaleman_id());
			ps.setString(2, tbl_SaleMan.getOpen_id());
			rs = ps.executeQuery();

			while (rs.next()) {
				saleMan = new Tbl_saleman();
				saleMan.setSaleman_id(StringUtil.nvl(rs.getString("saleman_id")));
				System.out.println("salemanID = " + StringUtil.nvl(rs.getString("saleman_id")));
				saleMan.setSaleman_pwd(StringUtil.nvl(rs.getString("saleman_pwd")));
				saleMan.setReg_date(rs.getTimestamp("reg_date"));
				saleMan.setOpen_id(StringUtil.nvl(rs.getString("open_id")));
				saleMan.setUser_integral(rs.getDouble("user_integral"));
				saleMan.setUp_saleman_id(StringUtil.nvl(rs.getString("up_saleman_id")));
				saleMan.setSaleman_tel_num(StringUtil.nvl(rs.getString("saleman_tel_num")));
				saleMan.setSaleman_addr(StringUtil.nvl(rs.getString("saleman_addr")));
				saleMan.setSaleman_post(StringUtil.nvl(rs.getString("saleman_post")));
				saleMan.setSaleman_name(StringUtil.nvl(rs.getString("saleman_name")));
				saleMan.setWeixin_id(StringUtil.nvl(rs.getString("weixin_id")));
				saleMan.setSaleman_sex(StringUtil.nvl(rs.getString("saleman_sex")));
				saleMan.setSaleman_birthday(rs.getTimestamp("saleman_birthday"));
				saleMan.setAlipay_id(StringUtil.nvl(rs.getString("alipay_id")));
				saleMan.setTenpay_id(StringUtil.nvl(rs.getString("tenpay_id")));
				saleMan.setCard_bank(StringUtil.nvl(rs.getString("card_bank")));
				saleMan.setCard_name(StringUtil.nvl(rs.getString("card_name")));
				saleMan.setCard_number(StringUtil.nvl(rs.getString("card_number")));
				saleMan.setWages(rs.getDouble("wages"));
				saleMan.setRevoke_date(rs.getTimestamp("revoke_date"));
				saleMan.setRevoke_reason(StringUtil.nvl(rs.getString("revoke_reason")));
				saleMan.setHome_area(StringUtil.nvl(rs.getString("home_area_code")));
				saleMan.setSaleman_level(StringUtil.nvl(rs.getString("saleman_level")));
				saleMan.setUserfrom(StringUtil.nvl(rs.getString("user_form")));
				saleMan.setUser_occupation(StringUtil.nvl(rs.getString("user_occupation")));
				saleMan.setUser_nation(StringUtil.nvl(rs.getString("user_nation")));
				saleMan.setUser_education(StringUtil.nvl(rs.getString("user_education")));
			}
		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return saleMan;
	}
	/**
	 * 销售员资料抽出
	 */
	@Override
	public Tbl_saleman selectSaleManByID(String salemanID) {
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,selectSaleManByID");
		Tbl_saleman saleMan = null;
		Tbl_code_master saleMan_home_area_info = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append("     tbl_saleman.saleman_id as saleman_id,");
			sql.append("     tbl_saleman.saleman_tel_num as saleman_tel_num,");
			sql.append("     tbl_saleman.saleman_name as saleman_name,");
			sql.append("     tbl_saleman.weixin_id as weixin_id,");
			sql.append("     tbl_saleman.home_area as home_area_code,");
			sql.append("     tcm1.code_value as home_area_name");
			sql.append(" FROM tbl_saleman");
			sql.append(" LEFT JOIN tbl_code_master tcm1 ON tcm1. CODE = tbl_saleman.home_area");
			sql.append(" WHERE saleman_id = ?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, salemanID);
			rs = ps.executeQuery();

			while (rs.next()) {
				saleMan = new Tbl_saleman();
				saleMan.setSaleman_id(StringUtil.nvl(rs.getString("saleman_id")));
				saleMan.setSaleman_tel_num(StringUtil.nvl(rs.getString("saleman_tel_num")));
				saleMan.setSaleman_name(StringUtil.nvl(rs.getString("saleman_name")));
				saleMan.setWeixin_id(StringUtil.nvl(rs.getString("weixin_id")));
				saleMan.setHome_area(StringUtil.nvl(rs.getString("home_area_code")));
				//设置区域信息
				saleMan_home_area_info = new Tbl_code_master();
				saleMan_home_area_info.setCode_value(StringUtil.nvl(rs.getString("home_area_name")));
				saleMan.setHome_area_info(saleMan_home_area_info);
			}
		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return saleMan;
	}

	/**
	 * 查询销售员一览
	 */
	@Override
	public List<Tbl_saleman> selectSaleMan() {
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,selectSaleMan");
		Tbl_saleman saleMan = null;
		List<Tbl_saleman> saleMan_list = new ArrayList<Tbl_saleman>();
		Tbl_code_master saleMan_home_area_info = null;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			sql.append(" SELECT");
			sql.append("     tbl_saleman.saleman_id as saleman_id,");
			sql.append("     tbl_saleman.saleman_pwd as saleman_pwd,");
			sql.append("     tbl_saleman.reg_date as reg_date,");
			sql.append("     tbl_saleman.open_id as open_id,");
			sql.append("     tbl_saleman.user_integral as user_integral,");
			sql.append("     tbl_saleman.up_saleman_id as up_saleman_id,");
			sql.append("     tbl_saleman.saleman_tel_num as saleman_tel_num,");
			sql.append("     tbl_saleman.saleman_addr as saleman_addr,");
			sql.append("     tbl_saleman.saleman_post as saleman_post,");
			sql.append("     tbl_saleman.saleman_name as saleman_name,");
			sql.append("     tbl_saleman.weixin_id as weixin_id,");
			sql.append("     tbl_saleman.saleman_sex as saleman_sex,");
			sql.append("     tbl_saleman.saleman_birthday as saleman_birthday,");
			sql.append("     tbl_saleman.alipay_id as alipay_id,");
			sql.append("     tbl_saleman.tenpay_id as tenpay_id,");
			sql.append("     tbl_saleman.card_bank as card_bank,");
			sql.append("     tbl_saleman.card_name as card_name,");
			sql.append("     tbl_saleman.card_number as card_number,");
			sql.append("     tbl_saleman.wages as wages,");
			sql.append("     tbl_saleman.revoke_date as revoke_date,");
			sql.append("     tbl_saleman.revoke_reason as revoke_reason,");
			sql.append("     tbl_saleman.home_area as home_area_code,");
			sql.append("     tcm1.code_value as home_area_name,");
			sql.append("     tbl_saleman.saleman_level, ");
			sql.append("     tbl_saleman.user_form, ");
			sql.append("     tbl_saleman.user_occupation, ");
			sql.append("     tbl_saleman.user_nation, ");
			sql.append("     tbl_saleman.user_education ");
			sql.append(" FROM tbl_saleman");
			sql.append(" INNER JOIN tbl_code_master tcm1 ON tcm1. CODE = tbl_saleman.home_area");
			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				saleMan = new Tbl_saleman();
				saleMan.setSaleman_id(StringUtil.nvl(rs.getString("saleman_id")));
				saleMan.setSaleman_pwd(StringUtil.nvl(rs.getString("saleman_pwd")));
				saleMan.setReg_date(rs.getTimestamp("reg_date"));
				saleMan.setOpen_id(StringUtil.nvl(rs.getString("open_id")));
				saleMan.setUser_integral(rs.getDouble("user_integral"));
				saleMan.setUp_saleman_id(StringUtil.nvl(rs.getString("up_saleman_id")));
				saleMan.setSaleman_tel_num(StringUtil.nvl(rs.getString("saleman_tel_num")));
				saleMan.setSaleman_addr(StringUtil.nvl(rs.getString("saleman_addr")));
				saleMan.setSaleman_post(StringUtil.nvl(rs.getString("saleman_post")));
				saleMan.setSaleman_name(StringUtil.nvl(rs.getString("saleman_name")));
				saleMan.setWeixin_id(StringUtil.nvl(rs.getString("weixin_id")));
				saleMan.setSaleman_sex(StringUtil.nvl(rs.getString("saleman_sex")));
				saleMan.setSaleman_birthday(rs.getTimestamp("saleman_birthday"));
				saleMan.setAlipay_id(StringUtil.nvl(rs.getString("alipay_id")));
				saleMan.setTenpay_id(StringUtil.nvl(rs.getString("tenpay_id")));
				saleMan.setCard_bank(StringUtil.nvl(rs.getString("card_bank")));
				saleMan.setCard_name(StringUtil.nvl(rs.getString("card_name")));
				saleMan.setCard_number(StringUtil.nvl(rs.getString("card_number")));
				saleMan.setWages(rs.getDouble("wages"));
				saleMan.setRevoke_date(rs.getTimestamp("revoke_date"));
				saleMan.setRevoke_reason(StringUtil.nvl(rs.getString("revoke_reason")));
				saleMan.setHome_area(StringUtil.nvl(rs.getString("home_area_code")));
				//设置区域信息
				saleMan_home_area_info = new Tbl_code_master();
				saleMan_home_area_info.setCode_value(rs.getString("home_area_name"));
				saleMan.setHome_area_info(saleMan_home_area_info);
				saleMan_list.add(saleMan);
				saleMan.setSaleman_level(StringUtil.nvl(rs.getString("saleman_level")));
				saleMan.setUserfrom(StringUtil.nvl(rs.getString("user_form")));
				saleMan.setUser_occupation(StringUtil.nvl(rs.getString("user_occupation")));
				saleMan.setUser_nation(StringUtil.nvl(rs.getString("user_nation")));
				saleMan.setUser_education(StringUtil.nvl(rs.getString("user_education")));
			}
		} catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}
		return saleMan_list;
	}

	/**
	 * 查看客户群
	 */
	@Override
	public List<Tbl_user> getViewusers(String orderDate,String salemanId) {
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,getViewusers");
		List<Tbl_user> list = new ArrayList<Tbl_user>();
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("");
			 sql.append(" SELECT                                                    ");
			 sql.append(" 	LEFT (tu.user_name, 1) AS NAME,                         ");
			 sql.append(" 	tu.user_name,                                           ");
			 sql.append(" 	sum(tor.order_price) as order_price,                    ");
			 sql.append(" 	sum(tbh.occur_bonus) as occur_bonus                     ");
			 sql.append(" FROM                                                      ");
			 sql.append(" 	tbl_user AS tu                                          ");
			 sql.append(" left JOIN tbl_order AS tor                                ");
			 sql.append("     ON tu.user_id = tor.order_user                        ");
			 sql.append("    AND tor.order_status = 'D005'                          ");
			 sql.append("    AND date_format(tor.order_date, '%Y-%m') = ?           ");
			 sql.append(" left JOIN tbl_bonus_history AS tbh                        ");
			 sql.append("     ON tbh.from_user_id = tu.user_id                      ");
			 sql.append("    AND tbh.break_flag = '0'                               ");
			 sql.append(" WHERE                                                     ");
			 sql.append(" tu.saleman_id = ?                                         ");
			 sql.append(" group by tu.user_id                                       ");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1,orderDate);
			ps.setString(2, salemanId);
			rs = ps.executeQuery();
			while(rs.next()){
				Tbl_user user = new Tbl_user();
				user.setName(StringUtil.nvl(rs.getString("name")));
				user.setUser_name(StringUtil.nvl(rs.getString("user_name")));
				user.setOrder_price(rs.getDouble("order_price"));
				user.setOccur_bonus(rs.getDouble("occur_bonus"));
				list.add(user);
			}
		} catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
		}finally{
			MySQLUtil.closeAll(rs, sm, con, ps);
		}

		return list;
	}

    @Override
	public String getSalemanAuthenticateId(String userName, String telNum,
            String weChatNo) {
        String salemanId = null;
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,getSalemanAuthenticateId");
        try {
            con = MySQLUtil.getConnection();
            StringBuffer sql = new StringBuffer("");
            sql.append("select saleman_id from tbl_saleman WHERE saleman_name=? and saleman_tel_num =? and weixin_id =? ");
            ps = con.prepareStatement(sql.toString());
            ps.setString(1,userName);
            ps.setString(2,telNum);
            ps.setString(3,weChatNo);
            rs = ps.executeQuery();
            if(rs.next()){
                salemanId = StringUtil.nvl(rs.getString("saleman_id"));
            }
            } catch (Exception e) {
            	CommonUtil.logger.error(e.getMessage());
            }finally{
                MySQLUtil.closeAll(rs, sm, con, ps);
            }
        return salemanId;
    }

    @Override
    public boolean updateSalemanOpenId(String openId,String salemanId) {
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,updateSalemanOpenId");
            int updatecount = 0;
        try {
            con = MySQLUtil.getConnection();
            StringBuffer sql = new StringBuffer("");
            sql.append("update tbl_saleman SET open_id = ? where saleman_id=?");
            ps = con.prepareStatement(sql.toString());
            ps.setString(1,openId);
            ps.setString(2,salemanId);
            updatecount = ps.executeUpdate();
            } catch (Exception e) {
            	CommonUtil.logger.error(e.getMessage());
            }finally{
                MySQLUtil.closeAll(rs, sm, con, ps);
            }

        return updatecount>0 ? true : false;
    }

    /**
     *
     * @param saleman_id
     * @return
     * I005:主管经理
     * I004:高级销售经理
     * I003:销售经理
     * I002:销售
     */
	public String selectSalemanLevel(String saleman_id){
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,selectSalemanLevel");
		String currentLevel = "I002";
		StringBuffer strSQL = new StringBuffer("");
		int level_1_count = 0;
		int level_2_count = 0;
		int level_3_count = 0;
		try{
			strSQL.append(" SELECT A.level_1_count,");
			strSQL.append("        B.level_2_count,");
			strSQL.append("        C.level_3_count");
			strSQL.append(" FROM");
			strSQL.append(" (SELECT");
			strSQL.append(" 	COUNT(saleman_id) level_1_count");
			strSQL.append(" FROM");
			strSQL.append(" 	tbl_saleman");
			strSQL.append(" WHERE");
			strSQL.append(" 	up_saleman_id = '" +saleman_id+ "'");
			strSQL.append(" AND saleman_level = 'I002'");
			strSQL.append(" )A,");
			strSQL.append(" (SELECT");
			strSQL.append(" 	COUNT(saleman_id) level_2_count");
			strSQL.append(" FROM");
			strSQL.append(" 	tbl_saleman");
			strSQL.append(" WHERE");
			strSQL.append(" 	up_saleman_id = '" +saleman_id+ "'");
			strSQL.append(" AND saleman_level = 'I003'");
			strSQL.append(" )B,");
			strSQL.append(" (SELECT");
			strSQL.append(" 	COUNT(saleman_id) level_3_count");
			strSQL.append(" FROM");
			strSQL.append(" 	tbl_saleman");
			strSQL.append(" WHERE");
			strSQL.append(" 	up_saleman_id = '" +saleman_id+ "'");
			strSQL.append(" AND saleman_level = 'I004'");
			strSQL.append(" )C");
			con = MySQLUtil.getConnection();
			ps = con.prepareStatement(strSQL.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				level_1_count = rs.getInt("level_1_count");
				level_2_count = rs.getInt("level_2_count");
				level_3_count = rs.getInt("level_3_count");
			}
			//销售等级判断
			if(level_1_count >=3 && level_2_count >=1 && level_3_count >=1){
				//销售人数>=3,销售经理人数>=1，高级销售经理人数>=1，则销售等级判定为为主管经理
				currentLevel = "I005";
			}else if(level_1_count >=3 && level_2_count >=1 && level_3_count ==0){
				//销售人数>=3,销售经理人数>=1, 高级销售经理人数=0，则销售等级判定为高级销售经理
				currentLevel = "I004";
			}else if(level_1_count >=3 && level_2_count ==0){
				//销售人数>=3,销售经理人数=0，则销售等级判定为销售经理
				currentLevel = "I003";
			}else if(level_1_count <3){
				//销售人数<3，则销售等级判定为销售
				currentLevel = "I002";
			}
		}catch(Exception e){
        	CommonUtil.logger.error(e.getMessage());
		} finally {
			MySQLUtil.closeAll(rs, sm, con, ps);
		}

		return currentLevel;
	}

	public boolean updateSalemanLevel(String saleman_level,String saleman_id) {
		CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,updateSalemanLevel");
		int result = 0;
		try {
			con = MySQLUtil.getConnection();
			StringBuffer sql = new StringBuffer("UPDATE tbl_saleman SET");
			sql.append(" saleman_level = '" +saleman_level +"' ");
			sql.append(" WHERE saleman_id = '"+ saleman_id +"' ");
			ps = con.prepareStatement(sql.toString());
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
    public boolean updateSalemanTransferflag(String openId) {
    	CommonUtil.logger.info("【mobile】"+"into SaleManDaoImpl,method:updateSalemanTransferflag");
        int updatecount = 0;
        try {
            con = MySQLUtil.getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("update tbl_user SET transfer_flag = '1'");
            sql.append("    where open_id = ?");
            ps = con.prepareStatement(sql.toString());
            ps.setString(1,openId );
            updatecount = ps.executeUpdate();
        } catch (SQLException e) {
        	CommonUtil.logger.error(e.getMessage());
        } catch (Exception e) {
        	CommonUtil.logger.error(e.getMessage());
        } finally {
            MySQLUtil.closeAll(rs, sm, con, ps);
        }

        return updatecount > 0 ? true : false;
    }

}