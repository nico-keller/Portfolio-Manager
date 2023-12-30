package com.example.bookexample.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Investor")
@Table(name = "investor")
public class Investor {

        @Id
        private long investorId;

        @Column
        private String firstName;

        @Column
        private String lastName;

        @Column
        private String email;

        @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
        private Set<Portfolio> portfolios;


        public Investor(long investorId, String firstName, String lastName, String email) {
            this.investorId = investorId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.portfolios = new HashSet<>();
        }
}
