package com.bohniman.travelpermit.repository;

import java.util.List;

import com.bohniman.travelpermit.model.MasterState;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterStateRepository extends JpaRepository<MasterState, String> {

    List<MasterState> findAllByOrderByStateNameAsc();
}