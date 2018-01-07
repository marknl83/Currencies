package com.api.currencies.start;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Deze interface is voor CRUD operaties op een depository met aanvullende functionaliteiten
 */
public interface CurrencyDepository extends PagingAndSortingRepository<Currency, Long>{
        List<Currency> findByTicker(String ticker);
    }
    
