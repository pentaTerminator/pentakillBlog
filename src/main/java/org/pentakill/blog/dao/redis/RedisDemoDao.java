package org.pentakill.blog.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository("redisDemoDao")
public class RedisDemoDao {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void save(String key, String value) {
		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
		valueOper.set(key, value);
	}

	public String read(String id) {
		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
		return valueOper.get(id);
	}

	public void delete(String id) {
		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
		RedisOperations<String, String> RedisOperations = valueOper.getOperations();
		// redisTemplate.sort(null)
		RedisOperations.delete(id);
	}

	
}
