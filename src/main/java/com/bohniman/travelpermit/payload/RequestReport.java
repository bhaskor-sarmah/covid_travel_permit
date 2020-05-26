package com.bohniman.travelpermit.payload;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestReport {

    List<JourneyRequestReport> requestReports = new ArrayList<>();
    String districtName;
    PageDetails pageDetails;

}