package com.atongmu.bean;

import java.io.Serializable;

public class Tbl_PayCallBackInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String return_code;         /*	return_code */
	private String return_msg;         /*	return_msg */

	public String getReturnCode() {
		return return_code;
	}

	public void setReturnCode(String return_code) {
		this.return_code = return_code;
	}
	public String getReturnMsg() {
		return return_msg;
	}

	public void setReturnMsg(String return_msg) {
		this.return_msg = return_msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}