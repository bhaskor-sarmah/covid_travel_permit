package com.bohniman.travelpermit.repository;

import java.util.List;

import com.bohniman.travelpermit.model.MasterDistrict;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MasterDistrictRepository extends JpaRepository<MasterDistrict, String> {

    List<MasterDistrict> findAllByState_stateCodeOrderByDistrictNameAsc(String stateCode);

    List<MasterDistrict> findByDistrictCodeNotInAndState_StateCode(List<String> fromDistrictCodes, String stateCode);

    MasterDistrict findByDistrictCode(String districtCode);

    @Query(value = "select a from MasterDistrict a where a.state.stateCode=:stateCode order by a.districtName")
    List<MasterDistrict> findByState_stateCodeOrderByDistrictName(String stateCode);
}