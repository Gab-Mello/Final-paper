package com.gabriel.pive.fiv.cultivation.repositories;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmbryoRepository extends JpaRepository<Embryo,Long> {

    List<Embryo> findAllByEmbryoBull(Bull bull);

    Embryo findByEmbryoReceiverCattle(ReceiverCattle receiverCattle);
}
