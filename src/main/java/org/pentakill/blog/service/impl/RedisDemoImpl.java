package org.pentakill.blog.service.impl;

import javax.annotation.Resource;

import org.pentakill.blog.dao.redis.RedisDemoDao;
import org.pentakill.blog.service.RedisDemo;
import org.springframework.stereotype.Service;

@Service("redisDemo")
public class RedisDemoImpl implements RedisDemo {

	@Resource(name = "redisDemoDao")
	private RedisDemoDao redisDemoDao;

	@Override
	public void save(String key, String value) {
		redisDemoDao.save(key, value);
	}

	@Override
	public String read(String id) {
		return redisDemoDao.read(id);
	}

	@Override
	public void delete(String id) {
		redisDemoDao.delete(id);
	}

}
