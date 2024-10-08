package com.gabriel.pive.animals.repositories;

import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.pregnancy.enums.PregnancyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiverCattleRepository extends JpaRepository<ReceiverCattle,Long> {
    ReceiverCattle findByRegistrationNumber(String registrationNumber);
    List<ReceiverCattle> findByRegistrationNumberStartingWith(String registrationNumber);
    List<ReceiverCattle> findByEmbryoIsNull();
    List<ReceiverCattle> findByPregnancyStatus(PregnancyStatus status);

    @Query("SELECT rc FROM ReceiverCattle rc " +
            "JOIN rc.pregnancy p " +
            "JOIN rc.embryo e " +
            "JOIN e.embryoEmbryoProduction ep " +
            "JOIN ep.oocyteCollection oc " +
            "JOIN oc.fiv f " +
            "WHERE f.id = :fivId " +
            "AND p.status = com.gabriel.pive.fiv.pregnancy.enums.PregnancyStatus.IN_PROGRESS")
    List<ReceiverCattle> findAllInProgressByFivId(@Param("fivId") Long fivId);
}
