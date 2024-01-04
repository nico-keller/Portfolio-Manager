package com.example.portfoliomanager.viewmodel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestorViewModel {

    private long investorId;

    private String firstName;

    private String lastName;

    private String email;
}
