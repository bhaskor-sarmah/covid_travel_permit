package com.bohniman.travelpermit.services;

import java.util.List;
import java.util.stream.Collectors;

import com.bohniman.travelpermit.model.MasterDistrict;
import com.bohniman.travelpermit.model.MasterState;
import com.bohniman.travelpermit.model.MasterSubDistrict;
import com.bohniman.travelpermit.model.MasterThana;
import com.bohniman.travelpermit.repository.MasterDistrictRepository;
import com.bohniman.travelpermit.repository.MasterStateRepository;
import com.bohniman.travelpermit.repository.MasterSubDistrictRepository;
import com.bohniman.travelpermit.repository.MasterThanaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterService {

    @Autowired
    private MasterStateRepository masterStateRepository;

    @Autowired
    MasterDistrictRepository masterDistrictRepository;

    @Autowired
    MasterSubDistrictRepository masterSubDistrictRepository;

    @Autowired
    MasterThanaRepository masterThanaRepository;

    public List<MasterState> getAllState() {
        return masterStateRepository.findAllByOrderByStateNameAsc();
    }

    public List<MasterDistrict> getDistrictByState(String stateCode) {
        return masterDistrictRepository.findAllByState_stateCodeOrderByDistrictNameAsc(stateCode);
    }

    public List<MasterSubDistrict> getSubDistrictByDistrict(String districtCode) {
        return masterSubDistrictRepository.findAllByDistrict_districtCodeOrderBySubDistrictNameAsc(districtCode);
    }

    public MasterState getStatebyCode(String string) {
        return masterStateRepository.findById(string).get();
    }

    public List<MasterThana> getThanaByDistrict(String districtCode) {
        return masterThanaRepository.findAllByDistrictCodeOrderByThanaNameAsc(districtCode);
    }

    public String getPoliceStationById(String policeStation) {
        String ps = "";
        if (masterThanaRepository.findById(policeStation).isPresent()) {
            ps = masterThanaRepository.findById(policeStation).get().getThanaName();
        }
        if (ps.equals("")) {
            ps = policeStation;
        }
        return ps;
    }

    public List<MasterState> getAllStateExcludingAssam() {
        return getAllState().stream().filter(state -> !state.getStateCode().equals("18")).collect(Collectors.toList());
    }

}