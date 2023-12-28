package com.example.bookexample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Stock {

    @Id
    private String tickerSymbol;

    @Column
    private String companyName;

    @Column
    private double currentPrice;

    @Column
    private String sector;

    @ManyToMany(mappedBy = "stocks", fetch = FetchType.EAGER)
    private List<Portfolio> portfolios;

}
