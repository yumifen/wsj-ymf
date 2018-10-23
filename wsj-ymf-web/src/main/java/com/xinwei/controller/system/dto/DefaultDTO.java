package com.xinwei.controller.system.dto;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * @创建人 yumifen
 * @创建时间 2018/10/15
 * @描述
 **/
@Data
public class DefaultDTO {

    @Expose
    private String userName;  //用户名称
    @Expose
    private String photoUrl;  //用户头像

}
