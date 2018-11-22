package com.xinwei.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;
@Data
public class PageResult<T> extends PageInfo {

	//当前页
	@Expose
	private int currNum;
	//每页的数量
	@Expose
	private int currSize;
	@Expose
	private boolean bltn;  //true 结果集为空，false 不为空
	//总记录数
	@Expose
	private long totalCount;
	//总页数
	@Expose
	private int pageCount;
	//结果集
	@Expose
	private List<T> grids;

	public PageResult(List list) {

		super(list);
		this.currNum =  super.getPageNum();
		this.currSize = super.getPageSize();
		this.totalCount = super.getTotal();
		this.pageCount = super.getPages();
		this.grids = super.getList();

		if(this.grids==null||this.grids.size()<=0){
             this.bltn = true;
		}
	}


}
