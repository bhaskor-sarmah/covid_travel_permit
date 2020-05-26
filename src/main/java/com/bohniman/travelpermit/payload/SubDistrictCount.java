package com.bohniman.travelpermit.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubDistrictCount {

    private String code;
    private String name;
    private Long pendingCount;
    private Long approvedCount;
    private Long rejectedCount;

}