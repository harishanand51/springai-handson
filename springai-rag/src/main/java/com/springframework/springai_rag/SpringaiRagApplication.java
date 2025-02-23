package com.springframework.springai_rag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.springframework.springai_rag.config.VectorStoreProperties;

@SpringBootApplication
@EnableConfigurationProperties(VectorStoreProperties.class) 
public class SpringaiRagApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringaiRagApplication.class, args);
	}

}
