package com.bohniman.travelpermit.repository;

import com.bohniman.travelpermit.model.DistrictPass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictPassRepository extends JpaRepository<DistrictPass, Long> {

}