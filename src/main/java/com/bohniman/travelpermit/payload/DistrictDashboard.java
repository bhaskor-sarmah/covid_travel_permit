package com.bohniman.travelpermit.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DistrictDashboard {
    private String district;
    private Long ttl_no;
    private Long rpt_no;
    private Long not_rpt_no;
}