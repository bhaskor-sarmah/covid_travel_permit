package com.bohniman.travelpermit.repository;

import com.bohniman.travelpermit.model.ClickedData;
import com.bohniman.travelpermit.model.Document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}