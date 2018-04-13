package com.atongmu.dao;

import java.util.List;

import com.atongmu.bean.Tbl_user;

public interface UserDao {
	//增加新管理员
    public boolean addUser(Tbl_user tbl_user);
    //更新管理员资料
    public boolean updateUser(Tbl_user tbl_user);
    //删除管理员
    public boolean deleteUser(Tbl_user tbl_user);
    //管理员登陆
    public Tbl_user selectUser(Tbl_user tbl_user);
    //获取所有管理员情报
    public List<Tbl_user> selectUser();
}
