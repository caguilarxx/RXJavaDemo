package com.awi.rxjavademo.controller;

import com.awi.rxjavademo.model.AccountRequest;
import com.awi.rxjavademo.model.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@AllArgsConstructor
public class WithdrawalController {

    @GetMapping(
            value = "/withdrawal",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void getCards() {

        Map<String, String> accountHeaders = buildAccountHeaders();
        AccountRequest accountBody = buildAccountBody();

        AccountResponse accountResponse = executeAccountCall(accountHeaders, accountBody);

        if (!accountResponse.isSuccess()) {
            throw new RuntimeException("Error");
        }
    }

    @SneakyThrows
    private Map<String, String> buildAccountHeaders() {

        Map<String, String> headers = new HashMap<>();
        headers.put("key1", "value1");
        headers.put("key2", "value2");
        headers.put("key3", "value3");
        headers.put("key4", "value4");
        headers.put("key5", "value5");

        Thread.sleep(1000);

        return headers;
    }

    @SneakyThrows
    private AccountRequest buildAccountBody() {
        AccountRequest request = new AccountRequest();
        request.setCardNumber("400412345423145");

        Thread.sleep(1000);

        return request;
    }

    @SneakyThrows
    private AccountResponse executeAccountCall(Map<String, String> headers, AccountRequest request) {

        Thread.sleep(1000);
        return new AccountResponse(new Random().nextBoolean());
    }
}
