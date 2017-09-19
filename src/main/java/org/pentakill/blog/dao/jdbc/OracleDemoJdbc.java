package org.pentakill.blog.dao.jdbc;

import java.util.List;
import java.util.Map;

import org.pentakill.blog.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("oracleDemoJdbc")
public class OracleDemoJdbc {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String findAll() {
		String sql = "select * from dual";
		return jdbcTemplate.queryForObject(sql, String.class);
	}

	public static final String GET_DATA = "SELECT (MAX(oper_time)-MIN(oper_time))*24*60*60 col_1,time_bucket  col_2,user_name col_3 FROM tp_test GROUP BY user_name,time_bucket ORDER BY user_name";

	public List<Map<String, Object>> getData() {
		return jdbcTemplate.queryForList(GET_DATA);
	}

	public static final String GET_TSM_WORKER = "SELECT  * FROM   (  SELECT A.*, ROWNUM RN   FROM (SELECT * FROM tsm_worker) A WHERE ROWNUM <= ?  )  WHERE RN >= ?  ";
	public static final String GET_TSM_WORKER_TOTAL = "SELECt COUNT(*) FROM tsm_worker";

	public PageDto getTsmWorker(int pageSize, int pageNumber) {
		PageDto ps = new PageDto();
		ps.setRows(jdbcTemplate.queryForList(GET_TSM_WORKER, pageSize * pageNumber, (pageNumber - 1) * pageSize));
		ps.setTotal(jdbcTemplate.queryForObject(GET_TSM_WORKER_TOTAL, Integer.class));
		return ps;
	}

	public static final String GET_USER_RADIO = " select count(oper_time) value,user_name name from tp_test GROUP BY user_name";

	public List<Map<String, Object>> getUserRadio() {
		return jdbcTemplate.queryForList(GET_USER_RADIO);
	}

}
