package com.ne.notification.model.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ne.notification.model.bean.RequestBean;
import com.ne.notification.model.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.Date;

@Produces({"application/xml", "application/json"})
@Repository
public class ServiceConnector {

    @Produces({"application/xml", "application/json"})
    public ResponseBean callService() throws IOException {
        return callService();
    }

    @Produces({"application/xml", "application/json"})
    public ResponseBean callService(RequestBean requestBean) throws IOException {
        ResponseBean responseBean = new ResponseBean();
        System.out.println("Start request: " + new Date());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        try {
            switch (RequestBean.MethodEnums.valueOf(requestBean.getMethod().name())) {
                case POST:
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json = ow.writeValueAsString(requestBean.getObject());
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    if (requestBean.getHeaderParam() != null) {
                        for (String key : requestBean.getHeaderParam().keySet()) {
                            headers.set(key, requestBean.getHeaderParam().get(key));
                        }
                    }
                    HttpEntity<String> request = new HttpEntity<String>(json, headers);
                    String personResultAsJsonStr = restTemplate.postForObject(requestBean.getUrl(), request, String.class);
                    responseBean.setResult(personResultAsJsonStr);
                    System.out.println("result : " + personResultAsJsonStr);
                    responseBean.setMessage("OK");

                    break;
                case GET:
                    if (requestBean.getHeaderParam() != null) {
                        for (String key : requestBean.getHeaderParam().keySet()) {
                            headers.set(key, requestBean.getHeaderParam().get(key));
                        }
                    }
                    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestBean.getUrl());
                    if (requestBean.getQueryParam() != null) {
                        for (String key : requestBean.getQueryParam().keySet()) {
                            builder.queryParam(key, requestBean.getQueryParam().get(key));
                        }
                    }
                    System.out.println(builder.toUriString());
                    HttpEntity<?> entity = new HttpEntity<>(headers);
                    HttpEntity<String> response = restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            entity,
                            String.class);
                    System.out.println("RESULT :" + response.getBody());
                    responseBean.setResult(response.getBody());
                    System.out.println("result : " + responseBean.getResult());
                    responseBean.setMessage("OK");
                    break;
                case PUT:

                case DELETE:

                    break;
                default:
                    break;

            }
        } catch (HttpClientErrorException httpClientErrorException) {
            System.out.println("EXCEPTION :");
            System.out.println("" + httpClientErrorException.getMessage());
            System.out.println(httpClientErrorException.getCause());
            responseBean.setMessage(httpClientErrorException.getMessage());
        } catch (HttpServerErrorException httpServerErrorException) {
            System.out.println(httpServerErrorException.getCause());
        } catch (Exception exception) {

            System.out.println("EXCEPTION :");
            System.out.println(exception.getCause());
        }
        System.out.println("End request: " + new

                Date());
        return responseBean;

    }
}

