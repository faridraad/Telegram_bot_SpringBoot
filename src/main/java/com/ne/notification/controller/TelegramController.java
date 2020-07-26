package com.ne.notification.controller;


import com.ne.notification.model.bean.TelegramBotBean;
import com.ne.notification.model.dto.base.BaseDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/telegram")
public class TelegramController extends BaseController {

    @PostMapping(value = "/bot")
    public ResponseEntity<?> sendMessageTelegramBot(@Valid @RequestBody TelegramBotBean telegramBotBean) {
        BaseDTO baseDTO = telegramService.sendBotMessage(telegramBotBean.getChatId(), telegramBotBean.getMessage() );
        return new ResponseEntity<>(baseDTO, HttpStatus.OK);
    }
}
