package com.xinwei.config.properties;

import java.io.IOException;
import java.util.Properties;

import com.xinwei.utils.ConfigFileUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;


@Configuration
public class PropertiesConfig {
	
	private static final String LOCATION_PATH = "classpath*:/com/xinwei/config/*.yml";
	
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws IOException {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean     yaml = new YamlPropertiesFactoryBean  ();
        Resource[] resources = ConfigFileUtils.getResources(LOCATION_PATH);
        yaml.setResources(resources);
        Properties properties = yaml.getObject();
        configurer.setProperties(properties);
        return configurer;
    }
}
