package org.pentakill.blog.service;

import java.util.List;
import java.util.Map;

public interface StudentService {
	void inset(String key, Long studentId, Double score);

	List<Map<Long, Double>> getStudentAll(String key);
	
}
