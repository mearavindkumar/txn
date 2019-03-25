package com.txn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@EnableTransactionManagement(proxyTargetClass=true)
public class TxnApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxnApplication.class, args);
	}

}
