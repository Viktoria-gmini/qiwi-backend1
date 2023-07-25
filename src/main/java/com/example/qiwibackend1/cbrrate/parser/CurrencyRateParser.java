package com.example.qiwibackend1.cbrrate.parser;

import com.example.qiwibackend1.cbrrate.model.CurrencyRate;

import java.util.List;

public interface CurrencyRateParser {

    List<CurrencyRate> parse(String ratesAsString);
}
