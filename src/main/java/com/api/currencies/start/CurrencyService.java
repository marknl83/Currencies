/**
 * 
 */
package com.api.currencies.start;

import java.util.List;

/**
 * Deze interface is voor alle service operaties op currency
 *
 */
public interface CurrencyService {
    
    public List<Currency> getCurrency();

    public void saveCurrency(Currency theCurrency);

    public Currency getCurrency(String ticker);
    
    public Currency getCurrency(Long id);

    public void deleteCurrency(String ticker);       
}
