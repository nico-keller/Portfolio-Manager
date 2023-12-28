package com.example.bookexample.repository;

import com.example.bookexample.model.Investor;
import com.example.bookexample.model.Portfolio;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
    Portfolio findByPortfolioId(long id);

}
