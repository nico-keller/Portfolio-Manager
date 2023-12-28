package com.example.bookexample.repository;

import com.example.bookexample.model.Investor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvestorRepository extends CrudRepository<Investor, Long> {
    List<Investor> findByFirstName(String firstName);
    List<Investor> findByLastName(String lastName);

    Investor findByInvestorId(long id);
    Investor findByEmail(String email);

}
