package com.example.portfoliomanager.viewmodel;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class StockTradeViewModel {

        private long transactionId;

        private String tickerSymbol;

        private double stockPrice;

        private int transactionAmount;

        private Date date;

        private long portfolioId;

        public StockTradeViewModel(long transactionId, String tickerSymbol, double stockPrice, int transactionAmount, Date date, long portfolioId) {
            this.transactionId = transactionId;
            this.tickerSymbol = tickerSymbol.toUpperCase();
            this.stockPrice = stockPrice;
            this.transactionAmount = transactionAmount;
            this.date = date;
            this.portfolioId = portfolioId;
        }
}
