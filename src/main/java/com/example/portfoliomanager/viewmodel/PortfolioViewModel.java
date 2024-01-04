package com.example.portfoliomanager.viewmodel;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioViewModel {

    private long portfolioId;

    private Date openingDate;

    private String portfolioName;

    private long investorId;
}
