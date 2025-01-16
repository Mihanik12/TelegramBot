package com.program.telegrambot.yandexGPT;

import java.util.List;

import lombok.Data;

@Data
public class Result {
	private final List<Alternatives> alternatives;
}
