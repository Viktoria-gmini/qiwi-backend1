package com.example.qiwibackend1.cbrrate.controller;

import com.example.qiwibackend1.cbrrate.model.CurrencyRate;
import com.example.qiwibackend1.cbrrate.services.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${app.rest.api.prefix}/v1")
public class CurrencyRateController {

    public final CurrencyRateService currencyRateService;

    @GetMapping("/currencyRate/{currency}/{date}")
    public Mono<CurrencyRate> getCurrencyRate(@PathVariable("currency") String currency,
                                              @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("date") LocalDate date)  {
        var rate = currencyRateService.getCurrencyRate(currency, date);
        return Mono.just(rate);
    }
}