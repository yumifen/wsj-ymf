package com.xinwei.common.base.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xinwei.common.base.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author chen
 */

public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());



}
