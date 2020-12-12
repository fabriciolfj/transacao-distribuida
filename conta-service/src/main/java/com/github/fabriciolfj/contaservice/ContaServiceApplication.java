package com.github.fabriciolfj.contaservice;

import com.github.fabriciolfj.contaservice.domain.integracao.TransacaoBinder;
import com.github.fabriciolfj.contaservice.domain.repository.ContaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.github.fabriciolfj.contaservice")
@EnableJpaRepositories(basePackageClasses = ContaRepository.class)
@EnableBinding(TransacaoBinder.class)
public class ContaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContaServiceApplication.class, args);
	}

}
