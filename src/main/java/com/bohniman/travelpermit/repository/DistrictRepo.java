package com.bohniman.travelpermit.repository;

import java.util.List;

import com.bohniman.travelpermit.model.District;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepo extends JpaRepository<District, Long> {

    List<District> findAllByOrderByDistrict();

}