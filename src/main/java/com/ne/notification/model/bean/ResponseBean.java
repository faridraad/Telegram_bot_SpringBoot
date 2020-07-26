package com.ne.notification.model.bean;

import lombok.Data;

@Data
public class ResponseBean {

    private Integer status = 0;
    private String baseUrl;
    private String message;
    private Object result;

}
