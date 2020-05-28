package com.bohniman.travelpermit.repository;

import java.util.List;

import com.bohniman.travelpermit.model.ScanLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanLocationRepo extends JpaRepository<ScanLocation, Long> {

    List<ScanLocation> findAllByScreeningCenterOrderByLocation(boolean b);

}