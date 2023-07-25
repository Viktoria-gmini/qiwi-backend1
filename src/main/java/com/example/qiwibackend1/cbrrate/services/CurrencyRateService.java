package com.example.qiwibackend1.cbrrate.services;
import com.example.qiwibackend1.cbrrate.config.CbrConfig;
import com.example.qiwibackend1.cbrrate.model.CurrencyRate;
import com.example.qiwibackend1.cbrrate.parser.CurrencyRateParser;
import com.example.qiwibackend1.cbrrate.requester.CbrRequester;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private final CbrRequester cbrRequester;
    private final CurrencyRateParser currencyRateParser;
    private final CbrConfig cbrConfig;


    public CurrencyRate getCurrencyRate(String currency, LocalDate date) {


        var urlWithParams = String.format("%s?date_req=%s", cbrConfig.getUrl(), DATE_FORMATTER.format(date));
        var ratesAsXml = cbrRequester.getRatesAsXml(urlWithParams);
        List<CurrencyRate> rates = currencyRateParser.parse(ratesAsXml);

        return rates.stream().filter(rate -> currency.equals(rate.getCharCode()))
                .findFirst()
                .orElseThrow(() -> new CurrencyRateNotFoundException("Currency Rate not found. Currency:" + currency + ", date:" + date));
    }
}
