package com.atongmu.dao;

import java.util.List;

import com.atongmu.bean.Tbl_user;

public interface UserDao {
	//�����¹���Ա
    public boolean addUser(Tbl_user tbl_user);
    //���¹���Ա����
    public boolean updateUser(Tbl_user tbl_user);
    //ɾ������Ա
    public boolean deleteUser(Tbl_user tbl_user);
    //����Ա��½
    public Tbl_user selectUser(Tbl_user tbl_user);
    //��ȡ���й���Ա�鱨
    public List<Tbl_user> selectUser();
}
