package com.bohniman.travelpermit.repository;

import java.util.List;

import com.bohniman.travelpermit.model.QrcodeScanDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QrcodeScanDetailRepo extends JpaRepository<QrcodeScanDetail, Long> {

    @Query(value = "SELECT token_id,scan_date,scan_time,location,is_screening_center FROM scan_details_view WHERE token_id = ?1 AND is_screening_center = 1", nativeQuery = true)
    List<Object[]> findAllbyToken(String tokenId);

}