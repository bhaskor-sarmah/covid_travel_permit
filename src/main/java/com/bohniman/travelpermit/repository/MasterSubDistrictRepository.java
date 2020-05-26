package com.bohniman.travelpermit.repository;

import java.util.List;

import com.bohniman.travelpermit.model.MasterSubDistrict;
import com.bohniman.travelpermit.payload.SubDistrictCount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterSubDistrictRepository extends JpaRepository<MasterSubDistrict, String> {

    List<MasterSubDistrict> findAllByDistrict_districtCodeOrderBySubDistrictNameAsc(String districtCode);

    List<MasterSubDistrict> findBySubDistrictCodeNotIn(List<String> subDistrictCodes);

    List<MasterSubDistrict> findBySubDistrictCodeNotInAndDistrict_districtCode(List<String> toSubDistrictCodes,
            String userScope);

}