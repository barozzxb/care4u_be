package vn.care4u.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import vn.care4u.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService{

	private final StringRedisTemplate redis;
	
	public RedisServiceImpl(StringRedisTemplate redis) {
		this.redis = redis;
	}
	
	@Override
	public void set(String key, String value, long timeout, TimeUnit unit) {
		redis.opsForValue().set(key, value, timeout, unit);
	}
	
	@Override
	public String get(String key) {
		return redis.opsForValue().get(key);
	}
	
	@Override
	public void delete(String key) {
		redis.delete(key);
	}
}
