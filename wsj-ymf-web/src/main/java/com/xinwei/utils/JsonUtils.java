package com.xinwei.utils;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {
//	public static final String DATA_FORMAT="yyyy-MM-dd HH:mm:ss:SSS";
	public static final String DATA_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	private static Gson getGson(){
		return  getGson(1.0,DATA_FORMAT,FieldNamingPolicy.IDENTITY) ;
	}
	private static Gson getGson(String dataformat){
		return  getGson(1.0,dataformat,FieldNamingPolicy.IDENTITY) ;
	}
	
	private static Gson getGson(double version,String dataformat,FieldNamingPolicy policy ){
		return  new GsonBuilder()  
	        .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性
	        .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式  
	        .serializeNulls().setDateFormat(dataformat)//时间转化为特定格式    
	        .setFieldNamingPolicy(policy)//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.  
//	        .setPrettyPrinting() //对json结果格式化.  
	        .setVersion(version)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.  
	                            //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么  
	                            //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.  
	        .create();  
	}

	public static <T> T   copyObject(Object ori,TypeToken<T> token){
		Gson gson=getGson();
		return gson.fromJson(gson.toJson(ori),token.getType());
	}
	public static String  object2Json(Object object){
		return object2Json(object,null);
	}
	public static String  object2Json(Object object,String dataformat){
		if(StringUtils.isBlank(dataformat)){
			return getGson().toJson(object);
		}
		return getGson(dataformat).toJson(object);
	}
	
	public static <T> T  json2Object(String json,Class<T> cls){
		return getGson().fromJson(json, cls);
	}
	public static <T> T  json2Object(String json,TypeToken<T> token){
		return (T) getGson().fromJson(json,token.getType());
	}
	public static <T> T  json2Object(String json,TypeToken<T> token,String dataformat){
		return (T) getGson(dataformat).fromJson(json,token.getType());
	}
}
