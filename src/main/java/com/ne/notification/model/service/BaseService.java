package com.ne.notification.model.service;

import com.ne.notification.configuration.resources.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    @Autowired
    protected ApplicationProperties applicationProperties;

    @Autowired
    protected ServiceConnector serviceConnector;



}
