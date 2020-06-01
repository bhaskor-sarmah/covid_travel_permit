package com.bohniman.travelpermit.repository;

import java.util.List;
import java.util.Optional;

import com.bohniman.travelpermit.model.QrCodeData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeDataRepo extends JpaRepository<QrCodeData, Long> {

    List<QrCodeData> findAllByReachedScreeningCenter(boolean b);

    List<QrCodeData> findAllByReachedScreeningCenterAndDestinationDistrict(boolean b, String string);

    List<QrCodeData> findAllByReachedScreeningCenterAndDestinationDistrictAndStatus(boolean reportedAtScreening,
            String district, String status);

    Optional<QrCodeData> findByTokenId(String token);

    List<QrCodeData> findAllByReachedScreeningCenterAndStatus(boolean b, String string);

    QrCodeData findByStatusAndUsernameAndEntryStatus(String string, String username, String string2);

    QrCodeData findTopByOrderByTokenIdAsc();

    QrCodeData findTopByStatusOrderByTokenIdAsc(String string);

    QrCodeData findTopByStatusAndEntryStatusNotOrderByTokenIdAsc(String string, String string2);

    QrCodeData findTopByStatusAndEntryStatusIsNullOrderByTokenIdAsc(String string);

}