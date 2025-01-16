package com.program.telegrambot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.program.telegrambot.yandexGPT.YandexGPTClient;

import lombok.SneakyThrows;

@Configuration
public class TelegramBotConfiguration {
	
	@Bean
	@SneakyThrows
	public TelegramBot telegramBot(
			@Value("${bot.token}") String botToken,
			@Value("${bot.name}") String botName,
			TelegramBotsApi telegramBotsApi,
			YandexGPTClient yandexGPTClient
	) {
		var botOptions = new DefaultBotOptions();
		var bot = new TelegramBot(botOptions, botToken, botName, yandexGPTClient);
		telegramBotsApi.registerBot(bot);
		return bot;
	}
	
	@Bean
	@SneakyThrows
	public TelegramBotsApi telegramBotsApi() {
		return new TelegramBotsApi(DefaultBotSession.class);
	}
}
