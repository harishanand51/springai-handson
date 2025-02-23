package com.springframework.springai_rag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.springframework.springai_rag.config.VectorStoreProperties;

@SpringBootApplication
@EnableConfigurationProperties(VectorStoreProperties.class)
public class SpringaiRagExpertApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringaiRagExpertApplication.class, args);
	}

}
