package org.pentakill.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.pentakill.blog.dao.jdbc.OracleDemoJdbc;
import org.pentakill.blog.dto.PageDto;
import org.pentakill.blog.service.OracleDemo;
import org.springframework.stereotype.Service;

@Service("oracleDemo")
public class OracleDemoImp implements OracleDemo {

	@Resource(name = "oracleDemoJdbc")
	private OracleDemoJdbc oracleDemoJdbc;

	@Override
	public String select() {
		return oracleDemoJdbc.findAll();
	}

	public List<Map<String, Object>> getData() {
		return oracleDemoJdbc.getData();
	}

	@Override
	public PageDto page(int pageSize, int pageSizeNo) {
		return oracleDemoJdbc.getTsmWorker(pageSize, pageSizeNo);
	}

	public List<Map<String, Object>> getUserRadio() {
		return oracleDemoJdbc.getUserRadio();
	}
}
