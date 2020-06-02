package com.bohniman.travelpermit.repository;

import com.bohniman.travelpermit.model.ClickedData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickedDataRepository extends JpaRepository<ClickedData, Long> {

    ClickedData findByUsernameAndEntryStatus(String username, String string);

    ClickedData findTopByEntryStatusIsNullOrderByIdAsc();

    ClickedData findByTokenId(String tokenId);

}