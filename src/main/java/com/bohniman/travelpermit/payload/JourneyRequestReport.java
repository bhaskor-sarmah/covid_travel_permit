package com.bohniman.travelpermit.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JourneyRequestReport {

    private Long id;
    private Date journeyDate;

    private String fromStateCode;
    private String fromStateName;
    private String fromDistrictCode;
    private String fromDistrictName;
    private String fromSubDistrictCode;
    private String fromSubDistrictName;
    private String fromPincode;
    private String fromPoliceStation;

    private String toStateCode;
    private String toStateName;
    private String toDistrictCode;
    private String toDistrictName;
    private String toSubDistrictCode;
    private String toSubDistrictName;
    private String toPincode;
    private String toPoliceStation;

    private String purposeCode;
    private String purposeName;
    private String purposeDetails;

    private Long citizenId;
    private String citizenName;
    private String citizenEmail;
    private String citizenContact;

    // private String professionCode;
    // private String professionName;

    private String arrangementTypeCode;
    private String arrangementTypeName;

    private String journeyTypeCode;
    private String journeyTypeName;

    // private Long epassId;
    // private Date epassDate;
    // private String epassVehicleNo;

    private String status;

    private String modeOfTransport;
    private String entryDetails;
    private String transportDetails;

    private List<Member> members = new ArrayList<>();

    private Date createdAt;

}