package com.example.portfoliomanager.repository;

import com.example.portfoliomanager.model.Investor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvestorRepository extends CrudRepository<Investor, Long> {
    List<Investor> findByFirstName(String firstName);
    List<Investor> findByLastName(String lastName);

    Investor findByInvestorId(long id);
    Investor findByEmail(String email);

}
