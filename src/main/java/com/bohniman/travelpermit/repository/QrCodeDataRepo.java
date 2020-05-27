package com.bohniman.travelpermit.repository;

import java.util.List;

import com.bohniman.travelpermit.model.QrCodeData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeDataRepo extends JpaRepository<QrCodeData, Long> {

    List<QrCodeData> findAllByReachedScreeningCenter(boolean b);

    List<QrCodeData> findAllByReachedScreeningCenterAndDestinationDistrict(boolean b, String string);

    List<QrCodeData> findAllByReachedScreeningCenterAndDestinationDistrictAndStatus(boolean reportedAtScreening,
            String district, String status);

}