package com.xinwei.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xinwei.common.entity.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * Created by chenjianan on 2017/3/1-16:12.
 * <p>
 * Describe: 角色实体类DO
 */
@TableName("sys_role")
public class SysRole  extends DataEntity<SysRole> {

    /**
     * varchar(64) NOT NULL角色名称
     */
    private String name;
    /**
     * varchar(64) NULL英文名称
     */
    private String code;

    /**
     * varchar(64) NULL数据范围
     */
    @TableField(value = "data_scope")
    private String dataScope;


    /**
     * varchar(64) NULL是否可用
     */
    private String useable;

    @Length(min = 0, max = 64, message = "角色code长度必须介于 1 和 64 之间")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Length(min = 0, max = 64, message = "角色名称长度必须介于 1 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }


    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }


    @Override
    protected Serializable pkVal() {

        return this.id;
    }
}

