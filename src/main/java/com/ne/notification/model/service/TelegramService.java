package com.ne.notification.model.service;

import com.ne.notification.configuration.resources.ApplicationProperties;
import com.ne.notification.model.dto.base.BaseDTO;
import com.ne.notification.model.dto.base.MetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Service
public class TelegramService extends TelegramLongPollingBot {


    String telegramBotUsername = "testFarid_Bot";
    String telegramBotToken = "1075721427:AAFERSS8-DXaCOfmFTBKZO5MKiBSiJye75E";

    @Autowired
    protected ApplicationProperties applicationProperties;

    public BaseDTO sendBotMessage(Long chatId, String message) {
        BaseDTO baseDTO = new BaseDTO(MetaDTO.getInstance(applicationProperties));
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(message);
        try {
            sendMessage(response);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
        return baseDTO;
    }


    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return telegramBotUsername;
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }
}
