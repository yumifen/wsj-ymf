package com.xinwei.entity;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.Date;

@Data
public class SysMenu {
    @Expose
    private Long id;
    @Expose
    private Long createId;
    @Expose
    private Long updateId;
    @Expose
    private Date createDate;
    @Expose
    private Date updateDate;
    @Expose
    private String remark;
    @Expose
    private String delFlag;
    @Expose
    private Long parentId;
    @Expose
    private String name;
    @Expose
    private String type;
    @Expose
    private String href;
    @Expose
    private String target;
    @Expose
    private String icon;
    @Expose
    private String showFlag;
    @Expose
    private String permission;
    @Expose
    private String path;
    @Expose
    private Integer sort;
    @Expose
    private Long sysCompanyId;


}