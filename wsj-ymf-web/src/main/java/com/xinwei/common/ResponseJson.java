package com.xinwei.common;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ResponseJson {

	@Expose
	public String msg;
	@Expose
	public String url;
	@Expose
	public String returnStr;
	@Expose
	public boolean success=true;


}
