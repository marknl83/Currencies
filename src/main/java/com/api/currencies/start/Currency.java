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
    
    public static class Builder {
        private long id;
        private String ticker;
        private String name;
        private long noCoins;
        private long marketCap;
        
       
        public Builder() {}
        public Builder id(Long id){
            this.id = id;
            return this; 
        }
        public Builder ticker(String ticker){
            this.ticker = ticker;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder noCoins(Long noCoins){
            this.noCoins = noCoins;
            return this;
        }
        public Builder marketCap(Long marketCap){
            this.marketCap = marketCap;
            return this;
        }
        public Currency build(){
            Currency currency = new Currency();
            currency.id = id;
            currency.ticker = ticker;
            currency.name = name;
            currency.noCoins = noCoins;
            currency.marketCap = marketCap;
            return currency;
        }
    }    
    
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
    
    private Currency(String ticker, String name, long noCoins, long marketCap) {
        this.ticker = ticker;
        this.name = name;
        this.noCoins = noCoins;
        this.marketCap = marketCap;
    }
    
    private Currency() {}
}
