package org.pentakill.blog.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableCaching
@ConfigurationProperties
public class DatasourceConfig {
	private static Logger logger = Logger.getLogger(DatasourceConfig.class);

	@Bean(name = "oracleDB")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource oracleDataSource() {
		logger.info("init oracle DataSource");
		DruidDataSource dataSource = new DruidDataSource();
		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	@Scope("prototype")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(oracleDataSource());
		return jdbcTemplate;
	}

	@Bean(name = "transactionInterceptor")
	public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		// 事物管理器
		transactionInterceptor.setTransactionManager(platformTransactionManager);
		Properties transactionAttributes = new Properties();
		// 新增
		transactionAttributes.setProperty("insert*", "PROPAGATION_REQUIRED,-Throwable");
		// 修改
		transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED,-Throwable");
		// 删除
		transactionAttributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Throwable");
		// 查询
		transactionAttributes.setProperty("select*", "PROPAGATION_REQUIRED,-Throwable,readOnly");

		transactionInterceptor.setTransactionAttributes(transactionAttributes);
		return transactionInterceptor;
	}

	// 代理到ServiceImpl的Bean
	@Bean
	public BeanNameAutoProxyCreator transactionAutoProxy() {
		BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
		transactionAutoProxy.setProxyTargetClass(true);
		transactionAutoProxy.setBeanNames("*Impl");
		transactionAutoProxy.setInterceptorNames("transactionInterceptor");
		return transactionAutoProxy;
	}

}
