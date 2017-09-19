package org.pentakill.blog.service;

import java.util.List;
import java.util.Map;

import org.pentakill.blog.dto.PageDto;

public interface OracleDemo {

	String select();

	List<Map<String, Object>> getData();

	PageDto page(int pageSize, int pageSizeNo);

	List<Map<String, Object>> getUserRadio();
}
