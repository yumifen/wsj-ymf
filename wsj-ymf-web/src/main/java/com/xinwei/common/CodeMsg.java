package com.xinwei.common;

import lombok.Data;

@Data
public class CodeMsg {
	
	private int code;
	private String msg;
	
	//通用的错误码
	public static CodeMsg OPT_SUCCESS = new CodeMsg(0, "操作成功");

	public static CodeMsg OPT_FAIL = new CodeMsg(1, "操作失败");


	public CodeMsg( int code,String msg ) {
		this.code = code;
		this.msg = msg;
	}
}
