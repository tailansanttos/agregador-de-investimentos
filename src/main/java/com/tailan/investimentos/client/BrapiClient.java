package com.tailan.investimentos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "Brapiclient",
        url = "https://brapi.dev"
)
public interface BrapiClient {
    @GetMapping(value = "/api/quote/{stockId}")
    BrapiResponse getQuote(@RequestParam("token") String token, @PathVariable("stockId") String stockId);


}
