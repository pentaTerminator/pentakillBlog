package org.pentakill.blog.dao.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@Repository("studentRedisDao")
public class StudentRedisDao {
	@Autowired
	private RedisTemplate redisTemplate;

	public void save(String key, Long studentId, Double score) {
		HashOperations opsForHash = redisTemplate.opsForHash();
		opsForHash.put(key, studentId, score);

		ZSetOperations<String, Long> zSetOperations = redisTemplate.opsForZSet();
		zSetOperations.add(key+"set", studentId, score);
	}

	public Set<Long> get(String key) {
		ZSetOperations zSetOperations = redisTemplate.opsForZSet();
		Set<Long> set = zSetOperations.reverseRange(key, 0, -1);
		return set;
	}

	public List<Map<Long, Double>> getStudentAll(String key) {
		List<Map<Long, Double>> list = new ArrayList<Map<Long, Double>>();
		Set<Long> set = get(key+"set");
		HashOperations<String, Long, Double> opsForHash = redisTemplate.opsForHash();
		Object[] object = set.toArray();
		for (int i = 0; i < object.length; i++) {
			Double score = opsForHash.get(key, object[i]);
			Map<Long, Double> map = new HashMap<Long, Double>();
			map.put((Long) object[i], score);
			list.add(map);
		}
		return list;
	}
}
