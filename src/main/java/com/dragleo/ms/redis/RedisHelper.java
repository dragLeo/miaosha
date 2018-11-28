package com.dragleo.ms.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dragleo.ms.common.keyprefix.PrefixKey;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisHelper {

	@Autowired
	JedisPool jedisPool;
	
	
	public <T> boolean exitsKey(PrefixKey prefix,String key){
		Jedis jedis=null;
		try {
			jedis = jedisPool.getResource();
			String k=prefix.getPrefix()+key;
			String string = jedis.get(k);
			return jedis.exists(k);
		} finally {
			closeJedis(jedis);
		}
	}
	
	public  <T>T getKey(PrefixKey prefix, String key,Class<T> clazz){
		Jedis jedis=null;
		try {
			jedis = jedisPool.getResource();
			String k=prefix.getPrefix()+key;
			String string = jedis.get(k);
			return string2Bean(string, clazz);
		} finally {
			closeJedis(jedis);
		}
	}
	
	public <T> boolean setKey(PrefixKey prefix,String key,T data){
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			String str = bean2String(data);
			if(StringUtils.isEmpty(str))
				return false;
			String k=prefix.getPrefix()+key;
			int expireT =prefix.expireTime();
			if(expireT<1){
				jedis.set(k, str);
			}else{
				jedis.setex(k, expireT, str);
			}
			return true;
		} 
		finally{
			closeJedis(jedis);
		}
	}
	
	public static <T>String bean2String(T value){
		if(value ==null)
			return null;
		Class<?> clazz =value.getClass();
		if(clazz == int.class || clazz == Integer.class) {
			 return ""+value;
		}else if(clazz == String.class) {
			 return (String)value;
		}else if(clazz == long.class || clazz == Long.class) {
			return ""+value;
		}else {
			return JSON.toJSONString(value);
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static <T> T string2Bean(String str,Class<T> clazz){
		if(str == null || str.length() <= 0 || clazz == null) {
			 return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			 return (T)Integer.valueOf(str);
		}else if(clazz == String.class) {
			 return (T)str;
		}else if(clazz == long.class || clazz == Long.class) {
			return  (T)Long.valueOf(str);
		}else {
			return JSON.toJavaObject(JSON.parseObject(str), clazz);
		}
	}
	
	private void closeJedis(Jedis jedis ){
		if(jedis != null){
			jedis.close();
		}
	}
	
}
