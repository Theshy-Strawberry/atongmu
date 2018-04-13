package com.atongmu.dao;

import java.util.List;

import com.atongmu.bean.Tbl_code_master;
import com.atongmu.bean.Tbl_manager;

public interface ManagerDao {
	//�����¹���Ա
    public boolean addManager(Tbl_manager tbl_manager);
    //���¹���Ա����
    public boolean updateManager(Tbl_manager tbl_manager);
    //ɾ������Ա
    public boolean deleteManager(Tbl_manager tbl_manager);
    //����Ա��½
    public Tbl_manager selectManager(Tbl_manager tbl_manager);
    //��ȡ���й���Ա�鱨
    public List<Tbl_manager> selectManager();
    public List<Tbl_manager> selectAdminManager();
    public List<Tbl_code_master> dropdownroleList();
    public List<Tbl_code_master> dropdownhomeareaList();
    
    
}
