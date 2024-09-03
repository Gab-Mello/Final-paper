package com.gabriel.pive.fiv.EmbryoProduction.repositories;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionRepository extends JpaRepository<EmbryoProduction, Long> {
}
