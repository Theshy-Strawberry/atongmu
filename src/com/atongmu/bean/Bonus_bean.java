package com.atongmu.bean;

public class Bonus_bean {
    private double lockedBouns;
    private double activeBouns;
    private String month;
    private double own_turnover;//自己的销售额
    private double seniorSalesManagerTurnover;//高级销售经理销售额
    private double selfGetBonus;//已经取出的佣金
	private double salesManagerTurnover;//销售经理的营业额
    private double salesturnover;//销售的营业额
    private double talPrizeMoney;//自己佣金合计
    private double systeAward;//制度奖
    private double seniorSalesManagerBonus;//来自高级销售经理的佣金
    private double bonusSalesManager;//来自销售经理的佣金
    private double salesBonus;//来自销售员的佣金
	public double getLockedBouns() {
		return lockedBouns;
	}
	public void setLockedBouns(double lockedBouns) {
		this.lockedBouns = lockedBouns;
	}
	public double getActiveBouns() {
		return activeBouns;
	}
	public void setActiveBouns(double activeBouns) {
		this.activeBouns = activeBouns;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getOwn_turnover() {
		return own_turnover;
	}
	public void setOwn_turnover(double own_turnover) {
		this.own_turnover = own_turnover;
	}
	public double getSeniorSalesManagerTurnover() {
		return seniorSalesManagerTurnover;
	}
	public void setSeniorSalesManagerTurnover(double seniorSalesManagerTurnover) {
		this.seniorSalesManagerTurnover = seniorSalesManagerTurnover;
	}
	public double getSalesManagerTurnover() {
		return salesManagerTurnover;
	}
	public void setSalesManagerTurnover(double salesManagerTurnover) {
		this.salesManagerTurnover = salesManagerTurnover;
	}
	public double getSalesturnover() {
		return salesturnover;
	}
	public void setSalesturnover(double salesturnover) {
		this.salesturnover = salesturnover;
	}
	public double getTalPrizeMoney() {
		return talPrizeMoney;
	}
	public void setTalPrizeMoney(double talPrizeMoney) {
		this.talPrizeMoney = talPrizeMoney;
	}
	public double getSysteAward() {
		return systeAward;
	}
	public void setSysteAward(double systeAward) {
		this.systeAward = systeAward;
	}
	public double getSeniorSalesManagerBonus() {
		return seniorSalesManagerBonus;
	}
	public void setSeniorSalesManagerBonus(double seniorSalesManagerBonus) {
		this.seniorSalesManagerBonus = seniorSalesManagerBonus;
	}
	public double getBonusSalesManager() {
		return bonusSalesManager;
	}
	public void setBonusSalesManager(double bonusSalesManager) {
		this.bonusSalesManager = bonusSalesManager;
	}
	public double getSalesBonus() {
		return salesBonus;
	}
	public void setSalesBonus(double salesBonus) {
		this.salesBonus = salesBonus;
	}
    public double getSelfGetBonus() {
		return selfGetBonus;
	}
	public void setSelfGetBonus(double selfGetBonus) {
		this.selfGetBonus = selfGetBonus;
	}

}
