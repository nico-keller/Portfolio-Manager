package com.example.portfoliomanager.repository;

import com.example.portfoliomanager.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findByTickerSymbol(String tickerSymbol);
    boolean existsByTickerSymbol(String tickerSymbol);

}
