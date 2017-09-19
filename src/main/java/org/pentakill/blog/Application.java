package org.pentakill.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 */
@EnableTransactionManagement
@SpringBootApplication
class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
