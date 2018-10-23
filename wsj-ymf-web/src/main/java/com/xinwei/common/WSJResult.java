package com.xinwei.common;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class WSJResult<T> {
	@Expose
	public String msg;
	@Expose
	private int code;
	@Expose
	private T data;
	@Expose
	private boolean  bltnTip;   //消息提示，true 成功  false 失败


	/**
	 * 成功时候的调用
	 */
	private static <T> WSJResult<T> info(T data,CodeMsg codeMsg,boolean bltn) {
		WSJResult<T> rbo = new WSJResult<>(codeMsg);
		rbo.setData(data);
		rbo.setBltnTip(bltn);
		return rbo;
	}

	/**
	 * 成功时候的调用
	 */
	public static <T> WSJResult<T> success(T data) {

		return WSJResult.info(data,CodeMsg.OPT_SUCCESS,true);
	}
	/**
	 * 失败时候的调用
	 */
	public static <T> WSJResult<T> error(CodeMsg codeMsg) {
		return WSJResult.info(null,codeMsg,false);
	}

	private WSJResult(T data) {
		this.data = data;
	}
	private WSJResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	private WSJResult(CodeMsg codeMsg) {
		if (codeMsg != null) {
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
	}

}
