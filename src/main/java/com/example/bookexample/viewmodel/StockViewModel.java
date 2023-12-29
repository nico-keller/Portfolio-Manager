package com.example.bookexample.viewmodel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class StockViewModel {

        private String tickerSymbol;

        private String companyName;

        private double currentPrice;

        private String sector;

        public StockViewModel(String tickerSymbol, String companyName, double currentPrice, String sector) {
            this.tickerSymbol = tickerSymbol.toUpperCase();
            this.companyName = companyName;
            this.currentPrice = currentPrice;
            this.sector = sector;
        }
}
