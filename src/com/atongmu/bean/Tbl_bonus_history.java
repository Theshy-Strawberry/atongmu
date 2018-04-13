package com.atongmu.bean;

import java.util.Date;

public class Tbl_bonus_history {
	int	bonus_id;       /*	佣金编号 */
	Date	get_date;       /*	获得日期 */
	String	to_user_id;     /*	佣金来源（接受用户ID） */
	String	from_user_id;   /*	佣金来源（发生方用户ID) */
	Double	occur_bonus;    /*	发生金额 */
	int     break_flag;     /*  是否为冻结状态*/
	public int getBreak_flag() {
		return break_flag;
	}


	public void setBreak_flag(int break_flag) {
		this.break_flag = break_flag;
	}


	String	bonus_type;     /*	佣金类别 */
	public int getBonus_id()
	{
		return(bonus_id);
	}


	public void setBonus_id( int bonus_id )
	{
		this.bonus_id = bonus_id;
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


	public String getFrom_user_id()
	{
		return(from_user_id);
	}


	public void setFrom_user_id( String from_user_id )
	{
		this.from_user_id = from_user_id;
	}


	public Double getOccur_bonus()
	{
		return(occur_bonus);
	}


	public void setOccur_bonus( Double occur_bonus )
	{
		this.occur_bonus = occur_bonus;
	}


	public String getBonus_type()
	{
		return(bonus_type);
	}


	public void setBonus_type( String bonus_type )
	{
		this.bonus_type = bonus_type;
	}
}
