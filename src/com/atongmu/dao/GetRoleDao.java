package com.atongmu.dao;

public interface GetRoleDao {
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
	public int getManagerRole(String managerID);

	/**
	 * getUserType
	 * 获取登录用户类型
	 * return code
	 * -1： 无权限登录
	 *  1： 会员
	 *  2： 销售员
	 */
	public int getUserType(String userID);

	/**
	 * getUserIDByOpenID
	 * 根据openID获取用户ID
	 * return
	 * null：获取失败
	 * UserID：用户ID
	 */
	public String getUserIDByOpenID(String openID);

}
