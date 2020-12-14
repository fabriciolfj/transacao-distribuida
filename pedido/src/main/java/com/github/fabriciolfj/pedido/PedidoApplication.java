package com.github.fabriciolfj.pedido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.pedido.domain.entity.Pedido;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.transaction.ChainedKafkaTransactionManager;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@EnableAutoConfiguration
@EnableKafka
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan("com.github.fabriciolfj.pedido")
@EntityScan(basePackageClasses = Pedido.class)
public class PedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public ChainedKafkaTransactionManager<Object, Object> chainedKafkaTransactionManager(
			KafkaTransactionManager kafkaTransactionManager,
			JpaTransactionManager transactionManager) {
		return new ChainedKafkaTransactionManager<>(kafkaTransactionManager, transactionManager);
	}

}
