package com.ne.notification.configuration.actuator;

import com.ne.notification.configuration.resources.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;

    public class BaseEndpoint {
    @Autowired
    protected ApplicationProperties applicationProperties;
}
