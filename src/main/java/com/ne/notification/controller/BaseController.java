package com.ne.notification.controller;

import com.ne.notification.configuration.resources.ApplicationProperties;
import com.ne.notification.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    protected ApplicationProperties applicationProperties;

    @Autowired
    protected TelegramService telegramService;

}
