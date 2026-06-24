package com.gabriel.pive.fiv.embryoproduction.repositories;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.embryoproduction.entities.Embryo;
import com.gabriel.pive.fiv.embryoproduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.embryoproduction.enums.EmbryoDestiny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmbryoRepository extends JpaRepository<Embryo,Long> {

    List<Embryo> findAllByEmbryoBull(Bull bull);

    List<Embryo> findAllByEmbryoDonorCattle(DonorCattle donorCattle);

    Embryo findByEmbryoReceiverCattle(ReceiverCattle receiverCattle);

    List<Embryo> findByEmbryoEmbryoProductionAndDestiny(EmbryoProduction production, EmbryoDestiny destiny);
}
