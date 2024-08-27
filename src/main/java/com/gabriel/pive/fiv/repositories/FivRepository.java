package com.gabriel.pive.fiv.repositories;

import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FivRepository extends JpaRepository<Fiv,Long> {

    Fiv findByCultivationId(Long cultivationId);

    List<Fiv> findByStatus(FivStatusEnum status);

    List<Fiv> findByOocyteCollection_Bull_Id(Long bullId);
}
