package com.bohniman.travelpermit.repository;

import com.bohniman.travelpermit.model.QrcodeScanDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrcodeScanDetailRepo extends JpaRepository<QrcodeScanDetail, Long> {

}