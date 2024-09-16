package com.gabriel.pive.fiv.EmbryoProduction.repositories;

import com.gabriel.pive.fiv.EmbryoProduction.dtos.TransferInitialDto;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoTransfer;
import com.gabriel.pive.fiv.entities.Fiv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<EmbryoTransfer, Long> {

    List<EmbryoTransfer> findByFivTransferId(Long fivId);
}
