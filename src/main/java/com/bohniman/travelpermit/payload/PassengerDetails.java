package com.bohniman.travelpermit.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PassengerDetails {

    private String tokenNo;
    private String name;
    private String phone;
    private String district;
    private String thana;
    private String pincode;
    private String address;
    private String status;
    private String arrival_time;
    private String reporting_time;
    private String timeDiff;
    private String screening_center;
}