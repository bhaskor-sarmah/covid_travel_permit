package com.bohniman.travelpermit.services;

import java.util.ArrayList;
import java.util.List;

import com.bohniman.travelpermit.model.DistrictPass;
import com.bohniman.travelpermit.model.MasterDistrict;
import com.bohniman.travelpermit.repository.DistrictPassRepository;
import com.bohniman.travelpermit.repository.MasterDistrictRepository;
import com.bohniman.travelpermit.utils.AppStaticData;
import com.bohniman.travelpermit.utils.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {

    // @Autowired
    // BCryptPasswordEncoder encoder;

    // @Autowired
    // DistrictPassRepository passRepository;

    // @Autowired
    // MasterDistrictRepository districtRepository;

    // public void generatePassword() {

    // List<MasterDistrict> districts = districtRepository
    // .findAllByState_stateCodeOrderByDistrictNameAsc(AppStaticData.STATE_CODE);

    // List<DistrictPass> districtPasses = new ArrayList<>();
    // DistrictPass districtPass;

    // for (MasterDistrict district : districts) {
    // districtPass = new DistrictPass();
    // String distName = district.getDistrictName();
    // String passString = RandomString.randomAlphaNumeric(5);
    // String passEncode = encoder.encode(passString);
    // districtPass.setDistName(distName);
    // districtPass.setPassString(passString);
    // districtPass.setPassEncode(passEncode);
    // districtPasses.add(districtPass);

    // }
    // passRepository.saveAll(districtPasses);

    // }

}