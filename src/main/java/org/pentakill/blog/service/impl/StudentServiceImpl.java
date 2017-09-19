package org.pentakill.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.pentakill.blog.dao.redis.StudentRedisDao;
import org.pentakill.blog.service.StudentService;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource(name = "studentRedisDao")
	private StudentRedisDao studentRedisDao;

	@Override
	public void inset(String key, Long studentId, Double score) {
		studentRedisDao.save(key, studentId, score);
	}

	@Override
	public List<Map<Long, Double>> getStudentAll(String key) {
		return studentRedisDao.getStudentAll(key);
	}

}
