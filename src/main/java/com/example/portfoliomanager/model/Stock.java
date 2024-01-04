package com.example.portfoliomanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Stock")
@Table(name = "stock")
public class Stock {

    @Id
    private String tickerSymbol;

    @Column
    private String companyName;

    @Column
    private double currentPrice;

    @Column
    private String sector;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private Set<StockTrade> stockTransactions;

    public Stock(String tickerSymbol, String companyName, double currentPrice, String sector) {
        this.tickerSymbol = tickerSymbol.toUpperCase();
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.sector = sector;
        this.stockTransactions = new HashSet<>();
    }

}
