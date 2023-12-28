package com.example.bookexample.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "StockTrade")
@Table(name = "stock_trade")
public class StockTrade {

    @Id
    @GeneratedValue
    private long transactionId;

    @Column
    private double stockPrice;

    @Column
    private int transactionAmount;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "portfolioId", nullable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "tickerSymbol", nullable = false)
    private Stock stock;

}
