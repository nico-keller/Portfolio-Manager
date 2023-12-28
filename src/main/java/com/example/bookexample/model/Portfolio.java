package com.example.bookexample.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "Portfolio")
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue
    private long portfolioId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingDate;

    @Column
    private String portfolioName;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "portfolio_stock",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_ticker")
    )
    private List<Stock> stocks;

    @ManyToOne
    @JoinColumn(name = "investor_id", referencedColumnName = "investorId")
    private Investor investor;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private Set<StockTrade> stockTransactions;

}
