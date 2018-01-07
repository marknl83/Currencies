package com.api.currencies.start;

import java.util.List;

import org.assertj.core.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/* De controllerklasse
 */
@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
    @Autowired
    private CurrencyService service;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /* Methode voor het ophalen van alle currency records
     * @return een List met alle currencies in JSON vorm
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Currency> findAll() {
        logger.info("Info methode get aangeroepen voor alle currencies");
        return service.getCurrency();
    }
    
    /* Methode voor het ophalen van een currency record
     * @param ticker Dit is het ticker symbool welke uniek is
     * @return een json representatie van een currency
     */ 
    @RequestMapping(value = "/{ticker}", method = RequestMethod.GET)
    public @ResponseBody Currency findOne(@PathVariable("ticker") String ticker) {
        logger.info("Info methode get aangeroepen met ticker: " + ticker);
        return service.getCurrency(ticker);
    }
    
    /* Methode voor het posten van een currency record
     * @param currency Dit is een JSON representatie van een currency
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Currency currency) {
        Preconditions.checkNotNull(currency);
        logger.info("Info methode post aangeroepen met currency: " + currency.toString());
        service.saveCurrency(currency);
    }
    
    /* Methode voor het updaten van een currency record
     * @param currency Dit is een JSON representatie van een currency
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Currency currency) {
        Preconditions.checkNotNull(currency);
        logger.info("Info methode put aangeroepen, currency wordt: " + currency.toString());
        service.saveCurrency(currency);
    }
    
    /* Methode voor het deleten van een currency record
     * * @param ticker Dit is het ticker symbool welke uniek is
     */
    @RequestMapping(value = "/{ticker}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("ticker") String ticker) {
        logger.info("Info methode delete aangeroepen met ticker: " + ticker);
        service.deleteCurrency(ticker);
    }
}
