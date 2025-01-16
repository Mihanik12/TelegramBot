package com.program.telegrambot.yandexGPT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YandexGPTConfiguration {

	@Bean
	public YandexGPTClient yandexGPTClient(
			@Value("${ai.api}") String aiAPI,
			@Value("${ai.folder}") String aiFolder
	) {
		return new YandexGPTClient(aiAPI,aiFolder,new RestTemplateBuilder().build());
	}
	
	
}
