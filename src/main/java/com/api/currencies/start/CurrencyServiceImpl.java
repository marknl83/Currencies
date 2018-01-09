/**
 * 
 */
package com.api.currencies.start;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * In deze klasse is de service functionaliteit vormgegeven
 */
//@Service
public class CurrencyServiceImpl implements CurrencyService {
    
    @Autowired 
    CurrencyDepository currencyDepository;    
    
    /* Methode voor het ophalen van alle currency records
     * @return een List met alle currencies
     */
    @Override
    public List<Currency> getCurrency() {        
        return (List<Currency>) currencyDepository.findAll();
    }
    
    /* Methode voor het updaten van een currency record
     * @param currency Dit is een currency object
     */
    public void saveCurrency(Currency theCurrency) {
        currencyDepository.save(theCurrency);        
    }
    
    /* Methode voor het ophalen van een currency record
     * @param ticker Dit is het ticker symbool welke uniek is
     * @return Dit is een currency object
     */ 
    @Override
    public Currency getCurrency(String ticker) {
        // Op basis van ticker symbool (uniek) de currency ophalen
        List <Currency> currencies = currencyDepository.findByTicker(ticker);
        if (currencies.isEmpty()) return null; else return currencies.get(0);
    }
    
    /* Methode voor het deleten van een currency record
     * * @param ticker Dit is het ticker symbool welke uniek is
     */
    @Override
    public void deleteCurrency(String ticker) {
        // Op basis van het tickersymbool de currency en de id ophalen en deze meegeven voor de delete
        Long id = currencyDepository.findByTicker(ticker).get(0).getId();
        currencyDepository.delete(id);        
    }
    
    /* Methode voor het getten van een currency record
     * * @param id Dit is de id van het record
     */
    @Override
    public Currency getCurrency(Long id) {
        return currencyDepository.findOne(id);
    }    
}
