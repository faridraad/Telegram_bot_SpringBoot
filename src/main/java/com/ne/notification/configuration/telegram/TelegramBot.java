package com.ne.notification.configuration.telegram;


import org.springframework.stereotype.Component;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class TelegramBot extends TelegramLongPollingBot {


    private String token = "1075721427:AAFERSS8-DXaCOfmFTBKZO5MKiBSiJye75E";
    private String username = "testFarid_Bot";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            String command = update.getMessage().getText();
            if (command.equals("/subscribe") || command.equals("/start")) {
                response.setChatId(chatId);
                if (update.getMessage().getFrom().getUserName() == null) {
                    response.setText("Test : Please change your telegram username and restart this bot again!");
                } else {
                    try {
                        response.setText("ChatId : "+ chatId + " Username : "+  update.getMessage().getFrom().getUserName() );
                    } catch (Exception e) {
                    }
                }
                try {
                    // execute(response);
                    sendMessage(response);
                } catch (TelegramApiException e) {
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @PostConstruct
    public void start() {
    }

}
