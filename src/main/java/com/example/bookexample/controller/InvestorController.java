package com.example.bookexample.controller;

import com.example.bookexample.model.Investor;
import com.example.bookexample.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investors")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @GetMapping(value = "/findall", produces = "application/json")
    public ResponseEntity<List<Investor>> getAllInvestors() {
        return new ResponseEntity<>(investorService.findAllInvestors(), HttpStatus.OK);
    }

    @GetMapping(value = "/findbyid/{id}", produces = "application/json")
    public ResponseEntity<Investor> getInvestorById(@PathVariable("id") long id) {
        Investor investor = investorService.findInvestorById(id);
        if (investor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(investor, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Investor> addInvestor(@RequestBody Investor investor) {
        investorService.addInvestor(investor);
        return new ResponseEntity<>(investor, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes = "application/json")
    public ResponseEntity<Investor> updateInvestor(@PathVariable("id") long id, @RequestBody Investor investorDetails) {
        Investor investor = investorService.findInvestorById(id);
        if (investor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        investor.setFirstName(investorDetails.getFirstName());
        investor.setLastName(investorDetails.getLastName());
        investor.setEmail(investorDetails.getEmail());
        investorService.updateInvestor(investor);
        return new ResponseEntity<>(investor, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteInvestor(@PathVariable("id") long id) {
        Investor investor = investorService.findInvestorById(id);
        if (investor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        investorService.deleteInvestorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/deleteall")
    public ResponseEntity<Void> deleteAllInvestors() {
        investorService.deleteAllInvestors();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

