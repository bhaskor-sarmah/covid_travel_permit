package com.bohniman.travelpermit.repository;

import java.util.List;

import com.bohniman.travelpermit.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByRole(String string);

}