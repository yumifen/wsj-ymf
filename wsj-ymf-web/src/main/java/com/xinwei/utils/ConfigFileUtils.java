package com.xinwei.utils;

import org.apache.commons.io.IOUtils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;

 
/**
 * 
 * @author yumifen
 *
 */
public class ConfigFileUtils extends ResourceUtils {

    private static ResourceLoader resourceLoader;
    private static ResourcePatternResolver resourceResolver;
    static{
        resourceLoader = new DefaultResourceLoader();
        resourceResolver = new PathMatchingResourcePatternResolver(resourceLoader);
    }

    /**
     * 获取资源加载器（可读取jar内的文件）
     * @author ThinkGem
     */
    public static ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    /**
     * 获取ClassLoader
     */
    public static ClassLoader getClassLoader() {
        return resourceLoader.getClassLoader();
    }

    /**
     * 获取资源加载器（可读取jar内的文件）
     */
    public static Resource getResource(String location) {
        return resourceLoader.getResource(location);
    }

    /**
     * 获取资源文件流（用后记得关闭）
     * @param location
     * @author ThinkGem
     * @throws IOException
     */
    public static InputStream getResourceFileStream(String location) throws IOException{
        Resource resource = resourceLoader.getResource(location);
        return resource.getInputStream();
    }

    /**
     * 获取资源文件内容
     * @param location
     * @author ThinkGem
     */
    public static String getResourceFileContent(String location){
        InputStream is = null;
        try{
            is = ConfigFileUtils.getResourceFileStream(location);
            return IOUtils.toString(is, "UTF-8");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * Spring 搜索资源文件
     * @param locationPattern
     * @author ThinkGem
     */
    public static Resource[] getResources(String locationPattern){
        try {
            Resource[] resources = resourceResolver.getResources(locationPattern);
            return resources;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}