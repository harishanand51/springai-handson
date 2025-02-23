package com.springframework.springai_rag.config;

import java.io.File;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class VectorStoreConfig {
	
	@Bean
	public SimpleVectorStore simpleVectoreStore(EmbeddingModel embeddingModel, VectorStoreProperties vectorStoreProperties) {
		
		SimpleVectorStore store = SimpleVectorStore.builder(embeddingModel).build();
		File vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());
		
		if(vectorStoreFile.exists()) 
			store.load(vectorStoreFile);
		else {
			//log.debug("Loading documents into vector store");
			vectorStoreProperties.getDocumentsToLoad().forEach( document -> {
				//log.debug("Loading document: "+ document.getFileName());
				Resource resource = (Resource)document;
				TikaDocumentReader documentReader = new TikaDocumentReader(resource);

				List<Document> docs = documentReader.get();
				
				TextSplitter textSplitter = new TokenTextSplitter();
				List<Document> splitDocs = textSplitter.apply(docs);
				store.add(splitDocs);
			});
			
			store.save(vectorStoreFile);
		}
		
		
		return store;
	}

}
