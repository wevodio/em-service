package com.abnamro.emservice.config.feign;

import com.abnamro.emservice.exception.ErrorMessageException;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class OriginalMessageErrorDecoderTest {

    private OriginalMessageErrorDecoder originalMessageErrorDecoder;

    @BeforeEach
    void init(){
        originalMessageErrorDecoder = new OriginalMessageErrorDecoder();
    }

    @Test
    void testDecodeWhenStatusIsBadRequest(){
        Response response = Response.builder()
                .request(Request.create(Request.HttpMethod.POST,"test", new HashMap(), Request.Body.create("{test}"), new RequestTemplate()))
                .status(400)
                .body("{\"errorMessages\":[\"test\"]}", Charset.defaultCharset())
                .build();
        var exception = (ErrorMessageException)originalMessageErrorDecoder.decode("", response);
        assertThat(exception.getErrorMessages().get(0)).isEqualTo("test");
    }

    @Test
    void testDecodeWhenResponseCantBeSerialized(){
        Response response = Response.builder()
                .request(Request.create(Request.HttpMethod.POST,"test", new HashMap(), Request.Body.create("{test}"), new RequestTemplate()))
                .status(400)
                .body("{\"successMessages\":[\"test\"]}", Charset.defaultCharset())
                .build();
        var exception = (FeignException)originalMessageErrorDecoder.decode("", response);
        assertThat(exception.status()).isEqualTo(400);
    }

    @Test
    void testDecode(){
        Response response = Response.builder()
                .request(Request.create(Request.HttpMethod.POST,"test", new HashMap(), Request.Body.create("{test}"), new RequestTemplate()))
                .status(200)
                .body("{\"errorMessages\":[\"test\"]}", Charset.defaultCharset())
                .build();
        var exception = (FeignException)originalMessageErrorDecoder.decode("", response);
        assertThat(exception.status()).isEqualTo(200);

    }

}