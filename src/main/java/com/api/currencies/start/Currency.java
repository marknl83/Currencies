package com.api.currencies.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Deze klasse is een weergave voor currency
 */
@Entity
@Table(name = "currencies")
public class Currency { 
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNoCoins() {
        return noCoins;
    }

    public void setNoCoins(long noCoins) {
        this.noCoins = noCoins;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
    
    @Column(name="ticker", unique = true)
    private String ticker;
    
    @Column(name="name")
    private String name;
    
    @Column(name="nocoins")
    private long noCoins;
    
    @Column(name="marketcap")
    private long marketCap;
    
    public Currency(String ticker, String name, long noCoins, long marketCap) {
        this.ticker = ticker;
        this.name = name;
        this.noCoins = noCoins;
        this.marketCap = marketCap;
    }
    
    public Currency() {}
}
