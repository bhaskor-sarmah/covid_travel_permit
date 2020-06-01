package com.bohniman.travelpermit.payload;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodePayload {

    private Long clickedDataId;
    private String tokenId;
    private String destinationDistrict;

    // member details
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Mobile number is required.")
    private String mobileNumber;
    private String address;
    @NotBlank(message = "District is required.")
    private String district;
    private String thana;
    private String pin;
    @NotBlank(message = "Screening center is required.")
    private String assignedScreeningCenter;

    @Temporal(TemporalType.TIMESTAMP)
    // @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm a")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm a")
    private Date reportingTimeAtAirport;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm a")
    private Date estimatedArivalTime;

    private String attemptNumber;

}