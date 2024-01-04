package com.example.portfoliomanager.repository;

import com.example.portfoliomanager.model.Portfolio;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {
    Portfolio findByPortfolioId(long id);

}
