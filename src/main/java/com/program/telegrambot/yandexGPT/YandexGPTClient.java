package com.program.telegrambot.yandexGPT;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class YandexGPTClient {
	
	private final String aiAPI;
	private final String aiFolder;
	private final RestTemplate restTemplate;
	
	public Root getResponseFromAI(String message) {
		String url = "https://llm.api.cloud.yandex.net/foundationModels/v1/completion";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		httpHeaders.add("Authorization", "Api-key " + aiAPI);
		httpHeaders.add("x-folder-id", aiFolder);
		
		String body = """
				{
				  "modelUri": "gpt://b1g7oahpdv05i9o2u0iq/yandexgpt/rc",
				  "completionOptions": {"maxTokens":500,"temperature":0.3},
				  "messages": [
				    {"role":"system","text":"Ты помошник, который любой ценой поможет пользователю."},
				    {"role":"user","text":"%s"}
				  ]
				}
				""".formatted(message);
		
		HttpEntity<String> httpEntity = new HttpEntity<>(body,httpHeaders);
		
		ResponseEntity<Root> responseEntity = restTemplate.exchange(
				url, HttpMethod.POST, httpEntity, Root.class
		);
		
		return responseEntity.getBody();
	}
}
