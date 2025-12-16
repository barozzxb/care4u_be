package vn.care4u.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {

	void delete(String key);

	String get(String key);

	void set(String key, String value, long timeout, TimeUnit unit);

}
