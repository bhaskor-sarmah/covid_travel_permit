package com.bohniman.travelpermit.repository;

import com.bohniman.travelpermit.model.QrCodeMemberDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeMemberDetailRepo extends JpaRepository<QrCodeMemberDetail, Long> {

}