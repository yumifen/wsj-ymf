package com.xinwei.common;

public class Constants {

	 
	public static String userStatus_0="0";//注册用户未审核
	public static String userStatus_1="1";//注册用户已审核
	public static String userStatus_2="2";//注册用户被锁定
	
	 
	
	public static String initPassword="111111";// 管理员在后台添加用户初始密码 111111 
	 
	
	
	/*****************************************shiro redis 管理设置 start*********************************************************/
	/**
	 * redis cache 前缀
	 */
	public final static String REDIS_SHIRO_CACHE = "shiro-cache:";

	/**
	 * redis session 前缀
	 */
	public final static String REDIS_SHIRO_SESSION = "shiro-session:";
	/*****************************************shiro redis 管理设置 end*********************************************************/

	public static final String HASH_ALGORITHM = "SHA-1";

	public static final int HASH_INTERATIONS = 1024;

	public static final int SALT_SIZE = 8;
	 
	
	
	
	
}
