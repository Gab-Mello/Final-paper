package com.gabriel.pive.fiv.EmbryoProduction.repositories;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<EmbryoTransfer, Long> {
}
