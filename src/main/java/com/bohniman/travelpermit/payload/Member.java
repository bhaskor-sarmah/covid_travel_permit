package com.bohniman.travelpermit.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long id;
    private String name;
    private String age;
    private String gender;
    private String medCondition;

}