package com.abnamro.emservice.config.feign;

import com.abnamro.empersistenceservice.generated.model.ErrorResponse;
import com.abnamro.emservice.exception.ErrorMessageException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
public class OriginalMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.info("OriginalMessageErrorDecoder, status:" + response.status() + ", methodKey: " + methodKey);
        if(response.status() == 400 && response.body() != null)  {
            log.info("400 return ErrorMessageException");
            try {
                var errorResponse  = objectMapper.readValue(parseResponseBody(response), ErrorResponse.class);
                return new ErrorMessageException(errorResponse.getErrorMessages());
            } catch (JsonProcessingException e) {
                defaultErrorDecoder.decode(methodKey, response);
            }
        }
        if (response.status() == 503) {
            return new RetryableException(
                    response.status(),
                    "Service Unavailable",
                    response.request().httpMethod(),
                    5L, // Cause
                    response.request());
        }
        log.info("Other status return defaultErrorDecoder");
        return defaultErrorDecoder.decode(methodKey, response);
    }


    private String parseResponseBody(Response response) {
        return Optional.ofNullable(response.body()).map(body -> {
            try {
                return new String(body.asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                return null;
            }
        }).orElse(null);
    }

}
