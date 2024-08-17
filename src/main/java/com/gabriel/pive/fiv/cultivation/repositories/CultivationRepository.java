package com.gabriel.pive.fiv.cultivation.repositories;

import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultivationRepository extends JpaRepository<Cultivation, Long> {
}
