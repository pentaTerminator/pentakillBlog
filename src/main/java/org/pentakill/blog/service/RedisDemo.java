package org.pentakill.blog.service;

public interface RedisDemo {
	/**
	 * redis 中保存某个值
	 * 
	 * @param order
	 */
	void save(String key, String value);

	/**
	 * 获取redis中的值
	 * 
	 * @param id
	 * @return
	 */
	String read(String id);

	/**
	 * 删除redis的某个值
	 * 
	 * @param id
	 */
	void delete(String id);
}
