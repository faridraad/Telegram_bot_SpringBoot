package com.ne.notification.model.dto.base;

import com.ne.notification.configuration.resources.ApplicationProperties;
import lombok.Data;

@Data
public class MetaDTO {
    Integer code;
    String message;

    public static MetaDTO getInstance(ApplicationProperties applicationProperties) {
        return new MetaDTO(
                applicationProperties.getCode("success-code"),
                applicationProperties.getProperty("success-text")
        );
    }

    public static MetaDTO getInstance(Integer code, String message) {
        return new MetaDTO(code, message);
    }

    public MetaDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
