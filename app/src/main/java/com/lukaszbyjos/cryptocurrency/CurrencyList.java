package com.lukaszbyjos.cryptocurrency;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Lukasz on 09.12.2015:20:35.
 */
public class CurrencyList {

    @JsonProperty("value")
    private List<Currency> currencyList;

    public CurrencyList() {
    }

    public CurrencyList(List<Currency> currencyList) {

        this.currencyList = currencyList;
    }

    /**
     * Gets currency list.
     *
     * @return the currency list
     */
    @JsonProperty("value")
    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    @JsonProperty("value")
    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public static class Currency {

        /**
         * The Currency name.
         */
        private String currencyName;
        /**
         * The Currency value.
         */
        @JsonProperty("last")
        private float currencyValue;
        /**
         * The Currency bid.
         */
        @JsonProperty("bid")
        private float currencyBid;
        /**
         * The Currency aid.
         */
        @JsonProperty("ask")
        private float currencyAsk;


        public Currency() {

        }

        public Currency(String currencyName, float currencyValue, float currencyBid, float currencyAsk) {

            this.currencyName = currencyName;
            this.currencyValue = currencyValue;
            this.currencyBid = currencyBid;
            this.currencyAsk = currencyAsk;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public float getCurrencyValue() {
            return currencyValue;
        }

        public void setCurrencyValue(float currencyValue) {
            this.currencyValue = currencyValue;
        }

        public float getCurrencyBid() {
            return currencyBid;
        }

        public void setCurrencyBid(float currencyBid) {
            this.currencyBid = currencyBid;
        }

        public float getCurrencyAsk() {
            return currencyAsk;
        }

        public void setCurrencyAsk(float currencyAsk) {
            this.currencyAsk = currencyAsk;
        }


    }
}
