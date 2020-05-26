package com.bohniman.travelpermit.repository;

import java.util.List;

import com.bohniman.travelpermit.model.MasterThana;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterThanaRepository extends JpaRepository<MasterThana, String> {

    List<MasterThana> findAllByDistrictCodeOrderByThanaNameAsc(String districtCode);

}