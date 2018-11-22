package com.xinwei.config.plus;

import com.bjjdsy.utility.page.mybatis.PagingAspect;
import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author chen
 */
@Configuration
public class MybatisPlusConfig {

	/**
	 *	 mybatis-plus分页插件
	 */
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum","true");
		properties.setProperty("rowBoundsWithCount","true");
		properties.setProperty("reasonable","true");
		properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}

	@Bean
	public PagingAspect pagingAspect(){
		return new PagingAspect();
	}

}
