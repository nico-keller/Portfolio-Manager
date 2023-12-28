package com.example.bookexample.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "Investor")
@Table(name = "investor")
public class Investor {

        @Id
        @GeneratedValue
        private long investorId;

        @Column
        private String firstName;

        @Column
        private String lastName;

        @Column
        private String email;

        @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Set<Portfolio> portfolios;
}
