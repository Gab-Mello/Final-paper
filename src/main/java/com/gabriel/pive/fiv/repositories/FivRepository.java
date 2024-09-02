package com.gabriel.pive.fiv.repositories;

import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FivRepository extends JpaRepository<Fiv,Long> {

    Fiv findByCultivationId(Long cultivationId);
    List<Fiv> findByStatusOrderByIdDesc(FivStatusEnum status);
    List<Fiv> findByOocyteCollections_Bull_IdOrderByIdDesc(Long bullId);
    List<Fiv> findByOocyteCollections_DonorCattle_IdOrderByIdDesc(Long donorId);
    List<Fiv> findAllByOrderByIdDesc();
}
