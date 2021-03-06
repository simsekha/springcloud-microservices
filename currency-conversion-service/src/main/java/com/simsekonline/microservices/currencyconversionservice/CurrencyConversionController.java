package com.simsekonline.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    Logger logger=LoggerFactory.getLogger(this.getClass());
    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean cınvertCurrency(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {
        CurrencyConversionBean response = currencyExchangeServiceProxy.retreiveExchangeValue(from, to);
        logger.info("{}",response);
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
                response.getConversionMultiple().multiply(quantity), quantity, response.getPort());
    }
}