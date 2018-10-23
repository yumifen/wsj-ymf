package com.xinwei.config.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

/**
 * 自定义的freemaker的shiro标签注入实现。
 * TODO add description
 */
@SuppressWarnings("ALL")
@Configuration
// @ConditionalOnClass({ FreeMarkerProperties.class })
@AutoConfigureAfter(FreeMarkerProperties.class)
// @ConditionalOnBean(FreeMarkerProperties.class)
// @EnableConfigurationProperties(FreeMarkerProperties.class)
public class FreemarkerConfiguration {

	@Autowired
	protected FreeMarkerProperties properties;

	protected void applyProperties(FreeMarkerConfigurationFactory factory) {
		factory.setTemplateLoaderPaths(this.properties.getTemplateLoaderPath());
		factory.setPreferFileSystemAccess(this.properties.isPreferFileSystemAccess());
		factory.setDefaultEncoding(this.properties.getCharsetName());
		Properties settings = new Properties();
		settings.putAll(this.properties.getSettings());
		factory.setFreemarkerSettings(settings);
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new ShiroTagFreeMarkerConfigurer();
		applyProperties(configurer);
		return configurer;
	}


}