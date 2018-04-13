package com.atongmu.dao;

import java.util.List;

import com.atongmu.bean.Tbl_saleman;
import com.atongmu.bean.Tbl_user;

public interface SaleManDao {
	//添加销售员
    public boolean addSaleMan(Tbl_saleman tbl_manager);
    //更新销售员资料
    public boolean updateSaleMan(Tbl_saleman tbl_manager);
    //删除销售员
    public boolean deleteSaleMan(Tbl_saleman tbl_manager);
    //查看销售员信息
    public Tbl_saleman selectSaleMan(Tbl_saleman tbl_manager);
    //查看销售员一览
    public List<Tbl_saleman> selectSaleMan();
    //查看客户群
    public List<Tbl_user> getViewusers(String orderDate,String salemanId);
    //根据ID查找销售员资料
    public Tbl_saleman selectSaleManByID(String salemanID);
    //根据会员真实姓名电话号码微信号查找销售员id
    public String getSalemanAuthenticateId(String userName,String telNum,String weChatNo);
    //根据销售员Id认证会员
    public boolean updateSalemanOpenId(String openId, String salemanId);
    //查询销售员等级
	public String selectSalemanLevel(String saleman_id);
	//更新销售员等级
	public boolean updateSalemanLevel(String saleman_level,String saleman_id);
	//更新转移flag
	public boolean updateSalemanTransferflag(String openId);
}
