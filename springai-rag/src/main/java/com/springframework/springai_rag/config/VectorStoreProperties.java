package com.springframework.springai_rag.config;

import java.util.List;

import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix= "sfg.aiapp")
public class VectorStoreProperties {
	
	private String vectorStorePath;
	private List<Resource> documentsToLoad;

	public String getVectorStorePath() {
		return vectorStorePath;
	}

	public List<Resource> getDocumentsToLoad() {
		return documentsToLoad;
	}

	public void setDocumentsToLoad(List<Resource> documentsToLoad) {
		this.documentsToLoad = documentsToLoad;
	}

	public void setVectorStorePath(String vectorStorePath) {
		this.vectorStorePath = vectorStorePath;
	}
	
	

}
