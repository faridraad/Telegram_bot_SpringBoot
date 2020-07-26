package com.ne.notification.model.bean;

import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class RequestBean {

    public  enum MethodEnums {

        POST,
        GET,
        PUT,
        DELETE

    }

    private Long id;
    private String url;
    private MethodEnums method;
    private Object object;
    private LinkedHashMap<String, String> queryParam;
    private LinkedHashMap<String, String> headerParam;

}
