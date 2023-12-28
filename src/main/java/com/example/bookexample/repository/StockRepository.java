package com.example.bookexample.repository;

import com.example.bookexample.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findByTickerSymbol(String tickerSymbol);
    boolean existsByTickerSymbol(String tickerSymbol);

}
