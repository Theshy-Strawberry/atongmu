package com.atongmu.bean;

import java.util.Date;

public class Tbl_wages_history {
	int	wages_id;               /*	���ʱ�� */
	Date	get_date;               /*	�������� */
	String	to_user_id;             /*	���ʽ�����ID */
	Double	original_wages;         /*	Ӧ�������� */
	String	wages_status;           /*	����״̬ */
	Double	true_wages;             /*	ʵ�������� */
	Double	debit_wages;            /*	�ۿ��� */
	String	debit_reason;           /*	�ۿ�ԭ�� */
	String	not_wages_reason;       /*	���ʲ��ܷ������� */
	public int getWages_id()
	{
		return(wages_id);
	}


	public void setWages_id( int wages_id )
	{
		this.wages_id = wages_id;
	}


	public Date getGet_date()
	{
		return(get_date);
	}


	public void setGet_date( Date get_date )
	{
		this.get_date = get_date;
	}


	public String getTo_user_id()
	{
		return(to_user_id);
	}


	public void setTo_user_id( String to_user_id )
	{
		this.to_user_id = to_user_id;
	}


	public Double getOriginal_wages()
	{
		return(original_wages);
	}


	public void setOriginal_wages( Double original_wages )
	{
		this.original_wages = original_wages;
	}


	public String getWages_status()
	{
		return(wages_status);
	}


	public void setWages_status( String wages_status )
	{
		this.wages_status = wages_status;
	}


	public Double getTrue_wages()
	{
		return(true_wages);
	}


	public void setTrue_wages( Double true_wages )
	{
		this.true_wages = true_wages;
	}


	public Double getDebit_wages()
	{
		return(debit_wages);
	}


	public void setDebit_wages( Double debit_wages )
	{
		this.debit_wages = debit_wages;
	}


	public String getDebit_reason()
	{
		return(debit_reason);
	}


	public void setDebit_reason( String debit_reason )
	{
		this.debit_reason = debit_reason;
	}


	public String getNot_wages_reason()
	{
		return(not_wages_reason);
	}


	public void setNot_wages_reason( String not_wages_reason )
	{
		this.not_wages_reason = not_wages_reason;
	}
}
