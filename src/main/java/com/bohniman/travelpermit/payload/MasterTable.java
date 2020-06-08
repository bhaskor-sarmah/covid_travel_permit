package com.bohniman.travelpermit.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasterTable {

    private String qrcodeId;
    private String reachedScreeningCenter;
    private String tokenId;
    private String destinationDistrict;
    private String name;
    private String mobileNumber;
    private String pin;
    private String thana;
    private String address;
    private String assignedScreeningCenter;
    private String entryStatus;
    private String username;
    private String entryDate;
    private String entryTime;

}