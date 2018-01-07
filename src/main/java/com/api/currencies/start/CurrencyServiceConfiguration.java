/**
 * 
 */
package com.api.currencies.start;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Deze klasse koppelt de currency service interface aan de implementatie
 */
@Configuration
public class CurrencyServiceConfiguration {
    
    @Bean
    @Primary
    public CurrencyService CurrencyServiceImpl() {return new CurrencyServiceImpl();}    
}
