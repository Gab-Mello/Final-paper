package com.gabriel.pive.fiv.embryoproduction.repositories;

import com.gabriel.pive.fiv.embryoproduction.entities.EmbryoProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionRepository extends JpaRepository<EmbryoProduction, Long> {
}
