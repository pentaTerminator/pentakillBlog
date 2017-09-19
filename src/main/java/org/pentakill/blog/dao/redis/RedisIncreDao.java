package org.pentakill.blog.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository("redisIncreDao")
public class RedisIncreDao {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void incre(String key) {
		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
		valueOper.increment(key, 1);
	}
}
