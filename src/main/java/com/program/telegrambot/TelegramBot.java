package com.program.telegrambot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.program.telegrambot.yandexGPT.Alternatives;
import com.program.telegrambot.yandexGPT.YandexGPTClient;

import lombok.SneakyThrows;


public class TelegramBot extends TelegramLongPollingBot {

	private final String botName;
	private final YandexGPTClient ai;
	
	public TelegramBot(DefaultBotOptions options, String botToken, String botName, YandexGPTClient ai) {
		super(options,botToken);
		this.botName = botName;
		this.ai = ai;
	}
	
	@SneakyThrows
	@Override
	public void onUpdateReceived(Update update) {
		if(update.hasMessage() && update.getMessage().hasText()) {
			
			var text = update.getMessage().getText();
			var chatId = update.getMessage().getChatId();
			String textResponse;
			
			switch(text) {
				case "/start":
					textResponse = """
								Этот бот создан для общения с yandexGPT через telegrambots API для java с использованием Spring framework.
							""";
					break;
				default:
					var aiResponce = ai.getResponseFromAI(text);
					textResponse = aiResponce.getResult().getAlternatives().get(0).getMessage().getText();
			}
			
			SendMessage sendMessage = new SendMessage(chatId.toString(),textResponse);
			sendApiMethod(sendMessage);
		}
	}

	@Override
	public String getBotUsername() {
		return botName;
	}
	
}
